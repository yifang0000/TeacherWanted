package com.example.teacherwanted.wish.repo;

import com.example.teacherwanted.wish.entity.Wish;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public class WishRepositoryImpl implements WishRepository {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countBywishId(Integer wishId) {
        return null;
    }

    @Override
    public Optional<Wish> findByWishId(Integer wishId) {
        return Optional.empty();
    }

    @Override
    public List<Wish> findByMemAccount(String memberAccount) {
        String query = "SELECT w FROM Wish w WHERE w.memAccount = :memberAccount";
        return entityManager.createQuery(query, Wish.class)
                .setParameter("memberAccount", memberAccount)
                .getResultList();
    }

    @Override
    public List<Wish> searchByKeyword(String keyword) {
        String query = "SELECT w FROM Wish w WHERE " +
                "w.wishSubject LIKE CONCAT('%', :keyword, '%') OR " +
                "w.wishLocation LIKE CONCAT('%', :keyword, '%') OR " +
                "w.wishStudents LIKE CONCAT('%', :keyword, '%') OR " +
                "w.wishPeriod LIKE CONCAT('%', :keyword, '%') OR " +
                "w.wishSalary LIKE CONCAT('%', :keyword, '%') OR " +
                "w.wishContent LIKE CONCAT('%', :keyword, '%')";
        return entityManager.createQuery(query, Wish.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    @Override
    public <S extends Wish> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Wish> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Wish> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Wish> findAll() {
        String query = "SELECT w FROM Wish w";
        return entityManager.createQuery(query, Wish.class)
                .getResultList();
    }

    @Override
    public Iterable<Wish> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Wish entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Wish> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
