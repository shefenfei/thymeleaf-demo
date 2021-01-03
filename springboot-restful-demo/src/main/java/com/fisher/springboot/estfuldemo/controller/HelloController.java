package com.fisher.springboot.estfuldemo.controller;

import com.fisher.springboot.estfuldemo.bean.User;
import com.fisher.starter.autoconfig.service.HelloService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    HelloService helloService;


    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello("shefenfei");
    }


    @PostMapping("/testJson")
    public String te1(@RequestBody User user) {
        LOGGER.info("user: {}", user.toString());
        return "";
    }

    @RequestMapping("/success1")
    public String success(Map<String, Object> map) {
        //language=JSON
        String jsonResult = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"name\": \"user1\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"user2\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"user3\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"user4\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"user5\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"user6\",\n" +
                "      \"address\": \"sha\",\n" +
                "      \"phone\": 13040691917\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        Gson gson = new Gson();
        JsonObject asJsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
        JsonArray users = asJsonObject.getAsJsonArray("users");


        ArrayList<User> userlist = new ArrayList<>();
        users.forEach(user -> {
            User user1 = gson.fromJson(user, User.class);
            userlist.add(user1);
        });
        map.put("hello", "hello world");
        map.put("users", userlist);
        //classapth:/temaplates/success.html
        return "success";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        LOGGER.info("删除用户");
        return "success";
    }


    @GetMapping("/testOOM")
    public String testOOm() throws InterruptedException {
//        gcDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ;
            }
        }, "testBusyThread").start();
        return "";
    }

    private void gcDemo() throws InterruptedException {
        ArrayList<OOmObject> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(50);
            arrayList.add(new OOmObject());
        }
        System.gc();
    }


    static class OOmObject {
        public byte[] b = new byte[64 * 1024];
    }


    @GetMapping("/testError")
    public ResponseEntity<Object> testError() {
        int num = 1/ 0;
        return ResponseEntity.ok("success");
    }




}
