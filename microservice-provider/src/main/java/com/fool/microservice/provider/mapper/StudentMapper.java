package com.fool.microservice.provider.mapper;

import com.fool.microservice.provider.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @Entity com.fool.microservice.provider.entity.Student
 */
@Component
public interface StudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}




