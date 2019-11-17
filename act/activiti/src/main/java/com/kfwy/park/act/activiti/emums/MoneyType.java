package com.kfwy.park.act.activiti.emums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2019/4/12.
 * 金额类型
 */
@Component
public class MoneyType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(MoneyType.class.getName(),"moneyType","moneyType"));
        list.add(new Dictionary(MoneyType.class.getName(),"1","收入","moneyType"));
        list.add(new Dictionary(MoneyType.class.getName(),"2","支出","moneyType"));
        return list;
    }
}
