package com.ssafy.petmily.api.controller;

import com.ssafy.petmily.api.request.ComuRegisterPostReq;
import com.ssafy.petmily.api.request.UserRegisterPostReq;
import com.ssafy.petmily.api.service.CommunityService;
import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.community.Board;
import com.ssafy.petmily.db.entity.user.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
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


}
