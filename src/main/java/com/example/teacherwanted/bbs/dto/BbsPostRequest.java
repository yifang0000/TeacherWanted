package com.example.teacherwanted.bbs.dto;

import com.example.teacherwanted.bbs.constant.BbsCategory;
import com.example.teacherwanted.bbs.constant.BbsTagCategory;

public class BbsPostRequest {



    private BbsCategory bbsCategory;

    private BbsTagCategory bbsTagCategory;

    private String postTitle;
    private String postContent;

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
}
