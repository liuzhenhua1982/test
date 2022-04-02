package cn.ikyou.domain.usercenter.service.impl;

import cn.ikyou.domain.usercenter.dao.MessageMapper;
import cn.ikyou.domain.usercenter.entity.Message;
import cn.ikyou.domain.usercenter.service.MessageDomainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MessageDomainServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageDomainService {

}
