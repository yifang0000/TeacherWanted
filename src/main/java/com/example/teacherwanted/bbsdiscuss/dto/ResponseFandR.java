package com.example.teacherwanted.bbsdiscuss.dto;

import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.model.PostReaction;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ResponseFandR {
    @NotEmpty
    private List<FavoriteArticle> favoriteArticleList;
    @NotEmpty
    private List<PostReaction> postReactionList;

    public List<FavoriteArticle> getFavoriteArticleList() {
        return favoriteArticleList;
    }

    public void setFavoriteArticleList(List<FavoriteArticle> favoriteArticleList) {
        this.favoriteArticleList = favoriteArticleList;
    }

    public List<PostReaction> getPostReactionList() {
        return postReactionList;
    }

    public void setPostReactionList(List<PostReaction> postReactionList) {
        this.postReactionList = postReactionList;
    }
}
