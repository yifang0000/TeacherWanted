package com.example.teacherwanted.bbsdiscuss.model;

import com.example.teacherwanted.bbsdiscuss.constant.BbsTagCategory;

public class BbsTag {

    private String bbsTagName;
    private BbsTagCategory bbsTagCategory;

    public String getBbsTagName() {
        return bbsTagName;
    }

    public void setBbsTagName(String bbsTagName) {
        this.bbsTagName = bbsTagName;
    }

    public BbsTagCategory getBbsTag() {
        return bbsTagCategory;
    }

    public void setBbsTag(BbsTagCategory bbsTag) {
        this.bbsTagCategory = bbsTag;
    }
}
