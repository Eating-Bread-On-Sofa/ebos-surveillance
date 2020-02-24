package cn.edu.bjtu.ebossurveillance.service;

public interface LogFind {
    String read(String key, String value);
    String readAll();
}
