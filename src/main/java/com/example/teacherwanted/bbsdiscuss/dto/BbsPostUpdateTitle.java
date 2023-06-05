package com.example.teacherwanted.bbsdiscuss.dto;

import jakarta.validation.constraints.NotBlank;

public class BbsPostUpdateTitle {
   @NotBlank
    private String postTitle;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
}
