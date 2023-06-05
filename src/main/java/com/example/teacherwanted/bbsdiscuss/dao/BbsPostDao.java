package com.example.teacherwanted.bbsdiscuss.dao;

import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.bbsdiscuss.dto.*;
import com.example.teacherwanted.bbsdiscuss.model.BbsComment;
import com.example.teacherwanted.bbsdiscuss.model.BbsPost;
import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.model.PostReaction;

import java.util.List;

public interface BbsPostDao {


    //查找會員資料-編輯文章用
    List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer id);

    //  根據文章id取得，mem大頭貼
    MemberActive getMemById(Integer bbsPostId);
    //  根據文章id取得，留言
    List<BbsComment> getCommById(Integer bbsPostId);
    //  根據文章id取得，收藏狀態
    FavoriteArticle geFavById(Integer bbsPostId);
    //  根據文章id取得，按讚狀態
    PostReaction getReactionById(Integer bbsPostId);
    // 根據文章id，取得文章的數據
    BbsPost getBbsPostById(Integer bbsPostId);
    // 根據留言id，取得留言的數據
    BbsComment getBbsCommById(Integer bbsCommentId);
    //  根據留言id取得，大頭貼
    MemberActive getBbsCommInfoById(Integer bbsCommentId);
    // 發文紀錄-根據會員編號取得該會員的所有發文數據
    List<BbsPost> getBbsPostBymemId(Integer memId);
    //語言討論版-顯示所有文章-無須登入
    List<BbsPost> getBbsPostsByKblg(String bbsCategoryName);

    List<BbsPost> getBbsPosts();
    // 新增文章
    Integer createBbsPost(BbsPostRequest bbsPostRequest);
    // 新增留言
    Integer createBbsComm(BbsCommentRequest bbsCommentRequest);
    //新增我的最愛
    Integer createBbsPostFav(BbsPostRequest bbsPostRequest);
    //新增按讚
    Integer createPostReaction(PostReactionRequest postReactionRequest);

    //更新文章按讚
    Integer updateBbsPostReaction(PostReactionRequest postReactionRequest,int reactionNum);

    Integer updateBbsPostFav(int postId, int postFav);


    //新增收藏資料
    int createBbsPostFavArt(FavoriterArticleRequest favoriterArticleRequest);
    //依據文章id 跟 status ，取得 收藏數字 ( 1有收藏 )( 0沒收藏 )
    int getFavoriteCountById(FavoriterArticleRequest favoriterArticleRequest);

    //依據文章id 跟 status ，取得 收藏數字 ( 1有收藏 )( 0沒收藏 )
    int getReactionCountById(PostReactionRequest postReactionRequest);
    int getNoLikedCountById(PostReactionRequest postReactionRequest);
    //依據文章id 跟 status 取得 按讚數字 ( 1有按讚 )
    int getUpLikedCountById(PostReactionRequest postReactionRequest);
    //依據文章id 跟 status 取得 按讚數字 ( 2倒讚 )
    int getDownLikedCountById(PostReactionRequest postReactionRequest);

    //修改文章標題
    void updateBbsPostTitle (Integer postId, BbsPostUpdateTitle bbsPostUpdateTitle);
    //修改文章內容
    void updateBbsPostContent (Integer postId, BbsPostUpdateContent bbsPostUpdateContent);
    //修改留言內容
    void updateComm (Integer commId, BbsCommUpdate bbsCommUpdate);

    //修改文章狀態為 0 (隱藏)  , 原本預設 1 (發布)
    void updateBbsPostStatus (Integer postId, BbsPostUpdateStatus bbsPostUpdateStatus);

    //修改留言狀態為 0 (隱藏)  , 原本預設 1 (發布)
    void updateBbsCommStatus (Integer commId, BbsCommUpdateStatus bbsCommUpdateStatus);
}
