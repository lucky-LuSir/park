<template>
    <div class="rootcss" ref="myRoot">

        <!-- 布局 -->
        <el-container>
            <!-- 头部 -->
            <el-header>
                <div class="navigation-menu">
                    <el-row :gutter="0">
                        <!-- logo -->
                        <el-col style="width: 160px;" v-if="mainObj.userObj.cpyImg == 'null'">
                            <img src="./img/logo.png" width="150px" style="margin-top: 3px;float: left;margin-left: 5px;">
                        </el-col>
                        <el-col style="width: 160px;" v-if="mainObj.userObj.cpyImg != 'null'">
                            <img :src=" parkBaseUrl + 'file/read/' + mainObj.userObj.cpyImg" width="150px" style="margin-top: 0px;float: left;margin-left: 1px;">
                        </el-col>
                        <el-dropdown class="user-info-select">
                            <span class="username-span">
                                {{ mainObj.userObj.userName }}
                                <i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu class="dropdownMenu" slot="dropdown">
                                <el-dropdown-item class="changepswParent">
                                    <el-button class="changepsw" @click="mainObj.dialogFormVisible = true" type="text">
                                        修改密码
                                    </el-button>
                                </el-dropdown-item>
                                <el-dropdown-item class="logoutParent">
                                    <el-button class="logout" @click="logout()" type="text">退出</el-button>
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>

                        <!-- 通知徽标 -->
                        <el-badge :value="messageCount" :max="9"
                                  :hidden="messageCount == 0 ? true : false"
                                  class="badge_item"
                                  v-if="mainObj.userObj.cpyCode == '180904868bbcb902de4b'">
                            <el-dropdown>
                                <span style="margin-right: 5px;">
                                    <i class="el-icon-bell" slot="reference" style="color: white; font-size: 20px; margin-top: 5px;"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>
                                        <el-button type="text" @click="openActivitiThrowLease(1)">
                                            退租单审批（
                                            <span class="msg_activiti">{{ throwLeaseApplyTaskCount}}</span>）
                                        </el-button>
                                    </el-dropdown-item>
                                    <el-dropdown-item>
                                        <el-button type="text" @click="openActivitiThrowLease(2)">
                                            退款单审批（
                                            <span class="msg_activiti">{{ throwLeasePayApplyTaskCount}}</span>）
                                        </el-button>
                                    </el-dropdown-item>
                                    <el-dropdown-item>
                                        <el-button type="text" @click="openActivitiLandlord()">
                                            大房东合同审批（
                                            <span class="msg_activiti">{{ landlordContractApplyTaskCount }}</span>）
                                        </el-button>
                                    </el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </el-badge>
                    </el-row>
                </div>
            </el-header>

            <!-- 主体 -->
            <el-container>
                <!-- 左侧菜单 -->
                <el-aside class="aside_style">
                    <el-scrollbar>
                        <el-menu id="asideMenu" background-color="#404040" :collapse="isCollapse" text-color="#fff" active-text-color="#ffd04b">
                            <div v-for="(item, index) in mainObj.menuTreeList" class="menu_tree_info" :key="index">
                                <div v-if="item1.menuType === '1'" v-for="(item1, index1) in item.children" @click="menuClick(item1)" :key="index1">
                                    <!-- 一级菜单下无子菜单 -->
                                    <el-menu-item :index="item1.menuCode" v-if="item1.children.length == 0 && item1.menuType == '1' && item1.level == '1'">
                                        <i :class="item1.iconCls"></i>
                                        <span slot="title">{{ item1.menuName }}</span>
                                        <!-- <template slot="title">
                                            <i :class="item1.iconCls"></i>
                                            <span>{{ item1.menuName }}</span>
                                        </template> -->
                                    </el-menu-item>

                                    <!-- 一级菜单下有子菜单 -->
                                    <el-submenu :index="item1.menuCode" v-if="item1.children.length > 0 && item1.level == '1' && item1.menuType === '1' &&  item1.children[0].menuType === '1'">
                                        <template slot="title">
                                            <i :class="item1.iconCls"></i>
                                            <span>{{ item1.menuName }}</span>
                                        </template>

                                        <div v-if="item2.menuType === '1'" v-for="(item2, index2) in item1.children" @click="menuClick(item2)" :key="index2">

                                            <!-- 二级菜单下无子级菜单 -->
                                            <el-menu-item :index="item2.menuCode" v-if="item2.menuType === '1' && item2.level == '2' && (item2.children.length == 0 || item2.children[0].menuType === '2')">
                                                <template slot="title">
                                                    <span style="margin-left: 12px;">{{ item2.menuName }}</span>
                                                </template>
                                            </el-menu-item>

                                            <!-- 二级菜单下有子级菜单 -->
                                            <el-submenu :index="item2.menuCode" v-if="item2.menuType === '1' && item2.children.length > 0 && item2.children[0].menuType === '1'">
                                                <template slot="title">
                                                    <span style="margin-left: 12px;">{{item2.menuName}}</span>
                                                </template>

                                                <div v-if="item3.menuType === '1'" v-for="(item3, index3) in item2.children" @click="menuClick(item3)" :key="index3">
                                                    <!-- 三级菜单下无子菜单 -->
                                                    <el-menu-item :index="item3.menuCode" v-if="item3.menuType === '1' && item3.level == '3'">
                                                        <template slot="title">
                                                            <span style="margin-left: 10px;">{{ item3.menuName }}</span>
                                                        </template>
                                                    </el-menu-item>

                                                    <el-submenu :index="item3.menuCode" v-if="item3.menuType === '1' && item3.children.length > 0 && item3.children[0].menuType === '1'">
                                                        <template slot="title">
                                                            <span style="margin-left: 10px;">{{item3.menuName}}</span>
                                                        </template>
                                                        <div v-if="item4.menuType === '1'" v-for="(item4,index4) in item3.children" @click="menuClick(item4)" :key="index4">
                                                            <el-submenu :index="item4.menuCode">
                                                                <template slot="title">
                                                                    <span style="margin-left: 10px;">{{item4.menuName}}</span>
                                                                </template>
                                                            </el-submenu>
                                                        </div>
                                                    </el-submenu>
                                                </div>
                                            </el-submenu>
                                        </div>
                                    </el-submenu>
                                </div>
                            </div>
                        </el-menu>
                        <div class="suofang">
                            <i ref="foldBtn" @click="cebianlansuo()" class="fold el-icon-diy-shouqicaidan"></i>
                        </div>
                    </el-scrollbar>
                </el-aside>
                <!-- 右侧内容区 -->
                <el-main id="globalMain">
                    <div class="mainBox" ref="myWidth">
                        <el-dropdown class="site-tabs__tools" :show-timeout="0">
                            <i class="el-icon-arrow-down el-icon--right"></i>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click.native="tabsCloseCurrentHandle">关闭当前标签页</el-dropdown-item>
                                <el-dropdown-item @click.native="tabsCloseOtherHandle">关闭其它标签页</el-dropdown-item>
                                <el-dropdown-item @click.native="clearTab">关闭全部标签页</el-dropdown-item>
                                <el-dropdown-item @click.native="tabsRefreshCurrentHandle">刷新当前标签页</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                        <!-- <div class="tabTools">
                            <i class="el-icon-diy-home"></i>
                            <i class="el-icon-diy-shuaxin"></i>
                            <i class="el-icon-diy-chuyidong"></i>
                            <i class="el-icon-diy-quanping"></i>
                        </div> -->
                        <el-tabs v-model="mainObj.editableTabsValue" type="card" closable @tab-remove="removeTab" class="el_tabs_style">
                            <el-tab-pane :style="{height:mainObj.floatDivHeight+'px'}" v-for="(item, index) in mainObj.editableTabs" lazy :key="item.name" :label="item.title" :name="item.name">
                                <!--注释掉页签刷新按钮-->
                                <!--<span slot="label"><i class="el-icon-refresh" @click.stop="refresh()" ></i>-->
                                <!--{{item.title}}-->
                                <!--</span>-->
                                <component v-if="index != mainObj.showComponent"
                                           v-bind:is="item.content"
                                           @backParam="backParam($event)"
                                           @waitApprovalTask="getWaitApprovalTaskCount"
                                           :paramsObj="item.param">
                                </component>
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                </el-main>
            </el-container>
        </el-container>

        <el-dialog title="修改密码" :visible.sync="mainObj.dialogFormVisible" width="50%" center>
            <el-form :model="mainObj.updatePwd" ref="updatePwd" :rules="rules" label-width="100px">
                <el-row :gutter="20">
                    <el-col :span="20">
                        <div class="grid-content">
                            <el-form-item label="原密码：" prop="oldPwd">
                                <el-input v-model="mainObj.updatePwd.oldPwd" type="password" clearable></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20">
                        <div class="grid-content">
                            <el-form-item label="新密码：" prop="newPwd">
                                <el-input v-model="mainObj.updatePwd.newPwd" type="password" clearable></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20">
                        <div class="grid-content">
                            <el-form-item label="确认密码：" prop="confirmPwd">
                                <el-input v-model="mainObj.updatePwd.confirmPwd" type="password" clearable></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="resetForm('updatePwd')">取 消</el-button>
                <el-button type="primary" @click="submitForm('updatePwd')">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script scoped>
    // import ElCol from "element-ui/packages/col/src/col";
    const ElCol = () =>
        import(
            /* webpackChunkName: "loading" */
            "element-ui/packages/col/src/col"
        );

    // import login from "../../sys/views/login/login";
    const login = () =>
        import(
            /* webpackChunkName: "loading" */
            "../../sys/views/login/login"
        );
    // import forgetPwd from "../../sys/views/login/forgetPwd";
    const forgetPwd = () =>
        import(
            /* webpackChunkName: "loading" */
            "../../sys/views/login/forgetPwd"
        );

    // import mainPage from "../../common/views/mainPage";
    const mainPage = () =>
        import(
            /* webpackChunkName: "loading" */
            "../../common/views/mainPage"
        );
    // import homeManager from "../../common/views/mainList";
    const mainList = () =>
        import(
                /* webpackChunkName: "loading" */
             "../../common/views/mainList"
            );
    const homeManager = () =>
        import(
            /* webpackChunkName: "loading" */
            // "../../common/views/mainList"
            "../../common/views/homePage"
        );
    // import back from "../../common/views/back";
    const back = () =>
        import(
            /* webpackChunkName: "loading" */
            "../../common/views/back"
        );

    // 拓展管理
    import expandList from "../../exp/views/expand/expandList";
    import expandCreate from "../../exp/views/expand/expandCreate";
    import expandDetail from "../../exp/views/expand/expandDetail";
    import expandEdit from "../../exp/views/expand/expandEdit";
    import expandFollowupList from "../../exp/views/expand/expandFollowupList.vue";
    import expandTodayTaskList from "../../exp/views/expand/expandTodayTaskList.vue";
    import landlordContractList from "../../exp/views/landlord/landlordContractList.vue";
    import landlordContractCreate from "../../exp/views/landlord/landlordContractCreate.vue";
    import landlordContractDetail from "../../exp/views/landlord/landlordContractDetail.vue";
    import landlordContractEdit from "../../exp/views/landlord/landlordContractEdit.vue";

    // 园区管理
    import parkList from "../../pro/views/park/parkList";
    import parkCreate from "../../pro/views/park/parkCreate";
    import parkEdit from "../../pro/views/park/parkEdit";
    import parkDetails from "../../pro/views/park/parkDetails";
    import buildingCreate from "../../pro/views/building/buildingCreate";
    import buildingEdit from "../../pro/views/building/buildingEdit";
    import buildingDetails from "../../pro/views/building/buildingDetails";
    import floorCreate from "../../pro/views/floor/floorCreate";
    import floorEdit from "../../pro/views/floor/floorEdit";
    import floorDetails from "../../pro/views/floor/floorDetails";
    import roomCreate from "../../pro/views/room/roomCreate";
    import roomDetails from "../../pro/views/room/roomDetails";
    import roomEdit from "../../pro/views/room/roomEdit";

    // 招商管理
    import customerCreate from "../../ati/views/customer/customerCreate";
    import customerList from "../../ati/views/customer/customerList";
    import customerDetail from "../../ati/views/customer/customerDetail";
    import customerEdit from "../../ati/views/customer/customerEdit";
    import followupCreate from "../../ati/views/customer/followup/followupCreate";
    import followupList from "../../ati/views/customer/followup/followupList";
    import channelList from "../../ati/views/customer/channel/channelList";
    import channelCpyList from "../../ati/views/customer/channel/channelCpy";
    import channelFollowupList from "../../ati/views/customer/channel/channelFollowup";
    import atiTaskList from "../../ati/views/customer/task/taskList";
    import atiTaskTarget from "../../ati/views/customer/task/atiTaskTarget";

    // 企业入驻管理
    import contractList from "../../ati/views/contract/contractList";
    import contractDetails from "../../ati/views/contract/contractDetails";
    import contractCreate from "../../ati/views/contract/contractCreate";
    import contractEdit from "../../ati/views/contract/contractEdit";
    import contractBasicEdit from "../../ati/views/contract/contractBasicEdit";
    import manualMeter from "../../ati/views/contract/manualMeter";
    import costDetail from "../../ati/views/contract/costDetail";
    import parkingAdd from "../../ati/views/contract/parkingAdd";
    import firstVerifyList from "../../ati/views/contract/firstVerifyList";
    import secondVerifyList from "../../ati/views/contract/secondVerifyList";

    // 物业管理
    import repairManager from "../../property/views/repairManager";
    import safetyInspection from "../../property/views/safetyInspection";
    import inOutRegister from "../../property/views/inOutRegister";
    import cleanCheck from "../../property/views/cleanCheck";
    import proTemplateManager from "../../property/views/proTemplateManager";
    import proTaskManager from "../../property/views/proTaskManager";
    import dailyCommunication from "../../property/views/dailyCommunication"
    import problemFeedback from "../../property/views/feedback/problemFeedback"
    import carManager from "../../property/views/carManagerList"
    import propertyEleMeter from "../../property/views/propertyEleMeter";
    import electricityRecords from "../../property/views/electricityRecords";
    import propertyWaterMeter from "../../property/views/propertyWaterMeter";
    import waterRecords from "../../property/views/waterRecords";

    // 增值服务管理
    import convenientStore from "../../valueAddedServices/views/convenientStore";
    import chargingPile from "../../valueAddedServices/views/chargingPile";
    import advertisingSpace from "../../valueAddedServices/views/advertisingSpace";
    import decoration from "../../valueAddedServices/views/decoration";
    import eia from "../../valueAddedServices/views/eia";
    import rentStaging from "../../valueAddedServices/views/rentStaging";
    import corporateLoan from "../../valueAddedServices/views/corporateLoan";

    // 报表管理
    import grossList from "../../bi/views/gross";
    import channelReport from "../../bi/views/channelReport";
    import merchantsReport from "../../bi/views/merchantsReport";
    import accountReceivableReport from "../../bi/views/accountReceivableReport";
    import waterReceivableReport from "../../bi/views/waterReceivableReport";
    import electricityReceivableReport from "../../bi/views/electricityReceivableReport";
    import firstReceivableReport from "../../bi/views/firstReceivableReport";
    import parkCustomInfoReport from "../../bi/views/parkCustomInfoReport";
    import parkFollowCountReport from "../../bi/views/parkFollowCountReport.vue";
    import expiredCustomReport from "../../bi/views/expiredCustomReport.vue";
    import premiumReport from "../../bi/views/premiumReport.vue";
    import landlordReceivableReport from "../../bi/views/landlordreceivableReport.vue";

    //工作流管理
    import throwLeaseAgreement from "../../act/views/workflow/throwLeaseAgreement.vue";
    import throwLeaseApplyLog from "../../act/views/workflow/throwLeaseApplyLog.vue";
    import throwLeasePayApplyLog from "../../act/views/workflow/throwLeasePayApplyLog.vue";
    import landlordRentApply from "../../act/views/workflow/landlordRentApply.vue";
    import landlordRentApplyLog from "../../act/views/workflow/landlordRentApplyLog.vue";

    // 数据分析
    import customerAnalysis from "../../bi/views/analysis/customerAnalysis.vue"

    // 组织结构
    import employeeList from "../../hrm/views/emp/empList";
    import empCreate from "../../hrm/views/emp/empCreate";
    import empEdit from "../../hrm/views/emp/empEdit";
    import empDetails from "../../hrm/views/emp/empDetails";
    import deptList from "../../hrm/views/dept/deptList";
    import postList from "../../hrm/views/post/postList";
    import roleList from "../../hrm/views/role/roleList";

    // 权限管理
    import dividePostList from "../../hrm/views/post/postDivide";
    import divideRoleList from "../../hrm/views/role/roleDivide";

    // 系统管理
    import myCpy from "../../hrm/views/myCpy/cpyInfo.vue"
    import processDeployment from "../../act/views/activiti/processDeployment";
    import areaList from "../../hrm/views/cpyArea/cpyAreaList";
    import userList from "../../hrm/views/user/userList";
    import parkAreaAnalysis from "../../hrm/views/tool/parkAreaAnalysis.vue"
    import floorInfoAnalysis from "../../hrm/views/tool/floorInfoAnalysis.vue"
    import bargainMsg from "../../sys/views/msg/bargainMsg.vue"
    import smsTemplate from "../../sys/views/sms/smsTemplate.vue";
    import smsSendHistory from "../../sys/views/sms/smsSendHistory.vue";
