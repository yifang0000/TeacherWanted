package com.example.teacherwanted.bbsdiscuss.dao.impl;

import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.bbsdiscuss.dao.BbsPostDao;
import com.example.teacherwanted.bbsdiscuss.dto.*;
import com.example.teacherwanted.bbsdiscuss.model.*;
import com.example.teacherwanted.bbsdiscuss.rowmapper.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BbsPostDaoImpl implements BbsPostDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private EntityManager entityManager;



    //   依據memId查找會員資料-回傳memName.memPhoto.memAccount-(參考)- post.html or bsdiscusspo.html
    @Override
    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer memId) {
        TypedQuery<ActiveOrderDetail> query = entityManager.createQuery(
                "FROM ActiveOrderDetail WHERE memId = :memId",
                ActiveOrderDetail.class);
        query.setParameter("memId", memId);
        return query.getResultList();

    }
//依文章id取得會員資料  -回傳 Member物件 - post.html的發文者
    @Override
    public MemberActive getMemById(Integer bbsPostId) {
        String sql = "SELECT m.mem_id, m.mem_account, m.mem_password, m.mem_name, m.mem_phone, m.mem_nickname, m.mem_birthday, " +
                "                m.mem_gender, m.mem_email, m.mail_verify, m.mem_location, m.mem_photo, m.interest1, m.interest2, m.interest3, m.create_time , " +
                "                m.update_time, m.mem_status " +
                "                FROM Member m JOIN BBS_POST bp ON m.mem_id = bp.mem_id " +
                "                WHERE bp.bbs_post_id = :bbsPostId ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<MemberActive> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        if(memberList.size() > 0){
            return memberList.get(0);
        }else {
            return null;
        }
    }
    //依文章id，取得留言數據 - post.html
    @Override
    public List<BbsComment> getCommById(Integer bbsPostId) {
        String sql = "SELECT bbs_comment_id, bbs_post_id, mem_id, comment_content, create_time, update_time," +
                " comment_status " +
                "FROM BBS_COMMENT " +
                "WHERE bbs_post_id = :bbsPostId AND comment_status = 1 " +
                "ORDER BY bbs_comment_id DESC";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);

        List<BbsComment> bbsCommentList = namedParameterJdbcTemplate.query(sql, map, new BbsCommentRowMapper());
        if(bbsCommentList.size() > 0){
            return bbsCommentList;
        }else {
            return null;
        }
    }
    //依文章id取得收藏數據  -我收藏的文章 - heart.html - post.html
    @Override
    public FavoriteArticle geFavById(Integer bbsPostId) {
        String sql = "SELECT f.favorite_article_id, f.bbs_post_id, f.mem_id, f.create_time, f.fav_status ," +
                " bp.post_title ,bp.post_content " +
                " FROM FAVORITE_ARTICLE f JOIN BBS_POST bp ON f.bbs_post_id = bp.bbs_post_id " +
                " WHERE bp.bbs_post_id = :bbsPostId AND post_status = 1 " +
                " ORDER BY f.create_time DESC ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<FavoriteArticle> favoriteArticleList = namedParameterJdbcTemplate.query(sql, map, new FavoriteArticleRowMapper());
        if(favoriteArticleList.size() > 0){
            return favoriteArticleList.get(0);
        }else {
            return null;
        }
    }
    //依文章id取得按讚數據 - post.html
    @Override
    public PostReaction getReactionById(Integer bbsPostId) {
        String sql = "SELECT r.post_reaction_id, r.bbs_post_id, r.mem_id, r.reaction_status " +
                " FROM POST_REACTION r JOIN BBS_POST bp ON r.bbs_post_id = bp.bbs_post_id " +
                " WHERE bp.bbs_post_id = :bbsPostId AND post_status = 1 ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<PostReaction> postReactionList = namedParameterJdbcTemplate.query(sql, map, new PostReactionRowMapper());
        if(postReactionList.size() > 0){
            return postReactionList.get(0);
        }else {
            return null;
        }
    }

    // 根據文章id，取得文章的數據 - index.html
    public BbsPost getBbsPostById(Integer bbsPostId){
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status , post_fav " +
                " FROM BBS_POST WHERE bbs_post_id = :bbsPostId AND post_status = 1 " +
                " ORDER BY update_time DESC ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());
        if(bbsPostList.size() > 0){
            return bbsPostList.get(0);
        }else {
            return null;
        }
    }
    // 根據留言id，取得留言的數據
    @Override
    public BbsComment getBbsCommById(Integer bbsCommentId) {
        String sql = "SELECT bbs_comment_id, bbs_post_id, mem_id, comment_content, create_time, update_time," +
                " comment_status " +
                " FROM BBS_COMMENT WHERE bbs_comment_id = :bbsCommentId AND comment_status = 1 " +
                " ORDER BY bbs_comment_id DESC ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsCommentId", bbsCommentId);
        List<BbsComment> bbsCommentList = namedParameterJdbcTemplate.query(sql, map, new BbsCommentRowMapper());
        if(bbsCommentList.size() > 0){
            return bbsCommentList.get(0);
        }else {
            return null;
        }
    }

    //  根據留言id取得，大頭貼 - post.html的留言區
    @Override
    public MemberActive getBbsCommInfoById(Integer bbsCommentId) {
        String sql = "SELECT m.mem_id, m.mem_account, m.mem_password, m.mem_name, m.mem_phone, m.mem_nickname, m.mem_birthday," +
                " m.mem_gender, m.mem_email, m.mail_verify, m.mem_location, m.mem_photo, m.interest1, m.interest2, m.interest3," +
                " m.create_time, m.update_time, m.mem_status " +
                " FROM MEMBER m JOIN BBS_COMMENT bc ON  m.mem_id = bc.mem_id " +
                " WHERE bbs_comment_id = :bbsCommentId";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsCommentId", bbsCommentId);

        List<MemberActive> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        if(memberList.size() > 0){
            return memberList.get(0);
        }else {
            return null;
        }
    }

    // 發文紀錄-根據會員編號取得該會員的所有發文數據
    @Override
    public List<BbsPost> getBbsPostBymemId(Integer memId) {
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status, post_fav " +
                " FROM BBS_POST " +
                " WHERE mem_id = :memId AND post_status = 1 " +
                " ORDER BY update_time DESC ";
        Map<String, Object> map = new HashMap<>();
        map.put("memId", memId);

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());
        if(bbsPostList.size() > 0){
            return bbsPostList;
        }else {
            return null;
        }
    }
    //語言討論版-顯示所有文章-無須登入
    @Override
    public List<BbsPost> getBbsPostsByKblg(String bbsCategoryName) {
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                " FROM BBS_POST " +
                " WHERE bbs_category_name = :bbsCategoryName AND post_status = 1 " +
                " ORDER BY update_time DESC";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsCategoryName", bbsCategoryName);

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());
        if(bbsPostList.size() > 0){
            return bbsPostList;
        }else {
            return null;
        }
    }

    // 論壇首頁-顯示所有文章
    @Override
    public List<BbsPost> getBbsPosts() {
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status , post_fav " +
                " FROM BBS_POST " +
                " WHERE post_status = 1 " +
                " ORDER BY create_time DESC";
        Map<String, Object> map = new HashMap<>();

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());

        if(bbsPostList.size() > 0){
            return bbsPostList;
        }else {
            return null;
        }
    }
