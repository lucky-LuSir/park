package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerFollowupManager;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.ati.customer.business.ICustomerUpDownManager;
import com.kfwy.park.ati.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.park.ati.customer.dao.ICustomerIntentParkDbDao;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;
import com.kfwy.park.ati.customer.entity.CustomerIntentParkEntity;
import com.kfwy.park.ati.customer.entity.CustomerUpDownEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 19:18
 */
@Service
public class CustomerFollowupManagerImpl extends AbstractManager<CustomerFollowupEntity> implements ICustomerFollowupManager {

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerFollowupDbDao;
    }
    @Autowired
    private ICustomerFollowupDbDao customerFollowupDbDao;
    @Autowired
    private ICustomerIntentParkDbDao customerIntentParkDbDao;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private ICustomerUpDownManager customerUpDownManager;

    @Override
    protected void beforeCreate(CustomerFollowupEntity customerFollowupEntity){
        customerFollowupEntity.setFollowupCode(CodeGenUtils.generate());
        //新增客户意向房源信息,第一版没有意向房源
//        List<CustomerIntentParkEntity> intentParkList = customerFollowupEntity.getCusIntentParkList();
//        for (int i=0;i<intentParkList.size();i++) {
//            intentParkList.get(i).setFollowupCode(customerFollowupEntity.getFollowupCode());
//        }
    }

    @Override
    protected void afterCreate(CustomerFollowupEntity customerFollowupEntity) {
        //新增客户意向房源信息,第一版没有意向房源
//        if (customerFollowupEntity.getCusIntentParkList().size() > 0) {
//            customerIntentParkDbDao.batchInsert(customerFollowupEntity.getCusIntentParkList());
//        }
        //修改客户状态
        CustomerEntity customerEntity = customerManager.findOne("cusCode",customerFollowupEntity.getCusCode());
        customerEntity.setCusStatus(customerFollowupEntity.getCusStatus());
        customerManager.update(customerEntity);
        //新增跟进选择客户状态为不合适时,将客户下架
        if("4".equals(customerFollowupEntity.getCusStatus())){
            CustomerUpDownEntity upDownEntity = new CustomerUpDownEntity();
            upDownEntity.setCusCode(customerEntity.getCusCode());
            upDownEntity.setCusDownType("3");//不租了
            upDownEntity.setCusUpdownType("2");//下架
            upDownEntity.setCusDownReason("新增跟进:" + customerFollowupEntity.getFollowupRes());
            customerUpDownManager.create(upDownEntity);
        }

    }


    /**
     * 非物理删除跟进信息
     * @param entity
     * @return
     */
    @Override
    public int followupDelete(CustomerFollowupEntity entity) throws Exception {
        entity.setIsDeleted(true);
        CustomerIntentParkEntity ipEntity = new CustomerIntentParkEntity();
        ipEntity.setFollowupCode(entity.getFollowupCode());
        ipEntity.setIsDeleted(true);
        try {
            customerFollowupDbDao.update(entity);
            customerIntentParkDbDao.update(ipEntity);
            return 1;
        }catch (Exception e) {
            throw new Exception("删除跟进信息错误");
        }


    }

    /**
     * 修改跟进信息
     * @param entity
     * @return
     */
    @Override
    public int followupUpdate(CustomerFollowupEntity entity) throws Exception {
        try {
            //更新客户跟进信息
            customerFollowupDbDao.update(entity);

            String followupCode = entity.getFollowupCode();
            List<CustomerIntentParkEntity> cusIntentParkList = entity.getCusIntentParkList();
            CustomerIntentParkEntity delEntity = new CustomerIntentParkEntity();
            if (cusIntentParkList.size() > 0) {
                delEntity.setFollowupCode(followupCode);
                delEntity.setIsDeleted(true);
                //将存在数据非物理删除
                customerIntentParkDbDao.update(delEntity);
                //插入修改后的数据
                customerIntentParkDbDao.batchInsert(cusIntentParkList);
            }
            return 1;
        }catch (Exception e) {
            throw new Exception("修改客户跟进信息错误");
        }
    }

    /**
     * 查询一条跟进的详细信息
     * @param followupCode
     * @return
     */
    /*@Override
    public List<CustomerFollowupEntity> selectOneFollowup(String followupCode) {
        return customerFollowupDbDao.selectOneFollowup(followupCode);
    }*/


    /**
     * @Description 看板：我的数据，查询本月意向客户
     * @Auth luming
     * @Date 2018/8/14 15:50
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int queryThisMonthFollowup(Map<String, Object> map) throws Exception {
        int count = 0;
        try{
            count = customerFollowupDbDao.queryThisMonthFollowup(map);
            if(count > 0){
                return count;
            }
        }catch (Exception e){
            throw new Exception("无数据！");
        }
        return count;
    }

    /**
     * @Description 查询招商人员的跟进数量
     * @Auth luming
     * @Date 2018/12/5 15:08
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectFollowup(String empCode, Date startDate, Date endDate) {
        return customerFollowupDbDao.selectFollowup(empCode, startDate, endDate);
    }

    /**
     ** @Description 查询每个园区的每个人跟进次数统计
     * @Auth gengtao
     * @Date 2019/3/14 11:29
     */
    @Override
    public List<Map<String,Object>> selectPkByCusFolCount(Map<String,Object>map,Date startDate, Date endDate){
        List<Map<String,Object>> result = new ArrayList<>();
        List<CustomerFollowupEntity> pkList = customerFollowupDbDao.selectPk(map);
        for(CustomerFollowupEntity pk : pkList){
            if(!pk.getPkCode().equals("0") ){
                Map<String, Object> countMap = new HashMap<>();
                countMap.put("pkCode",pk.getPkCode());
                countMap.put("pkName",pk.getPkName());

                map.put("createTimeStart",startDate);
                map.put("createTimeEnd",endDate);
                map.put("followupType",'2');
                map.put("pkCode",pk.getPkCode());
                List<CustomerFollowupEntity> countList = customerFollowupDbDao.selectPkByCusCount(map);

                int first = 0,second = 0,third = 0,fourth = 0,more = 0;
                for (CustomerFollowupEntity entity : countList) {
                    switch (entity.getPkByCusCount().intValue()){
                        case 1:
                            first += 1;
                            break;
                        case 2:
                            second +=1;
                            break;
                        case 3:
                            third += 1;
                            break;
                        case 4:
                            fourth += 1;
                            break;
                        default:
                            more += 1;
                            break;
                    }
                }
                countMap.put("firstW",first);
                countMap.put("secondW",second);
                countMap.put("thirdW",third);
                countMap.put("fourthW",fourth);
                countMap.put("moreW",more);
                result.add(countMap);
            }
        }
        return result;
    }
}
