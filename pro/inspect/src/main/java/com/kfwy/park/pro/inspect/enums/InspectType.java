package com.kfwy.park.pro.inspect.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InspectType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(InspectType.class.getName(),"InspectType","检查类型"));
        list.add(new Dictionary(InspectType.class.getName(),"1","卫生检查","InspectType"));
        list.add(new Dictionary(InspectType.class.getName(),"2","安全巡检","InspectType"));
        return list;
    }
}
