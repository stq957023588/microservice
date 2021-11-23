package com.fool.microservice.provider.service;

import com.fool.microservice.provider.entity.Student;
import com.fool.microservice.provider.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 * @author fool
 * @date 2021/11/22 16:58
 */
@Service
public class StudentService {


    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Student queryById(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

}
