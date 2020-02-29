package cn.edu.bjtu.ebossurveillance.service;

import org.springframework.stereotype.Service;

@Service
public interface LogService {
    public void debug(String message);
    public void info(String message);
    public void warn(String message);
    public void error(String message);
    public void getTop();
    public String findLogByCategory(String category);
    public String findAll();
}