//    import payPage from "../../sys/views/pay/payPage";

    // 全局配置
    import menuList from "../../hrm/views/menu/menuList";
    import cpyList from "../../hrm/views/cpy/cpyList";

    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";

    export default {
        components: {
            ElButton,
            ElCol,
            login,
            forgetPwd,
            mainPage,
            mainList,
            homeManager,
            back,

            /*-------------------------------- 项目拓展 --------------------------------*/
            expandList, // 拓展列表
            expandCreate, // 拓展新增
            expandDetail, // 拓展详情
            expandEdit, // 拓展编辑
            expandFollowupList, // 拓展跟进列表
            expandTodayTaskList, // 拓展日常工作
            landlordContractList, // 大房东合同列表
            landlordContractCreate, // 新增大房东合同
            landlordContractDetail, // 大房东合同详情
            landlordContractEdit, //修改合同

            /*-------------------------------- 园区概况 --------------------------------*/
            parkList, // 园区列表-
            parkCreate, // 园区新增-
            parkEdit, // 园区修改-
            parkDetails, // 园区详情-
            buildingCreate, //  楼栋新增
            buildingEdit, // 楼栋修改
            buildingDetails, // 楼栋详情-
            floorCreate,  // 楼层新增
            floorEdit, // 楼层修改
            floorDetails, // 楼层详情
            roomCreate, 
            roomDetails,
            roomEdit,

            /*-------------------------------- 招商管理 --------------------------------*/
            customerCreate, // 客户新增
            customerList, // 客户列表
            customerDetail, // 客户详情
            customerEdit, // 客户修改
            followupCreate, //客户跟进新增
            followupList, // 客户跟进列表
            channelList, // 渠道列表
            channelCpyList, // 渠道单位列表
            channelFollowupList, // 渠道跟进列表
            atiTaskList, // 招商日常
            atiTaskTarget, //招商指标

            /*-------------------------------- 入驻企业 --------------------------------*/
            contractList, // 合同列表
            contractDetails, // 合同详情
            contractCreate, // 合同新增
            contractEdit, // 合同编辑
            contractBasicEdit, //合同基础信息更改
            manualMeter, // 抄表
            costDetail, // 费用详情
            parkingAdd, // 停车位
            firstVerifyList, // 一审
            secondVerifyList, // 二审

            /*-------------------------------- 物业管理 --------------------------------*/
            inOutRegister, // 进出管理
            safetyInspection, // 安保管理
            cleanCheck, // 卫生检查
            proTemplateManager, // 物业评分模板管理
            proTaskManager,//物业评分分配任务管理
            propertyWaterMeter, // 抄水表-
            propertyEleMeter, // 抄电表-
            dailyCommunication, //日常沟通
            problemFeedback, //问题反馈
            repairManager, // 报修管理
            carManager, //园区车辆管理
            electricityRecords,// 电表抄表记录
            waterRecords,// 电表抄表记录

            /*-------------------------------- 增值服务 --------------------------------*/
            convenientStore, // 便利店
            chargingPile, // 充电桩
            advertisingSpace, // 广告位
            decoration, // 装修
            eia, // 环评
            rentStaging, // 租金分期
            corporateLoan, // 企业贷款

            /*-------------------------------- 报表 --------------------------------*/
            grossList, // 毛利率报表-
            channelReport, // 渠道报表-
            merchantsReport, // 过程指标报表-
            accountReceivableReport, // 应收租金报表-
            waterReceivableReport, // 应收水费报表-
            electricityReceivableReport, // 应收电费报表-
            firstReceivableReport, // 首笔款应收电费报表-
            parkCustomInfoReport, // 园区用户信息报表-
            parkFollowCountReport, // 多次带看报表-
            expiredCustomReport, // 到期租户报表-
            premiumReport, // 保险费报表-
            landlordReceivableReport, //大房东报表-

            /*-------------------------------- 工作流 --------------------------------*/
            throwLeaseAgreement, //退租协议
            throwLeaseApplyLog, //退租申请记录
            throwLeasePayApplyLog, //退款申请记录
            landlordRentApply, //大房东房租申请
            landlordRentApplyLog, //大房东申请记录

            /*-------------------------------- 数据分析 --------------------------------*/
            customerAnalysis, // 招商分析

            /*-------------------------------- 组织结构 --------------------------------*/
            employeeList, // 人员列表
            empDetails, // 人员详情
            empCreate, // 人员新增
            empEdit, // 人员修改

            /*-------------------------------- 权限管理 --------------------------------*/
            dividePostList, // 岗位分配
            divideRoleList, // 角色分配
            deptList, // 部门列表
            postList, // 岗位列表
            roleList, // 角色列表

            /*-------------------------------- 系统管理 --------------------------------*/
            myCpy, // 我的公司-
            processDeployment, // 流程部署管理-
            areaList, // 地区列表-
            userList, // 用户列表-
            parkAreaAnalysis, //园区数据异常校验-
            floorInfoAnalysis, //园区楼层信息-
            bargainMsg, // 成交通知
            smsTemplate, // 短信模板-
            smsSendHistory, // 短信发送历史-
//            payPage, //网上支付

            /*-------------------------------- 全局配置 --------------------------------*/
            menuList, // 菜单列表
            cpyList, // 公司列表
        },
        created() {
            let vm = this;
            var mainObj = window.sessionStorage.getItem("_mainObj");

            if (mainObj) {
                vm.mainObj = JSON.parse(mainObj);
            }

            vm.mainObj.userObj.userName = vm.$cookieStore.getCookie("userName");
            vm.mainObj.userObj.cpyName = vm.$cookieStore.getCookie("cpyName");
            vm.mainObj.userObj.cpyCode = vm.$cookieStore.getCookie("cpyCode");
            vm.mainObj.userObj.cpyImg = vm.$cookieStore.getCookie("cpyImg");

            vm.mainObj.menuTreeList = JSON.parse(
                window.sessionStorage.getItem("menuTreeList")
            );

            //如果没有获取到菜单就清除token让用户重新登录(此操作是只要关闭浏览器就要重新登录)
            if (!vm.mainObj.menuTreeList) {
                vm.$cookieStore.delCookie("gn_request_token");
                vm.$cookieStore.delCookie("userName");
            }

            if (!vm.$cookieStore.getCookie("gn_request_token")) {
                vm.$router.push({
                    name: "login"
                });
                return;
            }

            // 默认在首页页签
            if (vm.mainObj.editableTabs.length <= 0) {
                vm.mainObj.editableTabs.push({
                    title: "首页",
                    name: "0",
                    content: "homeManager"
                });
                vm.mainObj.editableTabsValue = "0";
            }

            //默认组件显示
            vm.mainObj.showComponent = -1;

            // 库房无忧显示工作流待办提醒
            if (vm.mainObj.userObj.cpyCode == '180904868bbcb902de4b') {
                // 查询待我处理的任务数量
                vm.waitApprovalTaskCount();
            }

            //提示3个月到期客户
            this.getExpireCustomCount(3);

            //获取开启检索的部门
            this.$usedQueryDept.usedQueryDept();
        },
        data() {
            // 校验两次密码输入是否一致
            let newPwd = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入密码"));
                } else {
                    if (this.mainObj.updatePwd.newPwd !== "") {
                        this.$refs.updatePwd.validateField("confirmPwd");
                    }
                    callback();
                }
            };
            let confirmPwd = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.mainObj.updatePwd.newPwd) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            };
            return {
                mainObj: {
                    dialogFormVisible: false,
                    showComponent: -1,

                    userObj: {
                        userName: "",
                        cpyCode: "",
                        cpyName: ""
                    },

                    // 修改密码
                    updatePwd: {
                        oldPwd: "",
                        newPwd: "",
                        confirmPwd: ""
                    },

                    isActive: true,
                    activeNames: ["1"],

                    /*------------------ 多标签参数 ------------------*/
                    editableTabs: [], // 显示tab页的集合
                    editableTabsValue: "", // 当前tab的model
                    tabIndex: 0, // 当前tab页

                    params: [], // 父组件向子组件传递数据

                    name: "",
                    index: "",
                    currentName: "",
                    menuTreeList: [], // 菜单集合
                    floatDivHeight: 0, // 高度
                    floatDivWidth: document.body.clientWidth // 宽度
                },
                //   suoflag: false, // 是否缩放
                isCollapse: false,
                holdHidden: false,
                messageCount: 0, // 待办任务总数
                throwLeaseApplyTaskCount: 0, // 退租单申请，待我办理的任务数
                throwLeasePayApplyTaskCount: 0, // 退款单申请，待我办理的任务数
                landlordContractApplyTaskCount: 0, // 大房东合同审批，待我办理的任务数

                /*------------------ 校验 ------------------*/
                rules: {
                    oldPwd: [{
                        required: true,
                        message: "请输入原密码",
                        trigger: "blur"
                    }],
                    newPwd: [{
                            required: true,
                            message: "请输入新密码",
                            trigger: "blur"
                        },
                        {
                            validator: newPwd,
                            trigger: "blur"
                        }
                    ],
                    confirmPwd: [{
                            required: true,
                            message: "请确认新密码",
                            trigger: "blur"
                        },
                        {
                            validator: confirmPwd,
                            trigger: "blur"
                        }
                    ]
                }
            };
        },
        methods: {
            cebianlansuo() {
                // var foldBtn = this.$refs.foldBtn;
                var aside = document.querySelector(".aside_style");
                var main = document.querySelector(".el-main");
                var submenu = document.querySelector(".el-submenu__title");
                var suofang = document.querySelector(".suofang");
                var fold = document.querySelector(".fold")
                if (!this.isCollapse) {
                    fold.classList.remove("el-icon-diy-shouqicaidan");
                    fold.classList.add("el-icon-diy-zhankaicaidan");
                    aside.classList.add("active");
                    main.classList.add("active");
                    suofang.classList.add("active");
                    setTimeout(() => {
                        this.isCollapse = true;
                    }, 300);
                } else {
                    fold.classList.remove("el-icon-diy-zhankaicaidan");
                    fold.classList.add("el-icon-diy-shouqicaidan");
                    aside.classList.remove("active");
                    main.classList.remove("active");
                    suofang.classList.remove("active");
                    setTimeout(() => {
                        this.isCollapse = false;
                    }, 300);
                }
            },
            //显示3月内到期客户信息
            getExpireCustomCount(index) {
                let vm = this;
                let contractDateStart = vm.get_month_first(0);
                let contractDateEnd = vm.get_month_last(2);
                let params = {
                    contractDateStart: contractDateStart,
                    contractDateEnd: contractDateEnd,
                    entity: {}
                };
                let options = {
                    method: "POST",
                    data: params,
                    url: "contract/expireInfoCount"
                };

                this.$ajax(options)
                    .then(response => {
                        if (response) {
                            //   vm.expireInfo = "3个月内到期客户数量："+response.data.result+" 个";
                            if (response.data.result > 0) {
                                vm.expireInfo =
                                    '<span>3个月内到期客户数量: <span class="showExpireCustomInfo" onclick="" style="color: orangered">' +
                                    response.data.result +
                                    '</span>  个 <span style="color: blue; cursor: pointer;text-decoration: underline"> 查看详情</span></span>';
                            } else {
                                vm.expireInfo =
                                    '<span>3个月内到期客户数量: <span class="showExpireCustomInfo" onclick="" style="color: deepskyblue">' +
                                    response.data.result +
                                    '</span>  个<span style="color: blue; cursor: pointer;text-decoration: underline"> 查看详情</span></span>';
                            }
                            vm.showExpireCustom();
                        }
                    })
                    .catch(error => {
                        vm.$alert("页面：查询近期到期客户失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {}
                        });
                    });
            },
            // 打开大房东合同工作流窗口
            openActivitiLandlord() {
                let param = {};
                this.openActivitiWindow(
                    "landlordRentApply",
                    "大房东合同工作流",
                    param
                );
            },
            // 打开退租单申请页面
            openActivitiThrowLease(val) {
                if (val === 1) {
                    let param = {};
                    this.openActivitiWindow(
                        "throwLeaseAgreement",
                        "退租工作流",
                        param
                    );
                } else if (val === 2) {
                    let param = {
                        activeName: "third"
                    };
                    this.openActivitiWindow(
                        "throwLeaseAgreement",
                        "退租工作流",
                        param
                    );
                }
            },
            openActivitiWindow(windowName, title, param) {
                /**
                 * title：标签名字
                 * name：标签下标
                 * content：标签item
                 */
                let flag = true;

                for (let i = 0; i < this.mainObj.editableTabs.length; i++) {
                    // 导航名 在不在 tabs集合里
                    if (windowName == this.mainObj.editableTabs[i].content) {
                        flag = false;
                        // 显示在的那个name的tab
                        this.mainObj.editableTabsValue = this.mainObj.editableTabs[
                            i
                        ].name;
                        break;
                    }
                }

                if (flag) {
                    let newTabName = ++this.mainObj.tabIndex + "";
                    this.mainObj.editableTabs.push({
                        title: title,
                        name: newTabName,
                        content: windowName,
                        param: param
                    });
                    this.mainObj.editableTabsValue = newTabName; // 当前标签页的下标
                }
            },
            // 查询大房东合同审批，待我处理任务的数量
            waitApprovalTaskCount() {
                let vm = this;

                let params = {
                    entity: {}
                };

                let option = {
                    method: "POST",
                    data: params,
                    url: "activitiManage/waitApprovalCount"
                };

                this.$ajax(option)
                    .then(function(response) {
                        if (response) {
                            vm.landlordContractApplyTaskCount =
                                response.data.result.landlordCount;
                            vm.throwLeaseApplyTaskCount =
                                response.data.result.throwLeaseApply;
                            vm.throwLeasePayApplyTaskCount =
                                response.data.result.throwLeasePayApply;

                            vm.messageCount =
                                vm.landlordContractApplyTaskCount +
                                vm.throwLeaseApplyTaskCount +
                                vm.throwLeasePayApplyTaskCount;
                        }
                    })
                    .catch(function(error) {
                        vm.$alert("页面：查询待我处理列表出错", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {}
                        });
                    });
            },
            // 接收子组件传递的数据（退租单待处理页面和打款待处理页面的数据）
            getWaitApprovalTaskCount(val) {
                let vm = this;
                vm.throwLeaseApplyTaskCount = val.params.throwLeaseApplyTaskCount;
                vm.throwLeasePayApplyTaskCount =
                    val.params.throwLeasePayApplyTaskCount;
                vm.landlordContractApplyTaskCount =
                    val.params.landlordContractApplyTaskCount;

                vm.messageCount =
                    vm.landlordContractApplyTaskCount +
                    vm.throwLeaseApplyTaskCount +
                    vm.throwLeasePayApplyTaskCount;
            },
            showExpireCustom() {
                this.$notify({
                    title: "提示",
                    message: this.expireInfo,
                    dangerouslyUseHTMLString: true,
                    offset: 100,
                    duration: 7000,
                    type: "warning",
                    onClick: this.showExpireCustomInfo
                });
            },
            showExpireCustomInfo() {
                let vm = this;
                let data = {
                    menuName: "近期到期客户报表",
                    item: "expiredCustomReport"
                };
                vm.operateAddTabs(data);
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.mainObj.dialogFormVisible = false;
            },
            // 修改密码
            submitForm(formName) {
                let oldPwd = this.$md5(this.mainObj.updatePwd.oldPwd);
                let newPwd = this.$md5(this.mainObj.updatePwd.newPwd);

                let obj = {
                    entity: {
                        password: oldPwd
                    },
                    newPwd: newPwd
                };

                this.$refs[formName].validate(valid => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: obj,
                            url: "user/update"
                        };

                        this.$confirm("是否确认修改密码？", "提示", {
                                confirmButtonText: "确定",
                                cancelButtonText: "取消",
                                type: "warning"
                            })
                            .then(() => {
                                this.$ajax(options).then(response => {
                                    if (!response.data.isException) {
                                        this.$message({
                                            type: "success",
                                            message: "密码修改成功,请重新登录！"
                                        });
                                        this.resetForm(formName);
                                        // 删除cookie和用户名
                                        this.$cookieStore.delCookie(
                                            "gn_request_token"
                                        );
                                        this.$cookieStore.delCookie("userName");

                                        // 退出登陆，将本地保存的页签全部清除
                                        window.sessionStorage.removeItem(
                                            "_mainObj"
                                        );

                                        //退出重新登录
                                        if (
                                            !this.$cookieStore.getCookie(
                                                "gn_request_token"
                                            )
                                        ) {
                                            this.toLogin();
                                        }
                                    }
                                });
                            })
                            .catch(() => {
                                this.$message({
                                    type: "info",
                                    message: "已取消修改"
                                });
                            });
                    }
                });
            },
            // 退出
            logout() {
                let vm = this;

                let tokenData = vm.$cookieStore.getCookie("gn_request_token");
                let tokenObj = {
                    entity: {
                        token: tokenData
                    }
                };

                this.$confirm("是否退出登陆？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    if (vm.$cookieStore.getCookie("gn_request_token")) {
                        let options = {
                            method: "POST",
                            data: tokenObj,
                            url: "user/logout"
                        };
                        this.$ajax(options)
                            .then(response => {
                                // 删除cookie和用户名
                                this.$cookieStore.delCookie("gn_request_token");
                                this.$cookieStore.delCookie("userName");

                                // 退出登陆，将本地保存的页签全部清除
                                window.sessionStorage.removeItem("_mainObj");

                                if (
                                    !this.$cookieStore.getCookie("gn_request_token")
                                ) {
                                    this.toLogin();
                                }
                                this.$message({
                                    type: "success",
                                    message: "退出成功!"
                                });
                            })
                            .catch(error => {
                                this.$alert("页面:退出失败", "系统异常", {
                                    confirmButtonText: "确定",
                                    callback: action => {}
                                });
                            });
                    }
                });
            },
            // 返回登陆页
            toLogin() {
                this.$router.push({
                    name: "login"
                });
            },
            /*---------------------------------- 多标签打开 ----------------------------------*/
            // 点击菜单
            menuClick(val) {
                // 看是否还有下级菜单，若存在下级菜单，则只展开菜单，若无下级菜单就新增tab页
                for (let i = 0; i < val.children.length; i++) {
                    if (val.children[i].menuType == "1") {
                        return;
                    }
                }

                let menuName = val.menuName;
                let flag = true;

                for (let i = 0; i < this.mainObj.editableTabs.length; i++) {
                    // 导航名 在不在 tabs集合里
                    if (menuName == this.mainObj.editableTabs[i].title) {
                        flag = false;
                        // 显示在的那个name的tab
                        this.mainObj.editableTabsValue = this.mainObj.editableTabs[
                            i
                        ].name;
                        break;
                    }
                }

                // 导航名不在tabs集合里 就++1
                if (flag) {
                    let newTabName = ++this.mainObj.tabIndex + "";
                    this.mainObj.editableTabs.push({
                        title: val.menuName,
                        name: newTabName,
                        content: val.item
                    });

                    this.mainObj.editableTabsValue = newTabName;
                }

                //页签信息保存在本地
                window.sessionStorage.setItem(
                    "_mainObj",
                    JSON.stringify(this.mainObj)
                );
            },
            // 清空Tabs标签页
            clearTab(command) {
                // 删除首页以外的数组值
                this.mainObj.editableTabs.splice(
                    1,
                    this.mainObj.editableTabs.length - 1
                );
                // 显示首页
                this.mainObj.editableTabsValue = this.mainObj.editableTabs[0].name;
                // 页签信息保存在本地
                window.sessionStorage.setItem(
                    "_mainObj",
                    JSON.stringify(this.mainObj)
                );
            },
            //关闭其他标签
            tabsCloseOtherHandle() {
                let mainTab = {
                    title: "首页",
                    name: "0",
                    content: "homeManager"
                };
                this.mainObj.editableTabs = this.mainObj.editableTabs.filter(item => item.name === this.mainObj.editableTabsValue || item.name === "0");
                if (this.mainObj.editableTabsValue === "0") {
                    this.mainObj.editableTabsValue = this.mainObj.editableTabs[0].name;
                } else {
                    this.mainObj.editableTabsValue = this.mainObj.editableTabs[1].name;
                }
                // 页签信息保存在本地
                window.sessionStorage.setItem(
                    "_mainObj",
                    JSON.stringify(this.mainObj)
                );
            },
            //关闭当前标签
            tabsCloseCurrentHandle() {
                this.removeTab(this.mainObj.editableTabsValue);
            },
            //刷新当前标签页
            tabsRefreshCurrentHandle() {
                for (let i = 0; i < this.mainObj.editableTabs.length; i++) {
                    if (this.mainObj.editableTabs[i].name === this.mainObj.editableTabsValue) {
                        this.refresh(i);
                    }
                }
            },
            // 删除要删除的tabs
            removeTab(targetName) {
                let tabs = this.mainObj.editableTabs;
                // 遍历tabs集合  判断集合name === 要删除的targetName
                for (let i = 0; i < tabs.length; i++) {
                    if (tabs[i].name === targetName) {
                        // 首页不准删除
                        if (tabs[i].content === "homeManager") {
                            return;
                        }
                    }
                }
                let activeName = this.mainObj.editableTabsValue;
                // 当前显示的name === 要删除的name
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            }
                        }
                    });
                }
                this.mainObj.editableTabsValue = activeName;
                this.mainObj.editableTabs = tabs.filter(
                    tab => tab.name != targetName || tab.content == "homeManager"
                );
                // 页签信息保存在本地
                window.sessionStorage.setItem(
                    "_mainObj",
                    JSON.stringify(this.mainObj)
                );
            },
            // 标签
            backParam(val) {

                if (val.flag === "del") {
                    this.operateDelTabs(val);
                } else {
                    this.operateAddTabs(val);
                }
            },
            //新增页签
            operateAddTabs(val) {
                let menuName = val.menuName;
                let flag = true;
                for (let i = 0; i < this.mainObj.editableTabs.length; i++) {
                    if (menuName == this.mainObj.editableTabs[i].title) {
                        flag = false;
                        //先关闭已经打开的页签
                        this.mainObj.editableTabs[i].param = val.params;
                        this.mainObj.editableTabsValue = this.mainObj.editableTabs[
                            i
                        ].name;
                        this.refresh(i);
                        break;
                    }
                }

                /**
                 * title：标签名字
                 * name：标签下标
                 * content：标签item
                 */
                if (flag) {
                    let newTabName = ++this.mainObj.tabIndex + "";
                    this.mainObj.editableTabs.push({
                        title: val.menuName,
                        name: newTabName,
                        content: val.item,
                        param: val.params
                    });
                    this.mainObj.editableTabsValue = newTabName;
                }

                //页签信息保存在本地
                window.sessionStorage.setItem(
                    "_mainObj",
                    JSON.stringify(this.mainObj)
                );
            },
            //关闭已经打开的页签
            operateDelTabs(val) {
                let item = val.item;
                for (let i = 0; i < this.mainObj.editableTabs.length; i++) {
                    if (item == this.mainObj.editableTabs[i].content) {
                        let delName = this.mainObj.editableTabs[i].name;
                        this.removeTab(delName);
                        break;
                    }
                }
            },
            //点击刷新
            refresh(index) {
                this.mainObj.showComponent = index;
                this.$nextTick(() => {
                    this.mainObj.showComponent = -1;
                });
            },
            //动态加载Menu
            createMenu() {
                let menuJson = JSON.parse(window.sessionStorage.getItem("menuTreeList"));
                let firstString = "<div class='menu_tree_info'>";
                let children = menuJson.children;
            },
            //获取拼接字段
            getString(children, val) {
                if (children.menuType === '1') {
                    val += "<el-menu-item :index='" + children.menuCode + "'>";
                }
            }
        }
    };
