package com.xiaou.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.xiaou.context.LoginMemberContext;
import com.xiaou.domain.Passenger;
import com.xiaou.domain.PassengerExample;
import com.xiaou.mapper.PassengerMapper;
import com.xiaou.req.PassengerQueryReq;
import com.xiaou.req.PassengerSaveReq;
import com.xiaou.resp.PassengerQueryResp;
import com.xiaou.util.SnowUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;


    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
    public List<PassengerQueryResp> queryList(PassengerQueryReq req){
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(1,2);
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList, PassengerQueryResp.class);

    }
}
