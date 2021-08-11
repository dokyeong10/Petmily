package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.*;
import com.ssafy.petmily.api.service.CommunityService;
import com.ssafy.petmily.common.auth.SsafyAgencyDetails;
import com.ssafy.petmily.common.auth.SsafyUserDetails;
import com.ssafy.petmily.api.service.BoardService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.animal.AnimalJoin;
import com.ssafy.petmily.db.entity.community.*;
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


//    //개인 회원 게시글 등록
    @PostMapping("/enroll/personal")
     public ResponseEntity<? extends BaseResponseBody> enoll(
            @RequestBody @ApiParam(value="글 등록", required = true) ComuRegisterPostReq comuRegisterPostReq,@ApiIgnore Authentication authentication )
    {
        // 게시글 등록
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        Long no = userDetails.getNo();

        System.out.println("============================= no : " + no);
        comuRegisterPostReq.setUserno(no);

        Board board = boardService.createBoard(comuRegisterPostReq);

        // 파일 등록
        String files[] = comuRegisterPostReq.getFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("============================ file name : " + files[i]);
        }

        for (int i = 0; i < files.length; i++) {
            String extension = "";
            String[] ext = files[i].split("\\.");
            extension = ext[(ext.length) - 1];
            System.out.println("============================ file extention : " + extension);
            BoardFile boardfile = boardService.fileUpload(files[i], extension);

        }
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //기관 게시글 등록
    @PostMapping("/enroll/agency")
    public ResponseEntity<? extends BaseResponseBody> enollAgency(
            @RequestBody @ApiParam(value="글 등록", required = true) ComuRegisterPostReq comuRegisterPostReq,@ApiIgnore Authentication authentication )
    {
        // 게시글 등록
        SsafyAgencyDetails userDetails = (SsafyAgencyDetails) authentication.getDetails();
        String agencycode = userDetails.getAgencycode();

        System.out.println("============================= agencycode : " + agencycode);
        comuRegisterPostReq.setAgencycode(agencycode);

        Board board = boardService.createBoard(comuRegisterPostReq);

        // 파일 등록
        String files[] = comuRegisterPostReq.getFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("============================ file name : " + files[i]);
        }

        for (int i = 0; i < files.length; i++) {
            String extension = "";
            String[] ext = files[i].split("\\.");
            extension = ext[(ext.length) - 1];
            System.out.println("============================ file extention : " + extension);
            BoardFile boardfile = boardService.fileUpload(files[i], extension);

        }
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

//    //다중사진 업로드
//    @PostMapping("/file")
//    @ApiOperation(value = "게시판 사진 등록", notes = "사진을 등록한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<? extends BaseResponseBody> BoardFile(
//            @RequestBody @ApiParam(value = "게시판 사진 업로드", required = true) FileBoardPostReq fileBoardPostReq) {
//        String file = fileBoardPostReq.getFile();
//        System.out.println(file);
//        String extension = "";
//        String[] ext = file.split("\\.");
//        extension = ext[(ext.length) - 1];
//        System.out.println("exten =="+ extension);
//        BoardFile boardFile = boardService.fileUpload(fileBoardPostReq,extension);
//
//
//        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//    }

    // 댓글 등록
    @PostMapping("/reply/register")
    public ResponseEntity<? extends BaseResponseBody> registerRepl(@ApiIgnore Authentication authentication, @RequestBody ReplyRegisterPostReq replyRegisterPostReq){
        String contents = replyRegisterPostReq.getContents();
        boolean isAgency = replyRegisterPostReq.getIsAgency();
        long bno = replyRegisterPostReq.getBno();
        System.out.println("===================== contents : " + contents + " | isAgency : "+ isAgency + " | bno : " + bno);

        // 기관 회원일 경우
        if(isAgency){
            SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
            String agencycode = agencyDetails.getAgencycode();

            communityService.createAgencyRepl(agencycode,contents,bno);

            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));

        }else{ // 일반 회원일 경우
            SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
            Long userno = userDetails.getNo();
            communityService.createUserRepl(userno, contents, bno);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
        }
    }

    // 답글 등록
    @PostMapping("/reply/re/register")
    public ResponseEntity<? extends BaseResponseBody> registerReRepl(@ApiIgnore Authentication authentication, @RequestBody ReplyReRegisterPostReq replyReRegisterPostReq){
        String contents = replyReRegisterPostReq.getContents();
        boolean isAgency = replyReRegisterPostReq.getIsAgency();
        long bno = replyReRegisterPostReq.getBno();
        long replno = replyReRegisterPostReq.getReplno();
        System.out.println("===================== contents : " + contents + " | isAgency : "+ isAgency + " | bno : " + bno + " | replno : " + replno);

        // 기관 회원일 경우
        if(isAgency){
            System.out.println("========================= 기관 회원");
            SsafyAgencyDetails agencyDetails = (SsafyAgencyDetails) authentication.getDetails();
            String agencycode = agencyDetails.getAgencycode();

            communityService.createAgencyReplRe(agencycode,contents,bno, replno);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));

        }else{ // 일반 회원일 경우
            System.out.println("========================= 일반 회원");
            SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
            long userno = userDetails.getNo();

            communityService.createUserReplRe(userno, contents, bno, replno);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
        }
    }

    // 게시글 상세 정보 with 댓글 List
    @GetMapping("/details/{no}")
    public ResponseEntity<BoardJoin> boarddetail(@PathVariable Long no) {
        BoardJoin BoardJoins = boardService.boarddetail(no);
        return new ResponseEntity<BoardJoin>(BoardJoins, HttpStatus.OK);
    }



}
