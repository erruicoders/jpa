package com.errui.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "sex")
    String sex;
}
