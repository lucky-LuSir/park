package com.kfwy.park.pro.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by xiongzhan on 2018/6/14.
 * 对接人类型字典
 */
@Component
public class ButtJointType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(ButtJointType.class.getName(),"ButtJointType","对接人类型"));
        list.add(new Dictionary(ButtJointType.class.getName(),"1","招商","ButtJointType"));
        list.add(new Dictionary(ButtJointType.class.getName(),"2","物业","ButtJointType"));
        list.add(new Dictionary(ButtJointType.class.getName(),"3","保安","ButtJointType"));
        list.add(new Dictionary(ButtJointType.class.getName(),"4","清洁","ButtJointType"));
        return list;
    }
}
