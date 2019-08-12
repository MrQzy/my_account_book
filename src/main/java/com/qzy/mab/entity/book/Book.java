package com.qzy.mab.entity.book;

import com.qzy.mab.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

}
