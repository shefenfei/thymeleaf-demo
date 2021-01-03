package com.fisher.mybatis.demo.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fisher.mybatis.demo.entity.Room;
import com.fisher.mybatis.demo.entity.Student;
import com.fisher.mybatis.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shefenfei
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/students")
public class StudentController {



    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Object> findOne(@PathVariable("id") Integer id) {
        Student student = studentService.getById(id);

        Arrays.asList(1,2,3,12).stream().sorted(Integer::compareTo);
        test();

        return ResponseEntity.ok().body(student);
    }

    private static void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        //language=JSON
        String json = "{\n" +
                "  \"name\": \"直播房间名\",\n" +
                "  \"roomid\": 1,\n" +
                "  \"cover_img\": \"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/Rl1RuuhdstSfZa8EEljedAYcbtX3Ejpdl2et1tPAQ37bdicnxoVialDLCKKDcPBy8Iic0kCiaiaalXg3EbpNKoicrweQ\\/0?wx_fmt=jpeg\",\n" +
                "  \"live_status\": 101,\n" +
                "  \"start_time\": 1568128900,\n" +
                "  \"end_time\": 1568131200,\n" +
                "  \"anchor_name\": \"李四\",\n" +
                "  \"anchor_img\": \"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/Rl1RuuhdstSfZa8EEljedAYcbtX3Ejpdlp0sf9YTorOzUbGF9Eib6ic54k9fX0xreAIt35HCeiakO04yCwymoKTjw\\/0?wx_fmt=jpeg\",\n" +
                "  \"goods\": [\n" +
                "    {\n" +
                "      \"cover_img\": \"http://mmbiz.qpic.cn/mmbiz_png/FVribAGdErI2PmyST9ZM0JLbNM48I7TH2FlrwYOlnYqGaej8qKubG1EvK0QIkkwqvicrYTzVtjKmSZSeY5ianc3mw/0?wx_fmt=png\",\n" +
                "      \"url\": \"pages/index/index.html\",\n" +
                "      \"price\": 1100,\n" +
                "      \"name\": \"fdgfgf\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        try {
            Room room = objectMapper.readValue(json, Room.class);
            System.out.println(room);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/student")
    public ResponseEntity<String> saveStudent(@Valid @RequestBody Student student, BindingResult result) {
        studentService.addNewStudent(student);
        return ResponseEntity.ok().body("success");
    }


    @GetMapping("/students")
    public ResponseEntity<Object> students(Integer pageIndex, Integer pageSize) {
        IPage<Student> page = studentService.page(new Page<>(pageIndex, pageSize));
        List<Student> students = page.getRecords();
        return ResponseEntity.ok().body(students);
    }

    /**
     * 更新student
     * @param id
     * @param student
     * @return
     */
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Integer id, @RequestBody Student student) {
//        updateMethod1(id, student);
        Student student1 = studentService.getById(id);
        //处理乐观锁
        student.setVersion(student1.getVersion());
        boolean update = studentService.saveOrUpdate(student);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping(params = "^\\w+\\$",value = "")
    public ResponseEntity<Object> saveValidate() {
        return null;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        boolean deleted = studentService.removeById(id);
        return ResponseEntity.ok().body(deleted);
    }

    private void updateMethod1(@PathVariable("id") Integer id, @RequestBody Student student) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", student.getName());
        updateWrapper.eq("id", id).eq("grade", 2);

        boolean update1 = studentService.update(updateWrapper);
        System.out.println(update1);
    }

    public static void main(String[] args) {
       /* List<String> ids = Stream.of("1", "2", "12", "112", "-1", "0")
                .sorted(Comparator.comparing((Function<String, Integer>) Integer::valueOf).reversed())
                .map(String::valueOf).collect(Collectors.toList());
        System.out.println(ids);*/

       test();
    }

}
