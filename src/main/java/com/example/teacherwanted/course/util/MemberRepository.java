package com.example.teacherwanted.course.util;

import com.example.teacherwanted.course.model.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberRepository extends JpaRepository<MemberVo, Integer> {
    MemberVo findByMemAccount(String account);
}
