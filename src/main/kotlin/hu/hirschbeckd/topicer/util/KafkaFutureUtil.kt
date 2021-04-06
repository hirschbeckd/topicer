package hu.hirschbeckd.topicer.util

import org.apache.kafka.common.KafkaFuture
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ReplayProcessor

class KafkaFutureUtil {

    companion object {

        @JvmStatic
        fun <T> toMono(kafkaFuture: KafkaFuture<T>): Mono<T> {
            val processor = ReplayProcessor.create<T>()
            kafkaFuture.whenComplete { result, error ->
                handleKafkaFutureResult<T>(processor, result, error)
                processor.onComplete()
            }
            return Mono.from(processor)
        }

        @JvmStatic
        fun <T> toFlux(kafkaFuture: KafkaFuture<Collection<T>>): Flux<T> {
            val processor = ReplayProcessor.create<T>()
            kafkaFuture.whenComplete { result, error ->
                result.forEach { it -> handleKafkaFutureResult(processor, it, error) }
                processor.onComplete()
            }
            return processor
        }

        @JvmStatic
        fun <T> toFlux(kafkaFutures: Collection<KafkaFuture<T>>): Flux<T> {
            val processor = ReplayProcessor.create<T>()

            kafkaFutures.forEach {
                it.whenComplete { result, error ->
                    handleKafkaFutureResult(processor, result, error)
                }
            }
            val toTypedArray = kafkaFutures.toTypedArray()
            KafkaFuture.allOf(*toTypedArray)
                .thenApply { processor.onComplete() }

            return processor
        }

        private fun <T> handleKafkaFutureResult(
            processor: ReplayProcessor<T>,
            result: T?,
            error: Throwable?
        ) {
            if (error != null) {
                processor.onError(error)
            } else if (result != null) {
                processor.onNext(result)
            }
        }

    }
}
