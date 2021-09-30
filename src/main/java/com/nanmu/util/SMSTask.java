package com.nanmu.util;

import com.nanmu.bean.Message;
import com.nanmu.data.User;

public class SMSTask {
    private static boolean flag = false;
    private static Thread t1;

    /**
     * 启动任务
     * @param time 任务执行的间隔时间
     * @param name 昵称
     * @param phoneNumber 手机号
     * @param city 城市
     */
    public static void start( String name, String phoneNumber, String city){
        if(!flag) {

            new Thread(() -> {
                flag = true;
                Message.getInstance();
                task:while (flag) {
                    String text = Util.send(phoneNumber,name,city);
                    if(!"OK".equals(text)){
                        continue;
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break task;
                    }
                }
            }).start();
            //创建消息，消息只会创建一次，创建的时间就是任务启动的时间，用于计算心跳次数 和 追求天数

        }
    }

    public static void end(){
        flag = false;
        if(t1!=null) {
            t1.interrupt();
        }
        Message.setNull();
        Application.remove("user");
    }


    public static void main(String[] args) {
        start("","17634336275","杭州");
    }
}
