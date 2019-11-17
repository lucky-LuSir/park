

var G_menuList = [];


export function judgeMenu(pageItemid){
    var mFlag = false;
    //mFlag = true;测试用,7月31日测试完成前清理
    //1获取localhost中的treeMenuList
    var menuTreeList=JSON.parse(sessionStorage.getItem("menuTreeList"));

    var menutreeObj = null;
    if(menuTreeList) {
        menutreeObj = menuTreeList[0];
    }
    //2将树转化成list
    var menuArr = [];
    G_menuList = [];
    treeToList(menutreeObj);
    menuArr = G_menuList;
    //浏览器打印("看treeToList取出的菜单集合:123");
    //浏览器打印(menuArr);
    G_menuList = [];
    //3根据pageItemid判断是否有权限
    for(var i=0;i<menuArr.length;i++){
        var one = menuArr[i];
        var oneItemid = one.item;
        if(pageItemid== oneItemid){
            mFlag = true;
            return mFlag;
        }
    }
    return mFlag;
}

function treeToList(node){
    if (!node) {
        return;
    }

    G_menuList.push(node);
    if (node.children && node.children.length > 0) {
        var i = 0;
        for (i = 0; i < node.children.length; i++) {
            treeToList(node.children[i]);
        }
    }
}