package me.seoly.mytable.advisor

import com.fasterxml.jackson.databind.ObjectMapper
import me.seoly.mytable.serializer.CommonPayload
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@RestControllerAdvice(basePackages = ["me.seoly.mytable.controller"])
class ResponseWrappingAdvisor(
    val objectMapper: ObjectMapper
): ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true;
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse,
    ): Any? {

        val requestBodyString = request.body.readAllBytes().toString(Charsets.UTF_8)
        val jsonNode = objectMapper.readTree(requestBodyString)

        return CommonPayload.Response(
            request = CommonPayload.Request(
                path = request.uri.path,
                query = request.uri.query,
                body = if (requestBodyString.isBlank()) null
                else if (jsonNode.isObject) objectMapper.readValue(requestBodyString, HashMap::class.java)
                else if (jsonNode.isArray) objectMapper.readValue(requestBodyString, List::class.java)
                else null
            ),
            contents = body,
        )
//        try {
//        }
//        catch (e: Exception) {
//            return CommonPayload.Response(
//                request = CommonPayload.Request(
//                    path = request.uri.path,
//                    query = request.uri.query,
//                    body = request.body.readAllBytes().toString(Charsets.UTF_8),
//                ),
//                contents = body,
//            )
//        }

    }

}