package io.olaph.slack.dto.jackson.common.messaging

import io.olaph.slack.dto.jackson.common.messaging.composition.Option
import io.olaph.slack.dto.jackson.common.messaging.composition.Text
import io.olaph.slack.dto.jackson.common.messaging.composition.plainTextSample
import io.olaph.slack.dto.jackson.common.messaging.composition.sample

fun Element.Image.Companion.sample(): Element.Image = Element.Image("https://via.placeholder.com/150", "altText")

fun Element.Button.Companion.sample(): Element.Button = Element.Button(Text.plainTextSample(), "actionId")

fun Element.StaticSelect.Companion.sample(): Element.StaticSelect = Element.StaticSelect(Text.plainTextSample(), "actionId", listOf(Option.sample()))

fun Element.ExternalSelect.Companion.sample(): Element.ExternalSelect = Element.ExternalSelect(Text.plainTextSample(), "actionId", listOf(Option.sample()))

fun Element.UsersSelect.Companion.sample(): Element.UsersSelect = Element.UsersSelect(Text.plainTextSample(), "actionId")

fun Element.ChannelsSelect.Companion.sample(): Element.ChannelsSelect = Element.ChannelsSelect(Text.plainTextSample(), "actionId")

fun Element.ConversationsSelect.Companion.sample(): Element.ConversationsSelect = Element.ConversationsSelect(Text.plainTextSample(), "actionId")

fun Element.Overflow.Companion.sample(): Element.Overflow = Element.Overflow("actionId", listOf(Option.sample(), Option.sample()))

fun Element.DatePicker.Companion.sample(): Element.DatePicker = Element.DatePicker("actionId")
