package com.example.teacherwanted.bbsdiscuss.dao;

import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;

import java.util.List;

public interface FavoriteArticleDao {
    List<FavoriteArticle> getBbsFavoriteBymemId(Integer memId);
}
