package com.company.project.task;

import com.company.project.websocket.GlobalUserUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettySendMessageTask {

   // @Scheduled(cron = "*/5 * * * * ?")
    public void message(){
        for(Channel channel : GlobalUserUtil.channels){
            //log.info("客户端连接:"+channel.toString());
            channel.writeAndFlush(new TextWebSocketFrame("我是服务端主动推的消息"));
        }
    }
}
