package com.kfwy.park.act.activiti.emums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2019/4/12.
 * 退租责任类型
 */
@Component
public class DutyType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(DutyType.class.getName(),"dutyType","dutyType"));
        list.add(new Dictionary(DutyType.class.getName(),"1","甲方责任","dutyType"));
        list.add(new Dictionary(DutyType.class.getName(),"2","乙方责任","dutyType"));
        list.add(new Dictionary(DutyType.class.getName(),"3","双方无责","dutyType"));
        return list;
    }
}
