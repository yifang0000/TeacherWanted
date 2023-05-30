package com.example.teacherwanted.bbs.dao;

import com.example.teacherwanted.bbs.model.FavoriteArticle;

import java.util.List;

public interface FavoriteArticleDao {
    List<FavoriteArticle> getFavoriteBymemId(Integer memId);
}
