package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userCreateRepository extends JpaRepository <SiteUser, Long>{

}
