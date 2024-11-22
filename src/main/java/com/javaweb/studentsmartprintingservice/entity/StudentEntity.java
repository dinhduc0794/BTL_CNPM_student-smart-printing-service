package com.javaweb.studentsmartprintingservice.entity;

import com.javaweb.studentsmartprintingservice.enums.FacultyEnum;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "student")
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class StudentEntity extends PersonEntity {
    @Column(name = "student_id", unique = true, nullable = false)
    private String studentId;

    @Column(name = "faculty", nullable = false)
    private FacultyEnum faculty;

    @Column(name = "paper_quanity", nullable = false)
    private Long paperQuanity = 5L;

    @Column(name = "is_color", nullable = false)
    private Boolean isColor = false;

    @Column(name = "page_size", nullable = false)
    private PageSizeEnum pageSize = PageSizeEnum.A4;

    @Column(name = "is_2_side", nullable = false)
    private Boolean is2Side = true;


}
