package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.ati.customer.business.ICustomerChannelCpyManager;
import com.kfwy.park.ati.customer.business.ICustomerChannelManager;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.ati.customer.dao.ICustomerDbDao;
import com.kfwy.park.ati.customer.dao.ICustomerNeedAreaDbDao;
import com.kfwy.park.ati.customer.dao.ICustomerRelateDbDao;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import com.kfwy.park.ati.customer.entity.CustomerNeedAreaEntity;
import com.kfwy.park.ati.customer.entity.CustomerRelateEntity;
import com.kfwy.park.sys.area.dao.ICompanyAreaDbDao;
import com.kfwy.park.sys.area.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description 客户管理manager
 * @Auth luming
 * @Date 2018/6/6 9:00
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class CustomerManagerImpl extends AbstractManager<CustomerEntity> implements ICustomerManager {

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private ICustomerRelateDbDao customerRelateDbDao;

    @Autowired
    private ICustomerNeedAreaDbDao customerNeedAreaDbDao;

    @Autowired
    private ICompanyAreaDbDao companyAreaDbDao;

    @Autowired
    private ICustomerChannelManager customerChannelManager;

    @Autowired
    private ICustomerChannelCpyManager customerChannelCpyManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerDbDao;
    }

    @Override
    protected void beforeCreate(CustomerEntity customerEntity) {
        //cusCode
        String cusCode = customerEntity.getCusCode();

        //设置客户主表cusCode
        customerEntity.setCusCode(cusCode);
        //设置负责人empCode
        customerEntity.setEmpCode(customerEntity.getCreateCode());
        customerEntity.setEmpName(customerEntity.getCreateName());

        //联系人设置code
        List<CustomerRelateEntity> relateEntityList = customerEntity.getContactList();
        Iterator<CustomerRelateEntity> iterator = relateEntityList.iterator();
        while (iterator.hasNext()){
            CustomerRelateEntity relateEntity = iterator.next();
            relateEntity.setCusCode(cusCode);
            relateEntity.setRelateCode(CodeGenUtils.generate());
        }

        //客户需求设置code
        List<CustomerNeedAreaEntity> needAreaList = customerEntity.getNeedAreaList();
        Iterator<CustomerNeedAreaEntity> it = needAreaList.iterator();
        while (it.hasNext()){
            CustomerNeedAreaEntity needAreaEntity = it.next();
            needAreaEntity.setCusCode(cusCode);
            needAreaEntity.setCusAreaCode(CodeGenUtils.generate());



            Map<String, Object> map = new HashMap<>();
            map.put("cpyCode",CurrentContext.getCpyCode());
            map.put("isDeleted",false);
            if(!StringUtils.isEmpty(needAreaEntity.getProvinceCode())){
                map.put("areaCode",needAreaEntity.getProvinceCode());
                //加入省市区中文name
                CompanyAreaEntity oneProv = companyAreaDbDao.selectUniqueByMap(map);
                needAreaEntity.setProvinceName(oneProv.getName());
            }

            if(!StringUtils.isEmpty(needAreaEntity.getCityCode())){
                map.put("areaCode",needAreaEntity.getCityCode());
                CompanyAreaEntity oneCity =  companyAreaDbDao.selectUniqueByMap(map);
                needAreaEntity.setCityName(oneCity.getName());
            }

            if(!StringUtils.isEmpty(needAreaEntity.getRegionCode())){
                map.put("areaCode", needAreaEntity.getRegionCode());
                CompanyAreaEntity oneRegion = companyAreaDbDao.selectUniqueByMap(map);
                needAreaEntity.setRegionName(oneRegion.getName());
            }

            OperateFillUtils.fill(needAreaEntity);
        }

        customerNeedAreaDbDao.batchInsert(needAreaList);

    }

    @Override
    protected void afterCreate(CustomerEntity customerEntity) {

    }

    /*
     * @Description 根据客户编号查询客户
     * @Auth luming
     * @Date 2018/6/14 20:06
     * @Version 1.0
     * @Param [cusCode]
     * @return java.util.List<com.kfwy.park.ati.customer.entity.CustomerEntity>
     */
    @Override
    public CustomerEntity details(Map<String,Object> map) throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();

        try{
            customerEntity = customerDbDao.selectUniqueByMap(map);
            if(customerEntity!=null){
                List<CustomerRelateEntity> customerRelateEntityList = customerRelateDbDao.selectByMap(map);
                List<CustomerNeedAreaEntity> needAreaEntityList = customerNeedAreaDbDao.selectByMap(map);
                customerEntity.setContactList(customerRelateEntityList);
                customerEntity.setNeedAreaList(needAreaEntityList);
            }
        }catch (Exception e){
            throw new BusinessException("查询到多条数据");
        }
        return customerEntity;
    }

    @Override
    public int findOneByPhone(String mainContactPhone) {
        Map<String, Object> mapOne = new  HashMap<String, Object>();
        mapOne.put("mainContactPhone",mainContactPhone);
        mapOne.put("cpyCode", CurrentContext.getCpyCode());
        int num = customerDbDao.countByMap(mapOne);
        return num;
    }

    /**
     * @Description 查询客户渠道来源报表
     * @Auth luming
     * @Date 2018/10/31 15:49
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<CustomerEntity> queryDitchCount(Map<String, Object> map) {
        return customerDbDao.queryDitchCount(map);
    }

    /**
     * @Description 查询每个职员招到的客户数
     * @Auth gengtao
     * @Date 2019/3/11 14:12
     * @Version 1.0
     * @param map
     * @return
     */
    @Override
    public PageResult<CustomerEntity> queryEmpCount(Map<String, Object> map, int start, int pageSize){
        PageResult<CustomerEntity> result = customerDbDao.selectByStatement(QUERY_EMP_COUNT, map, start, pageSize);
        return result;
    }

    /**
     * @Description 主动获客，排除ditch值为7和10
     * @Auth luming
     * @Date 2018/12/5 15:22
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectCusCount(String empCode, Date startDate, Date endDate) {
        return customerDbDao.selectCusCount(empCode, startDate, endDate);
    }

    /**
     * @Description 查询中介渠道的获客数
     * @Auth luming
     * @Date 2018/12/5 15:23
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectAgent(String empCode, Date startDate, Date endDate) {
        return customerDbDao.selectAgent(empCode, startDate, endDate);
    }

    /**
     * @Description 查询库房无忧的获客数
     * @Auth luming
     * @Date 2018/12/5 15:24
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectKfwy(String empCode, Date startDate, Date endDate) {
        return customerDbDao.selectKfwy(empCode, startDate, endDate);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 根据园区统计客户数
     *@Return
     **/
    @Override
    public List<CustomerEntity> analysisByPk(Map<String, Object> map){
        return this.customerDbDao.analysisByPk(map);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 根据行业统计客户数
     *@Return
     **/
    @Override
    public List<CustomerEntity> analysisByTrade(Map<String, Object> map){
        return this.customerDbDao.analysisByTrade(map);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:53:06
     *@Description 获取状态
     *@Return
     **/
    public List<CustomerEntity> selectStatus(Map<String, Object> map){
        return this.customerDbDao.selectStatus(map);
    }
}