package com.example.teacherwanted.administrator.dao.impl;

import com.example.teacherwanted.administrator.dao.AdministratorDao;
import com.example.teacherwanted.administrator.model.Administrator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int insert(Administrator administrator) {
        entityManager.persist(administrator);
        return 1;
    }

    @Override
    public int deleteByAdminId(Integer adminId) {
        Administrator administrator = entityManager.find(Administrator.class, adminId);
        entityManager.remove(administrator);
        return 0;
    }

    @Override
    public int updateByAdminId(Administrator administrator) {
        entityManager.merge(administrator);
        return 1;
    }

    @Override
    public Administrator selectByAdminId(Integer adminId) {
        return entityManager.find(Administrator.class, adminId);
    }

    @Override
    public List<Administrator> findAll() {
        final String hql = "FROM Administrator";
        return entityManager
                .createQuery(hql, Administrator.class)
                .getResultList();
    }
}

