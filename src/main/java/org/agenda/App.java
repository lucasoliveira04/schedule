package org.agenda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.agenda.model.ConfigurationMail;
import org.agenda.model.EmailRequest;
import org.agenda.model.EmailRequestPayload;
import org.agenda.model.MessageBody;
import org.agenda.services.MessageServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public App(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        App app = new App(httpClient, objectMapper);

        // Inicia o loop infinito para enviar emails
        while (true) {
            try {
                app.sendEmailRequests();
                // Intervalo de espera entre iterações (por exemplo, 1 dia)
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                logger.error("Interrupted while waiting to send emails", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void sendEmailRequests() {
        List<String> pagamentos = new ArrayList<>();
        pagamentos.add("faculdade");
        pagamentos.add("internet");

        logger.info("Starting to send email requests for {} pagamentos", pagamentos.size());
        for (String pagamento : pagamentos) {
            logger.debug("Sending email request for: {}", pagamento);
            sendEmailRequest(pagamento);
        }
    }

    public void sendEmailRequest(String type) {
        try {
            String apiUrl = "https://api-send-email-46gw.onrender.com/api/send/email/send";
            MessageBody messageBody = new MessageBody();

            MessageServices.MainMessage(type, messageBody);
            EmailRequestPayload payload = createEmailRequestPayload(messageBody);
            String requestBody = objectMapper.writeValueAsString(payload);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("Error sending email request for type: {}", type, e);
        }
    }

    private EmailRequestPayload createEmailRequestPayload(MessageBody messageBody) {
        ConfigurationMail configurationMail = new ConfigurationMail(
                "smtp.gmail.com",
                "587",
                "camposdlucasoli@gmail.com",
                "kwqjsnlelyhchkzp",
                "camposdlucasoli@gmail.com"
        );

        EmailRequest emailRequest = new EmailRequest(
                messageBody.getTitle(),
                messageBody.getMessage(),
                "",
                messageBody.getTitle(),
                ""
        );

        return new EmailRequestPayload(configurationMail, emailRequest);
    }

    private static long getDelayToNextExecution() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextExecution = LocalDateTime.of(now.getYear(), now.getMonthValue(), 5, 0, 0);

        if (now.isAfter(nextExecution)) {
            nextExecution = nextExecution.plusMonths(1);
        }
        long delay = now.until(nextExecution, ChronoUnit.SECONDS);
        logger.debug("Calculated delay to next execution: {} seconds", delay);
        return delay;
    }
}
