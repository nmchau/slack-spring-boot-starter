package io.olaph.slack.dto.jackson.group.conversations

fun ConversationUnarchiveRequest.Companion.sample(): ConversationUnarchiveRequest {
    return ConversationUnarchiveRequest("")
}

fun SuccessfulConversationUnarchiveResponse.Companion.sample(): SuccessfulConversationUnarchiveResponse {
    return SuccessfulConversationUnarchiveResponse(true)
}

fun ErrorConversationUnarchiveResponse.Companion.sample(): ErrorConversationUnarchiveResponse {
    return ErrorConversationUnarchiveResponse(true, "")
}




