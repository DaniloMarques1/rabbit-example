package com.example.rabbitexample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {

    public static void main(String[] args) {
        System.out.println("Produtor");
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("0.0.0.0");
        connectionFactory.setPort(5672);

        String filaName = "fila_hello";
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(filaName, false, false, false, null);
            String msg = "Ola, mundo!";
            channel.basicPublish("", filaName, null, msg.getBytes());
            System.out.println("Enviei mensagem" + msg);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
