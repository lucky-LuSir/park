package com.kfwy.park.exp.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 * @Description 大房东合同状态
 * @author luming
 * @Date 2019/4/19 9:03
 * @Version 1.0
 * @Param
 * @return
 */
@Component
public class LdCtStatus implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(LdCtStatus.class.getName(),"CtStatus","合同状态"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"1","待提交","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"2","一审提交","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"3","一审通过","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"4","二审通过","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"5","驳回","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"6","撤回","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"7","已退租","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"8","已改签","CtStatus"));
        list.add(new Dictionary(LdCtStatus.class.getName(),"9","已重申","CtStatus"));
        return list;
    }
}
