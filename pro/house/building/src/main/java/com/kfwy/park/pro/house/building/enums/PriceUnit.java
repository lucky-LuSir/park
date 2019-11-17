package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 价格单位枚举类
 * @Date 2018/5/29 18:14
 */
@Component
public class PriceUnit implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(PriceUnit.class.getName(),"PriceUnit","价格单位"));
        list.add(new Dictionary(PriceUnit.class.getName(),"1","元/㎡/天","PriceUnit"));
        list.add(new Dictionary(PriceUnit.class.getName(),"2","元/㎡/月","PriceUnit"));
        list.add(new Dictionary(PriceUnit.class.getName(),"3","元/㎡/年","PriceUnit"));
        return list;
    }
}