//新增文章
    @Override
    public Integer createBbsPost(BbsPostRequest bbsPostRequest) {

        System.out.println(
                bbsPostRequest
        );
            String sql = "INSERT INTO BBS_POST( mem_id ,bbs_category_name , bbs_tag_name,  post_title , post_content , create_time , update_time" +
                    " ) " +
                    " VALUES ( :memId, :bbsCategoryName, :bbsTagName, :postTitle, :postContent, " +
                    " :createTime, :updateTime)";
        Map<String, Object> map = new HashMap<>();
            map.put("memId", bbsPostRequest.getMemId());
            map.put("bbsCategoryName",bbsPostRequest.getBbsCategoryName());
            map.put("bbsTagName", bbsPostRequest.getBbsCategoryName());
            map.put("postTitle", bbsPostRequest.getPostTitle());
            map.put("postContent", bbsPostRequest.getPostContent());


            Date now = new Date();
            map.put("createTime", now);
            map.put("updateTime", now);

            //儲存資料庫自動生成的id
            KeyHolder keyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

            int bbsPostId = keyHolder.getKey().intValue();

            return  bbsPostId;
    }
    //新增留言
    @Override
    public Integer createBbsComm(BbsCommentRequest bbsCommentRequest) {
        System.out.println(
                bbsCommentRequest
        );
        String sql = "INSERT INTO BBS_COMMENT( bbs_post_id , mem_id , comment_content,  create_time , update_time   ) " +
                " VALUES ( :bbsPostId, :memId, :commentContent, :createTime, :updateTime )";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsCommentRequest.getBbsPostId());
        map.put("memId",bbsCommentRequest.getMemId());
        map.put("commentContent", bbsCommentRequest.getCommentContent());

        Date now = new Date();
        map.put("createTime", now);
        map.put("updateTime", now);

        //儲存資料庫自動生成的id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int bbsCommentId = keyHolder.getKey().intValue();

        return  bbsCommentId;
    }

    //新增我的最愛
    @Override
    public Integer createBbsPostFav(BbsPostRequest bbsPostRequest) {
        String sql = "INSERT INTO FAVORITE_ARTICLE ( bbs_post_id, mem_id, create_time, fav_status," +
                "VALUES ( :bbsPostId, :memId, :createTime, :favStatus ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostRequest.getMemId());
        map.put("memId",bbsPostRequest.getBbsCategoryName());

        Date now = new Date();
        map.put("createTime", now);


        //儲存資料庫自動生成的id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int favoriteArticleId = keyHolder.getKey().intValue();

        return  favoriteArticleId;
    }
