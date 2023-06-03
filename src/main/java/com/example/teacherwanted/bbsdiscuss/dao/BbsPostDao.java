package com.example.teacherwanted.bbsdiscuss.dao;

import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.bbsdiscuss.dto.BbsPostRequest;
import com.example.teacherwanted.bbsdiscuss.dto.Response;
import com.example.teacherwanted.bbsdiscuss.model.*;

import java.util.List;

public interface BbsPostDao {


    //查找會員資料-編輯文章用
    List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id);

    //  根據文章id取得，mem大頭貼
    Member getMemById(Integer bbsPostId);
    //  根據文章id取得，留言
    List<BbsComment> getCommById(Integer bbsPostId);
    //  根據文章id取得，收藏狀態
    FavoriteArticle geFavById(Integer bbsPostId);
    //  根據文章id取得，按讚狀態
    PostReaction getReactionById(Integer bbsPostId);
    // 根據文章id，取得文章的數據
    BbsPost getBbsPostById(Integer bbsPostId);
    //  根據留言id取得，大頭貼
    Member getBbsCommInfoById( Integer bbsCommentId);
    // 發文紀錄-根據會員編號取得該會員的所有發文數據
    List<BbsPost> getBbsPostBymemId(Integer memId);
    //語言討論版-顯示所有文章-無須登入
    List<BbsPost> getBbsPostsByKblg(String bbsCategoryName);

    List<BbsPost> getBbsPosts();
    // 新增文章
    Integer createBbsPost(BbsPostRequest bbsPostRequest);


    List<JoinAll> getBbs();
}
