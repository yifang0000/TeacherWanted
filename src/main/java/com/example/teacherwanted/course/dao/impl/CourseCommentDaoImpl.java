package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.CourseCommentDao;
import com.example.teacherwanted.course.model.vo.CourseCommentVo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseCommentDaoImpl implements CourseCommentDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<CourseCommentVo> findAll() {
        String hql = "FROM CourseCommentVo";
        TypedQuery<CourseCommentVo> query = entityManager.createQuery(hql, CourseCommentVo.class);
        return query.getResultList();
    }

    @Override
    public List<CourseCommentVo> getCommentsByCourseId(Integer courseId) {
        String hql = "FROM CourseCommentVo cr WHERE cr.courseId = :courseId ORDER BY cr.courseCommentId DESC";
        TypedQuery<CourseCommentVo> query = entityManager.createQuery(hql, CourseCommentVo.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public CourseCommentVo getCommentsById(Integer id) {
        return entityManager.find(CourseCommentVo.class, id);
    }

    @Override
    public void createComment(CourseCommentVo courseComment) {
        entityManager.persist(courseComment);
    }

    @Override
    public void editComment(CourseCommentVo courseComment) {
        entityManager.merge(courseComment);
    }

    @Override
    public void updateComment(CourseCommentVo courseComment) {
        entityManager.merge(courseComment);
    }

    @Override
    public void deleteComment(Integer id) {
        entityManager.remove(getCommentsById(id));
    }
}
