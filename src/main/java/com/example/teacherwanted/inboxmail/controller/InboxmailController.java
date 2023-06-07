package com.example.teacherwanted.inboxmail.controller;


import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.inboxmail.service.InboxmailService;
import com.example.teacherwanted.register_login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/inbox")
public class InboxmailController {

    @Autowired
    private InboxmailService inboxmailService;

    // 取得所有收件匣郵件
    @GetMapping("/getUserMails")
    public List<Inboxmail> selectAll( @SessionAttribute(value = "userInfo",required = false) User user) {
        System.out.println(inboxmailService.selectAll(user.getMemAccount()));
        return inboxmailService.selectAll(user.getMemAccount());
    }

    // 取得單一收件匣郵件
    @GetMapping("/inboxGetById")
    public ResponseEntity<?> getInboxmailById(@RequestParam("mailId") Integer mailId) {
        Inboxmail inboxmail = inboxmailService.selectById(mailId);
        System.out.println(inboxmail);
        if (inboxmail != null) {
            return  ResponseEntity.ok(inboxmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("查無此信件");
        }
    }

    @GetMapping("/inbox/{memId}")
    public ResponseEntity<List<Inboxmail>> getInboxmailByMemId(@PathVariable("memId") Integer memId) {
        List<Inboxmail> inboxmails = inboxmailService.getInboxmailByMemId(memId);

            return new ResponseEntity<>(inboxmails, HttpStatus.OK);
    }

    // 新增收件匣郵件
    @PostMapping("/inboxCreate")
    public ResponseEntity<?> createInboxmail( @SessionAttribute("adminSession") Administrator administrator,
                                              @RequestBody Inboxmail inboxmail) {
        System.out.println(inboxmail);
        inboxmail.setSender(administrator.getAdminId());
        inboxmail.setSenderName(administrator.getAdminName());
        return  ResponseEntity.ok(inboxmailService.insert(inboxmail));
    }

    // 更新收件匣郵件
    @PutMapping("/{id}")
    public ResponseEntity<Inboxmail> updateInboxmail(@PathVariable("id") Integer id, @RequestBody Inboxmail updatedInboxmail) {
        Inboxmail inboxmail = inboxmailService.selectById(id);
        if (inboxmail != null) {
            inboxmail.setSender(updatedInboxmail.getSender());
            inboxmail.setSender(updatedInboxmail.getSender());
            inboxmail.setMailStatus(updatedInboxmail.getMailStatus());
            inboxmailService.updateById(inboxmail);
            return new ResponseEntity<>(inboxmail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 刪除收件匣郵件
    @DeleteMapping("/deleteMail")
    public ResponseEntity<Void> deleteInboxmail(@RequestParam Integer mailId) {
//        Inboxmail inboxmail = inboxmailService.selectById(id);

            inboxmailService.deleteById(mailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

