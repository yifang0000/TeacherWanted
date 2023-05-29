package com.example.teacherwanted.bbs.controller;

import com.example.teacherwanted.bbs.model.BbsPost;
import com.example.teacherwanted.bbs.model.FavoriteArticle;
import com.example.teacherwanted.bbs.service.FavoriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavoriteArticleController {
    @Autowired
    private FavoriteArticleService favoriteArticleService;

    @GetMapping("/bbs/{memId}/fav")
    public ResponseEntity<List<FavoriteArticle>> getBbsPostBymemId(@PathVariable Integer memId){
        List<FavoriteArticle> favoriteArticleList = favoriteArticleService.getFavoriteBymemId(memId);
        return ResponseEntity.status(HttpStatus.OK).body(favoriteArticleList);

    }
}
