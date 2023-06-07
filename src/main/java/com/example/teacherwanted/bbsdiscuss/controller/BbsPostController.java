package com.example.teacherwanted.bbsdiscuss.controller;

import com.example.teacherwanted.active.model.MemberActive;
import com.example.teacherwanted.bbsdiscuss.dto.*;
import com.example.teacherwanted.bbsdiscuss.model.BbsComment;
import com.example.teacherwanted.bbsdiscuss.model.BbsPost;
import com.example.teacherwanted.bbsdiscuss.model.FavoriteArticle;
import com.example.teacherwanted.bbsdiscuss.model.PostReaction;
import com.example.teacherwanted.bbsdiscuss.service.BbsPostService;
import com.example.teacherwanted.register_login.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BbsPostController {

    @Autowired
    private BbsPostService bbsPostService;

    //把session傳到前端
    @GetMapping("/bbsdiscussGet/session")
    public Integer sessionToFront(@SessionAttribute(value = "userInfo", required = false) User user){
        if (user.getMemId() == null) {
            return 0;
        } else {
            return user.getMemId();
        }
    }



    //   依據memId查找會員資料-回傳memName.memPhoto.memAccount-(參考)-post.html or bsdiscusspo.html
    @GetMapping("/bbsdiscussGet/memberInfo")
    public ResponseEntity<?> selectByMemId(
            @SessionAttribute(value = "userInfo", required = false) User user) {
//        System.out.println(memId);
        if (user.getMemId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無登入狀態");
        } else {
            MemberActive memberInfo = bbsPostService.selectMemBerOrderInfo(user.getMemId());
            return ResponseEntity.ok(memberInfo);

        }

    }
    //根據 memId 取得 收藏數據 及 按讚數據
    @GetMapping("/bbsdiscussGet/favandreaction")
    public ResponseEntity <ResponseFandR> getFavAndReactionByMemId(@SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test//根據 memid 取得 收藏數據 及 按讚數據");

        List<FavoriteArticle> favoriteArticleList = bbsPostService.geFavByMemId(user.getMemId());
        List<PostReaction> postReactionList = bbsPostService.getRectionByMemId(user.getMemId());
        ResponseFandR responseFandR = new ResponseFandR();
        responseFandR.setFavoriteArticleList(favoriteArticleList);
        responseFandR.setPostReactionList(postReactionList);

        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (responseFandR != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseFandR);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //  根據文章id取得文章，大頭貼，tag，文章分類，收藏狀態，按讚狀態
    @GetMapping("/bbsdiscussGetAll")
    public ResponseEntity<Response> getBbsPostInfoById(@RequestParam(required = false) Integer bbsPostId,
                                                  @SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test查詢一筆文章-根據文章id取得文章，大頭貼，tag，文章分類，收藏狀態，按讚狀態");

        BbsPost bbsPost = bbsPostService.getBbsPostById(bbsPostId);

//        根據文章id取得大頭貼，收藏狀態，按讚狀態
        MemberActive member = bbsPostService.getMemById(bbsPostId);
        List<BbsComment> bbsCommentList = bbsPostService.getCommById(bbsPostId);
        FavoriteArticle favoriteArticle = bbsPostService.geFavById(bbsPostId);
        PostReaction postReaction = bbsPostService.getReactionById(bbsPostId);

//        String bbsPostTag = bbsPostService.getBbsTag(bbsPostId);
//        Response.setTag(bbsPostTag);
//        Response;
        Response response = new Response();
        response.setMemAccount(member.getMemAccount());
        response.setMemName(member.getMemName());
        response.setMemPhoto(member.getMemPhoto());
        response.setFavStatus(favoriteArticle.getFavStatus());
        response.setReaction_status(postReaction.getReactionStatus());
        response.setBbsPostId(bbsPost.getBbsPostId());

        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (bbsPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @GetMapping("/bbsdiscussGet/{bbsPostId}")
    // 根據文章編號取得文章的數據
    public ResponseEntity<BbsPost> getBbsPostById(@PathVariable Integer bbsPostId,
                                                  @SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test查詢一筆文章-根據文章編號取得文章的數據");

        BbsPost bbsPost = bbsPostService.getBbsPostById(bbsPostId);


        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (bbsPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bbsPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //  根據文章id取得，留言數據
    @GetMapping("/bbsdiscussGet/comm")
    public ResponseEntity<List<BbsComment>> getCommById(@RequestParam Integer bbsPostId,
                                                       @SessionAttribute(value = "userInfo", required = false) User user){
        System.out.println("留言數據留言數據test根據文章id取得，留言數據");
        System.out.println("bbsPostId:" +bbsPostId);
        System.out.println(user.getMemId());
        List<BbsComment> bbsCommentList = bbsPostService.getCommById(bbsPostId);

        if (bbsCommentList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bbsCommentList);
        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            BbsComment bbsComment = new BbsComment();
            List<BbsComment> list = new ArrayList<>();
            list.add(bbsComment);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }
    //  根據留言id取得，大頭貼
    @GetMapping("/bbsdiscussGet/commInfo")
    public ResponseEntity <MemberActive> getBbsCommInfoById(@RequestParam(required = false) Integer bbsCommentId,
                                                       @SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test根據留言id取得，大頭貼");
//        根據文章id取得大頭貼，
        MemberActive member = bbsPostService.getBbsCommInfoById(bbsCommentId);

        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (member != null) {
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/bbsdiscussGet/mypost")
    // 發文紀錄-根據會員編號取得該會員的所有發文數據
    public ResponseEntity<List<BbsPost>> getBbsPostBymemId(@SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test發文紀錄-根據會員編號取得文章的數據");
        System.out.println(user.getMemId());
        List<BbsPost> bbsPostList = bbsPostService.getBbsPostBymemId(user.getMemId());
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (bbsPostList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bbsPostList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //討論版-顯示所有文章-無須登入
    @GetMapping("/bbsdiscussGet/lg/{bbsCategoryName}")
    public ResponseEntity<List<BbsPost>> getBbsPostsByKblg(@PathVariable String bbsCategoryName,
                                                           @SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test語言討論版-顯示所有文章-無須登入");
        System.out.println(user.getMemId());
        System.out.println("bbsPostRequest:"+bbsCategoryName);
        List<BbsPost> bbsPostList = bbsPostService.getBbsPostsByKblg(bbsCategoryName);

        if (bbsPostList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bbsPostList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // 論壇首頁-顯示所有文章
    @GetMapping("/bbsdiscussGet")
    public ResponseEntity<List<BbsPost>> getBbsPosts(@SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("test論壇首頁-顯示所有文章-無須登入");
        System.out.println(user.getMemId());
        List<BbsPost> bbsPostList = bbsPostService.getBbsPosts();

        if (bbsPostList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bbsPostList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // 新增文章
    @PostMapping("/bbsdiscussGet/post")
    public ResponseEntity<?> createBbsPost(@RequestBody @Valid BbsPostRequest bbsPostRequest,

                                           @SessionAttribute(value = "userInfo", required = false) User user) {

        System.out.println(bbsPostRequest);
        System.out.println(user.getMemId());
        bbsPostRequest.setMemId(user.getMemId());
        Integer bbsPostId = bbsPostService.createBbsPost(bbsPostRequest);
        BbsPost bbsPost = bbsPostService.getBbsPostById(bbsPostId);

        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bbsPost);
    }
    // 新增留言
    @PostMapping("/bbsdiscussGet/newcomm")
    public ResponseEntity<?> createBbsComm(@RequestBody @Valid BbsCommentRequest bbsCommentRequest,
                                           @SessionAttribute(value = "userInfo", required = false) User user) {
        System.out.println("新增留言有嗎?");
        System.out.println(bbsCommentRequest);
        System.out.println(user.getMemId());
        bbsCommentRequest.setMemId(user.getMemId());

        Integer bbsCommentId = bbsPostService.createBbsComm(bbsCommentRequest);
        BbsComment bbsComment = bbsPostService.getBbsCommById(bbsCommentId);
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bbsComment);
    }
    // 新增我的收藏
    @PostMapping("/bbsdiscussGet/favStstatus")
    public ResponseEntity<?> createBbsPostFavArt(@RequestBody @Valid FavoriterArticleRequest favoriterArticleRequest,
                                           @SessionAttribute(value = "userInfo", required = false) User user) {

        System.out.println(favoriterArticleRequest);
        System.out.println(user.getMemId());
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        favoriterArticleRequest.setMemId(user.getMemId());
        Integer favoriteArticleId = bbsPostService.createBbsPostFavArt(favoriterArticleRequest);


        if(favoriteArticleId == null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
//          取的該篇文有幾個人 收藏
            int favArtNum = bbsPostService.getBbsPostFavArtById(favoriterArticleRequest);

//          更新收藏數
            bbsPostService.updateBbsPostFav(favoriterArticleRequest.getBbsPostId(),favArtNum);

//            updateBbsPostReaction(int postId , int postFav)

            return ResponseEntity.status(HttpStatus.CREATED).body(favArtNum);
        }
    }
    // 新增讚/倒讚
    @PostMapping("/bbsdiscussGet/reactionstatus")
    public ResponseEntity<?> createPostReaction(@RequestBody @Valid PostReactionRequest postReactionRequest,
                                                 @SessionAttribute(value = "userInfo", required = false) User user) {

        System.out.println(postReactionRequest);
        System.out.println(user.getMemId());
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(postReactionRequest);
        System.out.println(user.getMemId());
        postReactionRequest.setMemId(user.getMemId());
        Integer postReactionId = bbsPostService.createPostReaction(postReactionRequest);

        if(postReactionId == null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
//          取的該篇文有幾個人 按讚  status 取得 按讚數字 (1有按讚; 2倒讚)
            int reactionNum = bbsPostService.getBbsPostReactionById(postReactionRequest);

//          更新案讚數 0沒按讚 1有按讚; 2沒按倒讚 3倒讚
            bbsPostService.updateBbsPostReaction(postReactionRequest,reactionNum);

//            updateBbsPostReaction(int postId , int postFav)

            return ResponseEntity.status(HttpStatus.CREATED).body(reactionNum);
        }
    }
 //修改文章標題
    @PutMapping("/bbsdiscussGet/title/{postId}")
    public ResponseEntity<?> updateBbsPostTitle(@PathVariable Integer postId,
                                                @RequestBody @Valid BbsPostUpdateTitle bbsPostUpdateTitle,
                                                @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(postId);
        System.out.println(bbsPostUpdateTitle.getPostTitle());
        //檢查 postId 是否存在
        BbsPost bbsPost = bbsPostService.getBbsPostById(postId);

        if (bbsPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改文章標題
        //更新文章 id 為 postId ,她要修改的參數物件 bbsPostUpdateTitle
        bbsPostService.updateBbsPostTitle(postId, bbsPostUpdateTitle);

        //更新更新後查詢 postId
        BbsPost updatebbspost = bbsPostService.getBbsPostById(postId);
        //返回狀態碼及修改後的值 updatebbspost
        return ResponseEntity.status(HttpStatus.OK).body("文章標題更新成功");
    }

    //修改文章內容
    @PutMapping("/bbsdiscussGet/content/{postId}")
    public ResponseEntity<?> updateBbsPostContent(@PathVariable Integer postId,
                                                  @RequestBody @Valid BbsPostUpdateContent bbsPostUpdateContent,
                                                  @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(postId);
        System.out.println(bbsPostUpdateContent.getPostContent());
        //檢查 postId 是否存在
        BbsPost bbsPost = bbsPostService.getBbsPostById(postId);

        if (bbsPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改文章內容
        //更新文章 id 為 postId ,她要修改的參數物件 bbsPostUpdateContent
        bbsPostService.updateBbsPostContent(postId, bbsPostUpdateContent);

        //更新更新後查詢 postId
        BbsPost updatebbspostcontent = bbsPostService.getBbsPostById(postId);
        //返回狀態碼及修改後的值 updatebbspostcontent
        return ResponseEntity.status(HttpStatus.OK).body("文章內容更新成功");
    }

    //修改留言內容
    @PutMapping("/bbsdiscussGet/commupdate/{commId}")
    public ResponseEntity<?> updateComm(@PathVariable Integer commId,
                                        @RequestBody @Valid BbsCommUpdate bbsCommUpdate,
                                        @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(commId);
        System.out.println(bbsCommUpdate.getCommentContent());
        //檢查 postId 是否存在
        BbsComment bbsComment = bbsPostService.getBbsCommById(commId);

        if (bbsComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改留言內容
        //更新文章 id 為 commId ,她要修改的參數物件 bbsCommUpdate
        bbsPostService.updateComm(commId, bbsCommUpdate);

        //更新更新後查詢 commId
        BbsComment updatebbsComm = bbsPostService.getBbsCommById(commId);
        //返回狀態碼及修改後的值 updatebbsComm
        return ResponseEntity.status(HttpStatus.OK).body("留言更新成功");
    }
    //修改文章狀態為 0 (隱藏)  , 原本預設 1 (發布)
    @PutMapping("/bbsdiscussGet/bbspoststatus/{postId}")
    public ResponseEntity<?> updateBbsPostStatus(@PathVariable Integer postId,
                                                 @RequestBody @Valid BbsPostUpdateStatus bbsPostUpdateStatus,
                                                 @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(postId);
        System.out.println(bbsPostUpdateStatus.getPostStatus());
        //修改文章狀態為 0 (隱藏)
        //更新文章 id 為 postId ,她要修改的參數物件 bbsPostUpdateStatus
        bbsPostService.updateBbsPostStatus(postId, bbsPostUpdateStatus);

        return ResponseEntity.status(HttpStatus.OK).body("文章刪除成功");
    }

    //修改留言狀態為 0 (隱藏)  , 原本預設 1 (發布)
    @PutMapping("/bbsdiscussGet/bbscommstatus")
    public ResponseEntity<?> updateBbsCommStatus(@RequestBody @Valid BbsCommUpdateStatus bbsCommUpdateStatus,
                                                 @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(bbsCommUpdateStatus.getBbsCommentId());
        System.out.println("commId上面是幾號");
        System.out.println(bbsCommUpdateStatus.getCommentStatus());
        //修改留言狀態為 0 (隱藏)
        //更新留言 id 為 commId ,她要修改的參數物件 bbsCommUpdateStatus
        bbsPostService.updateBbsCommStatus(bbsCommUpdateStatus.getBbsCommentId(), bbsCommUpdateStatus);
        return ResponseEntity.status(HttpStatus.OK).body("留言刪除成功");
    }
    //修改收藏狀態為 0 (隱藏)
    @PutMapping("/bbsdiscussGet/bbsfavpagestatus")
    public ResponseEntity<?> updateBbsFavPageStatus(@RequestBody @Valid FavCancelRequest favCancelRequest,
                                                 @SessionAttribute(value = "userInfo", required = false) User user) {
        if (user.getMemId() == null) {
            // 如果未獲取到會員ID，返回相應錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(user.getMemId());
        System.out.println(favCancelRequest.getBbsPostId());
        System.out.println("上面是文章postId");
        //修改收藏狀態為 0 (隱藏)
        //更新收藏 postid 及 memid 都符合者
        bbsPostService.updateBbsFavPageStatus( favCancelRequest , user.getMemId());


        FavoriterArticleRequest favoriterArticleRequest = new FavoriterArticleRequest();
        favoriterArticleRequest.setMemId(user.getMemId());
        favoriterArticleRequest.setBbsPostId(favCancelRequest.getBbsPostId());
        favoriterArticleRequest.setFavStatus(1);

        //          取的該篇文有幾個人 收藏
        int favArtNum = bbsPostService.getBbsPostFavArtById(favoriterArticleRequest);

//          更新收藏數
        bbsPostService.updateBbsPostFav(favoriterArticleRequest.getBbsPostId(),favArtNum);

//            updateBbsPostReaction(int postId , int postFav)

//        return ResponseEntity.status(HttpStatus.CREATED).body(favArtNum);

//

        return ResponseEntity.status(HttpStatus.OK).body("取消收藏成功");
    }

}
