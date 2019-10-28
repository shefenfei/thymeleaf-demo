package com.fisher.arch.web.controller;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.model.dto.UserDto;
import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.form.UserForm;
import com.fisher.arch.model.vo.UserVO;
import com.fisher.arch.service.IndexService;
import com.fisher.arch.service.StudentService;
import com.fisher.arch.web.config.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/index")
@Slf4j
@CrossOrigin
public class IndexController {

    private PropertiesConfig config;

    private IndexService indexService;
    private StudentService studentService;

    public IndexController(IndexService service, PropertiesConfig config, StudentService studentService) {
        this.indexService = service;
        this.config = config;
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Map<String, Object> saveUser(@RequestBody @Validated UserForm userForm
//            ,
//                                        BindingResult bindingResult
    ) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
//        if (bindingResult.hasFieldErrors("username")) {
//            String nameMessage = bindingResult.getFieldError("username").getDefaultMessage();
//            sb.append(nameMessage);
//        }
//        if (bindingResult.hasFieldErrors("pass")) {
//            String passMessage = bindingResult.getFieldError("pass").getDefaultMessage();
//            sb.append(passMessage);
//        }


        CaffeineCacheManager caffeineCacheManager;
        map.put("data",  sb);
        map.put("status" , 10001);
        map.put("success" , false);
        log.error("data : {}" , map);
        log.info("data : {}" , map);
        return map;
    }


    @GetMapping("/getById")
    public Map<String ,Object> getUserById(@RequestParam(value = "id", required = false) Integer id , @RequestHeader("X-request-foo") String headerName, String par) {
        Map<String, Object> map = new HashMap<>();
        UserVO userVO = null;
        try {
            userVO = indexService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "会员不存在", e);
        }
        map.put("data" , userVO);
        map.put("header", headerName);
        map.put("query" , par);
//        map.put("success", 1/ 0);
        return map;
    }

    @GetMapping("/rewrite")
    @CrossOrigin
    public Map<String, Object> rewriteUrl() {
        log.info("rewrite 方法执行： {}", "rewriteUrl()");
        Map<String, Object> map = new HashMap<>();
        map.put("data", "rewritedata");

        IntStream chars = "123abc".chars();
        chars.forEach(p -> {
            System.out.println(p);
        });
        return map;
    }


    @GetMapping("/rewrite1")
    @CrossOrigin
    public Map<String, Object> rewriteUrl1() {
        try {
            Thread.sleep(2 * 1000);
            Map<String, Object> map = new HashMap<>();
            map.put("data", "success");
            map.put("aaa", "bbbb");
            map.put("aaa", "bbbb");
            map.put("aaa", "bbbb");
            map.put("aaa", "bbbb");
            map.put("ccc", "dddd");
            if (map.containsKey("aaa")) {
                log.info("data: {}", map);
            }
            return map;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/rewrite2")
    @CrossOrigin
    @Cacheable
    public Map<String, Object> rewriteUrl2() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", "success");
        map.put("aaa", "bbbb");
        map.put("aaa", "bbbb");
        map.put("aaa", "bbbb");
        map.put("aaa", "bbbb");
        map.put("ccc", "dddd");
        if (map.containsKey("aaa")) {
            log.info("data: {}", map);
        }

        log.info("config: {}, {}, {}, {}", config.getAuthor(), config.getDefaultSubject(), config.getName(), config.isEnable());
        log.info("config: {}", config.getServerList());
        log.info("config: {}", config.getCasttime().getSeconds());
        log.info("config: {}", config.getTest());
        log.info("config: {}", config.getTest());
        return map;
    }

    @GetMapping("/throws")
    public ResponseEntity<Integer> testThrows() {
        return new ResponseEntity<>(1 / 0, HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity save(@RequestBody  UserForm userForm) {
        boolean saveUserState = indexService.saveUser(userForm);
        return new ResponseEntity<>(saveUserState, HttpStatus.OK);
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("{\n" +
                "  \"success\":true\n" +
                "}", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        List allUser = indexService.findAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/findStudentById/{id}")
    public ResponseEntity findStudentById(@PathVariable("id") String id) {
        Student student = studentService.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        boolean b = studentService.saveStudent(student);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping("/fromOther")
    public ResponseEntity fromOther() {
        return new ResponseEntity<>("{\n" +
                "  \"data\" : \"hahahah from thymeleaf\",\n" +
                "  \"message\" : \"success\",\n" +
                "  \"code\" : 200\n" +
                "}", HttpStatus.OK);
    }


    public static void main(String[] args) {

        "123ssss".chars().forEach(p -> {
            System.out.println(p);
        });

        String[] strings = new String[]{"a", "b", "c", "d"};
        List<String> strings1 = Arrays.asList(strings);
        long count = strings1.stream().count();
        boolean a = strings1.stream().allMatch(str -> str.startsWith("a"));

        boolean a1 = strings1.stream().anyMatch(str -> str.startsWith("a"));

        boolean a2 = strings1.stream().noneMatch(str -> str.startsWith("a"));

        System.out.println(count);
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a2);



        UserPO userPO = new UserPO();
        userPO.setId(1);
        userPO.setUsername("shefenfei");
        userPO.setPass("123456");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.addMappings(new PropertyMap<UserPO, UserDto>() {
            @Override
            protected void configure() {
                //属性名不一样，自己设置对应关系
                //source生成目标类，destination数据来源类，这两个单词可以理解成两个指针，代指类
                map().setUser_name(source.getUsername());
                skip(destination.getPass());
            }
        });
        UserDto map = modelMapper.map(userPO, UserDto.class);
        log.info("map: {}", map);

//        voTypeMap.validate();
    }


}
