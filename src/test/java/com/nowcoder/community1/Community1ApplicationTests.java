package com.nowcoder.community1;

import com.nowcoder.community1.util.MailClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@SpringBootTest
class Community1ApplicationTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired KafkaProducer producer;

    @Test
    public void testKafka(){
        producer.sendMessage("test","你好");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testMailSend(){
        mailClient.sendMail("2268618708@qq.com","qige","奇哥你好");
    }

    @Test
    public void testRedis(){
        String key = "qi";
        redisTemplate.opsForValue().set(key,111);
        System.out.println(redisTemplate.opsForValue().get("qi"));
    }

}

@Component
class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic,String content){
        kafkaTemplate.send(topic,content);
    }
}

@Component
class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void handlerMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}
