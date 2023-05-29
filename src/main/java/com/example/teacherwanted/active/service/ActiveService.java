package com.example.teacherwanted.active.service;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.dao.impl.ActiveDaoImpl;
import com.example.teacherwanted.active.model.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ActiveService {

    List<Active> selectAll(String key, String type);
    List<Active> recommendActivities(String type);
    String insert(Active active);

    String deleteById(Integer id);

    String update(Active active);

    String updateStatus(Active active, Integer status);

    Active selectBackById(Integer id);

    Active selectById(Integer id);


    List<Active> selectBackAll(String key,String type,Integer id);
}
