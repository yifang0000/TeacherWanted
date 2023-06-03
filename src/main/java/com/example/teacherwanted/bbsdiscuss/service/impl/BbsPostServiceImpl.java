package com.example.teacherwanted.bbsdiscuss.service.impl;

import com.example.teacherwanted.active.dao.MemberDao;
import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.bbsdiscuss.dao.BbsPostDao;
import com.example.teacherwanted.bbsdiscuss.dto.BbsPostRequest;
import com.example.teacherwanted.bbsdiscuss.dto.Response;
import com.example.teacherwanted.bbsdiscuss.model.*;
import com.example.teacherwanted.bbsdiscuss.service.BbsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsPostServiceImpl implements BbsPostService {
    @Autowired
    private BbsPostDao bbsPostDao;
    @Autowired
    private MemberDao memberDao;



    //查找會員資料-編輯文章用
    @Override
    public Member selectMemBerOrderInfo(Integer id) {
        Member member = memberDao.selectById(id);
        Member memberInfo = new Member();
        memberInfo.setMemId(id);
        memberInfo.setMemName(member.getMemName());
        memberInfo.setMemEmail(member.getMemEmail());
        memberInfo.setMemPhone(member.getMemPhone());
        memberInfo.setMemPhoto(member.getMemPhoto());
        memberInfo.setMemAccount(member.getMemAccount());
        return memberInfo;
    }
    //  根據文章id取得文章，大頭貼
    @Override
    public Member getMemById(Integer bbsPostId) {
        Member member = bbsPostDao.getMemById(bbsPostId);
        Member memberInfo = new Member();
        memberInfo.setMemAccount(member.getMemAccount());
        memberInfo.setMemName(member.getMemName());
        memberInfo.setMemPhoto(member.getMemPhoto());
        return memberInfo;
    }
    //  根據文章id取得文章，留言
    @Override
    public List<BbsComment> getCommById(Integer bbsPostId) {
        return bbsPostDao.getCommById(bbsPostId);
    }
    //  根據文章id取得文章，收藏狀態
    @Override
    public FavoriteArticle geFavById(Integer bbsPostId) {
        FavoriteArticle favoriteArticle = bbsPostDao.geFavById(bbsPostId);
        FavoriteArticle favoriteArticleInfo = new FavoriteArticle();
        favoriteArticleInfo.setFavStatus(favoriteArticle.getFavStatus());
        return favoriteArticleInfo;
    }
    //  根據文章id取得文章，按讚狀態
    @Override
    public PostReaction getReactionById(Integer bbsPostId) {
        PostReaction postReaction = bbsPostDao.getReactionById(bbsPostId);
        PostReaction postReactionInfo = new PostReaction();
        postReactionInfo.setReactionStatus(postReaction.getReactionStatus());
        return postReactionInfo;
    }

    // 根據文章編號取得文章的數據
    @Override
    public BbsPost getBbsPostById(Integer bbsPostId) {
        return bbsPostDao.getBbsPostById(bbsPostId);
    }
    //  根據留言id取得，大頭貼
    @Override
    public Member getBbsCommInfoById(Integer bbsCommentId) {
        return bbsPostDao.getBbsCommInfoById(bbsCommentId);
    }

    // 發文紀錄-根據會員編號取得該會員的所有發文數據
    @Override
    public List<BbsPost> getBbsPostBymemId(Integer memId) {
        return bbsPostDao.getBbsPostBymemId(memId);
    }
    //語言討論版-顯示所有文章-無須登入
    @Override
    public List<BbsPost> getBbsPostsByKblg(String bbsCategoryName) {
        return bbsPostDao.getBbsPostsByKblg(bbsCategoryName);
    }
//    //程式討論版-顯示所有文章-無須登入
//    @Override
//    public List<BbsPost> getBbsPostsByKbpg() {
//        return bbsPostDao.getBbsPostsByKbpg();
//    }
//    //生活討論版-顯示所有文章-無須登入
//    @Override
//    public List<BbsPost> getBbsPostsByKblf() {
//        return bbsPostDao.getBbsPostsByKblf();
//    }
//    //電競討論版-顯示所有文章-無須登入
//    @Override
//    public List<BbsPost> getBbsPostsByKbgm() {
//        return bbsPostDao.getBbsPostsByKbgm();
//    }

    // 論壇首頁-顯示所有文章
    @Override
    public List<BbsPost> getBbsPosts() {
        return bbsPostDao.getBbsPosts();
    }

    // 新增文章
    @Override
    public Integer createBbsPost(BbsPostRequest bbsPostRequest) {
        return bbsPostDao.createBbsPost(bbsPostRequest);
    }


    @Override
    public List<JoinAll> getBbs() {
        return bbsPostDao.getBbs();
    }
}
