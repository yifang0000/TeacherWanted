package com.example.teacherwanted.administrator.service.impl;

import com.example.teacherwanted.administrator.dao.TeacherDao;
import com.example.teacherwanted.administrator.model.Teacher;
import com.example.teacherwanted.administrator.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    @Transactional
    public int insert(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    @Override
    @Transactional
    public int deleteByAdminId(Integer adminId) {
        return teacherDao.deleteByAdminId(adminId);
    }

    @Override
    @Transactional
    public int updateByAdminId(Teacher teacher) {
        return teacherDao.updateByAdminId(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher selectByAdminId(Integer adminId) {
        return teacherDao.selectByAdminId(adminId);
    }
}
