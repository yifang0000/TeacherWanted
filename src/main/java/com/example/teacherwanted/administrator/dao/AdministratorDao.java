package com.example.teacherwanted.administrator.dao;

import com.example.teacherwanted.administrator.model.Administrator;

import java.util.List;

public interface AdministratorDao {

    //    新增使用者
    public int insert(Administrator administrator);
    //    刪除
    public int deleteByAdminId(Integer adminId);
    //    修改
    public int updateByAdminId(Administrator administrator);
    //    查詢全部使用者
    public List<Administrator> findAll();
    //    查詢單個使用者
    public Administrator selectByAdminId(Integer adminId);
    public Administrator selectByAccount(String adminAccount);

//    登出

}