</script>

<style>
    /* 修改密码框的样式 */
    .changepswParent {
        padding: 0;
    }

    .logoutParent {
        padding: 0;
    }

    .logout {
        padding: 12px 20px;
        display: block !important;
        width: 100% !important;
        z-index: 9999 !important;
    }

    /* 修改密码框的样式 */
    .changepsw {
        padding: 12px 20px;
        display: block !important;
        width: 100% !important;
        z-index: 9999 !important;
    }

    .rootcss {
        /* min-width: 1210px; */
        background-color: rgb(242, 242, 242);
        /* height: 95vh; */
        height: 100vh;
        overflow: hidden;
        width: auto;
    }

    /*----------------- 菜单高度 -----------------*/
    .menu_height {
        height: 40px;
        line-height: 40px;
    }

    /*----------------- 头部 -----------------*/
    .el-header {
        background-color: rgb(242, 242, 242);
        color: #333;
        text-align: center;
        height: 50px !important;
    }

    /*导航栏菜单名称*/
    .navigation-menu {
        position: fixed;
        left: 0;
        top: 0;
        z-index: 900;
        width: 100%;
        height: 50px;
        overflow: hidden;
        background-color: #178679;
    }

    /*用户信息下拉选*/
    .user-info-select {
        float: right;
        color: white;
        font-size: 14px;
        margin-right: 5px;
        display: flex;
        align-items: center;
        height: 50px;
        cursor: pointer;
    }

    /*用户名 样式*/
    .username-span {
        color: white;
        margin-right: 20px;
        font-size: larger;
    }

    .cpyName_span {
        text-align: left;
        margin-top: 18px;
        /*color: #e6d205;*/
        color: #fffdef;
    }

    /*退出登陆 按钮*/
    .logout-button {
        float: right;
        color: white;
        margin-right: 15px;
        margin-top: 6px;
    }

    .mainBox {
        width: 100%;
        position: relative;
    }

    /*左侧菜单栏*/
    .menu_tree_info {
        padding-top: 20px;
        text-align: left;
        background-color: #404040;
        color: #ffffff;
    }

    /*左侧菜单栏滚动条*/
    .el-scrollbar {
        height: 100%;
    }

    /*清空所有标签*/
    .clear_tabs {
        position: absolute;
        z-index: 999;

        height: 40px;
        right: 0;
        top: -58px;
    }

    /*多页签组件的宽度*/
    .el-tabs__header {
        width: 100%;
    }

    /*通知徽标样式*/
    .badge_item {
        margin-right: 13px;
        float: right;
        /*display: flex;*/
        /*height: 50px;*/
        align-items: center;
        cursor: pointer;
        margin-top: 10px;
    }

    .el-menu.el-menu--inline .el-submenu__title {
        padding-left: 50px !important;
    }

    .el-menu.el-menu--inline .el-menu-item {
        padding-left: 50px !important;
    }

    .el-menu.el-menu--inline .el-menu.el-menu--inline .el-menu-item {
        padding-left: 70px !important;
    }

    .el-menu-item>span {
        margin-left: 8px;
    }

    .el-submenu__title i {
        color: #ffffff;
    }

    .el-submenu__title>span {
        margin-left: 8px;
    }

    .suofang {
        color: #fff;
        width: 98%;
        height: 56px;
        line-height: 56px;
        text-align: right;
        box-sizing: border-box;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        padding-right: 18px;
        transition: all 0.3s;
        z-index: 777;
        overflow: hidden;
        background-color: #404040;
        position: absolute;
        bottom: 50px;
        left: -4px;
        padding: 0 13px;
    }

    .suofang.active {
        /* justify-content: flex-start; */
        padding-left: 22px;
    }

    .fold {
        /* margin-right: 12px; */
        width: 18px;
        height: 18px;
        color: #fff;
        z-index: 999;
        vertical-align: middle;
        text-align: center;
        font-size: 18px;
        cursor: pointer;
    }

    .el-aside.aside_style {
        width: 225px !important;
        background-color: #404040;
        /* height: 93vh; */
        position: fixed;
        left: 0;
        top: 50px;
        z-index: 666;
        height: 100%;
        min-width: 225px;
        max-width: 225px;
        transition: all 0.3s;
    }

    .el-menu {
        border: 0 !important;
    }

    .el-aside.aside_style.active {
        min-width: 57px;
        max-width: 57px;
        width: 57px;
    }

    .el-aside.aside_style.active .el-submenu__icon-arrow {
        display: none;
    }

    /*多标签样式*/
    .el_tabs_style {
        width: 100%;
        background-color: white;
        border-bottom: solid rgb(242, 242, 242);
        line-height: 34px;
    }

    .el-tabs__header {
        /* position: fixed; */
        /* left: 234px; */
        /* top: 59px; */
        /* z-index: 888;
        overflow: hidden;
        background-color: #fff;
        order: -1; */
        overflow: hidden;
        margin: 0;
    }

    .comm-title-div[data-v-30e13d82] {
        /* position: fixed; */
        /* left: 242px; */
        /* left: 220px; */
        /* top: 158px; */
        overflow: hidden;
        z-index: 888;
        margin-left: -16px;
        /* margin-top: -7px !important; */
    }

    .el-scrollbar__view {
        height: 100%;
    }

    /* 侧边栏 菜单定高度 */
    #asideMenu {
        height: calc(100% - 183px);
    }

    /* 使菜单栏远离隐蔽按钮 */
    .menu_tree_info {
        padding-bottom: 100px;
    }

    /*工作流待办通知*/
    .msg_activiti {
        color: red;
        font-weight: bold;
    }

    .site-tabs__tools {
        position: fixed;
        top: 60px;
        right: 25px;
        z-index: 931;
        height: 28px;
        padding: 0 12px;
        font-size: 16px;
        line-height: 27px;
        margin-top: 5px;
        background-color: #fff;
        cursor: pointer;
    }

    .el-submenu [class^="el-icon-"] {
        font-size: 16px;
    }

    /* .el-tabs__nav-scroll {
        margin-right: 110px;
    } */

    .el-tabs__nav-next {
        line-height: 34px;
        margin-right: 5px;
        padding-left: 3px;
        border-left: 1px solid #e4e7ed;
    }

    .el-tabs__nav-prev {
        line-height: 34px;
        margin-left: 5px;
    }

    .tabTools {
        height: 34px;
        line-height: 34px;
        position: absolute;
        right: 5px;
        top: 2px;
        z-index: 9999;
    }

    .tabTools i {
        margin: 0 5px;
        font-size: 14px;
        color: #444444;
    }
</style>