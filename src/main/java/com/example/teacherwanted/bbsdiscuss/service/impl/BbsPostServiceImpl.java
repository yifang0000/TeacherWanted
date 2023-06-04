package com.example.teacherwanted.bbsdiscuss.service.impl;

import com.example.teacherwanted.active.dao.MemberDaoActive;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.bbsdiscuss.dao.BbsPostDao;
import com.example.teacherwanted.bbsdiscuss.dto.BbsPostRequest;
import com.example.teacherwanted.bbsdiscuss.dto.FavoriterArticleRequest;
import com.example.teacherwanted.bbsdiscuss.dto.PostReactionRequest;
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
    private MemberDaoActive memberDaoActive;



    //查找會員資料-編輯文章用
    @Override
    public MemberActive selectMemBerOrderInfo(Integer id) {
        MemberActive member = memberDaoActive.selectById(id);
        MemberActive memberInfo = new MemberActive();
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
    public MemberActive getMemById(Integer bbsPostId) {
        MemberActive member = bbsPostDao.getMemById(bbsPostId);
        MemberActive memberInfo = new MemberActive();
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
        if(favoriteArticle == null){
            favoriteArticleInfo.setFavStatus(0);
        }else{
            favoriteArticleInfo.setFavStatus(favoriteArticle.getFavStatus());
        }

        return favoriteArticleInfo;
    }
    //  根據文章id取得文章，按讚狀態
    @Override
    public PostReaction getReactionById(Integer bbsPostId) {
        PostReaction postReaction = bbsPostDao.getReactionById(bbsPostId);
        PostReaction postReactionInfo = new PostReaction();
        if( (postReaction) == null){
            postReactionInfo.setReactionStatus(0);
        }else{
            postReactionInfo.setReactionStatus(postReaction.getReactionStatus());
        }
        return postReactionInfo;
    }

    // 根據文章編號取得文章的數據
    @Override
    public BbsPost getBbsPostById(Integer bbsPostId) {
        return bbsPostDao.getBbsPostById(bbsPostId);
    }
    //  根據留言id取得，大頭貼
    @Override
    public MemberActive getBbsCommInfoById(Integer bbsCommentId) {
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
    //新增收藏資料
    @Override
    public int createBbsPostFavArt(FavoriterArticleRequest favoriterArticleRequest) {
        return bbsPostDao.createBbsPostFavArt(favoriterArticleRequest);

    }
    //依據文章id 跟 status ，取得 收藏數字 (0沒收藏; 1有收藏)
    @Override
    public int getBbsPostFavArtById(FavoriterArticleRequest favoriterArticleRequest) {
//        favStatus  = 0 ,1   (0沒收藏; 1有收藏)
        return bbsPostDao.getFavoriteCountById(favoriterArticleRequest);
    }
    //新增讚/倒讚資料
    @Override
    public int createPostReaction(PostReactionRequest postReactionRequest) {
        return 0;
    }
    //依據文章id 跟 status 取得 按讚數字 (0沒按讚; 1有按讚; 2倒讚)
    @Override
    public int getPostReactionById(PostReactionRequest postReactionRequest) {
        return 0;
    }
}
