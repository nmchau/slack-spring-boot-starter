package io.olaph.slack.client.spring.group

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


class SlackRequestBuilder<T>(private val token: String? = null, private val restTemplate: RestTemplate) {

    var body: Any? = null
    lateinit var uri: URI
    lateinit var responseType: Class<T>

    /**
     * with body
     */
    fun with(body: Any): SlackRequestBuilder<T> {
        this.body = body
        return this
    }

    fun toMethod(methodName: String): SlackRequestBuilder<T> {
        this.uri = URI.create("https://slack.com/api/$methodName")
        return this
    }

    fun returnAsType(responseType: Class<T>): SlackRequestBuilder<T> {
        this.responseType = responseType
        return this
    }

    internal fun postWithJsonBody(): ResponseEntity<T> {
        val requestEntity = RequestEntity(this.body, slackHeaders(listOf("application/json")), HttpMethod.POST, this.uri)
        return restTemplate.exchange(requestEntity, responseType)
    }

    internal fun postUrlEncoded(params: Map<String, String>): ResponseEntity<T> {

        val builder = UriComponentsBuilder.fromHttpUrl(this.uri.toString())

        params.forEach { key, value -> builder.queryParam(key, value) }

        val requestEntity = HttpEntity<Any>(slackHeaders(listOf("application/x-www-form-urlencoded")))
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                this.responseType)
    }

    private fun slackHeaders(contentType: List<String>): LinkedMultiValueMap<String, String> {
        val httpHeaders = LinkedMultiValueMap<String, String>()

        //set token if available
        token?.let { httpHeaders[HttpHeaders.AUTHORIZATION] = listOf("Bearer $it") }

        httpHeaders[HttpHeaders.CONTENT_TYPE] = contentType
        return httpHeaders
    }
}
