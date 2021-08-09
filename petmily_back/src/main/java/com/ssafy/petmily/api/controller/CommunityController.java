package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.FileBoardPostReq;
import com.ssafy.petmily.api.service.CommunityService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.animal.AnimalFile;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.community.BoardFile;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "게시글 API", tags = {"Community"})
@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class CommunityController {


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

        Board board =communityService.createBoard(comuRegisterPostReq);

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
        communityService.deleteBoard(no);
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
        BoardFile boardFile = communityService.fileUpload(fileBoardPostReq,extension);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
