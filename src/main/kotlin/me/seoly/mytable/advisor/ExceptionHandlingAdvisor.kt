package me.seoly.mytable.advisor

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import me.seoly.mytable.exception.ApplicationException
import me.seoly.mytable.payload.CommonPayload
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice(basePackages = ["me.seoly.mytable.controller"])
class ExceptionHandlingAdvisor(
    val objectMapper: ObjectMapper
): ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApplicationException::class)
    fun handleException(
        exception: ApplicationException,
        request: HttpServletRequest
    ): ResponseEntity<CommonPayload.Response<Map<String, String?>>> {

        val requestBodyString = request.inputStream.readAllBytes().toString(Charsets.UTF_8)
        val jsonNode = objectMapper.readTree(requestBodyString)

        val res = ResponseEntity(
            CommonPayload.Response(
                success = false,
                request = CommonPayload.Request(
                    path = request.requestURI,
                    query = request.queryString,
                    body = if (requestBodyString.isBlank()) null
                    else if (jsonNode.isObject) objectMapper.readValue(requestBodyString, HashMap::class.java)
                    else if (jsonNode.isArray) objectMapper.readValue(requestBodyString, List::class.java)
                    else null
                ),
                contents = exception.map,
            ),
            exception.status,
        )

        return res
    }

    @ExceptionHandler
    fun handleException(
        exception: Exception,
        request: HttpServletRequest
    ): ResponseEntity<CommonPayload.Response<Map<String, String?>>> {

        val requestBodyString = request.inputStream.readAllBytes().toString(Charsets.UTF_8)
        val jsonNode = objectMapper.readTree(requestBodyString)

        val res = ResponseEntity(
            CommonPayload.Response(
                success = false,
                request = CommonPayload.Request(
                    path = request.requestURI,
                    query = request.queryString,
                    body = if (requestBodyString.isBlank()) null
                    else if (jsonNode.isObject) objectMapper.readValue(requestBodyString, HashMap::class.java)
                    else if (jsonNode.isArray) objectMapper.readValue(requestBodyString, List::class.java)
                    else null
                ),
                contents = mapOf(
                    "message" to exception.message
                ),
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )

        return res
    }
}