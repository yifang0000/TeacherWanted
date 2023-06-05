package com.example.teacherwanted.course.dao.impl;

import com.example.teacherwanted.course.dao.CommentReplyDao;
import com.example.teacherwanted.course.model.vo.CommentReplyVo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Query;


import java.util.List;

@Repository
public class CommentReplyDaoImpl implements CommentReplyDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<CommentReplyVo> findAll() {
        String hql = "FROM CommentReplyVo";
        TypedQuery<CommentReplyVo> query = entityManager.createQuery(hql, CommentReplyVo.class);
        return query.getResultList();
    }

    @Override
    public List<CommentReplyVo> getRepliesByCommentId(Integer commentId) {
        String hql = "FROM CommentReplyVo cr WHERE cr.courseCommentId = :courseCommentId";
        TypedQuery<CommentReplyVo> query = entityManager.createQuery(hql, CommentReplyVo.class);
        query.setParameter("courseCommentId", commentId);
        return query.getResultList();
    }

    @Override
    public CommentReplyVo getReplyById(Integer id) {
        return entityManager.find(CommentReplyVo.class, id);
    }

    @Override
    public void createReply(CommentReplyVo commentReply) {
        entityManager.persist(commentReply);
    }

    @Override
    public void updateReply(CommentReplyVo commentReply) {
        entityManager.merge(commentReply);
    }

    @Override
    public void deleteReply(Integer id) {
        entityManager.remove(getReplyById(id));
    }

    @Override
    public int deleteRepliesByCommentId(Integer id) {
        String hql = "DELETE FROM CommentReplyVo WHERE courseCommentId = :courseCommentId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("courseCommentId", id);
        int deletedCount = query.executeUpdate();
        return deletedCount;
    }
}
