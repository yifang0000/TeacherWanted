package com.example.teacherwanted.administrator.service;

import com.example.teacherwanted.administrator.model.Teacher;

import java.util.List;

public interface TeacherService {
    //    新增使用者
    public int insert(Teacher teacher);
    //    刪除
    public int deleteByAdminId(Integer adminId);
    //    修改
    public int updateByAdminId(Teacher teacher);
    //    查詢全部使用者
    public List<Teacher> findAll();
    //    查詢單個使用者
    public Teacher selectByAdminId(Integer adminId);
}
