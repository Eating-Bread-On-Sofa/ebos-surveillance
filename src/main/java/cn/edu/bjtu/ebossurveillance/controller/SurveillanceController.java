package cn.edu.bjtu.ebossurveillance.controller;

import cn.edu.bjtu.ebossurveillance.service.*;
import cn.edu.bjtu.ebossurveillance.service.impl.LogFindImpl;
import cn.edu.bjtu.ebossurveillance.service.impl.log.LogImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/surveillance")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;
    @Autowired
    MqFactory mqFactory;
    @Autowired
    Log log = new LogImpl();
    @Autowired
    LogFind logFind = new LogFindImpl();

    @GetMapping("/commandtest")
    public String commandTest(){
        MqProducer mqProducer = mqFactory.createProducer();
        JSONObject command = new JSONObject();
        command.put("name","name1");
        command.put("commandId","cid");
        command.put("commandName","cn");
        command.put("commandType","get");
        command.put("deviceId","did");
        command.put("deviceName","dn");
        command.put("jsonObject","jo");
        command.put("jsonArray","ja");
        mqProducer.publish("run.command",command.toString());
        mqProducer.publish("test2",command.toString());
        return "提交发送";
    }

    @GetMapping("/mqtest/{destination}")
    public String mqTest(@PathVariable String destination, @RequestBody JSONObject jsonObject){
        mqFactory.createProducer().publish(destination,jsonObject.toString());
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

    @CrossOrigin
    @RequestMapping ("/logtest")
    public String logtest1(){
        log.info("surveillance");
        return "成功";
    }
    @CrossOrigin
    @GetMapping("/logtest")
    public String logtest2(){
        return logFind.read("level","INFO");
    }
}
