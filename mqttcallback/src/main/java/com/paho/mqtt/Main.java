package com.paho.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Main {
    public static void main(String[] args) {
        try {
            MQTTClient mqttClient = new MQTTClient("ssl://broker.hivemq.com:8883", "JavaClient");

            mqttClient.addTopicCallback("test/+/juanda", new TopicCallbackHandler() {
                @Override
                public void handleMessage(String topic, MqttMessage message) {
                    System.out.println("Recibido en " + topic + ": " + new String(message.getPayload()));
                    System.out.println("realizando tarea 1");
                }
            });

            mqttClient.addTopicCallback("test/data/casa", new TopicCallbackHandler() {
                @Override
                public void handleMessage(String topic, MqttMessage message) {
                    System.out.println("Recibido en " + topic + ": " + new String(message.getPayload()));
                    System.out.println("ejecutando trabajo 2");
                }
            });

            mqttClient.addTopicCallback("casa/sensor/#", new TopicCallbackHandler() {
                @Override
                public void handleMessage(String topic, MqttMessage message) {
                    System.out.println("Recibido en " + topic + ": " + new String(message.getPayload()));
                    System.out.println("trabajando sensores");
                }
            });

            // Verificar si el cliente está conectado
            if (mqttClient.isConnected()) {
                System.out.println("Cliente MQTT conectado con éxito.");

                // Publicar un mensaje cada 10 segundos
                while (true) {
                    mqttClient.publish("test/juanda", "Mensaje cada 10 segundos");
                    Thread.sleep(10000);
                }
            } else {
                System.out.println("Error: Cliente MQTT no está conectado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
