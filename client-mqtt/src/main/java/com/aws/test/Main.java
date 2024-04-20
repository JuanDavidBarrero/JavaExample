package com.aws.test;

import software.amazon.awssdk.crt.mqtt.MqttClientConnection;
import software.amazon.awssdk.crt.mqtt.MqttClientConnectionEvents;
import software.amazon.awssdk.crt.mqtt.MqttMessage;
import software.amazon.awssdk.crt.mqtt.QualityOfService;
import software.amazon.awssdk.iot.AwsIotMqttConnectionBuilder;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    static final String certificate_path = "./certificates/cert.crt";
    static final String private_key_path = "./certificates/priv.key";
    static final String cacert_path = "./certificates/cacert.pem";
    static final String end_point = "xxxxxx.iot.us-east-1.amazonaws.com";
    static final String clinet_name = "Test1";

    static final String sub_topic = "test/sub";
    static final String pub_topic = "test/pub";

    static String message = "";
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        MqttClientConnectionEvents callbacks = new MqttClientConnectionEvents() {

            @Override
            public void onConnectionInterrupted(int arg0) {
                System.out.println("The connection has ended");
            }

            @Override
            public void onConnectionResumed(boolean arg0) {
                System.out.println("The device is back on line!");
            }

        };

        AwsIotMqttConnectionBuilder builder = AwsIotMqttConnectionBuilder
                .newMtlsBuilderFromPath(certificate_path, private_key_path)
                .withConnectionEventCallbacks(callbacks)
                .withCertificateAuthorityFromPath(null, cacert_path)
                .withClientId(clinet_name)
                .withEndpoint(end_point)
                .withPort(8883)
                .withCleanSession(true)
                .withProtocolOperationTimeoutMs(60000);

        MqttClientConnection connection = builder.build();
        builder.close();

        CompletableFuture<Boolean> connected = connection.connect();
        try {
            boolean sessionPresent = connected.get();
            System.out.println("Connected to " + (!sessionPresent ? "new" : "existing") + " session!");
        } catch (Exception ex) {
            throw new RuntimeException("Exception occurred during connect", ex);
        }

        CompletableFuture<Integer> subscribed = connection.subscribe(
                sub_topic,
                QualityOfService.AT_LEAST_ONCE,
                (message) -> {
                    String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
                    System.out.println("MESSAGE: " + payload);
                });

        subscribed.get();

        while (true) {

            counter++;
            message = "{\"message\" : " + counter + "}";

            CompletableFuture<Integer> published = connection.publish(new MqttMessage(
                    pub_topic,
                    message.getBytes(),
                    QualityOfService.AT_LEAST_ONCE,
                    false));

            published.get();
            Thread.sleep(5000);
        }

    }
}