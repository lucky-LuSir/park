package com.kfwy.park.ati.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/9/20.
 */
@Component
public class ChannelStatus implements DictionaryProvider {
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ChannelStatus.class.getName(),"channelStatus","客户类型"));
        list.add(new Dictionary(ChannelStatus.class.getName(),"1","个人中介","channelStatus"));
        list.add(new Dictionary(ChannelStatus.class.getName(),"2","公司中介","channelStatus"));
        list.add(new Dictionary(ChannelStatus.class.getName(),"3","管委会","channelStatus"));
        list.add(new Dictionary(ChannelStatus.class.getName(),"4","房东","channelStatus"));
        list.add(new Dictionary(ChannelStatus.class.getName(),"5","其他","channelStatus"));
        return list;
    }
}
