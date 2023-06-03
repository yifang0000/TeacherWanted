package com.example.teacherwanted.bbsdiscuss.model;

import com.example.teacherwanted.active.model.Member;

public class JoinAll {
    private FavoriteArticle favoriteArticle;
    private PostReaction postReaction;
    private BbsTag bbsTag;
    private BbsPost bbsPost;
    private BbsComment bbsComment;
    private Bbs bbs;
    private Member member;

    public JoinAll(){}

    public JoinAll(FavoriteArticle favoriteArticle, PostReaction postReaction, BbsTag bbsTag, BbsPost bbsPost, BbsComment bbsComment, Bbs bbs, Member member) {
        this.favoriteArticle = favoriteArticle;
        this.postReaction = postReaction;
        this.bbsTag = bbsTag;
        this.bbsPost = bbsPost;
        this.bbsComment = bbsComment;
        this.bbs = bbs;
        this.member =member;
    }

    public FavoriteArticle getFavoriteArticle() {
        return favoriteArticle;
    }

    public void setFavoriteArticle(FavoriteArticle favoriteArticle) {
        this.favoriteArticle = favoriteArticle;
    }

    public PostReaction getPostReaction() {
        return postReaction;
    }

    public void setPostReaction(PostReaction postReaction) {
        this.postReaction = postReaction;
    }

    public BbsTag getBbsTag() {
        return bbsTag;
    }

    public void setBbsTag(BbsTag bbsTag) {
        this.bbsTag = bbsTag;
    }

    public BbsPost getBbsPost() {
        return bbsPost;
    }

    public void setBbsPost(BbsPost bbsPost) {
        this.bbsPost = bbsPost;
    }

    public BbsComment getBbsComment() {
        return bbsComment;
    }

    public void setBbsComment(BbsComment bbsComment) {
        this.bbsComment = bbsComment;
    }

    public Bbs getBbs() {
        return bbs;
    }

    public void setBbs(Bbs bbs) {
        this.bbs = bbs;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
