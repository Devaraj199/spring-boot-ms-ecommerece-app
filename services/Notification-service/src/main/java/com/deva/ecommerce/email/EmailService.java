package com.deva.ecommerce.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.deva.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
public class EmailService {

}
