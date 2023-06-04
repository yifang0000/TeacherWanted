package com.example.teacherwanted.inboxmail.dao;

import com.example.teacherwanted.inboxmail.model.Inboxmail;

import java.security.Timestamp;
import java.util.List;

public interface InBoxMailDao {
    //    新增使用者
    public int insert(Inboxmail inboxmail);
    //    刪除
    public int deleteBymailId(Integer mailId);
    //    修改
    public int updateBymailId(Integer mailId);
    //    查詢全部使用者
    public int updateStatus(Integer mailId);
    //    已讀未讀狀態
    public Inboxmail selectBymailId(Integer mailId);
    //    顯示所有站內信
    public List<Inboxmail> selectAll();

}




