package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.group.chat.ChatMeMessageMethod
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.chat.ChatUnfurlMethod
import io.olaph.slack.client.group.chat.GetChatPermalinkMethod
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI

class DefaultChatMethodGroup : ChatMethodGroup {
    override fun getPermalink(authToken: String): GetChatPermalinkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun meMessage(authToken: String): ChatMeMessageMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unfurl(authToken: String): ChatUnfurlMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultChatMethodGroup::class.java)
    }

    override fun delete(authToken: String): DefaultDeleteMethod {
        return DefaultDeleteMethod(authToken)
    }

    override fun postMessage(authToken: String): DefaultPostMessageMethod {
        return DefaultPostMessageMethod(authToken)
    }

    override fun postEphemeral(authToken: String): DefaultPostEphemeralMethod {
        return DefaultPostEphemeralMethod(authToken)
    }

    override fun update(authToken: String): DefaultUpdateMethod {
        return DefaultUpdateMethod(authToken)
    }

    override fun respondToUrl(body: Any?, responseUrl: String) {
        val uri = URI.create(responseUrl)

        val requestEntity = RequestEntity(body, HttpMethod.POST, uri)
        val response = RestTemplate().exchange<String>(requestEntity)
        if (!response.statusCode.is2xxSuccessful)
            LOG.error("failed to respond to url statuscode: ${response.statusCode.value()}")
    }
}
