package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.business.IParkingReceiptManager;
import com.kfwy.park.ati.contract.business.IPayManager;
import com.kfwy.park.ati.contract.business.IPremiumManager;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.business.IWaterReceiptManager;
import com.kfwy.park.ati.contract.dao.IPayDbDao;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.ati.contract.entity.ParkingReceiptEntity;
import com.kfwy.park.ati.contract.entity.PremiumEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;
import com.kfwy.park.ati.contract.enums.CostType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 租金、电费、水费、停车费，支付表
 * @Auth luming
 * @Date 2018/9/19 18:44
 * @Version 1.0
 * @Param
 * @return
 */
@Service
@Transactional
public class PayManagerImpl extends AbstractManager<RentReceiptPayEntity> implements IPayManager{

    @Autowired
    private IPayDbDao payDbDao;

    @Autowired
    private IRentReceiptManager rentReceiptManager;

    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;

    @Autowired
    private IWaterReceiptManager waterReceiptManager;

    @Autowired
    private IParkingReceiptManager parkingReceiptManager;

    @Autowired
    private IPremiumManager premiumManager;



    @Override
    protected IMyBatisBaseDao<RentReceiptPayEntity, Long> getMyBatisDao() {
        return this.payDbDao;
    }

    @Override
    protected void afterUpdate(RentReceiptPayEntity payEntity) {
        //缴费类型
        String costType = payEntity.getCostType();

        //缴费编码
        String code = payEntity.getCode();

        //费用记录查询参数
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        //缴费记录查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("costType", costType);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        //查询出当前费用缴费记录
        List<RentReceiptPayEntity> rentReceiptPayList = payDbDao.selectByMap(map);

        //缴费总和
        BigDecimal paidPrice = new BigDecimal(0);

        for (RentReceiptPayEntity entity: rentReceiptPayList) {
            //统计已经缴的费用
            paidPrice = paidPrice.add(entity.getRealRent());
        }

        //当前时间
        Date nowDate = new Date();

        /**
         * costType类型
         * @see CostType
         */
        if("1".equals(costType)) {//修改的缴费是租金的缴费

            param.put("rentCode", code);
            //查询本次应收记录
            RentReceiptEntity rentReceiptEntity = rentReceiptManager.findOne(param);

            //将缴费总和赋值给租金记录
            rentReceiptEntity.setPaidPrice(paidPrice);
            //判断缴费总和是否大于应收
            if((paidPrice.add(rentReceiptEntity.getDiscountsPrice())).compareTo(rentReceiptEntity.getTotalPrice()) >= 0) {
                rentReceiptEntity.setPayStatus("2");
            } else {
                if(rentReceiptEntity.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(rentReceiptEntity.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        rentReceiptEntity.setPayStatus("4");
                    } else {
                        //待支付
                        rentReceiptEntity.setPayStatus("1");
                    }
                } else {
                    rentReceiptEntity.setPayStatus("3");
                }
            }

            //将改动租金更新到租金费用
            rentReceiptManager.update(rentReceiptEntity);
        } else if("2".equals(costType)) {//修改的是电费的缴费
            param.put("eleCode", code);
            //查询本次应收记录
            ElectricityReceiptEntity electricityReceiptEntity = electricityReceiptManager.findOne(param);

            //将缴费总和赋值给电费记录
            electricityReceiptEntity.setPaidPrice(paidPrice);
            //判断缴费总和是否大于应收
            if(paidPrice.compareTo(electricityReceiptEntity.getReceivablePrice()) >= 0) {
                electricityReceiptEntity.setPayStatus("2");
            } else {
                if(electricityReceiptEntity.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(electricityReceiptEntity.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        electricityReceiptEntity.setPayStatus("4");
                    } else {
                        electricityReceiptEntity.setPayStatus("1");
                    }
                } else {
                    electricityReceiptEntity.setPayStatus("3");
                }
            }

            //记录更新人信息
            electricityReceiptEntity.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            electricityReceiptEntity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            electricityReceiptEntity.setLastUpdateTime(nowDate);

            //将改动租金更新到电费费用
            electricityReceiptManager.update(electricityReceiptEntity);

        } else if("3".equals(costType)) {//修改的是水费的缴费
            param.put("waterCode", code);
            //查询本次应收记录
            WaterReceiptEntity waterReceiptEntity = waterReceiptManager.findOne(param);

            //将缴费总和赋值给电费记录
            waterReceiptEntity.setPaidPrice(paidPrice);
            //判断缴费总和是否大于应收
            if(paidPrice.compareTo(waterReceiptEntity.getReceivablePrice()) >= 0) {
                waterReceiptEntity.setPayStatus("2");
            } else {
                if(waterReceiptEntity.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(waterReceiptEntity.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        waterReceiptEntity.setPayStatus("4");
                    } else {
                        waterReceiptEntity.setPayStatus("1");
                    }
                } else {
                    waterReceiptEntity.setPayStatus("3");
                }
            }

            //记录更新人信息
            waterReceiptEntity.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            waterReceiptEntity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            waterReceiptEntity.setLastUpdateTime(nowDate);

            //将改动租金更新到水费费用
            waterReceiptManager.update(waterReceiptEntity);
        } else if("4".equals(costType)) {//修改的是停车费的缴费
            param.put("parkingCode", code);
            //查询本次应收记录
            ParkingReceiptEntity parkingReceiptEntity = parkingReceiptManager.findOne(param);

            //将缴费总和赋值给停车费记录
            parkingReceiptEntity.setPaidPrice(paidPrice);
            //判断缴费总和是否大于应收
            if(paidPrice.compareTo(parkingReceiptEntity.getReceivablePrice()) >= 0) {
                parkingReceiptEntity.setPayStatus("2");
            } else {
                if(parkingReceiptEntity.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(parkingReceiptEntity.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        parkingReceiptEntity.setPayStatus("4");
                    } else {
                        parkingReceiptEntity.setPayStatus("1");
                    }
                } else {
                    parkingReceiptEntity.setPayStatus("3");
                }
            }

            //记录更新人信息
            parkingReceiptEntity.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            parkingReceiptEntity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            parkingReceiptEntity.setLastUpdateTime(nowDate);

            //将改动租金更新到停车费费用
            parkingReceiptManager.update(parkingReceiptEntity);
        } else if("5".equals(costType)) {//修改的是保险费的缴费
            param.put("premiumCode", code);
            //查询本次应收记录
            PremiumEntity premiumEntity = premiumManager.findOne(param);

            //将缴费总和赋值给保险费记录
            premiumEntity.setPaidPrice(paidPrice);
            //判断缴费总和是否大于应收
            if(paidPrice.compareTo(premiumEntity.getReceivablePremium()) >= 0) {
                premiumEntity.setPayStatus("2");
            } else {
                if(premiumEntity.getDeadline().getTime() >= nowDate.getTime()) {
                    if(premiumEntity.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        premiumEntity.setPayStatus("4");
                    } else {
                        premiumEntity.setPayStatus("1");
                    }
                } else {
                    premiumEntity.setPayStatus("3");
                }
            }

            //记录更新人信息
            premiumEntity.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            premiumEntity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            premiumEntity.setLastUpdateTime(nowDate);

            //将改动租金更新到保险费费用
            premiumManager.update(premiumEntity);
        }
    }

