package org.lxf.app.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.lxf.app.common.plugin.okhttp.OkHttpClientBuilder;
import org.lxf.app.common.plugin.okhttp.OkHttpClientResult;

import java.util.Map;

/**
 * OkHttp请求工具类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/11 23:12
 */
public class OkHttpClientUtil {
    /**
     * get请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return String字符串
     */
    public static String get(String url, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .get()
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result);
    }

    /**
     * get请求（支持范型对象返回参数）
     *
     * @param url          请求地址
     * @param headers      请求头
     * @param responseType 返回类型
     * @param <T>          返回类型：泛型
     * @return 返回类型：泛型实体
     */
    public static <T> T get(String url, Map<String, String> headers, TypeReference<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .get()
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result, responseType);
    }

    /**
     * post表单请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param headers  请求头
     * @return 返回结果：字符串
     */
    public static String postByForm(String url, Map<String, String> paramMap, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildFormBody(paramMap))
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result);
    }

    /**
     * post表单请求（支持范型对象返回参数）
     *
     * @param url          请求体
     * @param paramMap     请求参数
     * @param headers      请求头部
     * @param responseType 返回类型
     * @param <T>          返回类型泛型
     * @return 返回类型：泛型实体
     */
    public static <T> T postByForm(String url, Map<String, String> paramMap, Map<String, String> headers, TypeReference<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildFormBody(paramMap))
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result, responseType);
    }


    /**
     * post + json请求
     *
     * @param url     请求实体
     * @param value   请求参数
     * @param headers 请求头
     * @return 返回结果：字符串
     */
    public static String postByJson(String url, Object value, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildJsonBody(value))
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result);
    }


    /**
     * post + json请求（支持范型对象返回参数）
     *
     * @param url          请求地址
     * @param value        请求参数
     * @param headers      请求头
     * @param responseType 返回类型
     * @param <T>          返回类型泛型
     * @return 返回结果：泛型
     */
    public static <T> T postByJson(String url, Object value, Map<String, String> headers, TypeReference<T> responseType) {
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeaders(headers))
                .post(buildJsonBody(value))
                .build();
        OkHttpClientResult result = commonRequest(request);
        return buildResponse(result, responseType);
    }


    /**
     * 包装请求头部
     *
     * @param headers 请求参数header对象
     * @return okHttp Headers对象
     */
    private static Headers buildHeaders(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(headerBuilder::add);
        }
        return headerBuilder.build();
    }


    /**
     * 包装请求表单
     *
     * @param paramMap 请求参数对象
     * @return okHttp Form表单
     */
    private static FormBody buildFormBody(Map<String, String> paramMap) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (MapUtils.isNotEmpty(paramMap)) {
            paramMap.forEach(formBodyBuilder::add);
        }
        return formBodyBuilder.build();
    }

    /**
     * 包装请求json数据
     *
     * @param request 请求数据对象
     * @return okHttp RequestBody请求体
     */
    private static RequestBody buildJsonBody(Object request) {
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JacksonObjectUtil.objToJson(request), contentType);
    }

    /**
     * 包装返回结果，针对字符串
     *
     * @param result 请求结果
     * @return 返回结果：字符串
     */
    private static String buildResponse(OkHttpClientResult result) {
        if (!result.isSuccess() && StringUtils.isNotBlank(result.getMessage())) {
            throw new RuntimeException(result.getMessage());
        }
        return JacksonObjectUtil.byteToString(result.getBody());
    }

    /**
     * 包装返回结果，针对返回范型对象
     *
     * @param result 请求结果
     * @return 返回结果：对象实体
     */
    private static <T> T buildResponse(OkHttpClientResult result, TypeReference<T> responseType) {
        if (!result.isSuccess() && StringUtils.isNotBlank(result.getMessage())) {
            throw new RuntimeException(result.getMessage());
        }
        return JacksonObjectUtil.byteToObj(result.getBody(), responseType);
    }

    /**
     * 公共请求调用
     *
     * @param request okHttp请求体
     * @return 请求结果
     */
    private static OkHttpClientResult commonRequest(Request request) {
        return OkHttpClientBuilder.syncResponse(request);
    }
}
