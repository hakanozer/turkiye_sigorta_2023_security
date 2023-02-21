package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String username;
    private String password;
    private Boolean enable;

    @ManyToMany
    List<Role> roles;

}
