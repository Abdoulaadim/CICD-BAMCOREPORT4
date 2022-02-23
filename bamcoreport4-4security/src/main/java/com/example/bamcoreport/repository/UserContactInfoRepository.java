package com.example.bamcoreport.repository;


import com.example.bamcoreport.model.entity.UserContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactInfoRepository extends JpaRepository<UserContactInfo, Long> {

}

