package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 不合适原因
 * @Date 2018/6/4 19:01
 */
@Component
public class ImproperReason implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ImproperReason.class.getName(),"ImproperReason","不合适原因"));
        list.add(new Dictionary(ImproperReason.class.getName(),"1","园区不符合客户的要求","ImproperReason"));
        list.add(new Dictionary(ImproperReason.class.getName(),"2","客户不符合园区的要求","ImproperReason"));
        return list;
    }
}
