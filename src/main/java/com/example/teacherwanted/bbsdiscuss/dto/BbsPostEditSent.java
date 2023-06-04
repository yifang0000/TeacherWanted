package com.example.teacherwanted.bbsdiscuss.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BbsPostEditSent {
    @NotNull
    @NotEmpty
    private String bbsCategoryName;
    @NotNull
    @NotEmpty
    private String bbsTagName;
    @NotNull
    @NotEmpty
    private String postTitle;
    @NotNull
    @NotEmpty
    private String postContent;

    public String getBbsCategoryName() {
        return bbsCategoryName;
    }

    public void setBbsCategoryName(String bbsCategoryName) {
        this.bbsCategoryName = bbsCategoryName;
    }

    public String getBbsTagName() {
        return bbsTagName;
    }

    public void setBbsTagName(String bbsTagName) {
        this.bbsTagName = bbsTagName;
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
