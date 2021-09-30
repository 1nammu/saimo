package com.nanmu.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: nanmu
 * @description:
 * @author: 唐鹏
 * @date: 2021-07-16 16:44
 */
@Data
@Accessors(chain = true)
public class Parse {
    @TableId(type = IdType.ASSIGN_ID)
    @TableField("id")
    private Long id;
    private String name;

//    @TableField("phone_number")
    private String phoneNumber;

    private String city;
    private String prattle;
    private String weather;
    private String temperature;


}
