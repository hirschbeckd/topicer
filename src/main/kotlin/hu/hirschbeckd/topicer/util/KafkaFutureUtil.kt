package hu.hirschbeckd.topicer.util

import org.apache.kafka.common.KafkaFuture
import reactor.core.publisher.DirectProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ReplayProcessor

class KafkaFutureUtil {

    companion object {

        @JvmStatic
        fun <T> toMono(kafkaFuture: KafkaFuture<T>): Mono<T> {
            val processor = DirectProcessor.create<T>()
            kafkaFuture.thenApply { processor.sink().next(it); processor.sink().complete() }
            return Mono.from(processor)
        }

        @JvmStatic
        fun <T> toFlux(kafkaFuture: KafkaFuture<Collection<T>>): Flux<T> {
            val processor = ReplayProcessor.create<T>()
            kafkaFuture.thenApply {
                it.forEach { it -> processor.onNext(it) }
                processor.onComplete()
            }
            return processor
        }

        @JvmStatic
        fun <T> toFlux(kafkaFutures: Collection<KafkaFuture<T>>): Flux<T> {
            val processor = ReplayProcessor.create<T>()

            kafkaFutures.forEach {
                it.thenApply { it ->
                    processor.onNext(it)
                }
            }
            val toTypedArray = kafkaFutures.toTypedArray()
            KafkaFuture.allOf(*toTypedArray)
                    .thenApply { processor.onComplete() }

            return processor
        }
    }
}
