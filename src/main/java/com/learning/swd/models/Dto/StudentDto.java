package com.learning.swd.models.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Date;

@Data
public class StudentDto {

    @JsonIgnore
    private Long id;
    private String name;
    private boolean status;
    @JsonIgnore
    private Date create_time = new Date();
    @JsonIgnore
    private Date modify_time = new Date();
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private String updateBy;
}
