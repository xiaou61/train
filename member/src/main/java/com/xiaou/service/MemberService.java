package com.xiaou.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xiaou.MemberApplication;
import com.xiaou.domain.Member;
import com.xiaou.domain.MemberExample;
import com.xiaou.exception.BusinessException;
import com.xiaou.exception.BusinessExceptionEnum;
import com.xiaou.mapper.MemberMapper;
import com.xiaou.req.MemberLoginReq;
import com.xiaou.req.MemberRegisterReq;
import com.xiaou.req.MemberSendCodeReq;
import com.xiaou.resp.MemberLoginResp;
import com.xiaou.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final static Logger Log = LoggerFactory.getLogger(MemberApplication.class);

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        Member memberDB = selectMember(mobile);

        if (ObjectUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }


        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }


    public void sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        Member memberDB = selectMember(mobile);

        //如果手机号不存在，则插入记录
        if (ObjectUtil.isNull(memberDB)){
            Log.info("手机号不存在，插入一条记录");
            Member member = new Member();
            memberDB.setId(SnowUtil.getSnowflakeNextId());
            memberDB.setMobile(mobile);
            memberMapper.insert(member);
        }else {
            Log.info("手机号存在,不插入记录");
        }

        //生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        Log.info("生成短信验证码：{}",code);
        //保存短信记录表：手机号 短信验证码 有效期 是否已使用 业务类型 发送时间 使用时间

        //对接短信通道，发送短信
    }

    public MemberLoginResp login(MemberLoginReq req){
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectMember(mobile);
        //如果手机号不存在，则插入记录
        if (ObjectUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //检验短信验证码
        if (!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        return BeanUtil.copyProperties(memberDB,MemberLoginResp.class);
    }

    private Member selectMember(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        //如果手机号不存在，则插入记录
        if (CollUtil.isEmpty(list)){
           return null;
        }else {
            return list.get(0);
        }
    }

}
