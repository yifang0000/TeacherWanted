package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.CourseChapterDao;
import com.example.teacherwanted.course.model.vo.CourseChapterVo;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseChapterDaoImpl implements CourseChapterDao{
    @PersistenceContext
    private Session session;

    @Override
    public List<CourseChapterVo> findByCourseId(Integer courseId) {
        String hql = "FROM CourseChapterVo c WHERE c.courseId = :courseId";
        Query<CourseChapterVo> query = session.createQuery(hql, CourseChapterVo.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }
    @Override
    public CourseChapterVo findById(Integer id) {
        return session.find(CourseChapterVo.class, id);
    }

    @Override
    public Integer createChapters(CourseChapterVo chapterVo) {
        session.persist(chapterVo);
        session.flush();
        return chapterVo.getChapterId();
    }

    @Override
    public CourseChapterVo createChapter(CourseChapterVo updatedChapter) {
        session.persist(updatedChapter);
        session.flush();
        return updatedChapter;
    }

    @Override
    public Integer updateChapters(CourseChapterVo chapterVo) {
        session.merge(chapterVo);
        return chapterVo.getChapterId();
    }

    @Override
    public void updateChapter(Integer courseId, CourseChapterVo chapterVo) {
        chapterVo.setCourseId(courseId);
        session.merge(chapterVo);
    }

    @Override
    public Integer deleteChaptersById(Integer courseId) {
        String hql = "DELETE FROM CourseChapterVo WHERE courseId = :courseId";
        Query query = session.createQuery(hql);
        query.setParameter("courseId", courseId);
        int deletedCount = query.executeUpdate();
        return deletedCount;
    }

    @Override
    public void deleteChapterByOrderId(CourseChapterVo chapterVo) {
        session.remove(chapterVo);
    }
}
