package com.deva.ecommerce.kafka;

import com.deva.ecommerce.email.EmailService;
import com.deva.ecommerce.notification.PaymentNotificationRequest;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class NotificationsConsumer {
    @Autowired
    private  EmailService emailService;
        @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
       public void consumePaymentSuccessNotifications(PaymentNotificationRequest paymentConfirmation)  {
        System.out.println((format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation)));
            var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

            try {
                emailService.sendPaymentSuccessEmail(
                        paymentConfirmation.customerEmail(),
                        customerName,
                        paymentConfirmation.amount(),
                        paymentConfirmation.orderReference()
                );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        System.out.println(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

          }

}
