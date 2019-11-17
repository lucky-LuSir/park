package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Description 电费计费方式
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class ElectricityBillingType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ElectricityBillingType.class.getName(),"ElectricityBillingType","电费计费状态"));
        list.add(new Dictionary(ElectricityBillingType.class.getName(),"1","均价计费","ElectricityBillingType"));
        list.add(new Dictionary(ElectricityBillingType.class.getName(),"2","峰谷计费","ElectricityBillingType"));
        return list;
    }
}
