package com.xiaou.controller;

import com.xiaou.req.MemberRegisterReq;
import com.xiaou.req.MemberSendCodeReq;
import com.xiaou.resp.CommonResp;
import com.xiaou.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
   @Resource
   private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count(){
        int count= memberService.count();
        return new CommonResp<>(count);
    }
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }
    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid MemberSendCodeReq req){
       memberService.sendCode(req);
       return new CommonResp<>();
    }
}
