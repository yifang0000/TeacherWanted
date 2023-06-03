package com.example.teacherwanted.bbsdiscuss.model;

import com.example.teacherwanted.bbsdiscuss.constant.BbsCategory;

public class Bbs {
    private String bbsCategoryName;
    private BbsCategory bbsCategory;

    public String getBbsCategoryName() {
        return bbsCategoryName;
    }

    public void setBbsCategoryName(String bbsCategoryName) {
        this.bbsCategoryName = bbsCategoryName;
    }

    public BbsCategory getBbsCategoryTitle() {
        return bbsCategory;
    }

    public void setBbsCategoryTitle(BbsCategory bbsCategoryTitle) {
        this.bbsCategory = bbsCategoryTitle;
    }
}
