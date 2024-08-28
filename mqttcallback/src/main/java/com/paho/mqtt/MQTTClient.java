package com.paho.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;

public class MQTTClient {

    private MqttClient client;
    private Map<String, TopicCallbackHandler> topicCallbacks;

    public MQTTClient(String broker, String clientId) throws MqttException {
        this.client = new MqttClient(broker, clientId);
        this.topicCallbacks = new HashMap<>();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setSocketFactory(SSLSocketFactory.getDefault());
        options.setCleanSession(true);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Conexión perdida: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if (topicCallbacks.containsKey(topic)) {
                    topicCallbacks.get(topic).handleMessage(topic, message);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // No utilizado en este ejemplo
            }
        });

        client.connect(options);
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void addTopicCallback(String topic, TopicCallbackHandler callbackHandler) throws MqttException {
        topicCallbacks.put(topic, callbackHandler);
        client.subscribe(topic);
    }

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2); // Nivel de QoS
        client.publish(topic, mqttMessage);
    }

    public void disconnect() throws MqttException {
        client.disconnect();
    }
}
