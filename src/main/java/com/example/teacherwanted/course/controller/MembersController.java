package com.example.teacherwanted.course.controller;

import com.example.teacherwanted.course.model.vo.MemberVo;
import com.example.teacherwanted.course.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MembersController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberVo> getMemberById(@PathVariable Integer id) {
        MemberVo member = memberService.findById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/members/{id}")
//    public MemberVo getMemberById(@PathVariable Integer id) {
//        MemberVo member = memberService.findById(id);
//        if (member != null) {
//            System.out.println("OK");
//            return member;
//        } else {
//            System.out.println("null");
//            return null;
//        }
//    }

    @GetMapping("/members/account/{account}")
    public ResponseEntity<MemberVo> getMemberByAccount(@PathVariable String account) {
        MemberVo member = memberService.findByAccount(account);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/members")
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberVo memberVo) {
        memberService.save(memberVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Integer id, @RequestBody @Valid MemberVo memberVo) {
        memberVo.setMemId(id);
        memberService.update(memberVo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
