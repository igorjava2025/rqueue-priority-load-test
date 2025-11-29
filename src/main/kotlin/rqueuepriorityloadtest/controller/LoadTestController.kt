package rqueuepriorityloadtest.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rqueuepriorityloadtest.service.Priority
import rqueuepriorityloadtest.service.RqueuePublisher

@RequestMapping("/load-test")
@RestController
class LoadTestController(private val rqueuePublisher: RqueuePublisher) {
    @PostMapping
    fun pushMessageToRqueue(@RequestBody request: LoadTestRequest):String {
        val success = rqueuePublisher.publishUniqueMessage(request.priority)
        return "\n\n success - $success"
    }
}

data class LoadTestRequest(
    val priority: Priority,
)