package com.example.teacherwanted.wish.controller;


import com.example.teacherwanted.register_login.entity.User;
import com.example.teacherwanted.wish.WishNotFoundException;
import com.example.teacherwanted.wish.entity.Wish;
import com.example.teacherwanted.wish.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WishController {
    @Autowired
    private WishService service;

    public WishController(WishService wishService) {
        this.service = wishService;
    }

    @GetMapping("/wish")
    public String showWishList(Model model) {
        List<Wish> listWish = service.listAll();
        model.addAttribute("listWish", listWish);
        return "wish";
    }

    @GetMapping("/wish/new")
    public String showNewForm(Model model,HttpSession session,RedirectAttributes ra) {
        User currentUser = (User) session.getAttribute("userInfo");
        if (currentUser != null) {
            model.addAttribute("wish", new Wish());
            model.addAttribute("pageTitle", "新增許願");
            System.out.println("memAccount: " + currentUser);
            return "wish_form";
        } else {
            ra.addFlashAttribute("message", "請先登入後再許願<(￣︶￣)>");
            return "redirect:/wish";
        }
    }

    @PostMapping("/wish/save")
    public String saveWish(@ModelAttribute("wish") Wish wish, HttpSession session, Model model,RedirectAttributes ra) {
        String xx = (String) session.getAttribute("memAccount");
        wish.setMemAccount(xx);
        System.out.println("memAccount: " + wish.getMemAccount());

        service.save(wish);
        ra.addFlashAttribute("message", "許願成功＼(＾▽＾)／");
        return "redirect:/wish?message=success";
    }

    @GetMapping("/wish/edit/{wishId}")
    public String showEditForm(@PathVariable("wishId") Integer wishId, HttpSession session, Model model, RedirectAttributes ra) {
        try {
            String loggedInAccount = (String) session.getAttribute("memAccount");
            Wish wish = service.get(wishId);

            // 檢查登錄的帳戶和許願文章的帳戶是否一致
            if (loggedInAccount != null && loggedInAccount.equals(wish.getMemAccount())) {
                model.addAttribute("wish", wish);
                model.addAttribute("pageTitle", "編輯許願");
                return "wish_form";
            } else {
                ra.addFlashAttribute("message", "您沒有權限編輯這則許願(×﹏×)");
                return "redirect:/wish";
            }
        } catch (WishNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/wish_my";
        }
    }

    @GetMapping("/wish/delete/{wishId}")
    public String deleteWish(@PathVariable("wishId") Integer wishId, RedirectAttributes ra, HttpSession session) {
        try {
            Wish wish = service.get(wishId);

            // 檢查當前登入的帳號是否與該許願文章的 memAccount 相符
            String memAccount = (String) session.getAttribute("memAccount");
            if (!wish.getMemAccount().equals(memAccount)) {
                ra.addFlashAttribute("message", "您沒有權限刪除這則許願(×﹏×)");
                return "redirect:/wish";
            }

            service.delete(wishId);
            ra.addFlashAttribute("message", "許願已刪除");
        } catch (WishNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/wish_my";
    }

    @GetMapping("/mywish")
    public String showMyWishList(@ModelAttribute("wish") Wish wish, Model model, HttpSession session,RedirectAttributes ra) {
        // 檢查使用者是否已登入
        User currentUser = (User) session.getAttribute("userInfo");
        if (currentUser != null) {
            // 獲取當前會員的會員ID
            String memberAccount = currentUser.getMemAccount();
            // 根據會員ID查詢該會員發布的 Wish
            List<Wish> myWishList = service.listByMemberAccount(memberAccount);
            model.addAttribute("myWishList", myWishList);
            return "wish_my";
        } else {
            ra.addFlashAttribute("message", "請先登入再查看<(￣︶￣)>");
            return "redirect:/wish";
        }

        }
//        @GetMapping("/wish/search")
//        public String searchWish(@RequestParam("keyword") String keyword, Model model) {
//            List<Wish> searchResults = service.searchByKeyword(keyword);
//            model.addAttribute("searchResults", searchResults);
//            return "wish_search";
//        }
}
