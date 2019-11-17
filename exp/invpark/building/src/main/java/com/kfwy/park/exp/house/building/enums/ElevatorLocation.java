package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 电梯位置字典
 * @Auth luming
 * @Date 2018/11/5 15:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class ElevatorLocation implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ElevatorLocation.class.getName(),"ElevatorLocation","电梯位置"));
        list.add(new Dictionary(ElevatorLocation.class.getName(),"1","中间位置双开","ElevatorLocation"));
        list.add(new Dictionary(ElevatorLocation.class.getName(),"2","两头位置双开","ElevatorLocation"));
        list.add(new Dictionary(ElevatorLocation.class.getName(),"3","其他情况非双开","ElevatorLocation"));
        return list;
    }
}
