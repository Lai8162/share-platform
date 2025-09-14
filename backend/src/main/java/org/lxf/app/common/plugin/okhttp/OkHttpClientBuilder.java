package org.lxf.app.common.plugin.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * OkHttp单例对象构建
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/11 22:56
 */
@Slf4j
public class OkHttpClientBuilder {
    /**
     * 同步阻塞等待执行结果的时间，单位毫秒
     */
    private final static Integer DEFAULT_WAIT_TIMEOUT = 3000;

    /**
     * 服务启动时初始化OkHttpClient对象，确保客户端对象单例
     */
    private final static OkHttpClient OK_HTTP_CLIENT = OkHttpClientFactory.newInstance().build();

    private OkHttpClientBuilder() {
    }


    static {
        // 向JVM注册一个关闭钩子，当服务准备停止时，等待 OkHttpClient 中任务执行完毕再停止，防止线程池中正在执行的任务突然中断
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            int count = 100;
            Dispatcher dispatcher = OK_HTTP_CLIENT.dispatcher();
            log.info("ShutdownHook start：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
            while (dispatcher.queuedCallsCount() > 0 || dispatcher.runningCallsCount() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error("ShutdownHook interrupted：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
                    break;
                }
                // 防止无限循环
                if (--count == 0) {
                    log.error("ShutdownHook timeout：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
                    break;
                }
            }
            log.info("ShutdownHook end：queuedCallsCount {} , runningCallsCount {}", dispatcher.queuedCallsCount(), dispatcher.runningCallsCount());
        }));
    }


    /**
     * 同步执行请求，公共方法
     *
     * @param request okHttp请求对象
     * @return 请求结果返回
     */
    public static OkHttpClientResult syncRequest(Request request) {
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            return buildResponseResult(request, response);
        } catch (Exception e) {
            log.error("request error，request: {}", request, e);
            return new OkHttpClientResult(false, 500, "request error");
        }
    }


    /**
     * 异步执行请求，同步阻塞编程等待返回结果
     * 此方式在多线程环境下请求处理依然能保持高性能，根据不同的场景显式对ConnectionPool进行调优处理
     *
     * @param request okHttp请求对象
     * @return 请求结果返回
     */
    public static OkHttpClientResult syncResponse(Request request) {
        if (log.isDebugEnabled()) {
            log.debug("request start，request:{}", request);
        }
        // 使用异步编程，在指定时间内阻塞获取 OkHttp 异步执行结果
        CompletableFuture<Response> completableFuture = new CompletableFuture<>();
        // 发起异步请求调用
        Call call = OK_HTTP_CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                completableFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                completableFuture.complete(response);
            }
        });
        // 这里的异步执行结果等待时间，取决于get同步获取时间的设定，而不是全局请求超时配置
        try (Response response = completableFuture.get(DEFAULT_WAIT_TIMEOUT, TimeUnit.MILLISECONDS)) {
            return buildResponseResult(request, response);
        } catch (TimeoutException e) {
            call.cancel();
            log.error("request timeout，request: {}", request, e);
            return new OkHttpClientResult(false, 500, "request timeout");
        } catch (Exception e) {
            log.error("request error，request: {}", request, e);
            return new OkHttpClientResult(false, 500, "request error");
        }
    }

    /**
     * 封装返回值
     *
     * @param request  okHttp请求对象
     * @param response okHttp返回对象
     * @return 请求结果返回
     * @throws IOException IO异常
     */
    private static OkHttpClientResult buildResponseResult(Request request, Response response) throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("request end，request:{}, response:{}", request, response);
        }
        ResponseBody responseBody = response.body();
        OkHttpClientResult result = new OkHttpClientResult();
        result.setSuccess(response.isSuccessful());
        result.setCode(response.code());
        result.setMessage(response.message());
        result.setHeaders(response.headers().toMultimap());
        if (Objects.nonNull(responseBody)) {
            result.setBody(responseBody.bytes());
        }
        if (!result.isSuccess()) {
            log.warn("request fail, request:{}, response:{}", request, response);
        }
        return result;
    }
}
