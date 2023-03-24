package com.learning.swd.models.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseLessonDto {
    private Long course_id;
    private List<Long> lessonList;
}
