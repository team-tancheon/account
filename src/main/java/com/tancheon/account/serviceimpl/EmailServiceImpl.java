package com.tancheon.account.serviceimpl;

import com.tancheon.account.exception.SendEmailFailureException;
import com.tancheon.account.properties.ApplicationProperties;
import com.tancheon.account.service.EmailService;
import com.tancheon.account.utils.LocaleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final ApplicationProperties applicationProperties;
    private final MessageSource messageSource;
    private final TemplateEngine templateEngine;

    @Async(value = "threadPoolTaskExecutor")
    @Override
    public void sendSignupConfirmationEmail(String to, String language, int verifyCode) {
        Locale locale = LocaleUtils.getLocale(language);

        String template = getTemplateRelativePath("signup.confirm", locale);
        String subject = messageSource.getMessage("signup.confirm.email.subject", null, locale);

        Context context = new Context(locale);
        context.setVariable("verification_code", verifyCode);

        sendEmailFromTemplate(template, to, subject, context);
    }

    private void sendEmailFromTemplate(String template, String to, String subject, Context context) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(getSenderMailAddress()); //gmail smtp를 통해 발송하는 경우에는 발송 이메일 주소 변경 불가
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateEngine.process(template, context), true);
        } catch (MessagingException e) {
            throw new SendEmailFailureException(e);
        }

        mailSender.send(message);
    }

    private String getTemplateRelativePath(String name, Locale locale) {
        if (locale == Locale.US)
            return name + ".english";

        return name + ".korean";
    }

    private String getSenderMailAddress() {
        return applicationProperties.getMail().get("sender-mail-address");
    }
}
