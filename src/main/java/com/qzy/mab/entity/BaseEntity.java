package com.qzy.mab.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qzy
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "creator")
    private Integer creator;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "modifier")
    private Integer updator;

    @Column(name = "modifier_name")
    private String modifierName;


}
