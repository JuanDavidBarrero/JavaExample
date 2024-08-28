package com.paho.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MQTTClient {

    private MqttClient client;
    private Map<Pattern, TopicCallbackHandler> topicCallbacks;

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
                // Manejar el tema recibido con todos los temas suscritos
                for (Map.Entry<Pattern, TopicCallbackHandler> entry : topicCallbacks.entrySet()) {
                    Pattern pattern = entry.getKey();
                    TopicCallbackHandler handler = entry.getValue();
                    if (pattern.matcher(topic).matches()) {
                        handler.handleMessage(topic, message);
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // No utilizado en este ejemplo
            }
        });

        client.connect(options);
    }

    // Convierte un tema con comodines en una expresión regular
    private Pattern topicToPattern(String topic) {
        // Reemplaza `+` con `[^/]+` (cualquier cosa que no sea `/`)
        // Reemplaza `#` con `.*` (cualquier cosa, incluidas las subcarpetas)
        String regex = topic
                .replace("+", "[^/]+") // `+` se convierte en cualquier cosa excepto `/`
                .replace("#", ".*");   // `#` se convierte en cualquier cosa, incluyendo `/`
        
        // Asegúrate de que no hay caracteres `/` al final
        if (regex.endsWith("[^/]+")) {
            regex = regex.substring(0, regex.length() - 6) + "[^/]*";
        }
        
        // Añade `^` al principio y `$` al final para hacer una coincidencia exacta
        regex = "^" + regex + "$";
        return Pattern.compile(regex);
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void addTopicCallback(String topic, TopicCallbackHandler callbackHandler) throws MqttException {
        Pattern pattern = topicToPattern(topic);
        topicCallbacks.put(pattern, callbackHandler);
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
