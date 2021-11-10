package com.fool.microservice.auth.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_authority_relationship
 */
@Data
public class UserAuthorityRelationship implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer authorityId;

    private static final long serialVersionUID = 1L;
}