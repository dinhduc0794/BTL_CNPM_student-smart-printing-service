package com.javaweb.studentsmartprintingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "spso")
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class SPSOEntity extends PersonEntity {
    @Column(name = "staff_id", nullable = false)
    private String staffId;

}
