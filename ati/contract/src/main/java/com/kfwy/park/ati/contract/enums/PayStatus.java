package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 */
@Component
public class PayStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(PayStatus.class.getName(),"PayStatus","物业费状态"));
        list.add(new Dictionary(PayStatus.class.getName(),"1","待支付","PayStatus"));
        list.add(new Dictionary(PayStatus.class.getName(),"2","已支付","PayStatus"));
        list.add(new Dictionary(PayStatus.class.getName(),"3","逾期","PayStatus"));
        list.add(new Dictionary(PayStatus.class.getName(),"4","未结清","PayStatus"));
        return list;
    }


}
