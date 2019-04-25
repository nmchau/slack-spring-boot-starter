package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.chat.ChatMeMessageMethod
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.chat.ChatUnfurlMethod
import io.olaph.slack.client.group.chat.ChatUpdateMethod
import io.olaph.slack.client.group.chat.GetChatPermalinkMethod

class MockChatMethodGroup : ChatMethodGroup {
    override fun respondToUrl(body: Any?, responseUrl: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mockChatPostEphemeralMethod = MockChatPostEphemeral()
    private val mockChatPostMessageMethod = MockChatPostMessage()
    private val mockChatDeleteMethod = MockChatDelete()

    override fun delete(authToken: String): MockChatDelete {
        return mockChatDeleteMethod
    }

    override fun getPermalink(authToken: String): GetChatPermalinkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun meMessage(authToken: String): ChatMeMessageMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postEphemeral(authToken: String): MockChatPostEphemeral {
        return mockChatPostEphemeralMethod
    }

    override fun postMessage(authToken: String): MockChatPostMessage {
        return mockChatPostMessageMethod
    }

    override fun unfurl(authToken: String): ChatUnfurlMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(authToken: String): ChatUpdateMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
