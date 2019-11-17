package com.kfwy.park.ati.agent.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by xiongzhan on 2018/6/2.
 */
@Component
public class  MiddlemanType implements DictionaryProvider {



    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(MiddlemanType.class.getName(),"menuType","中介联系人类型"));
        list.add(new Dictionary(MiddlemanType.class.getName(),"1","企业","MiddlemanType"));
        list.add(new Dictionary(MiddlemanType.class.getName(),"2","个人","MiddlemanType"));
        return list;
    }
}
