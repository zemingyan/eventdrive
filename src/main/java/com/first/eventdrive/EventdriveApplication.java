package com.first.eventdrive;

import com.first.eventdrive.domainevent.DomainEventCenter;
import com.first.eventdrive.eventdesign.SelfEventCenter;
import com.first.eventdrive.userdata.topic.kafkahandler.DeliveryWorker;
import com.first.eventdrive.userdata.topic.kafkahandler.PaymentWorker;
import com.first.eventdrive.userdata.topic.kafkahandler.StocWorker;
import com.first.eventdrive.userdata.topic.kafkahandler.UniteConsumerHandler;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.result.SagaSuccessWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;


@SpringBootApplication
public class EventdriveApplication {

    private static Logger log = LoggerFactory.getLogger(EventdriveApplication.class);
    public static void main(String[] args) throws InterruptedException {

        //SpringApplication.run(EventdriveApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(EventdriveApplication.class, args);
        /*DomainEventSender domainEventSender = applicationContext.getBean(DomainEventSender.class);
        Consume consume = new Consume("create_order_success");
        new Thread(consume).start();

        while (true){
            long orderId = ThreadLocalRandom.current().nextLong();
            log.info("order事件触发 id = " + orderId);
           // domainEventSender.post(new OrderDomainEvent(orderId));


           *//* long paymentId = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
            domainEventSender.post(new PaymentDomainEvent(paymentId));
            log.info("payment事件触发 id = " + paymentId);*//*
            Thread.sleep(20000);
        }*/

        String broker = "localhost:9092";
        String groupId = "group2";
        String topic = "test";
        int workerNum = 5;

        System.out.println("init   success");


        /**
         * 以下这种一直在执行的线程，最好不要用内核数的线程池
         * 通常的电脑是2-4核，如果将线程池的coreNum设置成cpu的核数　线程池的优先级是core->workQueue->maxNum
         * 很有可能导致其他的线程一直都在等待队列里面，因为coreNum 的几个线程一直不会执行完毕
         */
        UniteConsumerHandler paymentDeal = new UniteConsumerHandler(broker, groupId,
                TopicEnum.ORDER_CREATE_SUCCESS.getTopic(),1, PaymentWorker.class);
        new Thread(paymentDeal).start();

        UniteConsumerHandler stocDeal = new UniteConsumerHandler(broker, groupId,
                TopicEnum.PAYMENT_SUCCESS.getTopic(), 1,StocWorker.class);
        new Thread(stocDeal).start();

        UniteConsumerHandler deliveryDeal = new UniteConsumerHandler(broker, groupId,
                TopicEnum.STOC_SUCCESS.getTopic(), 1,DeliveryWorker.class);
        new Thread(deliveryDeal).start();

        UniteConsumerHandler saga = new UniteConsumerHandler(broker, groupId, TopicEnum.DELIVERY_SUCCESS.getTopic(),
                1, SagaSuccessWorker.class);
        new Thread(saga).start();

      /*  ConsumerHandler consumers = new ConsumerHandler(brokerList, groupId, topic);
        consumers.execute(workerNum);

        ConsumerHandler consumers2 = new ConsumerHandler(brokerList, groupId, "test2");
        consumers2.execute(workerNum);*/


      /*  System.out.println("sleep before");
        Thread.sleep(5000);
        System.out.println("sleep after");
        OrderTopicGenerate topicGenerate = new OrderTopicGenerate();
        topicGenerate.firstWrite2Kafka();
        System.out.println("end");


        Thread.sleep(200000);*/

        /*EventSender eventSender = applicationContext.getBean(EventSender.class);
        while (true) {
            long orderId = ThreadLocalRandom.current().nextLong();
            eventSender.post(new OrderCreateEvent(orderId));
            log.info("有一个新订单，订单id={}", orderId);

            orderId = ThreadLocalRandom.current().nextLong();
            eventSender.post(new OrderCancelEvent(orderId));
            log.info("有一个订单取消，订单id={}", orderId);

            Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 10000));
        }*/


    }



    @Bean
    public SelfEventCenter eventCenter() {
       /* ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("event-bus-%d").build();*/
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        // 创建一个线程池

        Executor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024));
        return new SelfEventCenter(pool);
    }

    @Bean
    public DomainEventCenter domainEventCenter(){
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        // 创建一个线程池

        Executor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024));
        return new DomainEventCenter(pool);
    }

}
