package com.fool.microservice.provider.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.MessageProducer;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fool
 * @date 2021/11/22 14:07
 */
@RestController
public class CatController {

    @RequestMapping("first-cat-monitor")
    public String firstCatMonitor() {
        MessageProducer cat = Cat.getProducer();
        Transaction t = cat.newTransaction("URL", "first-cat-monitor");  //type=URL的事务记录: 你的接口/方法名称
        try {
            //do your business
            System.out.println("TEST");
            t.setStatus(Message.SUCCESS);
        } catch (Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
        } finally {
            t.complete();
        }

        return "SUCCESS";
    }

}
