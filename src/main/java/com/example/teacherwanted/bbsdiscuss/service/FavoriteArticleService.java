package com.example.teacherwanted.bbsdiscuss.service;

import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;

import java.util.List;

public interface FavoriteArticleService {
    List<FavoriteArticle> getBbsFavoriteBymemId(Integer memId);
}
