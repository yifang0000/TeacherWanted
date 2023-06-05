package com.example.teacherwanted.administrator.dao.impl;

import com.example.teacherwanted.administrator.dao.AdministratorDao;
import com.example.teacherwanted.administrator.model.Administrator;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Administrator administrator) {
//        final String sql = "INSERT INTO ADMINISTRATOR (admin_account, admin_password, admin_name, admin_email, admin_phone, permission_id, admin_status, CREATED_DATE, LAST_UPDATED_DATE) "
//                + "VALUES (:adminAccount, :adminPassword, :adminName, :adminEmail, :adminPhone, :permissionId, :adminStatus, :createdDate, :lastUpdatedDate)";
//
////        密碼隨機生成
//        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder randomPassword = new StringBuilder();
//        Random random = new Random();
//
//        for (int i = 0; i < 8; i++) {
//            int index = random.nextInt(characters.length());
//            randomPassword.append(characters.charAt(index));
//        }
//
//
//        String password = randomPassword.toString();
//
//        Query query = entityManager.createNativeQuery(sql)
//                .setParameter("adminAccount", administrator.getAdminAccount())
//                .setParameter("adminPassword", password)
//                .setParameter("adminName", administrator.getAdminName())
//                .setParameter("adminEmail", administrator.getAdminEmail())
//                .setParameter("adminPhone", administrator.getAdminPhone())
//                .setParameter("permissionId", administrator.getPermissionId())
//                .setParameter("adminStatus", 1)
//                .setParameter("createdDate", new Date())
//                .setParameter("lastUpdatedDate", new Date());
//        return query.executeUpdate();
                 entityManager.persist(administrator);
//        System.out.println(administrator.getAdminId());
        Integer NewadminId = administrator.getAdminId();
        return NewadminId;
    }


    @Override
    public int deleteByAdminId(Integer adminId) {
        Administrator administrator = entityManager.find(Administrator.class, adminId);
        entityManager.remove(administrator);
        return 0;
    }

    @Override
    public int updateByAdminId(Administrator administrator) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Administrator SET ");
        final String adminAccount = administrator.getAdminAccount();
        final String adminPassword = administrator.getAdminPassword();
        final String adminName = administrator.getAdminName();
        final String adminEmail = administrator.getAdminEmail();
        final String adminPhone = administrator.getAdminPhone();
        final Integer permissionId = administrator.getPermissionId();
        final Integer adminStatus = administrator.getAdminStatus();

        if (adminAccount != null && !adminAccount.isEmpty()) {
            hql.append("adminAccount = :adminAccount, ");
        }
        if (adminPassword != null && !adminPassword.isEmpty()) {
            hql.append("adminPassword = :adminPassword, ");
        }
        if (adminName != null && !adminName.isEmpty()) {
            hql.append("adminName = :adminName, ");
        }
        if (adminEmail != null && !adminEmail.isEmpty()) {
            hql.append("adminEmail = :adminEmail, ");
        }
        if (adminPhone != null && !adminPhone.isEmpty()) {
            hql.append("adminPhone = :adminPhone, ");
        }
        if (permissionId != null) {
            hql.append("permissionId = :permissionId, ");
        }
        if (adminStatus != null) {
            hql.append("adminStatus = :adminStatus, ");
        }
        hql.append("lastUpdatedDate = :lastUpdatedDate ")
                .append("WHERE adminId = :adminId");

        Query query = entityManager.createQuery(hql.toString());

        if (adminAccount != null && !adminAccount.isEmpty()) {
            query.setParameter("adminAccount", adminAccount);
        }
        if (adminPassword != null && !adminPassword.isEmpty()) {
            query.setParameter("adminPassword", adminPassword);
        }
        if (adminName != null && !adminName.isEmpty()) {
            query.setParameter("adminName", adminName);
        }
        if (adminEmail != null && !adminEmail.isEmpty()) {
            query.setParameter("adminEmail", adminEmail);
        }
        if (adminPhone != null && !adminPhone.isEmpty()) {
            query.setParameter("adminPhone", adminPhone);
        }
        if (permissionId != null) {
            query.setParameter("permissionId", permissionId);
        }
        if (adminStatus != null) {
            query.setParameter("adminStatus", adminStatus);
        }
        query.setParameter("lastUpdatedDate", new Date())
                .setParameter("adminId", administrator.getAdminId())
                .executeUpdate();
        return 1;
    }


    @Override
    public Administrator selectByAdminId(Integer adminId) {
        return entityManager.find(Administrator.class, adminId);
    }

    @Override
    public Administrator selectByAccount(String adminAccount) {
        String sql = "SELECT * FROM ADMINISTRATOR WHERE admin_account = :adminAccount";
        List<Administrator> resultList = entityManager.createNativeQuery(sql, Administrator.class)
                .setParameter("adminAccount", adminAccount)
                .getResultList();
        if (resultList.size() > 0) {
//            System.out.println(resultList.get(0));
            return resultList.get(0);
        } else {
            return null;
        }

    }


    @Override
    public List<Administrator> findAll() {
        final String sql = "select * FROM Administrator WHERE Permission_id = 2";
        List<Administrator> resultList = entityManager.createNativeQuery(sql, Administrator.class)
                .getResultList();
        return resultList;
    }
}

