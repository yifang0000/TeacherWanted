package com.example.teacherwanted.bbsdiscuss.service;

import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.bbsdiscuss.dto.BbsCommentRequest;
import com.example.teacherwanted.bbsdiscuss.dto.BbsPostRequest;
import com.example.teacherwanted.bbsdiscuss.dto.FavoriterArticleRequest;
import com.example.teacherwanted.bbsdiscuss.dto.PostReactionRequest;
import com.example.teacherwanted.bbsdiscuss.model.*;

import java.util.List;

public interface BbsPostService {

    //   依據memId查找會員資料-回傳memName.memPhoto.memAccount-(參考)-post.html or bsdiscusspo.html
    MemberActive selectMemBerOrderInfo(Integer id);
    //  根據文章id取得，mem大頭貼
    MemberActive getMemById(Integer bbsPostId);
    //  根據文章id取得，收藏狀態
    FavoriteArticle geFavById(Integer bbsPostId);
    //  根據文章id取得，按讚狀態
    PostReaction getReactionById(Integer bbsPostId);
    // 根據文章id取得文章的數據
    BbsPost getBbsPostById(Integer bbsPostId);
    // 根據留言id，取得留言的數據
    BbsComment getBbsCommById(Integer bbsCommentId);
    //  根據文章id取得，留言
    List<BbsComment> getCommById(Integer bbsPostId);
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


    //新增收藏資料
    int createBbsPostFavArt(FavoriterArticleRequest favoriterArticleRequest);
    //依據文章id 跟 status ，取得 收藏數字 (0沒收藏; 1有收藏)
    int getBbsPostFavArtById(FavoriterArticleRequest favoriterArticleRequest);
    //新增讚/倒讚資料
    int createPostReaction(PostReactionRequest postReactionRequest);
    //依據文章id 跟 status 取得 按讚數字 (0沒按讚; 1有按讚; 2倒讚)

    int getBbsPostReactionById(PostReactionRequest postReactionRequest);
    int getPostReactionById(PostReactionRequest postReactionRequest);

//  更新收藏文章數
    int updateBbsPostFav(int postId, int postFav);

    int updateBbsPostReaction(PostReactionRequest postReactionRequest ,int reactionNum);







}
