package com.example.teacherwanted.course.constant;

public enum Category {
    COURSE("課程", 1),
    ACTIVITY("活動", 2),
    SHOP("商城", 3),
    WISH("許願", 4),;

    private String categoryName;
    private int categoryId;

    Category(String categoryName, int categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
