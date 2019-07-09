package com.leyou.client;

import com.leyou.LySearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchService.class)
public class MqDemo {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, 我生产者哦，我要发送一条删除商品数据咯";
        this.amqpTemplate.convertAndSend("leyou.item.exchange","item.delete", msg);
        // 等待10秒后再结束
        //Thread.sleep(10000);
        System.out.println("已经发送信息:" +msg);
    }
}
