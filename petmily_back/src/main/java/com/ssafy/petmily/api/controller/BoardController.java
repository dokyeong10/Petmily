package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.ReplyRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.api.service.CommunityService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.auth.SsafyUserDetails;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.api.service.BoardService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;
import com.ssafy.petmily.db.entity.community.BoardJoin;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "게시글 API", tags = {"Community"})
@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {


    @Autowired
    BoardService boardService;

    @Autowired
    CommunityService communityService;

    @PostMapping("/enroll")
    @ApiOperation(value = "게시글 작성 ", notes = "게시글을 작성한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> enoll(
            @RequestBody @ApiParam(value="글 등록", required = true) ComuRegisterPostReq comuRegisterPostReq ) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.

        Board board = boardService.createBoard(comuRegisterPostReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //게시글 삭제
    @DeleteMapping("/{no}")
    @ApiOperation(value = "개인회원삭제", notes = "개인회원삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<? extends BaseResponseBody> deleteBoard(@PathVariable Long no){
        boardService.deleteBoard(no);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //다중사진 업로드
    @PostMapping("/file")
    @ApiOperation(value = "게시판 사진 등록", notes = "사진을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> BoardFile(
            @RequestBody @ApiParam(value = "게시판 사진 업로드", required = true) FileBoardPostReq fileBoardPostReq) {
        String file = fileBoardPostReq.getFile();
        System.out.println(file);
        String extension = "";
        String[] ext = file.split("\\.");
        extension = ext[(ext.length) - 1];
        System.out.println("exten =="+ extension);
        BoardFile boardFile = boardService.fileUpload(fileBoardPostReq,extension);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    // 댓글 등록
    @PostMapping("/reply/register")
    public ResponseEntity<? extends BaseResponseBody> registerRepl(@ApiIgnore Authentication authentication, @RequestBody ReplyRegisterPostReq replyRegisterPostReq){
        String contents = replyRegisterPostReq.getContents();
        boolean isAgency = replyRegisterPostReq.getIsAgency();
        long no = replyRegisterPostReq.getNo();
        System.out.println("===================== contents : " + contents + " | isAgency : "+ isAgency + " | no : " + no);

        // 기관 회원일 경우
        if(isAgency){
            SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
            String agencycode = agencyDetails.getAgencycode();

            communityService.createAgencyRepl(agencycode,contents,no);

            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));

        }else{ // 일반 회원일 경우
            SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
            Long userno = userDetails.getNo();
            communityService.createUserRepl(userno, contents, no);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
        }
    }

    // 답글 등록
    @PostMapping("/reply/re/register")
    public ResponseEntity<? extends BaseResponseBody> registerReRepl(@ApiIgnore Authentication authentication, @RequestBody ReplyRegisterPostReq replyRegisterPostReq){
        String contents = replyRegisterPostReq.getContents();
        boolean isAgency = replyRegisterPostReq.getIsAgency();
        long no = replyRegisterPostReq.getNo();
        System.out.println("===================== contents : " + contents + " | isAgency : "+ isAgency + " | no : " + no);

        // 기관 회원일 경우
        if(isAgency){
            SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
            String agencycode = agencyDetails.getAgencycode();

            communityService.createAgencyReplRe(agencycode,contents,no);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));

        }else{ // 일반 회원일 경우
            SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
            long userno = userDetails.getNo();

            communityService.createUserReplRe(userno, contents, no);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
        }

    }

    @GetMapping("/details/{no}")
    public ResponseEntity<List<BoardJoin>> boarddetail(@PathVariable Long no) {
        List<BoardJoin> BoardJoins = boardService.boarddetail(no);
        return new ResponseEntity<List<BoardJoin>>(BoardJoins, HttpStatus.OK);
    }



}
