package rqueuepriorityloadtest.service

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.core.instrument.MeterRegistry
import java.util.UUID
import org.springframework.stereotype.Service
import rqueuepriorityloadtest.service.RqueueListener.Companion.QUEUE_NAME

@Service
class RqueuePublisher(
    private val rqueueMessageEnqueuer: RqueueMessageEnqueuer,
    private val meterRegistry: MeterRegistry,
) {
    fun publishUniqueMessage(priority: Priority): Boolean {
        val messageId = UUID.randomUUID().toString()
        val messageData = messageData(priority, messageId)
        logger.debug { "publish message to rqueue '$messageData'" }
        try {
            val success = rqueueMessageEnqueuer.enqueueUniqueWithPriority(
                QUEUE_NAME,
                priority.getQueueNamePrioritySuffix(),
                messageId,
                messageData,
            )
            if (!success) {
                // usually it occurs for duplicated message
                logger.error { "failed to push message (unknown reason), message data '$messageData'" }
                meterRegistry.counter("publish_error", "priority", priority.getQueueNamePrioritySuffix()).increment()
                return false
            }
            return true
        } catch (e: Exception) {
            logger.error(e) { "failed to push message (see stacktrace for more details), message data '$messageData'" }
            meterRegistry.counter("publish_error", "priority", priority.getQueueNamePrioritySuffix()).increment()
            return false
        }
    }

    private fun messageData(priority: Priority, messageId: String): String {
        return "messageData(priority '${priority.getQueueNamePrioritySuffix()}', messageId '$messageId')"
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}