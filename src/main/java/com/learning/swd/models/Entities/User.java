package com.learning.swd.models.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")

})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_admin")
    Boolean is_Admin;

    @Column(name = "status")
    private boolean status;

    @Column(name = "create_time")
    private Date create_time = new Date();

    @Column(name = "modify_time")
    private Date modify_time = new Date();

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "updateBy")
    private String updateBy;

}
