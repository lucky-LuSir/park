<template>
    <div class="root">

        <el-tabs v-model="activeName" @tab-click="handleClick" class="navigation">
            <el-tab-pane label="指标管理" name="first">
                <el-select v-model="pkCode" filterable placeholder="园区筛选" size="small" class="navigation_select" clearable>
                    <el-option v-for="(item) in parkList" :key=" item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>
                <!-- 选择月份 -->
                <el-date-picker class="navigation_select" v-model="targetForm.targetDate" type="month" size="small" placeholder="选择月">
                </el-date-picker>
                <el-button @click="selectTarget()" size="small" class="select_button" icon="el-icon-search" type="primary">查询
                </el-button>
                <el-button v-if="atiTaskTargetBtn" @click="addTarget()" size="small" class="select_button" icon="el-icon-plus" type="primary">添加
                </el-button>

                <el-table :data="attractTargetList" border size="small" height="75vh" style="width: 100%;line-height: 30px;">
                    <el-table-column align="center" label="序号" type="index">
                    </el-table-column>
                    <el-table-column align="center" label="项目名称" show-overflow-tooltip width="180px" prop="pkName">
                    </el-table-column>
                    <el-table-column align="center" label="招商人员" prop="empName">
                    </el-table-column>
                    <el-table-column align="center" label="指标月份" width="90px">
                        <template slot-scope="scope">{{ scope.row.targetDate | timeDate }}</template>
                    </el-table-column>

                    <!--<el-table-column align="center"-->
                    <!--label="小广告"-->
                    <!--prop="leafletsTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="横幅"-->
                    <!--prop="bannerTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="朋友圈"-->
                    <!--prop="friendsShareTarget">-->
                    <!--</el-table-column>-->

                    <!--<el-table-column align="center"-->
                    <!--label="管委会/中介拜访"-->
                    <!--prop="otherChannelTarget">-->
                    <!--</el-table-column>-->

                    <!--<el-table-column align="center"-->
                    <!--label="电话跟进"-->
                    <!--prop="customerPhoneFollowupTarget">-->
                    <!--</el-table-column>-->


                    <el-table-column align="center" label="员工获客" prop="customerCountTarget">
                    </el-table-column>
                    <el-table-column align="center" label="客户带看" prop="customerVisityFollowupTarget">
                    </el-table-column>

                    <el-table-column align="center" label="去化面积" prop="signAreaTarget">
                    </el-table-column>
                    <el-table-column header-align="center" fixed="right" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button v-if="atiTaskTargetBtn" @click="editTarget(scope.row)" type="text" size="small">修改</el-button>
                            <el-button v-if="atiTaskTargetBtn" @click="delTarget(scope.row.id)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>

            <el-tab-pane label="指标结果" name="second">
                <el-select v-model="territoryType" placeholder="请选择大区" class="navigation_select" filterable size="small" @change="changeRegion()" clearable>
                    <el-option v-for="item in parkRegionsList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                    </el-option>
                </el-select>

                <el-select v-model="pkCode" filterable placeholder="园区筛选" size="small" class="navigation_select" clearable>
                    <el-option v-for="(item) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>

                <el-date-picker class="navigation_date_select" v-model="dateChangeList" type="daterange" align="right" size="small" unlink-panels :clearable="false" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="getChangeDate">
                </el-date-picker>
                <el-button @click="selectReport()" size="small" class="select_button" icon="el-icon-search" type="primary">查询
                </el-button>
                <el-button v-if="atiTaskTargetBtn" class="select_button" type="success" size="small" icon="el-icon-download" @click="exportReport()">导出
                </el-button>

                <el-table :data="taskTargetList" border size="small" height="75vh" style="width: 100%;line-height: 30px;">
                    <el-table-column align="center" label="序号" fixed type="index">
                    </el-table-column>
                    <el-table-column align="center" label="负责园区" fixed width="200" prop="pkName">
                    </el-table-column>
                    <el-table-column align="center" label="姓名" fixed prop="empName">
                    </el-table-column>

                    <!--<el-table-column label="贴纸/小广告粘贴" align="center">-->
                    <!--<el-table-column align="center"-->
                    <!--label="指标"-->
                    <!--prop="leafletsTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="总计"-->
                    <!--prop="leafletsSum">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="今日"-->
                    <!--prop="leafletsToday">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="完成进度">-->
                    <!--<template slot-scope="scope">{{ scope.row.leafletsFinish }}&nbsp;%</template>-->
                    <!--</el-table-column>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column label="横幅悬挂" align="center">-->
                    <!--<el-table-column align="center"-->
                    <!--label="指标"-->
                    <!--prop="bannerTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="总计"-->
                    <!--prop="bannerSum">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="今日"-->
                    <!--prop="bannerToday">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="完成进度">-->
                    <!--<template slot-scope="scope">{{ scope.row.bannerFinish }}&nbsp;%</template>-->
                    <!--</el-table-column>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column label="朋友圈/中介群推广" align="center">-->
                    <!--<el-table-column align="center"-->
                    <!--label="指标"-->
                    <!--prop="friendsShareTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="总计"-->
                    <!--prop="friendsShareSum">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="今日"-->
                    <!--prop="friendsShareToday">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="完成进度">-->
                    <!--<template slot-scope="scope">{{ scope.row.friendsShareFinish }}&nbsp;%</template>-->
                    <!--</el-table-column>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column label="管委会/中介等兼职拜访" align="center">-->
                    <!--<el-table-column align="center"-->
                    <!--label="指标"-->
                    <!--prop="otherChannelTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="总计"-->
                    <!--prop="otherChannelSum">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="今日"-->
                    <!--prop="otherChannelToday">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="完成进度">-->
                    <!--<template slot-scope="scope">{{ scope.row.otherChannelFinish }}&nbsp;%</template>-->
                    <!--</el-table-column>-->
                    <!--</el-table-column>-->
                    <!--<el-table-column label="电话跟进客户/中介" align="center">-->
                    <!--<el-table-column align="center"-->
                    <!--label="指标"-->
                    <!--prop="customerPhoneFollowupTarget">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="总计"-->
                    <!--prop="customerPhoneFollowupSum">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="今日"-->
                    <!--prop="customerPhoneFollowupToday">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column align="center"-->
                    <!--label="完成进度">-->
                    <!--<template slot-scope="scope">{{ scope.row.customerPhoneFollowupFinish }}&nbsp;%</template>-->
                    <!--</el-table-column>-->
                    <!--</el-table-column>-->
                    <el-table-column label="获客数" align="center">
                        <el-table-column align="center" label="指标" prop="customerCountTarget">
                        </el-table-column>
                        <el-table-column align="center" label="总计" prop="customerCountSum">
                        </el-table-column>
                        <el-table-column align="center" label="今日" prop="customerCountToday">
                        </el-table-column>
                        <el-table-column align="center" label="完成进度">
                            <template slot-scope="scope">{{ scope.row.customerCountFinish }}&nbsp;%</template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="带看数" align="center">
                        <el-table-column align="center" label="指标" prop="customerVisityFollowupTarget">
                        </el-table-column>
                        <el-table-column align="center" label="总计" prop="customerVisityFollowupSum">
                        </el-table-column>
                        <el-table-column align="center" label="今日" prop="customerVisityFollowupToday">
                        </el-table-column>
                        <el-table-column align="center" label="完成进度">
                            <template slot-scope="scope">{{ scope.row.customerVisityFollowupFinish }}&nbsp;%</template>
                        </el-table-column>
                    </el-table-column>
                    <el-table-column label="去化面积" align="center">
                        <el-table-column align="center" label="指标" prop="signAreaTarget">
                        </el-table-column>
                        <el-table-column align="center" label="总计" prop="signAreaMonthSum">
                        </el-table-column>
                        <el-table-column align="center" label="今日" prop="signAreaToday">
                        </el-table-column>
                        <el-table-column align="center" label="完成进度">
                            <template slot-scope="scope">{{ scope.row.signAreaFinish }}&nbsp;%</template>
                        </el-table-column>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
        </el-tabs>

        <el-dialog class="add_target_dialog" :title="addTargetTitle" width="65%" :close-on-press-escape="false" :close-on-click-modal="false" :visible.sync="addTargetVisible" center>
            <el-form :model="targetForm" ref="targetForm" :rules="rules" label-width="96px">
                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="指标月份：" prop="targetDate">
                            <!-- 选择月份 -->
                            <el-date-picker v-model="targetForm.targetDate" type="month" size="small" placeholder="选择月">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                        <el-form-item label="选择园区：" prop="pkCode">
                            <el-select v-model="targetForm.pkCode" filterable placeholder="选择园区" size="small" clearable @change="selectEmpList(true)">
                                <el-option v-for="(item) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode" :disabled="item.pkCode==''">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                        <el-form-item label="选择人员：" prop="empCode">
                            <el-select v-model="targetForm.empCode" size="small" placeholder="请选择" @change="choseEmp">
                                <el-option v-for="item in empAtiList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                                </el-option>
                            </el-select>
                            <span style="color: red;">(没有您要选的人员？请到该园区详情中绑定您要选的人员)</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <!--统计指标更改（注释不要删，以后可能还要统计这些维度） update by wangchuang 2019-6-4-->
                <!--<el-row>-->
                <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                <!--<el-form-item label="小广告：" prop="leafletsTarget" placeholder="请输入">-->
                <!--<el-input v-model="targetForm.leafletsTarget">-->
                <!--</el-input>-->
                <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">-->
                <!--<el-form-item label="横幅：" prop="bannerTarget" placeholder="请输入">-->
                <!--<el-input v-model="targetForm.bannerTarget">-->
                <!--</el-input>-->
                <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">-->
                <!--<el-form-item label="朋友圈：" prop="friendsShareTarget" placeholder="请输入">-->
                <!--<el-input v-model="targetForm.friendsShareTarget">-->
                <!--</el-input>-->
                <!--</el-form-item>-->
                <!--</el-col>-->
                <!--</el-row>-->
                <el-row>
                    <!--统计指标更改（注释不要删，以后可能还要统计这些维度） update by wangchuang 2019-6-4-->
                    <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                    <!--<el-form-item label="电话跟进：" prop="customerPhoneFollowupTarget" placeholder="请输入">-->
                    <!--<el-input v-model="targetForm.customerPhoneFollowupTarget">-->
                    <!--</el-input>-->
                    <!--</el-form-item>-->
                    <!--</el-col>-->
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="获客数：" prop="customerCountTarget" placeholder="请输入">
                            <el-input v-model="targetForm.customerCountTarget">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="客户带看：" prop="customerVisityFollowupTarget" placeholder="请输入">
                            <el-input v-model="targetForm.customerVisityFollowupTarget">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                        <el-form-item label="去化面积：" prop="signAreaTarget" placeholder="请输入">
                            <el-input v-model="targetForm.signAreaTarget">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!--<el-row>-->
                <!--<el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" placeholder="请输入">-->
                <!--<el-form-item label="管委会/中介拜访：" prop="otherChannelTarget" label-width="142px">-->
                <!--<el-input v-model="targetForm.otherChannelTarget">-->
                <!--</el-input>-->
                <!--</el-form-item>-->
                <!--</el-col>-->
                <!--</el-row>-->
            </el-form>
            <div slot="footer" style="text-align: center;height: 50px;line-height: 50px;">
                <div v-if="targetForm.targetCode">
                    <el-button type="primary" @click="addTargetVisible=false">取 消</el-button>
                    <el-button type="primary" @click="submitEditTarget( 'targetForm')">修 改</el-button>
                </div>
                <div v-else>
                    <el-button @click="cancelAddTarget( 'targetForm')">重 置</el-button>
                    <el-button v-if="!targetForm.targetCode" type="primary" @click="submitTarget( 'targetForm')">提
                        交
                    </el-button>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {
            ElRow
        },
        data() {
            //校验数字0～99999999.99
            let checkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    if (value < 0) {
                        callback(new Error('数字值不能为负数'));
                    } else if (value > 99999999.99) {
                        callback(new Error('请输入0～99999999.99数字值'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验数字只能为整数
            let checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                atiTaskTargetBtn: false,//控制增删改权限

                parkRegionsList: [], //大区列表
                territoryType: "", //大区
                dateChangeList: [], // 日期选择组件绑定数据
                parkList: [], // 园区集合
                empAtiList: [], //园区对接人员
                taskTargetList: [], // 报表数据集合
                attractTargetList: [], //招商指标集合

                addTargetVisible: false, //添加指标弹窗控制
                addTargetTitle: "", //弹窗标题
                activeName: "first", //tab标签激活

                targetForm: {
                    pkCode: "", //添加指标选择的园区
                    pkName: "", //项目名称
                    empCode: "", //选择的招商人员
                    empName: "", //招商人员
                    targetDate: "", //选择的月
                    leafletsTarget: "", //小广告
                    bannerTarget: "", //横幅
                    friendsShareTarget: "", //朋友圈
                    otherChannelTarget: "", //管委会/中介拜访
                    customerPhoneFollowupTarget: "", //电话跟进
                    customerCountTarget: "", //员工获客数量
                    customerVisityFollowupTarget: "", //客户带看
                    signAreaTarget: "", //去化面积

                }, //添加指标


                // 导航栏查询参数
                deptCode: '', // 部门编码
                pkCode: '', // 园区编码

                rules: {
                    targetDate: [{
                        required: true,
                        message: '请选择指标月份',
                        trigger: 'blur'
                    }, ],
                    pkCode: [{
                        required: true,
                        message: '请选择一个园区',
                        trigger: 'blur'
                    }, ],
                    empCode: [{
                        required: true,
                        message: '请选择一个招商人员',
                        trigger: 'blur'
                    }, ],
                    leafletsTarget: [{
                            required: true,
                            message: '请输入小广告数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    bannerTarget: [{
                            required: true,
                            message: '请输入横幅数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    friendsShareTarget: [{
                            required: true,
                            message: '请输入朋友圈数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    otherChannelTarget: [{
                            required: true,
                            message: '请输入管委会/中介拜访数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    customerPhoneFollowupTarget: [{
                            required: true,
                            message: '请输入电话跟进数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    customerCountTarget: [{
                            required: true,
                            message: '请获客数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    customerVisityFollowupTarget: [{
                            required: true,
                            message: '请输入客户带看数量',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                    signAreaTarget: [{
                            required: true,
                            message: '请输入去化面积',
                            trigger: 'blur'
                        },
                        {
                            validator: checkNumber
                        },
                        {
                            validator: checkInteger
                        },
                    ],
                },
            }
        },
        created() {
            let vm = this;

            // 菜单和按钮权限控制
            vm.atiTaskTargetBtn = vm.$flagMenuStore.judgeMenu("atiTaskTargetBtn");

            vm.selectAllPark(); // 查询所有园区
            vm.selectParkRegions();
        },
        methods: {
            // select查询全部大区
            selectParkRegions() {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift({
                    deptCode: '',
                    deptName: '全部大区'
                })
            },
            changeRegion() {
                let parkList = [];
                parkList.push({
                    pkCode: '',
                    pkName: '所有园区'
                });
                this.parkList = parkList;
                this.pkCode = '';
                this.selectAllPark();
            },


            // 选择tabs标签触发事件
            handleClick(tab, event) {},
            // 查询按钮
            selectReport() {
                let vm = this;

                if (vm.dateChangeList.length === 0) {
                    vm.$alert('页面：请选择查询日期！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                let params = {
                    entity: {
                        territoryType: vm.territoryType,
                        pkCode: vm.pkCode,
                        startDate: vm.dateChangeList[0],
                        endDate: vm.dateChangeList[1],
                    },
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: "taskTarget/targetReport",
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.taskTargetList = response.data.result;
                        vm.$message({
                            showClose: true,
                            message: '查询指标结果成功',
                            type: 'success',
                        });
                    }
                }).catch(error => {
                    vm.$alert('页面：招商人员统计数据查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 文件导出
            exportReport() {
                let vm = this;

                if (!(vm.dateChangeList[0] && vm.dateChangeList[1])) {
                    vm.$alert('页面：请选择日期！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                let pkCode = vm.pkCode;
                let startDate = vm.dateChangeList[0].getTime();
                let endDate = vm.dateChangeList[1].getTime();

                // 本地测试打开
                // window.open("http://localhost:8380/park/taskTarget/exportTargetExcel" + "?startDate=" + startDate + "&endDate=" + endDate + "&pkCode = + pkCode);

                // 发布线上打开
                window.open("/park/taskTarget/exportTargetExcel" +
                    "?startDate=" + startDate + "&endDate=" + endDate + "&pkCode=" + pkCode);
            },
            // 不能跨月查询
            getChangeDate() {
                let vm = this;
                let startDate = vm.dateChangeList[0];
                let endDate = vm.dateChangeList[1];
                startDate = JSON.stringify(startDate);
                endDate = JSON.stringify(endDate);

                let yearMonthStart = startDate.substring(0, 7);
                let yearMonthEnd = endDate.substring(0, 7);

                if (yearMonthStart != yearMonthEnd) {
                    vm.$alert('页面：查询起始日期必须为同一个月！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    vm.dateChangeList = [];
                }
            },

            // 查询所有园区
            selectAllPark() {
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {
                        "entity": {
                            "territoryType": vm.territoryType
                        }
                    },
                    url: 'park/list'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.parkList = response.data.result;
                        let aObj = {
                            pkCode: '',
                            pkName: '所有园区'
                        };
                        vm.parkList.unshift(aObj);
                    }
                }).catch(error => {
                    vm.$alert('页面：查询所有园区信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },

            //指标管理查询
            selectTarget() {
                let vm = this;

                let obj = {};
                obj.entity = {};
                obj.entity.pkCode = vm.pkCode;
                obj.entity.targetDate = vm.targetForm.targetDate;

                let option = {
                    method: 'POST',
                    data: obj,
                    url: 'taskTarget/list'
                };

                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.attractTargetList = response.data.result;
                        vm.$message({
                            showClose: true,
                            message: '查询指标列表成功',
                            type: 'success',
                        });
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询指标列表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //添加指标
            addTarget() {
                let vm = this;

                vm.targetForm = {};
                vm.addTargetTitle = "添加招商人员指标";
                vm.addTargetVisible = true;
            },

            //选择园区后查询绑定的招商人员名单
            selectEmpList(flag) {
                let vm = this;

                let param = {};
                param.entity = {};
                param.entity.pkCode = vm.targetForm.pkCode;
                //查询招商类型
                param.entity.buttJointType = "1";

                let option = {
                    method: 'POST',
                    data: param,
                    url: 'parkButtJoint/selectByPkCode'
                };
                this.$ajax(
                    option
                ).then(function(response) {
                    if (response) {
                        vm.empAtiList = response.data.result;
                        //flag不为true，则默认选择第一个人显示出来
                        if(flag) {
                            if (vm.empAtiList && vm.empAtiList.length > 0) {
                                vm.targetForm.empCode = vm.empAtiList[0].empCode;
                            }
                        }
                    }
                }).catch(function(error) {
                    vm.$alert('页面：查询园区下招商人员失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //切换选择值时渲染值
            choseEmp() {
                this.$forceUpdate();
            },

            //取消添加
            cancelAddTarget(formName) {
                this.$refs[formName].resetFields();
            },

            //提交新增指标
            submitTarget(subForm) {
                let vm = this;

                vm.$refs[subForm].validate((valid) => {
                    if (valid) {
                        //记录园区name
                        for (let i = 0; i < vm.parkList.length; i++) {
                            if (vm.targetForm.pkCode == vm.parkList[i].pkCode) {
                                vm.targetForm.pkName = vm.parkList[i].pkName;
                                break;
                            }
                        }

                        //记录员工名
                        for (let j = 0; j < vm.empAtiList.length; j++) {
                            if (vm.targetForm.empCode == vm.empAtiList[j].empCode) {
                                vm.targetForm.empName = vm.empAtiList[j].empName;
                                break;
                            }
                        }

                        let obj = {};
                        obj.entity = {};
                        obj.entity = vm.targetForm;
                        let option = {
                            method: 'POST',
                            data: obj,
                            url: 'taskTarget/create'
                        };
                        this.$ajax(
                            option
                        ).then(function(response) {
                            if (response) {
                                vm.addTargetVisible = false;
                                vm.selectTarget();
                            }
                        }).catch(function(error) {
                            vm.$alert('页面：添加招商指标失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
                            });
                        });
                    }
                });
            },

            //修改指标
            editTarget(obj) {
                let vm = this;

                vm.addTargetTitle = "修改招商人员指标";

                let tarForm = JSON.parse(JSON.stringify(obj));
                vm.targetForm = tarForm;
                vm.addTargetVisible = true;

                //查出本园区的招商人员
                vm.selectEmpList(false);

            },

            //修改指标提交
            submitEditTarget(subForm) {
                let vm = this;

                this.$refs[subForm].validate((valid) => {
                    if (valid) {

                        //记录员工名
                        for (let j = 0; j < vm.empAtiList.length; j++) {
                            if (vm.targetForm.empCode == vm.empAtiList[j].empCode) {
                                vm.targetForm.empName = vm.empAtiList[j].empName;
                                break;
                            }
                        }

                        let obj = {};
                        obj.entity = this.targetForm;

                        let option = {
                            method: 'POST',
                            data: obj,
                            url: 'taskTarget/update'
                        };
                        this.$ajax(
                            option
                        ).then(function(response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    type: 'success',
                                    message: '修改指标信息成功'
                                });
                                vm.addTargetVisible = false;
                                vm.selectTarget();
                            }
                        }).catch(function(error) {
                            vm.$alert('页面：添加招商指标失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
                            });
                        });
                    }
                });
            },

            //删除指标
            delTarget(id) {
                let vm = this;
                vm.$confirm('确定删除本条指标信息?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let param = {};
                    param.entity = {
                        id: id,
                    };

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "taskTarget/delete",
                    };

                    this.$ajax(
                        options
                    ).then(function(response) {
                        if (response) {
                            vm.$message({
                                showClose: true,
                                type: 'success',
                                message: '删除指标信息成功'
                            });
                            vm.selectTarget();
                        }
                    }).catch(function(error) {
                        vm.$alert('删除指标信息失败', '温馨提示', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                    })
                }).catch(() => {});
            },

        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        /*background-color:#E8EDF2;*/
    }

    /*导航栏*/
    .navigation {

        width: 100%;
    }


    /*导航栏筛选条件*/
    .navigation_select {
        float: left;
        width: 150px;
        margin-top: 5px;
        margin-bottom: 5px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*导航栏日期选择*/
    .navigation_date_select {
        margin-top: 5px;
        margin-bottom: 5px;
        float: left;
        width: 250px;
        line-height: 30px;
        margin-left: 20px;
    }

    /*查询按钮*/
    .select_button {
        margin-top: 5px;
        float: left;
        margin-bottom: 5px;
        margin-left: 20px;
    }

    /*导出按钮*/
    .export_button {
        margin-left: 20px;
        float: left;
    }
</style>