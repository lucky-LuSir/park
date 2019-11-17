package com.kfwy.park.act.activiti.emums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 审批状态字典
 */
@Component
public class ApprovalStatus implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ApprovalStatus.class.getName(),"approvalStatus","退租审批状态"));
        list.add(new Dictionary(ApprovalStatus.class.getName(),"1","审批中","approvalStatus"));
        list.add(new Dictionary(ApprovalStatus.class.getName(),"2","未通过","approvalStatus"));
        list.add(new Dictionary(ApprovalStatus.class.getName(),"3","已通过","approvalStatus"));
        list.add(new Dictionary(ApprovalStatus.class.getName(),"4","驳回","approvalStatus"));
        list.add(new Dictionary(ApprovalStatus.class.getName(),"5","同意","approvalStatus"));
        return list;
    }
}