    @Override
    public int deleteById(Long id) {
        //将删除的本条数据查询出来
        RentReceiptPayEntity rentReceiptPayEntity = payDbDao.selectById(id);
        //删除的那条缴费金额
        BigDecimal realRent = rentReceiptPayEntity.getRealRent();

        String code = rentReceiptPayEntity.getCode();

        //费用查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        String costType = rentReceiptPayEntity.getCostType();
        if("1".equals(costType)) {//修改的缴费是租金的缴费
            map.put("rentCode", code);
            RentReceiptEntity rentReceiptOne = rentReceiptManager.findOne(map);

            rentReceiptOne.setPaidPrice(rentReceiptOne.getPaidPrice().subtract(realRent));
            //减去后判断已收款是否大于应收
            if(rentReceiptOne.getPaidPrice().compareTo(rentReceiptOne.getTotalPrice()) >= 0) {
                rentReceiptOne.setPayStatus("2");
            } else {
                Date nowDate = new Date();
                if(rentReceiptOne.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(rentReceiptOne.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        rentReceiptOne.setPayStatus("4");
                    } else {
                        //待支付
                        rentReceiptOne.setPayStatus("1");
                    }
                } else {
                    //还是逾期
                    rentReceiptOne.setPayStatus("3");
                }
            }
            rentReceiptOne.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            rentReceiptOne.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            rentReceiptOne.setLastUpdateTime(new Date());
            //更新那条租金应收
            rentReceiptManager.update(rentReceiptOne);

        } else if("2".equals(costType)) {//修改的是电费的缴费
            map.put("eleCode", code);
            ElectricityReceiptEntity electricityReceiptManagerOne = electricityReceiptManager.findOne(map);

            electricityReceiptManagerOne.setPaidPrice(electricityReceiptManagerOne.getPaidPrice().subtract(realRent));
            //减去后判断已收款是否大于应收
            if(electricityReceiptManagerOne.getPaidPrice().compareTo(electricityReceiptManagerOne.getReceivablePrice()) >= 0) {
                electricityReceiptManagerOne.setPayStatus("2");
            } else {
                Date nowDate = new Date();
                if(electricityReceiptManagerOne.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(electricityReceiptManagerOne.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        electricityReceiptManagerOne.setPayStatus("4");
                    } else {
                        //待支付
                        electricityReceiptManagerOne.setPayStatus("1");
                    }
                } else {
                    //还是逾期
                    electricityReceiptManagerOne.setPayStatus("3");
                }
            }
            electricityReceiptManagerOne.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            electricityReceiptManagerOne.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            electricityReceiptManagerOne.setLastUpdateTime(new Date());
            //更新那条租金应收
            electricityReceiptManager.update(electricityReceiptManagerOne);
        } else if("3".equals(costType)) {//修改的是水费的缴费
            map.put("waterCode", code);
            WaterReceiptEntity waterReceiptManagerOne = waterReceiptManager.findOne(map);

            waterReceiptManagerOne.setPaidPrice(waterReceiptManagerOne.getPaidPrice().subtract(realRent));
            //减去后判断已收款是否大于应收
            if(waterReceiptManagerOne.getPaidPrice().compareTo(waterReceiptManagerOne.getReceivablePrice()) >= 0) {
                waterReceiptManagerOne.setPayStatus("2");
            } else {
                Date nowDate = new Date();
                if(waterReceiptManagerOne.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(waterReceiptManagerOne.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        waterReceiptManagerOne.setPayStatus("4");
                    } else {
                        //待支付
                        waterReceiptManagerOne.setPayStatus("1");
                    }
                } else {
                    //还是逾期
                    waterReceiptManagerOne.setPayStatus("3");
                }
            }
            waterReceiptManagerOne.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            waterReceiptManagerOne.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            waterReceiptManagerOne.setLastUpdateTime(new Date());
            //更新那条租金应收
            waterReceiptManager.update(waterReceiptManagerOne);
        } else if("4".equals(costType)) {//修改的是停车费的缴费
            map.put("parkingCode", code);
            ParkingReceiptEntity parkingReceiptManagerOne = parkingReceiptManager.findOne(map);

            parkingReceiptManagerOne.setPaidPrice(parkingReceiptManagerOne.getPaidPrice().subtract(realRent));
            //减去后判断已收款是否大于应收
            if(parkingReceiptManagerOne.getPaidPrice().compareTo(parkingReceiptManagerOne.getReceivablePrice()) >= 0) {
                parkingReceiptManagerOne.setPayStatus("2");
            } else {
                Date nowDate = new Date();
                if(parkingReceiptManagerOne.getReceivingDate().getTime() >= nowDate.getTime()) {
                    if(parkingReceiptManagerOne.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        parkingReceiptManagerOne.setPayStatus("4");
                    } else {
                        //待支付
                        parkingReceiptManagerOne.setPayStatus("1");
                    }
                } else {
                    //还是逾期
                    parkingReceiptManagerOne.setPayStatus("3");
                }
            }
            parkingReceiptManagerOne.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            parkingReceiptManagerOne.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            parkingReceiptManagerOne.setLastUpdateTime(new Date());
            //更新那条租金应收
            parkingReceiptManager.update(parkingReceiptManagerOne);
        } else if("5".equals(costType)) {//修改的是保险费的缴费
            map.put("premiumCode", code);
            PremiumEntity premiumManagerOne = premiumManager.findOne(map);

            premiumManagerOne.setPaidPrice(premiumManagerOne.getPaidPrice().subtract(realRent));
            //减去后判断已收款是否大于应收
            if(premiumManagerOne.getPaidPrice().compareTo(premiumManagerOne.getReceivablePremium()) >= 0) {
                premiumManagerOne.setPayStatus("2");
            } else {
                Date nowDate = new Date();
                if(premiumManagerOne.getDeadline().getTime() >= nowDate.getTime()) {
                    if(premiumManagerOne.getPaidPrice().compareTo(BigDecimal.ZERO) > 0) {
                        //未结清
                        premiumManagerOne.setPayStatus("4");
                    } else {
                        //待支付
                        premiumManagerOne.setPayStatus("1");
                    }
                } else {
                    //还是逾期
                    premiumManagerOne.setPayStatus("3");
                }
            }
            premiumManagerOne.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
            premiumManagerOne.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
            premiumManagerOne.setLastUpdateTime(new Date());
            //更新那条租金应收
            premiumManager.update(premiumManagerOne);
        }

        //删除那条缴费记录
        int res = payDbDao.deleteById(id);

        return res;
    }

}
