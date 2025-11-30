package rqueuepriorityloadtest.config

import com.github.sonus21.rqueue.config.SimpleRqueueListenerContainerFactory
import com.github.sonus21.rqueue.models.enums.PriorityMode
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class RqueueConfigurationCustom(
    @param:Value($$"${rqueue-custom.listener-factory-priority-mode}") private val priorityMode: String,
    @param:Value($$"${rqueue-custom.listener-factory-back-off-time-in-millis}") private val backOffTime: Long,
    @param:Value($$"${rqueue-custom.listener-factory-polling-interval-in-millis}") private val pollingInterval: Long,
    @param:Value($$"${rqueue-custom.listener-factory-max-num-workers}") private val maxNumWorkers: Int,
) {
    // replace rqueue library listener factory
    @Primary
    @Bean
    fun simpleRqueueListenerContainerFactory(): SimpleRqueueListenerContainerFactory {
        val factory = SimpleRqueueListenerContainerFactory()
        factory.priorityMode = PriorityMode.valueOf(priorityMode)
        factory.pollingInterval = pollingInterval
        factory.backOffTime = backOffTime
        factory.maxNumWorkers = maxNumWorkers
        return factory
    }
}