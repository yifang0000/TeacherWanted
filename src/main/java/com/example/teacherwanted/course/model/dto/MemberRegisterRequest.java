package com.example.teacherwanted.course.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MemberRegisterRequest {

    @NotNull
    private String memAccount;
    @NotNull
    private String memPassword;
    @NotNull
    private String memName;
    @NotNull
    private String memPhone;
    @NotNull
    private String memNickname;
    @NotNull
    private Date memBirthday;
    @NotNull
    private Integer memGender;
    @NotBlank
    private String memEmail;
    @NotNull
    private String memLocation;
    @NotNull
    private Integer interest1;
    @NotNull
    private Integer interest2;
    @NotNull
    private Integer interest3;

}
