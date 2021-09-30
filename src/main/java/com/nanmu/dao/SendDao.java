package com.nanmu.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanmu.data.send.Send;
import com.nanmu.mapper.SendMapper;
import org.springframework.stereotype.Repository;

/**
 * @program: nanmu
 * @description: Send
 * @author: 唐鹏
 * @date: 2021-09-30 15:29
 */
@Repository
public class SendDao extends ServiceImpl<SendMapper, Send> {
}
