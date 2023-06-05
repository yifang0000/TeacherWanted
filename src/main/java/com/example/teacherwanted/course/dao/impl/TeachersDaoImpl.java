package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.TeacherDao;
import com.example.teacherwanted.course.model.vo.TeacherVo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeachersDaoImpl implements TeacherDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<TeacherVo> getAllTeachers() {
        String hql = "FROM  TeacherVo ORDER BY teaId";
        return (List<TeacherVo>) entityManager.createQuery(hql).getResultList();
    }

    public TeacherVo getTeacherById(Integer id) {
        return entityManager.find(TeacherVo.class, id);
    }
    public void createTeacher(TeacherVo teacherVo) {
        entityManager.persist(teacherVo);
    }

    public void updateTeacher(TeacherVo teacherVo) {

    }

    public void deleteTeacher(Integer id) {
        entityManager.remove(getTeacherById(id));
    }
}
