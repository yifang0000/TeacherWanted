package com.example.teacherwanted.active.controller;

import com.example.teacherwanted.active.dao.ActiveDao;
import com.example.teacherwanted.active.model.Active;
import com.example.teacherwanted.active.service.ActiveService;
import com.example.teacherwanted.active.utils.ActivityDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.List;

@RestController
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @GetMapping("/activeBack")
    public List<Active> selectAllActive() {
        List<Active> activeList = activeService.selectAll();
        return activeList;
    }

    @PostMapping("/activeBackAdd")
    public String insertActive(@RequestBody Active active) {
        return activeService.insert(active);
    }

    @GetMapping("/activeBackEdit")
    public Active selectByIdActive(@RequestParam Integer activityId) {
//        System.out.println(activityId);
        Active activeEdit = activeService.selectById(activityId);
        return activeEdit;
    }

    @PutMapping("/activeBackEdit")
    public String updateActive(@RequestBody Active active) {
//        String updateMeg = activeService.update(active);
//        System.out.println(updateMeg);
//        return updateMeg;
        try {
            String updateMsg = activeService.update(active);
            return updateMsg;
        } catch (Exception e) {
            return "更新失敗：" + e.getMessage();
        }
    }

    @DeleteMapping("/activeBackDelete")
    public String deleteActive(@RequestParam Integer activityId) {
        try {
            activeService.deleteById(activityId);
            return "刪除成功";
        } catch (Exception e) {
            return "刪除失敗:" + e.getMessage();
        }

    }





}
