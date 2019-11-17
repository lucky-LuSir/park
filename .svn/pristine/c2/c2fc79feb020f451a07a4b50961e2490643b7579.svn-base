package com.kfwy.park.exp.house.building.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class InvFollowupType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(InvFollowupType.class.getName(),"InvFollowupType","拓展跟进类型"));
        list.add(new Dictionary(InvFollowupType.class.getName(),"1","电话咨询","InvFollowupType"));
        list.add(new Dictionary(InvFollowupType.class.getName(),"2","实地拜访","InvFollowupType"));
        return list;
    }

}
