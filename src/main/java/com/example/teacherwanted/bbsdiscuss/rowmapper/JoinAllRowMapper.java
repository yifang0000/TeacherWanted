package com.example.teacherwanted.bbsdiscuss.rowmapper;

import com.example.teacherwanted.active.model.Member;
import com.example.teacherwanted.bbsdiscuss.constant.BbsCategory;
import com.example.teacherwanted.bbsdiscuss.constant.BbsTagCategory;
import com.example.teacherwanted.bbsdiscuss.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinAllRowMapper implements RowMapper<JoinAll> {

    @Override
    public JoinAll mapRow(ResultSet rs, int rowNum) throws SQLException {
        JoinAll joinAll = new JoinAll();

        FavoriteArticle favoriteArticle = new FavoriteArticle();
        favoriteArticle.setFavoriteArticleId(rs.getInt("favorite_article_id"));
        favoriteArticle.setBbsPostId(rs.getInt("bbs_post_id"));
        favoriteArticle.setMemId(rs.getInt("mem_id"));
        favoriteArticle.setCreateTime(rs.getTimestamp("create_time"));
        favoriteArticle.setFavStatus(rs.getInt("fav_status"));


        PostReaction postReaction = new PostReaction();
        postReaction.setPostReactionId(rs.getInt("post_reaction_id"));
        postReaction.setBbsPostId(rs.getInt("bbs_post_id"));
        postReaction.setMemId(rs.getInt("mem_id"));
        postReaction.setReactionStatus(rs.getInt("reaction_status"));


        BbsTag bbsTag = new BbsTag();
        bbsTag.setBbsTagName(rs.getString("bbs_tag_name"));

        String bbsTagStr = rs.getString("bbs_tag");
        BbsTagCategory bbsTagCategory = BbsTagCategory.valueOf(bbsTagStr);
        bbsTag.setBbsTag(bbsTagCategory);


        BbsPost bbsPost = new BbsPost();
        bbsPost.setBbsPostId(rs.getInt("bbs_post_id"));
        bbsPost.setMemId(rs.getInt("mem_id"));
        bbsPost.setBbsCategoryName(rs.getString("bbs_category_name"));
        bbsPost.setBbsTagName(rs.getString("bbs_tag_name"));
        bbsPost.setPostTitle(rs.getString("post_title"));
        bbsPost.setPostContent(rs.getString("post_content"));
        bbsPost.setCreateTime(rs.getTimestamp("create_time"));
        bbsPost.setUpdateTime(rs.getTimestamp("update_time"));
        bbsPost.setPostViews(rs.getInt("post_views"));
        bbsPost.setPostLikes(rs.getInt("post_likes"));
        bbsPost.setPostDislikes(rs.getInt("post_dislikes"));
        bbsPost.setPostStatus(rs.getInt("post_status"));



        BbsComment bbsComment = new BbsComment();
        bbsComment.setBbsCommentId(rs.getInt("bbs_comment_id"));
        bbsComment.setBbsPostId(rs.getInt("bbs_post_id"));
        bbsComment.setMemId(rs.getInt("mem_id"));
        bbsComment.setCommentContent(rs.getString("comment_content"));
        bbsComment.setCreateTime(rs.getTimestamp("create_time"));
        bbsComment.setUpdateTime(rs.getTimestamp("update_time"));
        bbsComment.setCommentStatus(rs.getInt("comment_status"));


        Bbs bbs = new Bbs();
        bbs.setBbsCategoryName(rs.getString("bbs_category_name"));


        String bbsStr = rs.getString("bbs_category_title");
        BbsCategory bbsCategory = BbsCategory.valueOf(bbsStr);
        bbs.setBbsCategoryTitle(bbsCategory);

        Member member = new Member();
        member.setMemId(rs.getInt("mem_id"));
        member.setMemAccount(rs.getString("mem_account"));
        member.setMemPassword(rs.getString("mem_password"));
        member.setMemName(rs.getString("mem_name"));
        member.setMemPhone(rs.getString("mem_phone"));
        member.setMemNickname(rs.getString("mem_nickname"));
        member.setMemBirthday(rs.getTimestamp("mem_birthday"));
        member.setMemGender(rs.getInt("mem_gender"));
        member.setMemEmail(rs.getString("mem_email"));
        member.setMailVerify(rs.getInt("mail_verify"));
        member.setMemLocation(rs.getString("mem_location"));
        member.setMemPhoto(rs.getBytes("mem_photo"));
        member.setInterest1(rs.getInt("interest1"));
        member.setInterest2(rs.getInt("interest2"));
        member.setInterest3(rs.getInt("interest3"));
        member.setCreateTime(rs.getTimestamp("create_time"));
        member.setUpdateTime(rs.getTimestamp("update_time"));
        member.setMemStatus(rs.getInt("mem_status"));




        return new JoinAll(favoriteArticle, postReaction, bbsTag, bbsPost, bbsComment, bbs, member);
    }


}
