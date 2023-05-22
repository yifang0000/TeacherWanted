package com.example.teacherwanted.administrator.dao;

import com.example.teacherwanted.administrator.model.Administrator;

import java.util.List;

public interface AdministratorDao {
    public int insert(Administrator administrator);
    public int deleteByAdminId(Integer adminId);
    public int updateByAdminId(Administrator administrator);
    public Administrator selectByAdminId(Integer adminId);
    public List<Administrator> findAll();

}
