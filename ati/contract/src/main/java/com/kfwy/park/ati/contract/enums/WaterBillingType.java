package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 水表计费方式字典
 * @Auth luming
 * @Date 2019/8/6 11:30
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class WaterBillingType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(WaterBillingType.class.getName(),"waterBillingType","电费计费状态"));
        list.add(new Dictionary(WaterBillingType.class.getName(),"1","均价计费","waterBillingType"));
        list.add(new Dictionary(WaterBillingType.class.getName(),"2","抄表计费","waterBillingType"));
        return list;
    }
}
