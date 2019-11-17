package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 付款规则
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class PayRule implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(PayRule.class.getName(),"PayRule","付款规则"));
        list.add(new Dictionary(PayRule.class.getName(),"1","押二付三","PayRule"));
        list.add(new Dictionary(PayRule.class.getName(),"2","押一付六","PayRule"));
        list.add(new Dictionary(PayRule.class.getName(),"3","年付","PayRule"));
        return list;
    }
}
