package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: nanmu
 * @description: WeatherType
 * @author: 唐鹏
 * @date: 2021-09-30 15:23
 */
@Data
@Accessors(chain = true)
public class WeatherType {
    private String name;
    private int value;
}
