package com.errui.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "subjects")   //学科信息表
public class Subject {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    @Id
    int cid;

    @Column(name = "name")
    String name;

    @Column(name = "time")
    int time;

    @ManyToMany   //多对多场景
    @JoinTable(name = "teach_relation",     //多对多中间关联表
            joinColumns = @JoinColumn(name = "cid"),    //当前实体主键在关联表中的字段名称
            inverseJoinColumns = @JoinColumn(name = "tid")   //教师实体主键在关联表中的字段名称
    )
    List<Teacher> teacher;
}