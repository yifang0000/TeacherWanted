package com.example.teacherwanted.active.service.impl;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveDao activeDao;

    @Override
    public String insert(Active active) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        active.setCreateTime(timestamp);

//        System.out.println("Service：" + active);
        try {
            activeDao.insert(active);
            return "新增成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }

    }

    @Override
    public String deleteById(Integer id) {
        try {
            activeDao.deleteById(id);
            return "刪除成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }
    }

    @Override
    public String update(Active active) {
        System.out.println("TEST");
        Active activeCreateTime = activeDao.selectById(active.getActivityId());
//        System.out.println("CreateTime:"+activeCreateTime.getCreateTime());
        active.setCreateTime(activeCreateTime.getCreateTime());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        active.setUpdateTime(timestamp);
        try {
            activeDao.update(active);
            return "更新成功";
        } catch (Exception e) {
            return "錯誤：" + e.getMessage();
        }
    }

    @Override
    public Active selectById(Integer id) {
        return activeDao.selectById(id);
    }

    @Override
    public List<Active> selectAll() {
        return activeDao.selectAll();
    }
}
