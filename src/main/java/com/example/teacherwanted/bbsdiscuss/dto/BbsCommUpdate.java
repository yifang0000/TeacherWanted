package com.example.teacherwanted.bbsdiscuss.dto;

import jakarta.validation.constraints.NotBlank;

public class BbsCommUpdate {
    @NotBlank
    private String commentContent;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
