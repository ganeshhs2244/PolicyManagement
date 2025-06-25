package com.m32.Lic_project.mapper;

import com.m32.Lic_project.entity.LicPolicy;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.BindException;


import javax.sql.RowSet;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class PolicyMapper implements FieldSetMapper<LicPolicy> {

    private final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public LicPolicy mapFieldSet(FieldSet fieldSet) throws BindException {
        LicPolicy policy = new LicPolicy();
        try {
            policy.setFamilyCode(fieldSet.readString("familyCode").isEmpty()?"":fieldSet.readString("familyCode"));
            policy.setPolicyHolderName(fieldSet.readString("policyHolderName").isEmpty()?"":fieldSet.readString("policyHolderName"));
            policy.setDOB(parseDate(fieldSet.readString("DOB")));
            policy.setMobile(fieldSet.readString("mobile").isEmpty()?"":fieldSet.readString("mobile"));
            policy.setEmail(fieldSet.readString("email").isEmpty()?"":fieldSet.readString("email"));
            policy.setAddress(fieldSet.readString("address").isEmpty()?"":fieldSet.readString("address"));
            policy.setPolicyNumber(fieldSet.readString("policyNumber").isEmpty()?"":fieldSet.readString("policyNumber"));
            policy.setAgencyCode(fieldSet.readString("agencyCode").isEmpty()?"":fieldSet.readString("agencyCode"));
            policy.setCommecementDate(parseDate(fieldSet.readString("commecementDate")));
            policy.setPlan(fieldSet.readString("plan").isEmpty()?"":fieldSet.readString("plan"));
            policy.setTerm(fieldSet.readString("Term").isEmpty()?"":fieldSet.readString("Term"));
            policy.setPPT(fieldSet.readString("PPT").isEmpty()?"":fieldSet.readString("PPT"));
            policy.setSumAssured(fieldSet.readString("sumAssured").isEmpty()?"":fieldSet.readString("sumAssured"));
            policy.setMode(fieldSet.readString("mode").isEmpty()?"":fieldSet.readString("mode"));
            policy.setFUPDate(parseDate(fieldSet.readString("FUPDate")));
            policy.setPremium(fieldSet.readInt("Premium"));
            policy.setNominee(fieldSet.readString("nominee").isEmpty()?"":fieldSet.readString("nominee"));
        } catch (Exception e) {
            throw new BindException(e.getMessage(), "LicPolicy");
        }

        return policy;
    }

    private Date parseDate(String dateStr) throws BindException {

        if (dateStr.contains("/")) {
            return java.sql.Date.valueOf(LocalDate.parse(dateStr, formatter1));
        }else  if (dateStr.contains("-")) {
            return java.sql.Date.valueOf(LocalDate.parse(dateStr, formatter2));
        }else
        {
            return new Date(01/01/1990);
        }

    }


}

