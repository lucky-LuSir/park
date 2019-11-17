package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 缴费类型字典
 * @Auth luming
 * @Date 2018/12/5 11:14
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class CostType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CostType.class.getName(),"CostType","缴费类型"));
        list.add(new Dictionary(CostType.class.getName(),"1","租金","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"2","电费","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"3","水费","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"4","停车费","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"5","保险费","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"6","退租费","CostType"));
        list.add(new Dictionary(CostType.class.getName(),"7","大房东合同缴费","CostType"));

        return list;
    }
}
