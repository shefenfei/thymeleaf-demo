package com.fisher.springboot.estfuldemo.stream;

import com.fisher.springboot.estfuldemo.bean.User;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListStreamDemo {

    public static void main(String[] args) throws Exception {
        testGroupBy();
//        readFromFile();

        Stream<String> generate = Stream.generate(String::new);
        IntStream stream = Arrays.stream(new int[10], 0, 10);
        Stream<String> stream1 = Arrays.stream(new String[10], 0, 10);
        Stream<String> stringStream = Pattern.compile("").splitAsStream("");
        Stream<String> lines = Files.lines(Paths.get(""));
        IntStream stringStream1 = IntStream.range(0, 10);

        Stream<String> stringStream12 = stream.mapToObj(i -> 1 + "");


        Stream.of("1", "2").flatMap(new Function<String, Stream<?>>() {
            @Override
            public Stream<?> apply(String s) {
                return null;
            }
        });

        String s = Optional.ofNullable(generate.findFirst())
                .orElseThrow(RuntimeException::new)
                .get();

        Collector<CharSequence, ?, String> collector = Collectors.joining(",","[", "]");
        List<String> integers = Arrays.asList("1", "2", "24");
        String collect = Optional.ofNullable(integers)
                .orElse(Collections.emptyList())
                .stream()
                .collect(collector);
        System.out.println(collect);

        Map<String, String> map = integers.stream()
                .collect(Collectors.toMap(key -> generateKey(key), value -> value));
        System.out.println(map);


        System.out.println(s);
    }

    private static String generateKey(String key) {
        return key + "dd";
    }


    private static String readFromFile() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("classpath:alice.txt"));
            String s = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void testGroupBy() {
        IntStream.range(0, 10).mapToObj(index -> {
            User user = new User();
            user.setId(index);
            user.setName("name" + index);
            user.setAddress("address" + index);
            user.setPhone(1040691917 + index);
            return user;
        }).collect(Collectors.groupingBy(User::getName))
                .forEach((key, value) -> {
                    System.out.println(key + "..." + value);
                });
    }

    private static void testXpath() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try {
            String evaluate = xPath.evaluate("/configuration", null);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
