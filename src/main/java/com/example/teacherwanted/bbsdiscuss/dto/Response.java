package com.example.teacherwanted.bbsdiscuss.dto;

import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.bbsdiscuss.model.BbsPost;
import com.example.teacherwanted.bbsdiscuss.model.BbsTag;

import java.sql.Timestamp;
import java.util.List;

public class Response {
    private String memAccount;
    private String memName;
    private byte[] memPhoto;
    private Integer favStatus;
    private Integer reaction_status;

    public String getMemAccount() {
        return memAccount;
    }

    public void setMemAccount(String memAccount) {
        this.memAccount = memAccount;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public byte[] getMemPhoto() {
        return memPhoto;
    }

    public void setMemPhoto(byte[] memPhoto) {
        this.memPhoto = memPhoto;
    }

    public Integer getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(Integer favStatus) {
        this.favStatus = favStatus;
    }

    public Integer getReaction_status() {
        return reaction_status;
    }

    public void setReaction_status(Integer reaction_status) {
        this.reaction_status = reaction_status;
    }
}
