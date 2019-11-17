package com.kfwy.park.sys.msg.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luming on 2019/6/21.
 * 客户渠道字典
 */
@Component
public class ChannelType implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ChannelType.class.getName(),"channelType","客户渠道"));
        list.add(new Dictionary(ChannelType.class.getName(),"1","58端口","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"2","户外横幅","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"3","小广告/传单","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"4","微信朋友圈","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"5","园区建筑广告","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"6","电话拨打","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"7","中介","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"8","兼职/转介绍","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"9","其他","channelType"));
        list.add(new Dictionary(ChannelType.class.getName(),"10","库房无忧","channelType"));
        return list;
    }
}
