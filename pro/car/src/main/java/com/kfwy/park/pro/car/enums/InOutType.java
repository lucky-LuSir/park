package com.kfwy.park.pro.car.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gengtao
 * @date 2019/7/24 17:35
 **/

@Component
public class InOutType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(InOutType.class.getName(),"InOutType","进出类型"));
        list.add(new Dictionary(InOutType.class.getName(),"1","车辆进出","InOutType"));
        list.add(new Dictionary(InOutType.class.getName(),"2","人员进出","InOutType"));
        return list;
    }
}