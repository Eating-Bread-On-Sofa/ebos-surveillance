package cn.edu.bjtu.ebossurveillance.service;

public interface MqProducer {
    void publish(String topic, String message);
}
