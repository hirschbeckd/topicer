package hu.hirschbeckd.topicer.exception

import org.apache.kafka.common.KafkaException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono

@Component
@RestControllerAdvice
class GlobalWebExceptionHandler {

    @ExceptionHandler(KafkaException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleKafkaException(e: KafkaException): Mono<String> {
        return Mono.just(e.message.toString())
    }
}
