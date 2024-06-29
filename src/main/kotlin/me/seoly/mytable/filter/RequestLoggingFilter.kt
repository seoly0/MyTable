package me.seoly.mytable.filter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter


class RequestLoggingFilter(
    private val options: RequestLoggingFilterOption,
    val callback: (request: HttpServletRequest, logger: Logger) -> Unit
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("[${request.method} ${request.requestURI}]")

        val startTime = System.currentTimeMillis()
        filterChain.doFilter(request, response)

        if (response.status >= 400 || Math.random() < options.sampleRate) {
            callback(request, log)
        }
        val end = System.currentTimeMillis()

        log.info("[Request End: ${end - startTime}ms - ${HttpStatus.valueOf(response.status)}]")
    }
}