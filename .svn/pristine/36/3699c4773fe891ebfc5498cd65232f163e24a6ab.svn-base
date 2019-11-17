package com.kfwy.park.ati.contract.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.IDic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 */
@Component
public class ReceivingChannel implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ReceivingChannel.class.getName(),"ReceivingChannel","收款渠道"));
        list.add(new Dictionary(ReceivingChannel.class.getName(),"1","支付宝","ReceivingChannel"));
        list.add(new Dictionary(ReceivingChannel.class.getName(),"2","微信","ReceivingChannel"));
        list.add(new Dictionary(ReceivingChannel.class.getName(),"3","银行转账","ReceivingChannel"));
        list.add(new Dictionary(ReceivingChannel.class.getName(),"4","账户余额","ReceivingChannel"));
        list.add(new Dictionary(ReceivingChannel.class.getName(),"5","现金","ReceivingChannel"));
        return list;
    }


}
