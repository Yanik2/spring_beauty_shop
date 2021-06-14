package com.yan.beauty_shop_spring2.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "service")
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "service")
    @ToString.Exclude
    private List<Account> accounts;
}
