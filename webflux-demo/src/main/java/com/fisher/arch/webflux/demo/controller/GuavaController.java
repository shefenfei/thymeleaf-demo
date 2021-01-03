package com.fisher.arch.webflux.demo.controller;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
//静态导入
import static com.fisher.arch.webflux.demo.utils.MyUtils.*;

@RestController
@RequestMapping("/guava")
public class GuavaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaController.class);

    @RequestMapping("/test1")
    public Mono<Object> testOptional(Integer a) {
        Optional<Integer> possible = Optional.of(4);
        java.util.Optional<Integer> integer1 = java.util.Optional.of(-1);
        java.util.Optional<Object> o = java.util.Optional.ofNullable(null);
        boolean present2 = o.isPresent();
        boolean present1 = integer1.isPresent();


        java.util.Optional<Integer> a1 = java.util.Optional.ofNullable(a);
        boolean present3 = a1.isPresent();
        Integer integer2 = a1.orElseGet(() -> 1);
        LOGGER.info("{}, {}", present3, integer2);

        boolean present = possible.isPresent();
        Integer integer = possible.get();
        LOGGER.info("{}, {}", present, integer);
        String s = sayHello();
        return Mono.just("{}");
    }


    @GetMapping("/testPredicate")
    public Mono<Object> testPredicate(String input) {
        boolean test = Predicates.notNull().test(input);
        LOGGER.info("{}", test);

        try {
//            Preconditions.checkArgument(test, "输入值是空的");

//            String s = Preconditions.checkNotNull(input);
            Preconditions.checkState(test, "状态不对");
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
//            e.printStackTrace();
        }
        return Mono.just("{}");
    }

    @GetMapping("/testRange")
    public Mono<Object> testRange(String ...as) {

        Range<Integer> closed = Range.closed(1, 3);
        boolean contains = closed.contains(5);
        LOGGER.info("{}", contains);

        boolean contains1 = closed.contains(2);
        LOGGER.info("{}", contains1);

        //可判断时间在某个区间内
        Range<Date> dateRange = Range.range(new Date(), BoundType.CLOSED, new Date(), BoundType.OPEN);
        dateRange.contains(new Date());

        String s = t1("1", "12");

        return Mono.just("{}");
    }

    private String t1(String... as) {
        return "";
    }


    public static void main(String[] args) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(100, .75f);
        for (int i=0; i < 80; i++) {
            stringObjectHashMap.put("i"+ i, i);
        }

        int size = stringObjectHashMap.size();
        System.out.println(size);


        ConcurrentHashMap<String, Object> stringObjectConcurrentHashMap = new ConcurrentHashMap<>(100, .75f, 1);
        stringObjectConcurrentHashMap.put("aaa", "bbb"); //Exception in thread "main" java.lang.NullPointerException


        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        //优化的地方
        strings.ensureCapacity(1000);
        strings.add("11");

        List<String> strings1 = Collections.synchronizedList(strings);
        synchronized (strings1) {
            Iterator<String> iterator1 = strings1.iterator();
            while (iterator1.hasNext()) {
                String next = iterator1.next();
                System.out.println(next);
            }
        }

        LinkedList<String> strings2 = new LinkedList<>();
        strings2.removeFirst();
        strings2.removeLast();
        strings2.getFirst();
        strings2.getLast();

//        ClassLoader.getSystemClassLoader().loadClass()
    }


}
