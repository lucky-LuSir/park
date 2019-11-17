package com.kfwy.park.hrm.org.employee.dictionary;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 15:12
 * @Version 1.0
 */
@Component
public class EducationType implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {

        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(EducationType.class.getName(), EducationType.class.getSimpleName(),"学历"));
        dic.add(new Dictionary(EducationType.class.getName(),"1","博士后", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"2","博士", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"3","硕士", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"4","本科", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"5","专科", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"6","高中", EducationType.class.getSimpleName()));
        dic.add(new Dictionary(EducationType.class.getName(),"7","初中", EducationType.class.getSimpleName()));
        return dic;
    }
}
