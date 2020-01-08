package cn.edu.bjtu.ebossurveillance.service;

public interface MqFactory {
    MqProducer createProducer();
    MqConsumer createConsumer(String topic);
}
