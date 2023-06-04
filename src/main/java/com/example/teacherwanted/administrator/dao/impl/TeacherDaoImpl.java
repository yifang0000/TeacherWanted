package com.example.teacherwanted.administrator.dao.impl;

import com.example.teacherwanted.administrator.dao.TeacherDao;
import com.example.teacherwanted.administrator.model.Administrator;
import com.example.teacherwanted.administrator.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public int insert(Teacher teacher) {
        final String sql = "INSERT INTO TEACHER (tea_id,admin_id,create_time, update_time, tea_name) "
                + "VALUES (:teaId, :adminId, :createTime, :updateTime, :teaName)";


        Query query = entityManager.createNativeQuery(sql)
                .setParameter("adminId", teacher.getAdminId())
                .setParameter("teaId", teacher.getAdminId())
                .setParameter("teaName", teacher.getTeaName())
                .setParameter("createTime", new Date())
                .setParameter("updateTime", new Date());
        return query.executeUpdate();
        //        entityManager.persist(administrator);
//        return 1;
    }

    @Override
    public int deleteByAdminId(Integer adminId) {
        final String sql = "DELETE FROM TEACHER WHERE admin_id = :adminId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("adminId", adminId);
        return query.executeUpdate();
    }


    @Override
    public int updateByAdminId(Teacher teacher) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Teacher SET ");
        final String teaProfile = teacher.getTeaProfile();
        final byte[] teaPhoto = teacher.getTeaPhoto();
        final String teaLocation = teacher.getTeaLocation();

        final Integer teachingSubject1 = teacher.getTeachingSubject1();
        final Integer teachingSubject2 = teacher.getTeachingSubject2();
        final Integer teachingSubject3 = teacher.getTeachingSubject3();
        final String bankCode = teacher.getBankCode();
        final String bankAccount = teacher.getBankAccount();
        final String teaName = teacher.getTeaName();

        if (teaProfile != null && !teaProfile.isEmpty()) {
            hql.append("teaProfile = :teaProfile, ");
        }
        if (teaPhoto != null) {
            hql.append("teaPhoto = :teaPhoto, ");
        }
        if (teaLocation != null && !teaLocation.isEmpty()) {
            hql.append("teaLocation = :teaLocation, ");
        }
        if (teachingSubject1 != null) {
            hql.append("teachingSubject1 = :teachingSubject1, ");
        }
        if (teachingSubject2 != null) {
            hql.append("teachingSubject2 = :teachingSubject2, ");
        }
        if (teachingSubject3 != null) {
            hql.append("teachingSubject3 = :teachingSubject3, ");
        }
        if (bankCode != null && !bankCode.isEmpty()) {
            hql.append("bankCode = :bankCode, ");
        }
        if (bankAccount != null && !bankAccount.isEmpty()) {
            hql.append("bankAccount = :bankAccount, ");
        }
        if (teaName != null && !teaName.isEmpty()) {
            hql.append("teaName = :teaName, ");
        }
        hql.append("updateTime = :updateTime ")
                .append("WHERE adminId = :adminId");

        Query query = entityManager.createQuery(hql.toString());

        if (teaProfile != null && !teaProfile.isEmpty()) {
            query.setParameter("teaProfile", teaProfile);
        }
        if (teaPhoto != null) {
            query.setParameter("teaPhoto", teaPhoto);
        }
        if (teaLocation != null && !teaLocation.isEmpty()) {
            query.setParameter("teaLocation", teaLocation);
        }
        if (teachingSubject1 != null) {
            query.setParameter("teachingSubject1", teachingSubject1);
        }
        if (teachingSubject2 != null) {
            query.setParameter("teachingSubject2", teachingSubject2);
        }
        if (teachingSubject3 != null) {
            query.setParameter("teachingSubject3", teachingSubject3);
        }
        if (bankCode != null && !bankCode.isEmpty()) {
            query.setParameter("bankCode", bankCode);
        }
        if (bankAccount != null && !bankAccount.isEmpty()) {
            query.setParameter("bankAccount", bankAccount);
        }
        if (teaName != null && !teaName.isEmpty()) {
            query.setParameter("teaName", teaName);
        }
        query.setParameter("updateTime", new Date())
                .setParameter("adminId", teacher.getAdminId())
                .executeUpdate();
        return 1;
    }



    @Override
    public List<Teacher> findAll() {
        final String hql = "FROM Teacher";
        List<Teacher> resultList = entityManager
                .createQuery(hql, Teacher.class)
                .getResultList();
        return resultList;
    }

    @Override
    public Teacher selectByAdminId(Integer adminId) {
        final String sql ="SELECT * FROM Teacher WHERE admin_Id = :adminId";
        List<Teacher> resultList = entityManager.createNativeQuery(sql, Teacher.class)
                .setParameter("adminId", adminId)
                .getResultList();
        if (resultList.size() > 0) {
            System.out.println(resultList.get(0));
            return resultList.get(0);
        } else {
            return null;
        }
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("adminId", adminId);
//        return (Teacher) query.getSingleResult();
    }

}
