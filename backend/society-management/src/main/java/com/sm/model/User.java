package com.sm.model;

import com.sm.common.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "authority")
    private String authority;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
