package com.fool.microservice.auth.mapper;

import com.fool.microservice.auth.entity.UserAuthorityRelationship;

/**
 * @Entity com.fool.microservice.auth.entity.UserAuthorityRelationship
 */
public interface UserAuthorityRelationshipMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserAuthorityRelationship record);

    int insertSelective(UserAuthorityRelationship record);

    UserAuthorityRelationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAuthorityRelationship record);

    int updateByPrimaryKey(UserAuthorityRelationship record);

}




