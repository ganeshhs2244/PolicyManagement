package com.m32.Lic_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LicPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String familyCode;
    private String policyHolderName;
    private Date DOB;
    private String mobile;
    private String email;
    private String address;
    private String policyNumber;
    private String agencyCode;
    private Date commecementDate;
    private String plan;
    private String Term;
    private String PPT;
    private String sumAssured;
    private String mode;
    private Date FUPDate;
    private int Premium;
    private String nominee;




}
