package com.fisher.springboot2.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Component
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {


//    @Override
//    @ExceptionHandler(value = ArithmeticException.class)
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
//                                                             Object body,
//                                                             HttpHeaders headers,
//                                                             HttpStatus status, WebRequest request) {
//        System.out.println("出现异常了");
//
//        return new ResponseEntity<>("{\n" +
//                "  \"message\": \"内部异常\"\n" +
//                "}", HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(HttpServletRequest req, Exception e){
        System.out.println("未知异常！原因是:  " + e.getMessage());
        return new ResponseEntity("{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
