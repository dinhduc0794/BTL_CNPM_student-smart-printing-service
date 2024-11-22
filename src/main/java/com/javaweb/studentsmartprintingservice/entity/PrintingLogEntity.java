package com.javaweb.studentsmartprintingservice.entity;

import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "printing_log")
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class PrintingLogEntity extends OrderLogEntity{
    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "number_of_copies", nullable = false)
    private Long numberOfCopies = 1L;

    @Column(name = "document_path", nullable = false)
    private String documentPath = "./document/document1.pdf";

    @Column(name = "color", nullable = false)
    private Boolean isColor = false;

    @Column(name = "page_size", nullable = false)
    private PageSizeEnum pageSize;

    @Column(name = "is_2_side", nullable = false)
    private Boolean is2Side;

    //quan he n-n printer
}
