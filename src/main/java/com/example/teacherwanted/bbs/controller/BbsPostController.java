package com.example.teacherwanted.bbs.controller;

import com.example.teacherwanted.bbs.model.BbsPost;
import com.example.teacherwanted.bbs.service.BbsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BbsPostController {

    @Autowired
    private BbsPostService bbsPostService;

    @GetMapping("/bbs/{memId}/bbsposts")
    public ResponseEntity<List<BbsPost>> getBbsPostBymemId(@PathVariable Integer memId){
        List<BbsPost> bbsPostList = bbsPostService.getBbsPostBymemId(memId);
       return ResponseEntity.status(HttpStatus.OK).body(bbsPostList);

    }

    @GetMapping("/bbs")
    public ResponseEntity<List<BbsPost>> getBbsPosts(){
        List<BbsPost> bbsPostList = bbsPostService.getBbsPosts();
       return ResponseEntity.status(HttpStatus.OK).body(bbsPostList);
    }



}
