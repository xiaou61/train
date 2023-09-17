package com.xiaou.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaou.context.LoginMemberContext;
import com.xiaou.domain.Passenger;
import com.xiaou.domain.PassengerExample;
import com.xiaou.mapper.PassengerMapper;
import com.xiaou.req.PassengerQueryReq;
import com.xiaou.req.PassengerSaveReq;
import com.xiaou.resp.PageResp;
import com.xiaou.resp.PassengerQueryResp;
import com.xiaou.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;
private final static Logger Log = LoggerFactory.getLogger(PassengerService.class);


    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if (ObjectUtil.isNull(passenger.getId())){
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        }else {
            //新增编辑时间
            passenger.setUpdateTime(now);
            //如果不是空 应该去更新
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req){

        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        //自动生成一个select count
        PageInfo<Passenger> pageInfo=new PageInfo<>(passengerList);

        Log.info("总行数:{}",pageInfo.getTotal());
        Log.info("总页数:{}",pageInfo.getPages());
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);

        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }
}