//新增按讚
    @Override
    public Integer createPostReaction(PostReactionRequest postReactionRequest) {

        String sql = "INSERT INTO post_reaction ( bbs_post_id, mem_id, reaction_status) " +
                "VALUES ( :bbsPostId, :memId, :reactionStatus) ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", postReactionRequest.getBbsPostId());
        map.put("memId", postReactionRequest.getMemId());
        map.put("reactionStatus",postReactionRequest.getReactionStatus());

        //儲存資料庫自動生成的id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int postReactionId = keyHolder.getKey().intValue();

        return  postReactionId;
    }


//   更新文章收藏
    public Integer updateBbsPostFav(int postId, int postFav) {

        String sql = "UPDATE bbs_post SET post_fav =:postFav " +
                    "WHERE bbs_post_id = :bbsPostId ";

            Map<String, Object> uMap = new HashMap<>();
            uMap.put("postFav",postFav);
            uMap.put("bbsPostId",postId);

        return namedParameterJdbcTemplate.update(sql,uMap);

    }
    //更新文章按讚
    @Override
    public Integer updateBbsPostReaction(PostReactionRequest postReactionRequest,int reactionNum) {

        String sql = "" ;

        Map<String, Object> uMap = new HashMap<>();

        if (postReactionRequest.getReactionStatus() == 2) {
//         倒讚
            sql = "UPDATE bbs_post SET post_dislikes =:postDislikes " +
                    "WHERE bbs_post_id = :bbsPostId ";

            uMap.put("postDislikes",reactionNum);
            uMap.put("bbsPostId",postReactionRequest.getBbsPostId());

        }

        if (postReactionRequest.getReactionStatus() == 1) {
//          案讚
            sql = "UPDATE bbs_post SET post_likes =:postLikes " +
                    "WHERE bbs_post_id = :bbsPostId ";

            uMap.put("postLikes",reactionNum);
            uMap.put("bbsPostId",postReactionRequest.getBbsPostId());

        }

        return namedParameterJdbcTemplate.update(sql,uMap);
    }


    //新增收藏資料
    @Override
    public int createBbsPostFavArt(FavoriterArticleRequest favoriterArticleRequest) {
        //        select favorite_article_id  from FAVORITE_ARTICLE where bbs_post_id = 2 and mem_id = 4;

        String selectSql = "SELECT favorite_article_id  as result FROM FAVORITE_ARTICLE " +
                "WHERE bbs_post_id = :bbsPostId and mem_id = :memId order by create_time desc";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", favoriterArticleRequest.getBbsPostId());
        map.put("memId", favoriterArticleRequest.getMemId());

        List<FavAndReactionCount> data = namedParameterJdbcTemplate.query(selectSql, map, new FavAndReactionCountRowMapper());

        if (data.size() > 0) {
            // 從列表中取得第一個 FavAndReactionCount 物件
            FavAndReactionCount favAndReactionCount = data.get(0);
            // 獲取結果數字
            int id= favAndReactionCount.getResult();
//            select favorite_article_id  from FAVORITE_ARTICLE where bbs_post_id = 2 and mem_id = 9 order by create_time desc;

            String sql = "UPDATE FAVORITE_ARTICLE SET fav_status =:favStatus " +
                         "WHERE bbs_post_id = :bbsPostId and   mem_id =:memId order by create_time desc";

            Map<String, Object> uMap = new HashMap<>();

            uMap.put("favStatus",favoriterArticleRequest.getFavStatus());
            uMap.put("bbsPostId",favoriterArticleRequest.getBbsPostId());
            uMap.put("memId",favoriterArticleRequest.getMemId());

            namedParameterJdbcTemplate.update(sql,uMap);

            return  id;

        } else{

            //        ==================================
            String sql = "INSERT INTO FAVORITE_ARTICLE (bbs_post_id, mem_id, create_time, fav_status) "
                    + "VALUES (:bbsPostId, :memId, :createTime, :favStatus)";
//        select count(1)  as result from FAVORITE_ARTICLE where bbs_post_id = 1 and fav_status = 1;

            Map<String, Object> iMap = new HashMap<>();

            iMap.put("bbsPostId", favoriterArticleRequest.getBbsPostId());
            iMap.put("memId", favoriterArticleRequest.getMemId());
            iMap.put("createTime", new Date());
            iMap.put("favStatus", favoriterArticleRequest.getFavStatus());

            //儲存資料庫自動生成的id
            KeyHolder keyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(iMap), keyHolder);

            int result = keyHolder.getKey().intValue();

            return  result;
        }

    }
    //依據文章id 跟 status 取得 按讚數字 ( 1有按讚 )
    @Override
    public int getFavoriteCountById(FavoriterArticleRequest favoriterArticleRequest) {


        String sql = "SELECT count(1)  as result FROM FAVORITE_ARTICLE " +
                "WHERE bbs_post_id = :bbsPostId and fav_status = :favStatus;";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", favoriterArticleRequest.getBbsPostId());
        map.put("favStatus", 1);

        List<FavAndReactionCount> favAndReactionCountList = namedParameterJdbcTemplate.query(sql, map, new FavAndReactionCountRowMapper());


        if (favAndReactionCountList.size() > 0) {
            // 從列表中取得第一個 FavAndReactionCount 物件
            FavAndReactionCount favAndReactionCount = favAndReactionCountList.get(0);
            // 獲取結果數字
            int favoriteCount = favAndReactionCount.getResult();
            // 返回數字
            return favoriteCount;
        }

        // 預設返回 0，表示無按讚數字
        return 0;
    }

    @Override
    public int getReactionCountById(PostReactionRequest postReactionRequest) {
        String sql = "SELECT count(1)  as result FROM post_reaction " +
                "WHERE bbs_post_id = :bbsPostId and reaction_status = :reactionStatus;";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", postReactionRequest.getBbsPostId());
        map.put("reactionStatus", postReactionRequest.getReactionStatus());

        List<FavAndReactionCount> favAndReactionCountList = namedParameterJdbcTemplate.query(sql, map, new FavAndReactionCountRowMapper());


        if (favAndReactionCountList.size() > 0) {
            // 從列表中取得第一個 FavAndReactionCount 物件
            FavAndReactionCount reactionCountCount = favAndReactionCountList.get(0);
            // 獲取結果數字
            int reactionCount = reactionCountCount.getResult();
            // 返回數字
            return reactionCount;
        }

        // 預設返回 0，表示無按讚數字
        return 0;
    }

    //依據文章id 跟 status 取得 按讚數字 ( 0沒按讚 )
    @Override
    public int getNoLikedCountById(PostReactionRequest postReactionRequest) {
        String sql = "SELECT count(1)  as result FROM POST_REACTION " +
                "WHERE bbs_post_id = :bbsPostId and reaction_status = 0;";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", postReactionRequest.getBbsPostId());

        List<FavAndReactionCount> favAndReactionCountList = namedParameterJdbcTemplate.query(sql, map, new FavAndReactionCountRowMapper());

        if(favAndReactionCountList.size() > 0){
            return 0;
        }else {
            return 0;
        }
    }
    //依據文章id 跟 status 取得 按讚數字 ( 1有按讚 )
    @Override
    public int getUpLikedCountById(PostReactionRequest postReactionRequest) {
        String sql = "SELECT count(1)  as result FROM POST_REACTION " +
                "WHERE bbs_post_id = :bbsPostId and reaction_status = 1;";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", postReactionRequest.getBbsPostId());

        List<FavAndReactionCount> favAndReactionCountList = namedParameterJdbcTemplate.query(sql, map, new FavAndReactionCountRowMapper());

        if(favAndReactionCountList.size() > 0){
            return 0;
        }else {
            return 0;
        }
    }
    //依據文章id 跟 status 取得 按讚數字 ( 2倒讚 )
    @Override
    public int getDownLikedCountById(PostReactionRequest postReactionRequest) {
        String sql = "SELECT count(1)  as result FROM POST_REACTION " +
                "WHERE bbs_post_id = :bbsPostId and reaction_status = 2;";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", postReactionRequest.getBbsPostId());

        List<FavAndReactionCount> favAndReactionCountList = namedParameterJdbcTemplate.query(sql, map, new FavAndReactionCountRowMapper());

        if(favAndReactionCountList.size() > 0){
            return 0;
        }else {
            return 0;
        }
    }
