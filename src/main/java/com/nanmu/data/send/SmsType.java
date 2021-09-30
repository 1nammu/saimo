package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: nanmu
 * @description: SmsType
 * @author: 唐鹏
 * @date: 2021-09-30 15:21
 */
@Data
@Accessors(chain = true)
public class SmsType {
    private List<String> names;
    private List<Integer> values;
}
