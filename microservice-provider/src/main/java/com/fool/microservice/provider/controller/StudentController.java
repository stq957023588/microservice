package com.fool.microservice.provider.controller;

import com.fool.microservice.provider.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fool
 * @date 2021/11/22 17:03
 */
@RestController
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "student",method = RequestMethod.GET)
    public Object queryById(Long id) {
        return studentService.queryById(id);
    }

}
