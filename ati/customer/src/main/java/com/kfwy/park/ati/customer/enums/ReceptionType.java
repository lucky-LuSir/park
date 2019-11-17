package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 客户带看类型字典
 * @Auth luming
 * @Date 2018/7/25 11:53
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class ReceptionType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ReceptionType.class.getName(),"ReceptionType","客户跟进类型"));
        list.add(new Dictionary(ReceptionType.class.getName(),"1","主动获客","ReceptionType"));
        list.add(new Dictionary(ReceptionType.class.getName(),"2","中介带看","ReceptionType"));
        return list;
    }
}
