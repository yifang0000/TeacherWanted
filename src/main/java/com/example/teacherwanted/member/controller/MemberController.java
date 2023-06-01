package com.example.teacherwanted.member.controller;


import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private MemberService memberService;


//      用memId拿會員資料明細
    @PostMapping("/memberDetail")
    public ResponseEntity<Member> selectByMemId(
        @RequestBody Member memberRequest)  {
        Integer memId = memberRequest.getMemId();
        Member member = memberService.selectById(memId);
        if (member != null) {
            return ok(member);
        } else {
            return notFound().build();
            }
        }


//      會員資料編輯
    @PutMapping("/memberDetailEdit")
    public String updateMemberDetail(@RequestBody Member member,
                               @SessionAttribute("MemberSession") Integer memId) {
    member.setMemId(memId);
    return memberService.update(member);
}





}
