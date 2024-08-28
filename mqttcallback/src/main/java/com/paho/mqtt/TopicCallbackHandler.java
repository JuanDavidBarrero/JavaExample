package com.paho.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public abstract class TopicCallbackHandler {
    public abstract void handleMessage(String topic, MqttMessage message);
}
