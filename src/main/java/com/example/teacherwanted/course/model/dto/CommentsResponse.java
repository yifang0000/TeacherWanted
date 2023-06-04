package com.example.teacherwanted.course.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsResponse<T, U> {
    private List<T> courseComments;
    private List<U> members;

    public CommentsResponse(List<T> courseComments, List<U> members) {
        this.courseComments = courseComments;
        this.members = members;
    }
}
