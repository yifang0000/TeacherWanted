package com.example.teacherwanted.inboxmail.controller;


import com.example.teacherwanted.inboxmail.model.Inboxmail;
import com.example.teacherwanted.inboxmail.service.InboxmailService;
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
    @GetMapping
    public List<Inboxmail> selectAll(@RequestParam(required = false) String searchKeyword,
                                     @RequestParam(required = false) String activityType) {
        return inboxmailService.selectAll();
    }

    // 取得單一收件匣郵件
    @GetMapping("/inbox")
    public ResponseEntity<Inboxmail> getInboxmailById(@PathVariable("id") Long id) {
        Inboxmail inboxmail = inboxmailService.selectById(id);
        if (inboxmail != null) {
            return new ResponseEntity<>(inboxmail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 新增收件匣郵件
    @PostMapping
    public ResponseEntity<Inboxmail> createInboxmail(@RequestBody Inboxmail inboxmail) {
        inboxmailService.insert(inboxmail);
        return new ResponseEntity<>(inboxmail, HttpStatus.CREATED);
    }

    // 更新收件匣郵件
    @PutMapping("/{id}")
    public ResponseEntity<Inboxmail> updateInboxmail(@PathVariable("id") Long id, @RequestBody Inboxmail updatedInboxmail) {
        Inboxmail inboxmail = inboxmailService.selectById(id);
        if (inboxmail != null) {
            inboxmail.setSender(updatedInboxmail.getSender());
            inboxmail.setSender(updatedInboxmail.getSender());
            inboxmail.setMailStatus(updatedInboxmail.getMailStatus());
            inboxmailService.update(inboxmail);
            return new ResponseEntity<>(inboxmail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 刪除收件匣郵件
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInboxmail(@PathVariable("id") Long id) {
        Inboxmail inboxmail = inboxmailService.selectById(id);
        if (inboxmail != null) {
            inboxmailService.deleteBymailId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

