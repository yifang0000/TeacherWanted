package com.example.teacherwanted.member.controller;


import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.active.service.MemberServiceActive;
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

    @Autowired
    private ActiveOrderDetailDao activeOrderDetailDao;


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

    @PostMapping ("/init")
    public Map<String, List<ActiveOrderDetail>> init() {
        // 调用 findAll() 获取数据
        List<ActiveOrderDetail> activeOrderDetail = activeOrderDetailDao.selectAll();

        Map<String, List<ActiveOrderDetail>> map = new HashMap();

        map.put("groupOrderDetail", activeOrderDetail);
        return map;

    }


    //     活動訂單查詢
    @PostMapping ("/OrderList")
    public ResponseEntity<?> selectActiveOrderByMemId(
            @RequestBody (required = false) MemberActive memberRequest, @SessionAttribute(value = "MemberId", required = false) Integer memId){
        // 根據memId查询相應的memberActiveOrder資料
        List<ActiveOrderDetail> activeOrderDetail = activeOrderDetailDao.selectActiveOrderDetailByMemberId(memId);

        if (activeOrderDetail != null) {
            System.out.println(activeOrderDetail);
            System.out.println("有東西");
            return ResponseEntity.ok(activeOrderDetail);
        } else {
            System.out.println("沒有東西");
            return ResponseEntity.notFound().build();
        }
    }

    //活動訂單刪除(後台)
    @DeleteMapping("/activeOrderDelete")
    public String deleteActiveOrder (@RequestBody (required = false) MemberActive memberRequest,
                                     @SessionAttribute(value = "MemberId", required = false) Integer memId) {
        return memberService.deleteById(memId);
    }



}
