package com.fool.microservice.auth.mapper;

import com.fool.microservice.auth.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.fool.microservice.auth.entity.User
 */
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(@Param("email") String email);

    User selectByUsername(@Param("username")String username);

}




