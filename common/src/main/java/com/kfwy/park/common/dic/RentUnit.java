package com.kfwy.park.common.dic;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 租金单价字典
 * @author luming
 * @Date 2019/4/19 8:55
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class RentUnit implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(RentUnit.class.getName(),"rentUnit","租金单位"));
        list.add(new Dictionary(RentUnit.class.getName(),"1","元/天","rentUnit"));
        list.add(new Dictionary(RentUnit.class.getName(),"2","元/月","rentUnit"));
        list.add(new Dictionary(RentUnit.class.getName(),"3","元/年","rentUnit"));
        return list;
    }
}
