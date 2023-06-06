package com.example.teacherwanted.member.controller;


import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.bbsdiscuss.dto.BbsCommentRequest;
import com.example.teacherwanted.course.model.vo.CourseVo;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import com.example.teacherwanted.register_login.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        @RequestBody Member memberRequest, @SessionAttribute(value = "UserId", required = false) User user)  {

        Member member = memberService.selectById(user.getMemId());
        if (member != null) {
            System.out.println(member);
            return ok(member);
        } else {
            return notFound().build();
            }
        }


//      會員資料編輯
    @PutMapping("/memberDetailEdit/{memId}")
    public String updateMemberOrder(@RequestBody Member member,
                                   @SessionAttribute("UserId") User user) {
       member.setMemId(user.getMemId());
        return memberService.update(member);
    }


}











