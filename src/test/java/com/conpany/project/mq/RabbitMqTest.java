package com.conpany.project.mq;

import com.company.project.mq.SendMsg;
import com.conpany.project.Tester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMqTest extends Tester {

    @Autowired
    SendMsg sendMsg;

    @Test
    public void send(){
        String testContent = "send msg via spring boot";
        sendMsg.send(testContent);
    }

    @Test
    public void sendAll() {
        String testContent = "send all msg via spring boot";
        sendMsg.sendAll(testContent);
    }
}
