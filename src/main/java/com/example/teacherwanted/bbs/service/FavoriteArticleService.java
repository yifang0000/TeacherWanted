package com.example.teacherwanted.bbs.service;

import com.example.teacherwanted.bbs.model.FavoriteArticle;

import java.util.List;

public interface FavoriteArticleService {
    List<FavoriteArticle> getFavoriteBymemId(Integer memId);
}
