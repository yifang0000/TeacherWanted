package com.example.teacherwanted.bbsdiscuss.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class ResponseBbsFavAndBbsReaction {
    private Integer favoriteArticleId;
    private Integer bbsPostIdFav;
    private Integer memIdFav;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTimeFav;
    private Integer favStatus;


    private Integer postReactionId;
    private Integer bbsPostIdReaction;
    private Integer memIdReaction;
    private Integer reactionStatus;

    public Integer getFavoriteArticleId() {
        return favoriteArticleId;
    }

    public void setFavoriteArticleId(Integer favoriteArticleId) {
        this.favoriteArticleId = favoriteArticleId;
    }

    public Integer getBbsPostIdFav() {
        return bbsPostIdFav;
    }

    public void setBbsPostIdFav(Integer bbsPostIdFav) {
        this.bbsPostIdFav = bbsPostIdFav;
    }

    public Integer getMemIdFav() {
        return memIdFav;
    }

    public void setMemIdFav(Integer memIdFav) {
        this.memIdFav = memIdFav;
    }

    public Date getCreateTimeFav() {
        return createTimeFav;
    }

    public void setCreateTimeFav(Date createTimeFav) {
        this.createTimeFav = createTimeFav;
    }

    public Integer getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(Integer favStatus) {
        this.favStatus = favStatus;
    }

    public Integer getPostReactionId() {
        return postReactionId;
    }

    public void setPostReactionId(Integer postReactionId) {
        this.postReactionId = postReactionId;
    }

    public Integer getBbsPostIdReaction() {
        return bbsPostIdReaction;
    }

    public void setBbsPostIdReaction(Integer bbsPostIdReaction) {
        this.bbsPostIdReaction = bbsPostIdReaction;
    }

    public Integer getMemIdReaction() {
        return memIdReaction;
    }

    public void setMemIdReaction(Integer memIdReaction) {
        this.memIdReaction = memIdReaction;
    }

    public Integer getReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(Integer reactionStatus) {
        this.reactionStatus = reactionStatus;
    }
}
