package com.example.teacherwanted.member.controller;


import com.example.teacherwanted.active.dao.ActiveOrderDetailDao;
import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.member.model.Member;
import com.example.teacherwanted.member.service.MemberService;
import com.example.teacherwanted.register_login.entity.User;
import com.example.teacherwanted.register_login.service.UserService;
import com.example.teacherwanted.wish.entity.Wish;
import com.example.teacherwanted.wish.repo.WishRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private WishRepository repo;


//      會員中心相關
//      會員資料
//      用memId拿會員資料明細
    @PostMapping("/memberDetail")
    public ResponseEntity<Member> selectByMemId(
            @RequestBody(required = true) Integer memId,
         @SessionAttribute(value = "userInfo", required = false) User user)  {

        Member member = memberService.selectById(memId);
        if (user != null) {
            System.out.println(member);
            return ok(member);
        } else {
            return notFound().build();
            }
        }


//      會員資料編輯
    @PutMapping("/memberDetail")
    public String updateMemberDetail(@RequestBody Member member, HttpSession session
                             ) {
//        User user = new User();
//        session.setAttribute("userInfo", userService.s);
        System.out.println(member);
        System.out.println(memberService.update(member));
        return memberService.update(member);
}


//      許願

    @GetMapping("/mySubscribe/{wishId}")
    public ResponseEntity<List<Wish>> getWishByMemId(@PathVariable("wishId") int wishId,
                                                     @SessionAttribute("userInfo")User user){
        List<Wish> wishes = memberService.getWishByMemId(user.getMemId());

//        if (optionalWish.isPresent()) {
//            Wish wish = optionalWish.get();

            // 根據你的需求，假設Wish物件中有一個memberId屬性表示許願的會員ID
//            Integer memberId = wish.getMemberId();

            // 在這裡，你可以根據memberId查詢相關的會員資料，
            // 或者進行其他操作，例如將相關資料存儲到Model中供會員頁面使用
            // ...

            return new ResponseEntity<>(wishes, HttpStatus.OK);
//        } else {
//            // 若找不到對應的Wish資料，你可以根據需求返回一個預設值或處理異常情況
//            System.out.println("沒有許願過喔!");
//            return null;
        }






}
