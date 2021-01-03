package com.fisher.springboot2.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/timeout")
public class TimeoutController {


    @GetMapping("/test")
    public Callable<String> mockTimeOut() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                return "hahaha";
            }
        };
    }


    @GetMapping("/test1")
    public ResponseEntity<String> getResponse() {
        User user = new User();
        user.setAge(1);
        user.setName("shefenfei");
        user.setAddress("sha");

        User1 user1 = new User1();

        BeanUtils.copyProperties(user, user1);

        System.out.println(user1);

        return new ResponseEntity<String>("", HttpStatus.OK);

    }


    @GetMapping("/checkout")
    public ResponseEntity<String> checkoutError() {
        int result = 1 / 0;
        System.out.println(result);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<String>();
        strings.parallelStream().collect(Collectors.toList());


        HashMap<String, Object> stringObjectHashMap = new HashMap<>(100);
        //发生rehash操作的 里面的元素 = 75的时候 .75 的加载因子
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(MultipartFile file, HttpRequest request) {

        //1. 拿到前端上传的file文件

        //2. 调用腾讯云服务器给的接口， 将对应的参数给过去 ，拿到远程文件url
        //xxxResponse response = TXService.upload(file, xxx, yyy);
        //解析response
        //3. 返回给前端

        return new ResponseEntity<String>("", HttpStatus.OK);
    }



}
