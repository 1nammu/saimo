package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: nanmu
 * @description: Temp
 * @author: 唐鹏
 * @date: 2021-09-30 15:23
 */
@Data
@Accessors(chain = true)
public class Temp {
    private List<String> high;
    private List<String> low;
}
