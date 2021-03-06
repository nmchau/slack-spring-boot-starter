package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Channel

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationsRenameResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationsRenameResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsRenameResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.rename)
 */
data class SuccessfulConversationsRenameResponse(
        override val ok: Boolean,
        @JsonProperty("channel") val channel: Channel) : ConversationsRenameResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.rename)
 */
@JacksonDataClass
data class ErrorConversationsRenameResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : ConversationsRenameResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/conversations.rename)
 */
data class ConversationsRenameRequest(@JsonProperty("channel") val channel: String,
                                      @JsonProperty("name") val name: String) {
    companion object
}
