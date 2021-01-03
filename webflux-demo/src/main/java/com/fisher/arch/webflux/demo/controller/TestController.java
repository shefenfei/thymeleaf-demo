package com.fisher.arch.webflux.demo.controller;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.model.Fruit;
import com.fisher.arch.webflux.demo.model.Item;
import com.fisher.arch.webflux.demo.model.SceneRule;
import com.fisher.arch.webflux.demo.service.AppleSellProxy;
import com.fisher.arch.webflux.demo.service.DemoService;
import com.fisher.arch.webflux.demo.service.DroolsService;
import com.fisher.arch.webflux.demo.service.FruitFactory;
import com.fisher.arch.webflux.demo.service.impl.AppleFactory;
import com.fisher.arch.webflux.demo.service.impl.AppleSellServiceImpl;
import com.fisher.arch.webflux.demo.service.impl.BananaFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequestMapping("/rest")
@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private DemoService demoService;
    private DroolsService droolsService;

    private Future<String> futureResult;

    public TestController(DemoService demoService, DroolsService droolsService) {
        this.demoService = demoService;
        this.droolsService = droolsService;
    }

    @GetMapping("/findOne")
    public Mono<String> findOne() {
        demoService.printMessage();
        return Mono.just("{}");
    }

    @GetMapping("/testOne")
    public Mono<String> testOne() {
        return Mono.just("{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"hahha\"\n" +
                "}");
    }


    @GetMapping("/getResult")
    public Mono<String> getResult() {
        futureResult = demoService.asyncMethodWithReturnType();
        return Mono.just("");
    }


    @GetMapping("/getResult1")
    public Mono<Boolean> getResult1() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/first.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());

        StatefulKnowledgeSession kieSession = knowledgeBase.newStatefulKnowledgeSession();

        Item item = new Item();
        item.setId(1);
        item.setPrice(100);
        item.setName("book");

        kieSession.insert(item);
        int i = kieSession.fireAllRules();

        System.out.println("i" + i);
        return Mono.just(true);
    }


    @GetMapping("/drools")
    public Mono<String> testGetFromDrools() {
        String s = droolsService.filterRules1();
        return Mono.just(s);
    }


    @GetMapping("/execute")
    public Mono<String> executeRule() {
        SceneRule sceneRule = new SceneRule();
        sceneRule.setSceneId(1);
        sceneRule.setPlazaId(0);
        sceneRule.setActivityTime("2019-10-28 11:11:11");
        droolsService.executeRules(sceneRule);
        return Mono.just("hahh");
    }


    @PostMapping("dynamic")
    public Mono<String> testDynamicRule(@RequestBody SceneRule sceneRule) {
        droolsService.checkRule(sceneRule);

        return Mono.just("hi");
    }


    @PostMapping("testProxy")
    public Mono<String> testProxy() {
        AppleSellProxy appleSellProxy = new AppleSellProxy(new AppleSellServiceImpl());
        Apple apple = new Apple();
        apple.setName("好的apple");
        apple.setAddress("甘肃");
        apple.setPrice(6D);
        appleSellProxy.sellApple(apple);
        return Mono.just("hi");
    }


    @GetMapping("testFactory")
    public Mono<String> testFactory() {
        FruitFactory fruitFactory = new AppleFactory();
        Fruit fruit = fruitFactory.buildFruit();
        System.out.println(fruit.toString());


        FruitFactory bananaFactory = new BananaFactory();
        Fruit fruit1 = bananaFactory.buildFruit();
        System.out.println(fruit1);
        return Mono.just("apple");
    }


    @GetMapping("/batchInsert")
    public Mono<String> testBatchInsertRedis() {
        demoService.batchInsert();
        return Mono.just("ok");
    }

    @PostMapping("/testSaveHash")
    public Mono<String> testSaveHash() {
        LOGGER.info("开始存储hash");
        demoService.saveHash();
        return Mono.just("jo");
    }

    @PostMapping("/testGetHash")
    public Mono<String> testGetHash() {
        LOGGER.info("开始获取hash");
        demoService.getHash();



        return Mono.just("jo");
    }


    @PostMapping("/testDebug")
    public Mono<String> testDebug() {
        LOGGER.info("开始测试debug");
        List<Apple> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Apple apple = new Apple();
            apple.setNo(i);
            apple.setName("apple:" + i);

            objects.add(apple);
        }

        for (Apple apple : objects) {
            apple.setPrice(100D);
        }

        Apple apple = new Apple();
        apple.setNo(100);

        //设定一个条件
        Predicate<Integer> predicate = x -> x > 100;
        if (predicate.test(apple.getNo())) {
            LOGGER.info("值确实是大于100 ");
        }

        //消费消息
        Consumer<String> consumer = str -> LOGGER.info(str);
        consumer.accept("这是要打印的东西");


        //转换消息
        Function<Apple, String> appleFunction = apple1 -> {
            return apple1.getAddress();
        };
        appleFunction.apply(apple);

        //生产消息
        Supplier<String> stringSupplier = () -> {
            return BigDecimal.TEN.toString();
        };

        String s = stringSupplier.get();

        //一元操作
        UnaryOperator<String> unaryOperator = s1 -> {
            return "" + 1;
        };
        String apply = unaryOperator.apply("2");


        //二元操作
        BinaryOperator<String> binaryOperator = (s1, s2) -> {
            return s1 + s2;
        };
        binaryOperator.apply("", "");

        BinaryOperator<Integer> integerBinaryOperator = (arg1, arg2) -> {
            return arg1 + arg2;
        };
        integerBinaryOperator.apply(1, 2);

        List<Apple> collect = Stream.of(new Apple(), new Apple(), new Apple()).collect(Collectors.toList());
        Map<Integer, Apple> appleMap = Stream.of(new Apple(), new Apple(), new Apple())
                .collect(Collectors.toMap((keyObj) -> {
                    return keyObj.getNo();
                }, (valObj) -> {
                    return valObj;
                }));

        Stream.of(new Apple(), new Apple(), new Apple()).filter(a -> a.getNo() == 1).collect(Collectors.toList());
        Optional<Apple> appleOptional = Stream.of(new Apple(), new Apple(), new Apple()).max(Comparator.comparing(appleObj -> appleObj.getNo()));
        Stream.of(new Apple(), new Apple(), new Apple()).filter(obj -> obj.getNo() == 1).count();
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 8).reduce(0, (acx, x) -> acx + x);

        Stream.of(new Apple(), new Apple(), new Apple()).max(Comparator.comparing(a -> a.getNo()));
        Stream.of(new Apple(), new Apple(), new Apple()).min(Comparator.comparing(a -> a.getNo()));

        //以条件分组
        Map<Boolean, List<Apple>> listMap
                = Stream.of(new Apple(), new Apple(), new Apple()).collect(Collectors.partitioningBy(apple1 -> apple1.getPrice() > 100));

        String collect1 = Stream.of(new Apple(), new Apple(), new Apple())
                .map(app1 -> app1.getName())
                .collect(Collectors.joining(",", "{", "}"));

        return Mono.just("jo");
    }


    public static void main(String[] args) throws InterruptedException {
//        此队列按FIFO（先进先出）排序元素
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(100);
        IntStream.range(0, 100).forEach(blockingQueue::offer);

        int size = blockingQueue.size();
        System.out.println(size);

        //isEmpty()的性能要好过 size()的性能
        while (blockingQueue.isEmpty()) {
            int i = blockingQueue.take();
            System.out.println(i);
        }
    }

    @GetMapping("/sendMessage")
    public Mono<String> sendMessage() {
        HashMap<String, Object> paramsConfig = new HashMap<>();
        paramsConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        paramsConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        paramsConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        paramsConfig.put(ProducerConfig.RETRIES_CONFIG, 3);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(paramsConfig);
        String json = "{\n" +
                "    \"ext\": \"{}\",\n" +
                "    \"extObject\": {},\n" +
                "    \"isRefundFlag\": 0,\n" +
                "    \"memberId\": \"15000000384802799\",\n" +
                "    \"orderAmt\": 33,\n" +
                "    \"orderNo\": \"1190120626191794176\",\n" +
                "    \"orderPayNo\": \"3190120626661507072\",\n" +
                "    \"orderSrc\": 1001,\n" +
                "    \"orderStatus\": 40,\n" +
                "    \"orderTradeTime\": \"2019-11-07 15:11:29\",\n" +
                "    \"orderType\": \"checkout_order\",\n" +
                "    \"parkId\": \"100000204\",\n" +
                "    \"phoneNo\": \"18595756643\",\n" +
                "    \"plazaId\": \"1000274\",\n" +
                "    \"plazaName\": \"上海周浦万达广场\",\n" +
                "    \"productInfo\": [\n" +
                "        {\n" +
                "            \"id\": 8756,\n" +
                "            \"memberId\": \"15000000384802799\",\n" +
                "            \"orderNo\": \"1190120626191794176\",\n" +
                "            \"productCode\": \"13769\",\n" +
                "            \"productCount\": 1,\n" +
                "            \"productExt\": \"{\\\"recordId\\\":\\\"16549048-093B-473F-9713-FDB5E3B52BB7\\\",\\\"serviceFee\\\":10,\\\"entryTime\\\":\\\"20191107112200\\\",\\\"fpType\\\":\\\"WXV\\\",\\\"staySecond\\\":13769,\\\"serviceFeeEnabled\\\":true,\\\"plateNumber\\\":\\\"沪T00122\\\",\\\"sp\\\":\\\"ETCP\\\"}\",\n" +
                "            \"productPrice\": 33,\n" +
                "            \"productSalePrice\": 33,\n" +
                "            \"productType\": \"park\",\n" +
                "            \"tenantId\": \"2018092600001\",\n" +
                "            \"title\": \"ETCP停车费\",\n" +
                "            \"version\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"realPayAmt\": 33,\n" +
                "    \"storeId\": \"100000204\",\n" +
                "    \"storeName\": \"周浦万达停车场测试名称有点长(勿动)\",\n" +
                "    \"tenantId\": \"2018092600001\",\n" +
                "    \"thirdPartNo\": \"ETCP_p1573110689377009001001886212920\",\n" +
                "    \"timeout\": 0,\n" +
                "    \"updateTime\": \"2019-11-07 15:11:30\",\n" +
                "    \"version\": 1\n" +
                "}";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("trade-order", json);
        kafkaProducer.send(producerRecord, ((recordMetadata, e) -> {
            LOGGER.info("recordData: {}", recordMetadata);
        }));

        return Mono.just("{}");
    }

}
