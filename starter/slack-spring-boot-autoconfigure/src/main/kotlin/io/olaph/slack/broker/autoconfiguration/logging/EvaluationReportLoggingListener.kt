package io.olaph.slack.broker.autoconfiguration.logging

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationEvent
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.GenericApplicationListener
import org.springframework.core.Ordered
import org.springframework.core.ResolvableType


class EvaluationReportLoggingListener : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        private val LOG = LoggerFactory.getLogger(EvaluationReportLoggingListener::class.java)
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        applicationContext.addApplicationListener(ConditionEvaluationReportListener())
    }

    fun onApplicationEvent(event: ApplicationEvent) {
        if (event is ContextRefreshedEvent) {
            val reportMessage = ReportMessage()

            if (LOG.isDebugEnabled) {
                val context = event.applicationContext
                reportMessage.logSlashCommandReceivers(context.getBeansOfType(SlashCommandReceiver::class.java))
                reportMessage.logEventReceivers(context.getBeansOfType(EventReceiver::class.java))
                reportMessage.logInteractiveComponentReceivers(context.getBeansOfType(InteractiveComponentReceiver::class.java))
                LOG.debug(reportMessage.toString())
            }


        }
    }

    private inner class ConditionEvaluationReportListener : GenericApplicationListener {

        override fun getOrder(): Int {
            return Ordered.LOWEST_PRECEDENCE
        }

        override fun supportsEventType(resolvableType: ResolvableType): Boolean {
            val type = resolvableType.rawClass ?: return false
            return ContextRefreshedEvent::class.java.isAssignableFrom(type)
        }

        override fun supportsSourceType(sourceType: Class<*>?): Boolean {
            return true
        }

        override fun onApplicationEvent(event: ApplicationEvent) {
            this@EvaluationReportLoggingListener.onApplicationEvent(event)
        }

    }

    class ReportMessage {

        companion object {
            const val REPORT_TITLE = "SLACK SPRING BOOT EVALUATION REPORT"
            val REPORT_TITLE_SEPERATOR = REPORT_TITLE.map { "=" }.joinToString(separator = "")

            const val SLASHCOMMANDS_TITLE = "Registered Slash Command Receivers"
            val SLASHCOMMANDS_TITLE_SEPERATOR = SLASHCOMMANDS_TITLE.map { "=" }.joinToString(separator = "")

            const val EVENTRECEIVER_TITLE = "Registered Event Receivers"
            val EVENTRECEIVER_TITLE_SEPERATOR = EVENTRECEIVER_TITLE.map { "=" }.joinToString(separator = "")

            const val IC_RECEIVER_TITLE = "Registered InteractiveComponent Receivers"
            val IC_RECEIVER_TITLE_SEPERATOR = IC_RECEIVER_TITLE.map { "=" }.joinToString(separator = "")
        }

        val report: StringBuilder = StringBuilder()


        init {

            report.appendln()
            report.appendln()
            report.appendln(REPORT_TITLE_SEPERATOR)
            report.appendln(REPORT_TITLE)
            report.appendln(REPORT_TITLE_SEPERATOR)
        }

        fun logSlashCommandReceivers(slashCommandReceivers: Map<String, SlashCommandReceiver>) {

            report.appendln().appendln()
            report.appendln(SLASHCOMMANDS_TITLE)
            report.appendln(SLASHCOMMANDS_TITLE_SEPERATOR)
            report.appendln()
            slashCommandReceivers.forEach {
                report.appendln("   - ${it.value::class.simpleName}").appendln()
            }


        }

        fun logEventReceivers(eventReceivers: Map<String, EventReceiver>) {

            report.appendln().appendln()
            report.appendln(EVENTRECEIVER_TITLE)
            report.appendln(EVENTRECEIVER_TITLE_SEPERATOR)
            report.appendln()
            eventReceivers.forEach {
                report.appendln("   - ${it.value::class.simpleName}").appendln()
            }

        }

        fun logInteractiveComponentReceivers(interactiveComponentReceivers: Map<String, InteractiveComponentReceiver>) {

            report.appendln().appendln()
            report.appendln(IC_RECEIVER_TITLE)
            report.appendln(IC_RECEIVER_TITLE_SEPERATOR)
            report.appendln()
            interactiveComponentReceivers.forEach {
                report.appendln("   - ${it.value::class.simpleName}").appendln()
            }

        }

        override fun toString(): String {
            return report.toString()
        }


    }
}
