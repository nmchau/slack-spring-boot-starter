package io.olaph.sample

import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.implementation.DefaultSlackClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SampleApplication {


	@Bean
	fun slackClient(): SlackClient {
		return DefaultSlackClient()
	}

}

fun main(args: Array<String>) {
	runApplication<SampleApplication>(*args)
}
