package com.kfwy.park.hrm.org.employee.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.employee.business.IPostManager;
import com.kfwy.park.hrm.org.employee.dao.IPostDbDao;
import com.kfwy.park.hrm.org.employee.dao.IPostMenuDbDao;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;
import com.kfwy.park.hrm.org.employee.entity.PostMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 22:52
 * @Version 1.0
 */
@Service
public class PostManagerImpl extends AbstractManager<PostEntity> implements IPostManager {

    @Autowired
    private IPostDbDao postDbDao;

    @Autowired
    private IPostMenuDbDao postMenuDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return postDbDao;
    }


    @Transactional
    public int create(PostEntity postEntity, List<String> menus) {

        postEntity.setPostCode(CodeGenUtils.generate());

        int i = this.create(postEntity);

        this.createPostMenu(postEntity.getPostCode(),menus);

        return i;
    }

    @Transactional
    public int update(PostEntity post,List<String> menus){

        if (menus!=null && menus.size()>0){
            postMenuDbDao.deleteByPostCode(post.getPostCode());
        }

        int i = postDbDao.update(post);

        createPostMenu(post.getPostCode(),menus);

        return i;
    }

    private void createPostMenu(String postCode,List<String> menus){
        List<PostMenuEntity> list = new ArrayList<PostMenuEntity>();

        for (String menu : menus){
            PostMenuEntity pme = new PostMenuEntity();
            pme.setPostCode(postCode);
            pme.setMenuCode(menu);
            //pme.setCpyCode(CurrentContext.getCpyCode());

            list.add(pme);
        }

        postMenuDbDao.batchInsert(list);
    }

}
