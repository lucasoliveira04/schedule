package org.agenda.services;

import org.agenda.model.MessageBody;

public class MessageServices {
    public static void MainMessage(String type, MessageBody body) {
        switch (type) {
            case "faculdade":
                faculdade(body);
                break;
            case "internet":
                internet(body);
                break;
        }
    }

    private static void faculdade(MessageBody messageBody) {
        messageBody.setTitle("PAGAR MENSALIDADE DA FACULDADE");
        double valor = 149.50;
        messageBody.setMessage("REALIZAR PAGAMENTO DA MENSALIDADE. \nVALOR: " + valor + " Url : https://aluno.uninove.br/seu/CENTRAL/aluno//central.php");
    }

    private static void internet(MessageBody messageBody) {
        messageBody.setTitle("PAGAR MENSALIDADE DA INTERNET");
        double valor = 120;
        messageBody.setMessage("REALIZAR PAGAMENTO DA INTERNET. \nValor: " + valor + "");
    }
}
