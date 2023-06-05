package com.example.teacherwanted.member.controller;


import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberService memberService;


    //活動

    @Autowired
    private ActiveOrderDetailDao activeOrderDetailDao;

//      會員中心相關
//      會員資料
//      用memId拿會員資料明細
    @PostMapping("/memberDetail")
    public ResponseEntity<Member> selectByMemId(
        @RequestBody Member memberRequest, @SessionAttribute(value = "MemberId", required = false) Integer memId)  {

        Member member = memberService.selectById(memId);
        if (member != null) {
            return ok(member);
        } else {
            return notFound().build();
            }
        }


//      會員資料編輯
    @PutMapping("/memberDetail")
    public String updateMemberDetail(@RequestBody Member member,
                               @SessionAttribute("MemberId") Integer memId) {
    member.setMemId(memId);
    return memberService.update(member);
}








}
