package com.nanmu.service;

import com.nanmu.bean.Message;
import com.nanmu.dao.ParseDao;
import com.nanmu.dao.SendDao;
import com.nanmu.data.Parse;
import com.nanmu.data.send.*;
import com.nanmu.util.ParseJSON;
import com.nanmu.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: nanmu
 * @description:
 * @author: 唐鹏
 * @date: 2021-07-16 17:16
 */
@Service
@RequiredArgsConstructor
public class ParseService {

    private final ParseDao parseDao;
    private final SendDao sendDao;
    private int id = 1;

    /**
     * Desc: 消息发送
     *
     * @param name        姓名
     * @param phoneNumber 电话
     * @param city        城市
     * @return {@link com.nanmu.data.Parse}
     * @author 唐鹏
     * @date 2021/9/30 15:11
     */
    public Parse send(String name, String phoneNumber, String city) {
        //1. 根据城市查天气
        String json = Utils.getWeather(city);
        //2. 根据天气的JSON信息，解析出天气、温度区间、温馨提示
        ParseJSON obj = new ParseJSON(json);
        //天气信息
        String s1 = obj.getWeather();
        //获取并组装了温度区间
        HashMap<String, String> temp = obj.getTemp();
        String lowTemp = temp.get("low");
        String highTemp = temp.get("high");
        String s2 = lowTemp + "-" + highTemp + "°";

        //获取指数
        Map<String, String> tips = obj.getTips();
        String s3 = tips.values().toArray()[0].toString();

        //进行短信数据的逻辑处理，以及组装存储一个message
        Message msg = Message.getInstance();
        //更新信息
        //更新1. 未来五天温度信息
        HashMap<String, ArrayList<String>> temps = obj.getTemps();
        msg.setTemp(temps);
        //更新2. 更新此次短信发送的温馨提示数据类型
        msg.addSmsType(tips.keySet().toArray()[0].toString());
        //更新3. 更新温度类型出现的数量
        int tempType = -1;
        if (Integer.parseInt(lowTemp) < 0) {
            //0类天气
            tempType = 0;
        } else if (Integer.parseInt(lowTemp) >= 0 && Integer.parseInt(lowTemp) < 10) {
            //1类天气
            tempType = 1;
        } else if (Integer.parseInt(lowTemp) >= 10 && Integer.parseInt(lowTemp) < 20) {
            //2类天气
            tempType = 2;
        } else if (Integer.parseInt(lowTemp) >= 20 && Integer.parseInt(lowTemp) < 30) {
            //3类天气
            tempType = 3;
        } else {
            //4类天气
            tempType = 4;
        }
        msg.addTempTypeCount(tempType);
        //更新4. 这次短信的风向
        msg.addWindirect(obj.getWinddirect());
        //更新5. 这次短信 五天内夜间风力 和 白天风力
        msg.setWindpower(obj.getWindpowers());
        //更新6. 更新天气情况的次数
        msg.addWeatherTypeCount(s1);
        send(msg);
        System.out.println("msg.toJSON()======="+msg.toJSON());
        Send send = new Send();
//        sendDao.save(msg.toJSON());
        //3. 发短信
        Parse parse = new Parse();
        System.out.println("++id = " + ++id);
        parse.setPrattle(s3)
                .setCity(city)
                .setName(name)
                .setPhoneNumber(phoneNumber)
                .setTemperature(s2)
                .setWeather(s1);
        parseDao.save(parse);
        Utils.sendSms(name, phoneNumber, s1, s2, s3);
        return parse;
    }

    private void send(Message msg) {
        // smType
        SmsType smsType1 = new SmsType();
        HashMap<String, ArrayList> smsType = msg.getSmsType();
        smsType1.setNames(smsType.get("names"));
        smsType1.setValues(smsType.get("values"));
        // status
        int status = msg.getStatus();
        // temp
        Temp temp = new Temp();
        HashMap<String, ArrayList<String>> temp1 = msg.getTemp();
        temp.setLow(temp1.get("low"));
        temp.setHigh(temp1.get("high"));
        //tempType
        List<Integer> tempType = msg.getTempType();
        //weatherType
        List<WeatherType> list = new ArrayList<>();
        WeatherType weatherType = new WeatherType();
        List<WeatherType> weatherType1 = msg.getWeatherType();
        for (WeatherType type : weatherType1) {
            weatherType.setName(type.getName());
            weatherType.setValue(type.getValue());
            list.add(weatherType);
        }
        //winddirect
        Winddirect winddirect = new Winddirect();
        HashMap<String, ArrayList> winddirect1 = msg.getWinddirect();
        winddirect.setNames(winddirect1.get("names"));
        winddirect.setNames(winddirect1.get("values"));
        //windpower
        Windpower windpower = new Windpower();
        HashMap<String, ArrayList<String>> windpower1 = msg.getWindpower();
        windpower.setLow(windpower1.get("low"));
        windpower.setHigh(windpower1.get("high"));
        Send send = new Send();
        send.setSmsType(smsType1).setTempType(tempType)
                .setTemp(temp).setStatus(status).setWindpower(windpower)
                .setWinddirect(winddirect).setWeatherType(list);

    }
}
