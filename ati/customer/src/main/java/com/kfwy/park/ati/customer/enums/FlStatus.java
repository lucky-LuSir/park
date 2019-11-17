package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 跟进时楼层状态
 * @Date 2018/6/5 11:42
 */
@Component
public class FlStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FlStatus.class.getName(),"FlStatus","跟进时楼层状态"));
        list.add(new Dictionary(FlStatus.class.getName(),"1","可招商","FlStatus"));
        list.add(new Dictionary(FlStatus.class.getName(),"2","已出租","FlStatus"));
        return list;
    }
}
