package com.example.teacherwanted.bbsdiscuss.dto;

import com.example.teacherwanted.bbsdiscuss.constant.BbsCategory;
import com.example.teacherwanted.bbsdiscuss.constant.BbsTagCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

public class BbsPostRequest {


    @NotNull
    private BbsCategory bbsCategory;
    @NotNull
    private BbsTagCategory bbsTagCategory;
    @NotNull
    private String postTitle;
    @NotNull
    private String postContent;

    private Integer memId;




    public BbsCategory getBbsCategory() {
        return bbsCategory;
    }

    public void setBbsCategory(BbsCategory bbsCategory) {
        this.bbsCategory = bbsCategory;
    }

    public BbsTagCategory getBbsTagCategory() {
        return bbsTagCategory;
    }

    public void setBbsTagCategory(BbsTagCategory bbsTagCategory) {
        this.bbsTagCategory = bbsTagCategory;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }
}
