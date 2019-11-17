package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 楼层状态枚举类
 * @Date 2018/5/29 18:14
 */
@Component
public class FloorStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FloorStatus.class.getName(),"FloorStatus","楼层状态"));
        list.add(new Dictionary(FloorStatus.class.getName(),"1","热租中","FloorStatus"));
        list.add(new Dictionary(FloorStatus.class.getName(),"2","已租完","FloorStatus"));
        return list;
    }
}
