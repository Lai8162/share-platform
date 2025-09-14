package org.lxf.app.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * MDC日志链路追踪
 *
 * @author lxf
 * @since 2025/4/9 20:27
 * @version 1.0
 */
@Component
public class MdcFilter implements Filter {
    private static final String REQUEST_ID_HEADER = "MAX_REQUEST_ID";
    private static final String MDC_REQUEST_ID = "REQUEST_ID";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = Optional.ofNullable(httpRequest.getHeader(REQUEST_ID_HEADER))
                .filter(id -> !id.isBlank())
                .orElseGet(() -> "REQ-" + UUID.randomUUID());

        String ip = getClientIp(httpRequest);

        MDC.put(MDC_REQUEST_ID, requestId);
        MDC.put("IP", ip);
        httpResponse.setHeader(REQUEST_ID_HEADER, requestId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 获取客户端IP
     *
     * @param request servlet请求
     * @return 客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return "0.0.0.0"; // 或抛出自定义异常
        }

        // 按优先级顺序检查的Header列表
        String[] ipHeaders = {
                "X-Forwarded-For",        // 标准代理链IP（可能包含多个IP）
                "Proxy-Client-IP",        // Apache代理
                "WL-Proxy-Client-IP",     // WebLogic代理
                "HTTP_CLIENT_IP",         // 非标准但常见
                "HTTP_X_FORWARDED_FOR",   // 非标准代理链
                "HTTP_X_FORWARDED",
                "HTTP_FORWARDED"
        };

        // 遍历Header获取有效IP
        for (String header : ipHeaders) {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                return parseFirstIp(ip); // 处理多IP情况
            }
        }

        // 默认返回远程地址
        return request.getRemoteAddr();
    }

    /**
     * 验证IP有效性
     * @param ip 待检验IP
     * @return 是否是有效IP
     */
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty()  && !"unknown".equalsIgnoreCase(ip);
    }

    /**
     * 处理多IP（如X-Forwarded-For: ip1,ip2）
     *
     * @param ip 待转换IP
     * @return 转换后的IP地址
     */
    private static String parseFirstIp(String ip) {
        if (ip.contains(","))  {
            return ip.split(",")[0].trim();  // 取第一个IP
        }
        return ip;
    }
}
