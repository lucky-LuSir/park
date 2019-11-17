package com.kfwy.park.hrm.org.dept.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 部门类型字典
 * @Date 2018/5/31 15:29
 */
@Component
public class DeptType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(DeptType.class.getName(),"DeptType","部门类型"));
        list.add(new Dictionary(DeptType.class.getName(),"1","业务A部门","DeptType"));
        list.add(new Dictionary(DeptType.class.getName(),"2","业务B部门","DeptType"));
        list.add(new Dictionary(DeptType.class.getName(),"3","业务C部门","DeptType"));
        return list;
    }
}
