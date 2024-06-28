package org.agenda.model;

public class EmailRequestPayload {
    private ConfigurationMail configurationMail;
    private EmailRequest emailRequest;

    public EmailRequestPayload(ConfigurationMail configurationMail, EmailRequest emailRequest) {
        this.configurationMail = configurationMail;
        this.emailRequest = emailRequest;
    }

    public ConfigurationMail getConfigurationMail() {
        return configurationMail;
    }

    public void setConfigurationMail(ConfigurationMail configurationMail) {
        this.configurationMail = configurationMail;
    }

    public EmailRequest getEmailRequest() {
        return emailRequest;
    }

    public void setEmailRequest(EmailRequest emailRequest) {
        this.emailRequest = emailRequest;
    }
}
