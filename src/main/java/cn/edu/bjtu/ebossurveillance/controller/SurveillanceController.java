package cn.edu.bjtu.ebossurveillance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.edu.bjtu.ebossurveillance.service.MqService;
import cn.edu.bjtu.ebossurveillance.service.SurveillanceService;
import cn.edu.bjtu.ebossurveillance.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;
    @Autowired
    MqService mqService;

    @GetMapping("/commandtest")
    @ResponseBody
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
    @ResponseBody
    public String mqTest(@PathVariable String destination, @RequestBody JSONObject jsonObject){
        mqService.publish(destination,jsonObject);
        return "向"+destination+"发送"+jsonObject;
    }

    @GetMapping("/surnum")
    @ResponseBody
    public int getSurNum(){
        return surveillanceService.getOnlineDevices().size();
    }

    @GetMapping("/surid")
    @ResponseBody
    public JSONArray getId(){
        return surveillanceService.getOnlineDevices();
    }

    @GetMapping("/totalnum")
    @ResponseBody
    public int getTotalNum(){
        return surveillanceService.getTotalNum();
    }

    @GetMapping("/surdetails")
    @ResponseBody
    public JSONObject getDetails(@RequestParam String id){
        return surveillanceService.getDeviceDetail(id);
    }

    @GetMapping("/expiringnum")
    @ResponseBody
    public int getExpiringNum(){
        return surveillanceService.getExpiringDevice().size();
    }

    @GetMapping("/expiringdetails")
    @ResponseBody
    public LayuiTableResultUtil getExpiring(@RequestParam Integer page, @RequestParam Integer limit){
        LayuiTableResultUtil<JSONArray> table = new LayuiTableResultUtil<>("",surveillanceService.getExpiringDevice(),0,surveillanceService.getExpiringDevice().size());
        return table;
    }

    @GetMapping("/regnum")
    @ResponseBody
    public int getRegNum(){
        return surveillanceService.getRegNum();
    }

    @GetMapping("/regdetails")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getReg(@RequestParam Integer page, @RequestParam Integer limit){
        LayuiTableResultUtil<JSONArray> table = new LayuiTableResultUtil<>("",surveillanceService.getRegDevice(),0,surveillanceService.getRegDevice().size());
        return table;
    }

    @GetMapping("/agedis")
    @ResponseBody
    public JSONObject getAgeDistribution(){        return surveillanceService.getAge();}
}
