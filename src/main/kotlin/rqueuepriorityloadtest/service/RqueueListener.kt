package rqueuepriorityloadtest.service

import com.github.sonus21.rqueue.annotation.RqueueListener
import com.github.sonus21.rqueue.core.RqueueMessageManager
import com.github.sonus21.rqueue.listener.RqueueMessageHeaders
import com.github.sonus21.rqueue.utils.PriorityUtils
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class RqueueListener(
    private val meterRegistry: MeterRegistry,
    private val rqueueMessageManager: RqueueMessageManager,
) {

    @RqueueListener(
        value = [QUEUE_NAME],
        priority = $$"${rqueue-custom.listener-priority}",
        batchSize = $$"${rqueue-custom.listener-batch-size}",
        concurrency = $$"${rqueue-custom.listener-concurrency}",
    )
    fun onMessage(
        message: String,
        @Header(RqueueMessageHeaders.ID) messageId: String,
    ) {
        logger.debug { "rqueue listener: process message '$message'" }
        val priority = priority(message)
        if (priority == null) {
            logger.error { "priority not defined, do nothing" }
            meterRegistry.counter("listener_error", "priority", "unknown").increment()
            return
        }
        val priorityRqueueName = PriorityUtils.getQueueNameForPriority(QUEUE_NAME, priority.getQueueNamePrioritySuffix())
        rqueueMessageManager.deleteMessage(priorityRqueueName, messageId)
        meterRegistry.counter(
            "processed_message",
            "priority",
            priority.getQueueNamePrioritySuffix(),
        ).increment()
    }

    private fun priority(message: String): Priority? {
        if (message.contains(Priority.HIGH.getQueueNamePrioritySuffix())) {
            return Priority.HIGH
        }
        if (message.contains(Priority.MEDIUM.getQueueNamePrioritySuffix())) {
            return Priority.MEDIUM
        }
        if (message.contains(Priority.LOW.getQueueNamePrioritySuffix())) {
            return Priority.LOW
        }
        return null
    }

    companion object {
        private val logger = KotlinLogging.logger {}
        const val QUEUE_NAME = "loadTestPriorityQueue"
    }
}

enum class Priority(private val queueNamePrioritySuffix: String) {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    fun getQueueNamePrioritySuffix(): String {
        return queueNamePrioritySuffix
    }
}