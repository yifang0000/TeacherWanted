package com.example.teacherwanted.bbsdiscuss.controller;

import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.service.FavoriteArticleService;
import com.example.teacherwanted.register_login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteArticleController {
    @Autowired
    private FavoriteArticleService favoriteArticleService;

//    我的收藏
//    @GetMapping("/bbsdiscussGet/fav")
//    public ResponseEntity<List<FavoriteArticle>> getBbsPosts(@SessionAttribute(value = "MemberId", required = false) Integer memId){
//        List<FavoriteArticle> favoriteArticleList = favoriteArticleService.getBbsPosts(memId);
//        return ResponseEntity.status(HttpStatus.OK).body(favoriteArticleList);
//    }
//
//    查詢我的收藏
    @GetMapping("/bbsdiscussGet/fav")
    public ResponseEntity<List<FavoriteArticle>> getBbsFavoriteBymemId(@SessionAttribute(value = "userInfo", required = false) User user){
        System.out.println("test-我的收藏");
        System.out.println(user.getMemId());
        List<FavoriteArticle> favoriteArticleList = favoriteArticleService.getBbsFavoriteBymemId(user.getMemId());
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (favoriteArticleList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(favoriteArticleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
