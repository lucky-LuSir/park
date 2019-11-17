package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 客户跟进类型
 * @Date 2018/6/4 18:25
 */
@Component
public class FollowupType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FollowupType.class.getName(),"FollowupType","客户跟进类型"));
        list.add(new Dictionary(FollowupType.class.getName(),"1","电话拜访","FollowupType"));
        list.add(new Dictionary(FollowupType.class.getName(),"2","客户来访","FollowupType"));
        return list;
    }
}
