package com.kfwy.park.hrm.org.employee.dictionary;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 21:30
 * @Version 1.0
 */
@Component
public class EmployeeStatus implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(EmployeeStatus.class.getName(),EmployeeStatus.class.getSimpleName(),"员工状态"));
        dic.add(new Dictionary(EmployeeStatus.class.getName(),"1","在职",EmployeeStatus.class.getSimpleName()));
        dic.add(new Dictionary(EmployeeStatus.class.getName(),"2","离职",EmployeeStatus.class.getSimpleName()));

        return dic;
    }
}
