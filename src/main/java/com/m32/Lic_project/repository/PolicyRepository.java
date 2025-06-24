package com.m32.Lic_project.repository;

import com.m32.Lic_project.entity.LicPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<LicPolicy,Integer> {
}
