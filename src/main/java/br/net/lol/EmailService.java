package br.net.lol;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailSenha(String destinatario, String senha) {
        String assunto = "Senha de cadastro";
        String conteudo = "Sua senha de cadastro é: " + senha;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(conteudo);

        mailSender.send(message);
    }
}


