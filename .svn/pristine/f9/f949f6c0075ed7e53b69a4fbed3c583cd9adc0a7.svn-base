package com.kfwy.park.hrm.org.employee.dictionary;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 户籍类型
 * @Auth davidCun
 * @Date 2018/6/12 15:43
 * @Version 1.0
 */
@Component
public class HouseholdType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(HouseholdType.class.getName(),"houseHoldType","户籍类型"));
        dic.add(new Dictionary(HouseholdType.class.getName(),"1","农业","houseHoldType"));
        dic.add(new Dictionary(HouseholdType.class.getName(),"2","非农业","houseHoldType"));

        return dic;
    }
}
