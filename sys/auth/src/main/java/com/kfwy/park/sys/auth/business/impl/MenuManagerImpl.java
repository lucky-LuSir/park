package com.kfwy.park.sys.auth.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.park.sys.auth.business.IMenuManager;
import com.kfwy.park.sys.auth.dao.IMenuDbDao;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@Service
public class MenuManagerImpl extends AbstractManager<MenuEntity> implements IMenuManager {

    @Autowired
    private IMenuDbDao menuDbDao;


    @Override
    protected IMyBatisBaseDao<MenuEntity, Long> getMyBatisDao() {
        return this.menuDbDao;
    }

    @Override
    protected void beforeCreate(MenuEntity menuEntity) {
        menuEntity.setMenuCode(CodeGenUtils.generate());

        /**
         * 如果是顶级菜单就默认设置为0
         */
        if (menuEntity.getParentCode()==null){
            menuEntity.setParentCode("0");
        }
    }

    @Override
    protected void beforeUpdate(MenuEntity menuEntity) {

        if (StringUtils.isEmpty(menuEntity.getMenuCode())
                && menuEntity.getId()==null){
            throw new RestBusinessException("更新数据，menuCode或者ID不能都为空");
        }
    }


    @Override
    public List<MenuEntity> findUserMenuToTreeByUserCode(String userCode) {

        if (StringUtils.isEmpty(userCode)){
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectUserMenuByRoleRelation(userCode);

        List<MenuEntity> menus2 = menuDbDao.selectUserMenuByPostRelation(userCode);

        menus.addAll(menus2);

        Map<String,MenuEntity> map = new HashMap<String,MenuEntity>();

        for (MenuEntity me : menus) {
            map.put(me.getMenuCode(),me);
        }

        List<MenuEntity> mn = new ArrayList<MenuEntity>();

        for (Map.Entry<String,MenuEntity> entry: map.entrySet()) {

            mn.add(entry.getValue());

        }

        return processMenuToTree(mn);
    }

    /**
     * @Description 描述:目前根据岗位查询菜单列表,目前不用转化为树
     * @Auth xpp
     * @Date 2018/7/25 15:05
     * @Version 1.0
     * @Param [postCode]
     * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
     **/
    @Override
    public List<MenuEntity> findUserMenuToListByPostRelation(String userCode) {

        if (StringUtils.isEmpty(userCode)){
            throw new RestBusinessException("查询用户菜单权限，岗位编码不能为空.");
        }

        List<MenuEntity> menus = menuDbDao.selectUserMenuByPostRelation(userCode);

        return menus;
    }



    /**
    * @Description 描述:把List<MenuEntity>转成(树)
    * @Auth xpp
    * @Date 2018/7/25 16:04
    * @Version 1.0
    * @Param [menuList]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    @Override
    public List<MenuEntity> menuListToTree(List<MenuEntity> menuList) {

        return processMenuToTree(menuList);
    }


    @Override
    public List<MenuEntity> findAllMenuTree() {

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("isDeleted",Boolean.FALSE);
//        param.put("cpyCode", CurrentContext.getCpyCode());

        List<MenuEntity> menus = this.findByMap(param,"sort",true);

        return processMenuToTree(menus);
    }

    @Override
    public List<MenuEntity> findMenuListByPost(String postCode) {
        if (StringUtils.isEmpty(postCode)){
            throw new RestBusinessException("查询菜单权限，岗位编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectMenuListByPost(postCode);

        return menus;
    }

    @Override
    public List<MenuEntity> findMenuListByRole(String roleCode) {
        if (StringUtils.isEmpty(roleCode)){
            throw new RestBusinessException("查询菜单权限，角色编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectMenuListByRole(roleCode);

        return menus;
    }

    @Override
    public List<MenuEntity> findUserMenuToListByRoleRelation(String userCode) {
        if (StringUtils.isEmpty(userCode)){
            throw new RestBusinessException("查询用户菜单权限，传入用户编码不能为空.");
        }

        List<MenuEntity> menus = menuDbDao.selectUserMenuByRoleRelation(userCode);

        return menus;
    }


    private List<MenuEntity> processMenuToTree(List<MenuEntity> menus){

        Map<String,MenuEntity> map = new HashMap<String,MenuEntity>();
        List<MenuEntity> result = new ArrayList<MenuEntity>();

        for (MenuEntity m : menus){
            map.put(m.getMenuCode(),m);

            if(StringUtils.isEmpty(m.getParentCode())
                    || m.getParentCode().equals("0")){
                result.add(m);
            }
        }

        for (Map.Entry<String,MenuEntity> entry : map.entrySet()){
            MenuEntity me = map.get(entry.getValue().getParentCode());
            if(me != null){
                me.getChildren().add(entry.getValue());
                //根据sort字段对树进行排序
                sortList(me.getChildren());
            }
        }

        return result;

    }

    /**
     * @Description 对集合进行排序（本排序是根据MenuEntity中的sort字段升序，如果相同则比较id）
     * @author wangchuang
     * @Date 2018/12/12 9:51
     * @Version 1.0
     * @Param [list]
     * @return void
     **/
    public void sortList(List<MenuEntity> list) {

        //传入的集合进行排序
        Collections.sort(list, new Comparator<MenuEntity>() {
            @Override
            public int compare(MenuEntity o1, MenuEntity o2) {
                //根据排序字段排序
                Integer sort1 = 0;
                Integer sort2 = 0;
                if(o1.getSort() != null) {
                    sort1 = o1.getSort();
                }
                if(o2.getSort() != null) {
                    sort2 = o2.getSort();
                }
                //若排序字段为空则为0，菜单排在前面
                int i = sort1 - sort2;
                //如果排序字段相同，则根据新增顺序排
                if(i == 0) {
                    return (int) (o1.getId() - o2.getId());
                }
                return i;
            }
        });

    }

    /**
     * @Description 描述:获取用户所有菜单(包含岗位,角色中的菜单)
     * @Auth xpp
     * @Date 2018/8/31 9:44
     * @Version 1.0
     * @Param [userCode]
     * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
     **/
    @Override
    public List<MenuEntity> getMenuResult(String userCode,String empCode) {
        //a.1 从人员岗位关系中获取叶子权限
        List<MenuEntity> postMenuListA = new ArrayList<MenuEntity>();
        postMenuListA = this.findUserMenuToListByPostRelation(userCode);

        //a.2 从人员配置中获取额外叶子权限
        String uEmpCode = empCode;
        List<MenuEntity> postMenuListB = new ArrayList<MenuEntity>();
        postMenuListB = this.findUserMenuToListByRoleRelation(userCode);

        //a.3 总叶子权限去重,再合并到postMenuListA
        postMenuListA.removeAll(postMenuListB);
        postMenuListA.addAll(postMenuListB);
        //a.4 根据总叶子菜单,获得完整叶子菜单
        //一个叶子的时候,获取叶子这一条线
        List<MenuEntity> entireMenuList = new ArrayList<MenuEntity>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentCode", "0");
        param.put("isDeleted", false);
        MenuEntity one = new MenuEntity();
        one = this.findOne(param);
        entireMenuList.add(one);


        for(int i=0;i<postMenuListA.size();i++){
            MenuEntity leaf = new MenuEntity();
            leaf = postMenuListA.get(i);
            List<MenuEntity> partList = recursive(leaf);
            entireMenuList.addAll(partList);
        }

        //a.5 根据总叶子权限递归遍历获得完整权限树
        List<MenuEntity> menuTreeResult = new ArrayList<MenuEntity>();
        menuTreeResult = this.menuListToTree(entireMenuList);

        return menuTreeResult;
    }

    /**
     * @Description 描述:递归,传入子类实体,返回所有父类
     * @Auth xpp
     * @Date 2018/7/26 9:32
     * @Version 1.0
     * @Param [inMenu]
     * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
     **/
    public List<MenuEntity> recursive(MenuEntity inMenu) {

        String parentCode = inMenu.getParentCode();
        if(parentCode.equals("0")){
            return new ArrayList<MenuEntity>();
        }else{
            List<MenuEntity> tempList = new ArrayList<MenuEntity>();
            tempList.add(inMenu);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("menuCode", inMenu.getParentCode());
            param.put("isDeleted", false);
            MenuEntity one = new MenuEntity();
            one = this.findOne(param);
            tempList.addAll(recursive(one));
            return tempList;
        }
    }

}
