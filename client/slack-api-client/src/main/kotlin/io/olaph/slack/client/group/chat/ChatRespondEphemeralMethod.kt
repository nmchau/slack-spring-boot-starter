package io.olaph.slack.client.group.chat

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest

abstract class ChatRespondEphemeralMethod : ApiCallMethod<ChatRespondEphemeralMethod, Unit, Unit, SlackPostEphemeralRequest>()



