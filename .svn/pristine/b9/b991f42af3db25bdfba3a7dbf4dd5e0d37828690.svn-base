package com.kfwy.park.sys.msg.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.sys.msg.business.IBargainMsgManager;
import com.kfwy.park.sys.msg.dao.IBargainMsgDbDao;
import com.kfwy.park.sys.msg.entity.BargainMsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2019/6/21.
 */
@Service
public class BargainMsgManagerImpl extends AbstractManager<BargainMsgEntity> implements IBargainMsgManager {
    @Autowired
    private IBargainMsgDbDao bargainMsgDbDao;

    @Override
    protected IMyBatisBaseDao<BargainMsgEntity, Long> getMyBatisDao() {
        return this.bargainMsgDbDao;
    }

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/6/21 15:52
     * @Version 1.0
     * @Param [bargainMsgEntity]
     * @return void
     */
    @Override
    public void checkParams(BargainMsgEntity bargainMsgEntity) throws IllegalAccessException {
        List<String> list = new ArrayList();
        list.add("pkCode");
        list.add("empCode");
        list.add("area");
        list.add("rent");
        list.add("channel");
        ParamUtil<BargainMsgEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(bargainMsgEntity, list);
    }
}
