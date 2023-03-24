package com.learning.swd.models.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "course_lesson")
public class CouseLesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_id")
    private Long course_id;

    @Column(name = "lesson_id")
    private Long lesson_id;
}
