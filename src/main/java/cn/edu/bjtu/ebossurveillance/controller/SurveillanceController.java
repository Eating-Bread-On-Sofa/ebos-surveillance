package cn.edu.bjtu.ebossurveillance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.edu.bjtu.ebossurveillance.service.MqService;
import cn.edu.bjtu.ebossurveillance.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/surveillance")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;
    @Autowired
    MqService mqService;

    @GetMapping("/commandtest")
    public String commandTest(){
        JSONObject command = new JSONObject();
        command.put("name","name1");
        command.put("commandId","cid");
        command.put("commandName","cn");
        command.put("commandType","get");
        command.put("deviceId","did");
        command.put("deviceName","dn");
        command.put("jsonObject","jo");
        command.put("jsonArray","ja");
        mqService.publish("run.command",command);
        mqService.publish("test2",command);
        return "提交发送";
    }

    @GetMapping("/mqtest/{destination}")
    public String mqTest(@PathVariable String destination, @RequestBody JSONObject jsonObject){
        mqService.publish(destination,jsonObject);
        return "向"+destination+"发送"+jsonObject;
    }

    @CrossOrigin
    @GetMapping("/onlinenum")
    public int getSurNum(){
        return surveillanceService.getOnlineDevices().size();
    }

    @CrossOrigin
    @GetMapping("/onlineid")
    public JSONArray getId(){
        return surveillanceService.getOnlineDevices();
    }

    @CrossOrigin
    @GetMapping("/totalnum")
    public int getTotalNum(){
        return surveillanceService.getTotalNum();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public JSONObject getDetails(@PathVariable String id){
        return surveillanceService.getDeviceDetail(id);
    }
}