//修改文章標題
    @Override
    public void updateBbsPostTitle(Integer postId, BbsPostUpdateTitle bbsPostUpdateTitle) {
        String sql = "UPDATE BBS_POST SET post_title = :postTitle , update_time = :updateTime" +
                " WHERE bbs_post_id = :postId ";

        Map<String, Object> map = new HashMap<>();

        map.put("postId",postId);
        map.put("postTitle",bbsPostUpdateTitle.getPostTitle());

        map.put("updateTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
    //修改文章內容
    @Override
    public void updateBbsPostContent(Integer postId, BbsPostUpdateContent bbsPostUpdateContent) {
        String sql = "UPDATE BBS_POST SET post_content = :postContent , update_time = :updateTime" +
                " WHERE bbs_post_id = :postId ";

        Map<String, Object> map = new HashMap<>();

        map.put("postId",postId);
        map.put("postContent",bbsPostUpdateContent.getPostContent());

        map.put("updateTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
    //修改留言內容
    @Override
    public void updateComm(Integer commId, BbsCommUpdate bbsCommUpdate) {
        String sql = "UPDATE BBS_COMMENT SET comment_content = :commentContent , update_time = :updateTime" +
                " WHERE bbs_comment_id = :bbsCommentId ";

        Map<String, Object> map = new HashMap<>();

        map.put("bbsCommentId",commId);
        map.put("commentContent",bbsCommUpdate.getCommentContent());

        map.put("updateTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
    //修改文章狀態為 0 (隱藏)  , 原本預設 1 (發布)
    @Override
    public void updateBbsPostStatus(Integer postId, BbsPostUpdateStatus bbsPostUpdateStatus) {
        String sql = "UPDATE BBS_POST SET post_status = 0 , update_time = :updateTime" +
                " WHERE bbs_post_id = :postId ";

        Map<String, Object> map = new HashMap<>();

        map.put("postId",postId);

        map.put("updateTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
    //修改留言狀態為 0 (隱藏)  , 原本預設 1 (發布)
    @Override
    public void updateBbsCommStatus(Integer commId, BbsCommUpdateStatus bbsCommUpdateStatus) {
        String sql = "UPDATE BBS_COMMENT SET comment_status = 0 , update_time = :updateTime" +
                " WHERE bbs_comment_id = :commId ";

        Map<String, Object> map = new HashMap<>();

        map.put("commId",commId);

        map.put("updateTime",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }
}
