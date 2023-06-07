package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.model.vo.CourseVo;
import com.example.teacherwanted.course.dao.CourseDao;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private Session session;

    @Override
    public List<CourseVo> getCourses(int page, int pageSize, Integer courseCategoryId, String keyword) {
        String hql = "FROM CourseVo";

        // 根据查询条件构建查询语句
        if (courseCategoryId != null || keyword != null) {
            hql += " WHERE";
            if (courseCategoryId != null) {
                hql += " courseCategoryId = :courseCategoryId";
            }
            if (keyword != null) {
                if (courseCategoryId != null) {
                    hql += " AND";
                }
                hql += " (courseName LIKE :keyword OR courseDetail LIKE :keyword)";
            }
        }

        // 创建查询对象
        Query<CourseVo> query = session.createQuery(hql, CourseVo.class);

        // 设置查询参数
        if (courseCategoryId != null) {
            query.setParameter("courseCategoryId", courseCategoryId);
        }
        if (keyword != null) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        // 分页查询
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);

        // 执行查询并返回结果
        List<CourseVo> courseList = query.getResultList();
        return courseList;
    }

    @Override
    public CourseVo getCourseById(Integer courseId) {
        return session.find(CourseVo.class, courseId);
    }
    @Override
    public List<CourseVo> getCoursesByKeyword(String keyword) {
        String hql = "FROM CourseVo WHERE courseName LIKE :keyword";
        TypedQuery<CourseVo> query = session.createQuery(hql, CourseVo.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public Map<String, Object> getAllCourses(int page, int pageSize, Integer courseCategoryId, String keyword) {
        String hql = "FROM CourseVo WHERE 1 = 1";
        if (courseCategoryId != null) {
            hql += " AND courseCategoryId = :courseCategoryId";
        }

        if (keyword != null && !keyword.isEmpty()) {
            hql += " AND courseName LIKE :keyword";
        }

        hql += " ORDER BY courseId DESC"; // 按照 courseId 降序排序

        TypedQuery<CourseVo> query = session.createQuery(hql, CourseVo.class);

        if (courseCategoryId != null) {
            query.setParameter("courseCategoryId", courseCategoryId);
        }

        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }
        int totalCount = query.getResultList().size();

        int offset = (page - 1) * pageSize;
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<CourseVo> courseVoList = query.getResultList();

        Map<String, Object> result = new HashMap<>();
        result.put("courses", courseVoList);
        result.put("total", totalCount);
        return result;
    }

    @Override
    public Map<String, Object> getCoursesByTeacher(Integer teaId, int page, int pageSize, Integer courseCategoryId, String keyword) {
        String hql = "FROM CourseVo WHERE teaId = :teaId";
        if (courseCategoryId != null) {
            hql += " AND courseCategoryId = :courseCategoryId";
        }

        if (keyword != null && !keyword.isEmpty()) {
            hql += " AND courseName LIKE :keyword";
        }

        hql += " ORDER BY courseId DESC"; // 按照 courseId 降序排序

        TypedQuery<CourseVo> query = session.createQuery(hql, CourseVo.class);
        query.setParameter("teaId", teaId);

        if (courseCategoryId != null) {
            query.setParameter("courseCategoryId", courseCategoryId);
        }

        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }
        int totalCount = query.getResultList().size();

        int offset = (page - 1) * pageSize;
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<CourseVo> courseVoList = query.getResultList();

        Map<String, Object> result = new HashMap<>();
        result.put("courses", courseVoList);
        result.put("total", totalCount);
        return result;
    }

    @Override
    public Integer createCourse(CourseVo courseVo) throws IOException {
        session.persist(courseVo);
        session.flush();
        Integer primaryKey = courseVo.getCourseId();
        return primaryKey;
    }

    @Override
    public void updateCourseStatus(Integer courseId, CourseVo courseRequest) {
        CourseVo courseVo = session.find(CourseVo.class, courseId);
        courseVo.setCourseStatus(courseRequest.getCourseStatus());
        session.merge(courseVo);
    }

    @Override
    public void updateCourse(Integer courseId, CourseVo courseRequest) {
        session.merge(courseRequest);
    }

    @Override
    public void updateBoughtCount(CourseVo courseVo) {
        session.merge(courseVo);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        session.remove(getCourseById(courseId));
    }
}
