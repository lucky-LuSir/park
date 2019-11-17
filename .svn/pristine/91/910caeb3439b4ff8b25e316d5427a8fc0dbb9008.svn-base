package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2018/6/5.
 * 客户状态字典
 */
@Component
public class CusStatus implements DictionaryProvider{

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CusStatus.class.getName(),"cusStatus","客户状态"));
        list.add(new Dictionary(CusStatus.class.getName(),"1","跟进中","cusStatus"));
        list.add(new Dictionary(CusStatus.class.getName(),"2","谈判中","cusStatus"));
        list.add(new Dictionary(CusStatus.class.getName(),"3","已签约","cusStatus"));
        list.add(new Dictionary(CusStatus.class.getName(),"4","不合适","cusStatus"));
        list.add(new Dictionary(CusStatus.class.getName(),"5","在外入驻","cusStatus"));
        list.add(new Dictionary(CusStatus.class.getName(),"6","已退租","cusStatus"));
        return list;
    }
}
