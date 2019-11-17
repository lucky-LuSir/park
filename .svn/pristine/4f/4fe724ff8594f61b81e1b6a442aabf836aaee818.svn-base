package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiepingping
 * @Descirption 用途类型枚举类
 * @Date 2018/5/29 18:06
 * 和floorEntity中的用途保持一致
 */
@Component
public class UseTypeCus implements DictionaryProvider{

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(UseTypeCus.class.getName(),"UseType","用途类型"));
        list.add(new Dictionary(UseTypeCus.class.getName(),"1","只做生产","UseType"));
        list.add(new Dictionary(UseTypeCus.class.getName(),"2","只做仓储","UseType"));
        list.add(new Dictionary(UseTypeCus.class.getName(),"3","只做办公","UseType"));
        list.add(new Dictionary(UseTypeCus.class.getName(),"4","生产和仓储","UseType"));
        list.add(new Dictionary(UseTypeCus.class.getName(),"5","生产仓储和办公","UseType"));
        return list;
    }
}
