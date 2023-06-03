package com.example.teacherwanted.bbsdiscuss.service.impl;

import com.example.teacherwanted.bbsdiscuss.dao.FavoriteArticleDao;
import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.service.FavoriteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteArticleServiceImpl implements FavoriteArticleService {

    @Autowired
    private FavoriteArticleDao favoriteArticleDao;


    @Override
    public List<FavoriteArticle> getBbsFavoriteBymemId(Integer memId) {
        return favoriteArticleDao.getBbsFavoriteBymemId(memId);
    }
}
