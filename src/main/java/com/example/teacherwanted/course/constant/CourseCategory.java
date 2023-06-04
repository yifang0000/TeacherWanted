package com.example.teacherwanted.course.constant;

public enum CourseCategory {
    LANGUAGE("語言", 1),
    LIFE("生活", 2),
    PROGRAMMING("程式語言", 3),
    GAME("電競", 4),;

    private String categoryName;
    private int categoryId;

    CourseCategory(String categoryName, int categoryId) {
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
