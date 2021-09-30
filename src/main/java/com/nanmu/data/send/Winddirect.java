package com.nanmu.data.send;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: nanmu
 * @description: Winddirect
 * @author: 唐鹏
 * @date: 2021-09-30 15:24
 */
@Data
@Accessors(chain = true)
public class Winddirect {
    private List<String> names;
    private List<Integer> values;
}
