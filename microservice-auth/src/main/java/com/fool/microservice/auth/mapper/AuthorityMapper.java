package com.fool.microservice.auth.mapper;

import com.fool.microservice.auth.entity.Authority;
import com.fool.microservice.auth.entity.UserAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Entity com.fool.microservice.auth.entity.Authority
 */
public interface AuthorityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    Set<UserAuthority> selectByUserId(@Param("userId") Integer uId);

}




