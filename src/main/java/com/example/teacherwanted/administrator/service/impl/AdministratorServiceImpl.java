package com.example.teacherwanted.administrator.service.impl;

import com.example.teacherwanted.administrator.dao.AdministratorDao;
import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.administrator.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorDao administratorDao;
    @Override
    public int insert(Administrator administrator) {
        return administratorDao.insert(administrator);
    }

    @Override
    public int deleteByAdminId(Integer adminId) {
        return administratorDao.deleteByAdminId(adminId);
    }

    @Override
    public int updateByAdminId(Administrator administrator) {
        return administratorDao.updateByAdminId(administrator);
    }

    @Override
    public List<Administrator> findAll() {
        List<Administrator> all = administratorDao.findAll();
        return all;
    }

    @Override
    public Administrator selectByAdminId(Integer adminId) {
        return administratorDao.selectByAdminId(adminId);
    }
}
