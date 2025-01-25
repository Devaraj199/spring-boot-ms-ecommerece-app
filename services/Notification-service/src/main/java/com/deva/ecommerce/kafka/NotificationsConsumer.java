package com.deva.ecommerce.kafka;

import com.deva.ecommerce.kafka.order.OrderConfirmation;
import com.deva.ecommerce.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class NotificationsConsumer {
    //    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        System.out.println((format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation)));
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
    }

    //    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
//        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
    }

}
