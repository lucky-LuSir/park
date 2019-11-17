package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 递增规则
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class IncreaseRule implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(IncreaseRule.class.getName(),"IncreaseRule","递增规则"));
        list.add(new Dictionary(IncreaseRule.class.getName(),"1","三年或以上递增","IncreaseRule"));
        list.add(new Dictionary(IncreaseRule.class.getName(),"2","两年递增","IncreaseRule"));
        list.add(new Dictionary(IncreaseRule.class.getName(),"3","年递增","IncreaseRule"));
        return list;
    }
}
