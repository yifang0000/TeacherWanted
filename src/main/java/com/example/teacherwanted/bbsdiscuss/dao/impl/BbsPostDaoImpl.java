package com.example.teacherwanted.bbsdiscuss.dao.impl;

import com.example.teacherwanted.active.model.ActiveOrderDetail;
import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.bbsdiscuss.dao.BbsPostDao;
import com.example.teacherwanted.bbsdiscuss.dto.BbsPostRequest;
import com.example.teacherwanted.bbsdiscuss.dto.Response;
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



    //   依據memId查找會員資料-回傳memName.memPhoto.memAccount-(參考)-post.html or po.html
    @Override
    public List<ActiveOrderDetail> selectActiveOrderDetailByMemberId(Integer memId) {
        TypedQuery<ActiveOrderDetail> query = entityManager.createQuery(
                "FROM ActiveOrderDetail WHERE memId = :memId",
                ActiveOrderDetail.class);
        query.setParameter("memId", memId);
        return query.getResultList();

    }
//依文章id取得會員資料
    @Override
    public Member getMemById(Integer bbsPostId) {
        String sql = "SELECT m.mem_id, m.mem_account, m.mem_password, m.mem_name, m.mem_phone, m.mem_nickname, m.mem_birthday, " +
                "                m.mem_gender, m.mem_email, m.mail_verify, m.mem_location, m.mem_photo, m.interest1, m.interest2, m.interest3, m.create_time , " +
                "                m.update_time, m.mem_status " +
                "                FROM Member m JOIN BBS_POST bp ON m.mem_id = bp.mem_id " +
                "                WHERE bp.bbs_post_id = :bbsPostId ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
        if(memberList.size() > 0){
            return memberList.get(0);
        }else {
            return null;
        }
    }
    //依文章id，取得留言數據
    @Override
    public List<BbsComment> getCommById(Integer bbsPostId) {
        String sql = "SELECT bbs_comment_id, bbs_post_id, mem_id, comment_content, create_time, update_time," +
                " comment_status " +
                "FROM BBS_COMMENT WHERE bbs_post_id = :bbsPostId";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);

        List<BbsComment> bbsCommentList = namedParameterJdbcTemplate.query(sql, map, new BbsCommentRowMapper());
        if(bbsCommentList.size() > 0){
            return bbsCommentList;
        }else {
            return null;
        }
    }
    //依文章id取得收藏數據
    @Override
    public FavoriteArticle geFavById(Integer bbsPostId) {
        String sql = "SELECT f.favorite_article_id, f.bbs_post_id, f.mem_id, f.create_time, f.fav_status ," +
                " bp.post_title ,bp.post_content " +
                " FROM FAVORITE_ARTICLE f JOIN BBS_POST bp ON f.bbs_post_id = bp.bbs_post_id " +
                " WHERE bp.bbs_post_id = :bbsPostId ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<FavoriteArticle> favoriteArticleList = namedParameterJdbcTemplate.query(sql, map, new FavoriteArticleRowMapper());
        if(favoriteArticleList.size() > 0){
            return favoriteArticleList.get(0);
        }else {
            return null;
        }
    }
    //依文章id取得按讚數據
    @Override
    public PostReaction getReactionById(Integer bbsPostId) {
        String sql = "SELECT r.post_reaction_id, r.bbs_post_id, r.mem_id, r.reaction_status " +
                " FROM POST_REACTION r JOIN BBS_POST bp ON r.bbs_post_id = bp.bbs_post_id " +
                " WHERE bp.bbs_post_id = :bbsPostId ";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<PostReaction> postReactionList = namedParameterJdbcTemplate.query(sql, map, new PostReactionRowMapper());
        if(postReactionList.size() > 0){
            return postReactionList.get(0);
        }else {
            return null;
        }
    }

    // 根據文章id，取得文章的數據
    public BbsPost getBbsPostById(Integer bbsPostId){
        String sql = "SELECT bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content," +
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                "FROM BBS_POST WHERE bbs_post_id = :bbsPostId";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsPostId", bbsPostId);
        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());
        if(bbsPostList.size() > 0){
            return bbsPostList.get(0);
        }else {
            return null;
        }
    }
    //  根據留言id取得，大頭貼
    @Override
    public Member getBbsCommInfoById(Integer bbsCommentId) {
        String sql = "SELECT m.mem_id, m.mem_account, m.mem_password, m.mem_name, m.mem_phone, m.mem_nickname, m.mem_birthday," +
                " m.mem_gender, m.mem_email, m.mail_verify, m.mem_location, m.mem_photo, m.interest1, m.interest2, m.interest3," +
                " m.create_time, m.update_time, m.mem_status " +
                "FROM MEMBER m JOIN BBS_COMMENT bc ON  m.mem_id = bc.mem_id " +
                "WHERE bbs_comment_id = :bbsCommentId";
        Map<String, Object> map = new HashMap<>();
        map.put("bbsCommentId", bbsCommentId);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());
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
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                "FROM BBS_POST WHERE mem_id = :memId";
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
                "FROM BBS_POST WHERE bbs_category_name = :bbsCategoryName";
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
                " create_time, update_time, post_views, post_likes, post_dislikes, post_status " +
                " FROM BBS_POST ";
        Map<String, Object> map = new HashMap<>();

        List<BbsPost> bbsPostList = namedParameterJdbcTemplate.query(sql, map, new BbsPostRowMapper());

        if(bbsPostList.size() > 0){
            return bbsPostList;
        }else {
            return null;
        }
    }

    @Override
    public Integer createBbsPost(BbsPostRequest bbsPostRequest) {

//            String sql = "INSERT INTO BBS_POST(bbs_post_id, mem_id, bbs_category_name, bbs_tag_name, post_title, post_content, create_time, update_time, post_views, post_likes, post_dislikes, post_status) " +
//                    "VALUES (:productName, :category, :imageUrl, :price, :stock, :description," +
//                    ":createdDate, :lastModifiedDate)";
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("productName",productRequest.getProductName());
//            map.put("category", productRequest.getCategory().toString());
//            map.put("imageUrl", productRequest.getImageUrl());
//            map.put("price", productRequest.getPrice());
//            map.put("stock", productRequest.getStock());
//            map.put("description", productRequest.getDescription());
//
//            Date now = new Date();
//            map.put("createdDate", now);
//            map.put("lastModifiedDate", now);
//
//
//
//            //儲存資料庫自動生成的id
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//
//            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
//
//            int productId = keyHolder.getKey().intValue();

            return  null;


    }

    //test
    public List<JoinAll> getBbs() {
        String sql = "SELECT fa.favorite_article_id, fa.bbs_post_id, fa.mem_id, fa.create_time AS favoriteCreateTime, fa.fav_status, " +
                "       pr.post_reaction_id, pr.bbs_post_id AS reactionBbsPostId, pr.mem_id AS reactionMemId, pr.reaction_status, " +
                "       bt.bbs_tag_name, bt.bbs_tag, " +
                "       bp.bbs_post_id, bp.mem_id AS postMemId, bp.bbs_category_id, bp.bbs_tag_id AS postBbsTagId, bp.post_title, bp.post_content, bp.create_time AS postCreateTime, bp.update_time AS postUpdateTime, bp.post_views, bp.post_likes, bp.post_dislikes, bp.post_status, " +
                "       bc.bbs_comment_id, bc.bbs_post_id AS commentBbsPostId, bc.mem_id AS commentMemId, bc.comment_content, bc.create_time AS commentCreateTime, bc.update_time AS commentUpdateTime, bc.comment_status, " +
                "       bb.bbs_category_id, bb.bbs_category_title, " +
                "       m.mem_id, m.mem_name, m.mem_nickname, m.mem_photo" +
                " FROM FAVORITE_ARTICLE fa" +
                "         JOIN POST_REACTION pr ON fa.bbs_post_id = pr.bbs_post_id " +
                "         JOIN BBS_TAG bt ON bt.bbs_tag_id = bp.bbs_tag_id " +
                "         JOIN BBS_POST bp ON fa.bbs_post_id = bp.bbs_post_id " +
                "         JOIN BBS_COMMENT bc ON fa.bbs_post_id = bc.bbs_post_id " +
                "         JOIN BBS bb ON bp.bbs_category_id = bb.bbs_category_id " +
                "         JOIN MEMBER m ON bp.mem_id = m.mem_id;";
        Map<String, Object> map = new HashMap<>();

        List<JoinAll> joinAllList = namedParameterJdbcTemplate.query(sql, map, new JoinAllRowMapper());

        if(joinAllList.size()>0){
            return joinAllList;
        }else{
            return null;
        }
    }
}
