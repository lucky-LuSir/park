package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luming
 * @Descirption 客户类型字典
 * @Date 2018/5/31
 */
@Component
public class CustomerType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerType.class.getName(),"customerType","客户类型"));
        list.add(new Dictionary(CustomerType.class.getName(),"1","公司","customerType"));
        list.add(new Dictionary(CustomerType.class.getName(),"2","个人","customerType"));
        return list;
    }
}
