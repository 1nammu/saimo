package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: nanmu
 * @description:
 * @author: 唐鹏
 * @date: 2021-09-30 15:21
 */
@Data
@Accessors(chain = true)
public class Send {
    private SmsType smsType;
    private int status;
    private Temp temp;
    private List<Integer> tempType;
    private long time;
    private List<WeatherType> weatherType;
    private Winddirect winddirect;
    private Windpower windpower;
}
