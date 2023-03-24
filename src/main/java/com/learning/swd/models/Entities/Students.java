package com.learning.swd.models.Entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "students", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")

})

public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

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
