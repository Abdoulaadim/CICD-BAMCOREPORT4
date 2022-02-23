package com.example.bamcoreport.repository;

import com.example.bamcoreport.model.entity.ProfileMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMemberRepository extends JpaRepository<ProfileMember, Long> {

}