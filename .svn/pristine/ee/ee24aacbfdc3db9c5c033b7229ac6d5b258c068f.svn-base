package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 */
@Component
public class DepositStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(DepositStatus.class.getName(),"DepositStatus","押金状态"));
        list.add(new Dictionary(DepositStatus.class.getName(),"1","无押金","DepositStatus"));
        list.add(new Dictionary(DepositStatus.class.getName(),"2","待支付","DepositStatus"));
        list.add(new Dictionary(DepositStatus.class.getName(),"3","已支付","DepositStatus"));
        return list;
    }


}
