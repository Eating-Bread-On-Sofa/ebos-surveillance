package cn.edu.bjtu.ebossurveillance.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public interface SurveillanceService {
    JSONArray getOnlineDevices();
    int getTotalNum();
    JSONObject getDeviceDetail(String id);
}
