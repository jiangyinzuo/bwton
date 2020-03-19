package pers.jiangyinzuo.carbon;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.jiangyinzuo.carbon.rpc.producer.CreditDropService;

import java.util.concurrent.TimeUnit;

/**
 * @author Jiang Yinzuo
 */

public class Application {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");

    private static void run() {
        CreditDropService creditDropService = context.getBean("creditDropService", CreditDropService.class);

        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.MINUTES.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            creditDropService.addCreditDrop(1L, 2.3);
        }

    }

    public static void main(String[] args) {
        context.start();
        run();
    }
}
