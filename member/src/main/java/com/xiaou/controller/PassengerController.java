package com.xiaou.controller;

import com.xiaou.context.LoginMemberContext;
import com.xiaou.req.PassengerQueryReq;
import com.xiaou.req.PassengerSaveReq;
import com.xiaou.resp.CommonResp;
import com.xiaou.resp.PageResp;
import com.xiaou.resp.PassengerQueryResp;
import com.xiaou.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passenger;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passenger.save(req);
        return new CommonResp<>();
    }
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passenger.queryList(req);
        return new CommonResp<>(list);
    }


}
