package com.nanmu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanmu.data.send.Send;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: nanmu
 * @description: Send
 * @author: 唐鹏
 * @date: 2021-09-30 15:29
 */
@Mapper
public interface SendMapper extends BaseMapper<Send> {
}
