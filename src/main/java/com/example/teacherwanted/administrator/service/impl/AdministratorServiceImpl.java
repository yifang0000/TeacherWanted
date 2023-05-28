package com.example.teacherwanted.administrator.service.impl;

import com.example.teacherwanted.administrator.dao.AdministratorDao;
import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.administrator.service.AdministratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final static Logger log = LoggerFactory.getLogger(AdministratorServiceImpl.class);
    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public int insert(Administrator administrator) {
        //先查詢資料庫是否有同帳號？
        Administrator administrator1 = administratorDao.selectByAccount(administrator.getAdminAccount());
        if (administrator1 != null) {
            log.warn("該帳號:{}已存在",administrator.getAdminAccount());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "該帳號已存在");
        }
        //創建
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

    @Override
    public Administrator login(Administrator administrator) {
        //先查詢資料庫是否有同帳號？
        Administrator administratorDb = administratorDao.selectByAccount(administrator.getAdminAccount());
        if(administratorDb== null){
            log.warn("該帳號:{}尚未存在",administrator.getAdminAccount());
            //400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(administratorDb.getAdminPassword().equals(administrator.getAdminPassword())){
            return administratorDb;
        }else {
            log.warn("該帳號:{}的密碼不正確",administrator.getAdminAccount());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
