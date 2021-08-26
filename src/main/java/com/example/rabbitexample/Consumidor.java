package com.example.rabbitexample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {

    public static void main(String[] args) {
        System.out.println("Consumidor");
        String filaName = "fila_hello";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("0.0.0.0");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(filaName, false, false, false, null);

            DeliverCallback callback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody());
                System.out.println("Recebi a mensagem = " + msg);
            };

            channel.basicConsume(filaName, true, callback, consumerTag -> {});
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
