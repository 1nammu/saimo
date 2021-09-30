package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: nanmu
 * @description: Windpower
 * @author: 唐鹏
 * @date: 2021-09-30 15:25
 */
@Data
@Accessors(chain = true)
public class Windpower {
    private List<String> high;
    private List<String> low;
}
