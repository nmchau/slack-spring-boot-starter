package io.olaph.sample.commands.ping

import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class PingCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/ping")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders) {
        this.slackClient.chat().postMessage("")
                .with(SlackPostMessageRequest(
                        text = "Pong",
                        channel = slackCommand.channelId
                )).invoke()
    }
}