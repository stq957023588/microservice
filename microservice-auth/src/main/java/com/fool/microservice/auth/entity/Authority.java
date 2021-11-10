package com.fool.microservice.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName authority
 */
@Data
public class Authority implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}