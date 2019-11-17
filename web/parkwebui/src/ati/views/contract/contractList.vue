<template>
    <div class="not-position_root" ref="myRoot" id="contractList">
        <!-- 悬浮框 -->
        <div class="not-position_header">
            <div class="search_buttons">
                <div style="float: left;">
                    <el-input v-model="changeData.keyword" @keyup.enter.native="searchCon()" placeholder="支持乙方名、录入人搜索" class="input-with-select" size="small" clearable>
                        <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
                    </el-input>

                    <el-select @change="searchCon" v-model="pkCode" placeholder="请选择园区" class="select_park" filterable size="small">
                        <el-option v-for="item in pkNames" :key="item.code" :label="item.name" :value="item.code">
                        </el-option>
                    </el-select>

                    <el-select @change="searchCon" v-model="ctStatus" placeholder="状态查询" class="select_park" filterable size="small">
                        <el-option v-for="item in ctStatusList" :key="item.code" :label="item.name" :value="item.code">
                        </el-option>
                    </el-select>

                    <!-- 注释掉已选条件显示 -->
                    <el-button class="more-change" size="small" type="primary" @click="dialogFormVisible = true">
                        更多查询
                    </el-button>
                </div>

                <el-popover placement="top-start" width="200" trigger="hover">

                    <el-tag class="tag-style" v-show="(changeData.conDateStart)">
                        s:{{changeData.conDateStart | timeDate}}
                    </el-tag>
                    <el-tag class="tag-style" v-show="(changeData.conDateEnd)">
                        e:{{ changeData.conDateEnd | timeDate }}
                    </el-tag>
                    <el-tag class="tag-style" v-show="(changeData.ctName)">
                        {{changeData.ctName}}
                    </el-tag>
                    <el-tag class="tag-style" v-show="(changeData.contractor)">
                        {{changeData.contractor}}
                    </el-tag>
                    <el-tag class="tag-style" v-show="(changeData.tradeName)">
                        {{changeData.tradeName}}
                    </el-tag>
                    <!-- 注释掉已选条件显示 -->
                    <!--<span v-if="!changeData.conDateStart && !changeData.conDateEnd && !changeData.contractor && !changeData.tradeName && !changeData.ctName">暂无筛选条件</span>-->
                    <!--<el-button class="more-term-button" size="small" slot="reference" plain>已选条件-->
                    <!--</el-button>-->
                </el-popover>

                <div class="search_box_create">
                    <el-button v-if="showAddTenantBtn" type="primary" size="small" @click="createRentCus()">
                        入驻办理
                    </el-button>
                </div>
            </div>
        </div>

        <!-- 更多条件 -->
        <el-dialog class="dialog_search" :visible.sync="dialogFormVisible" width="42%" center>
            <el-form :model="diaForm" label-width="96px">
                <el-row :gutter="5" style="text-align: center;">
                    <div style="margin-bottom: 20px;line-height: 10px;">
                        <span>条件查询</span>
                    </div>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <div class="grid-content ">
                            <el-form-item label="筛选日期">
                                <el-date-picker v-model="diaForm.contractDateList" type="daterange" align="right" unlink-panels :clearable="false" range-separator="至" start-placeholder="搜索开始日" end-placeholder="搜索结束日" :picker-options="pickerOptions">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <div class="grid-content ">
                            <el-form-item label="合同编号">
                                <el-input v-model="diaForm.ctName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <div class="grid-content ">

                            <el-form-item label="签单人" label-width="96px">
                                <el-select v-model="diaForm.contractor" filterable placeholder="甲方签单人" style="width: 100%;">
                                    <el-option v-for="item in empList" :key="item.empCode" :label="item.empName" :value="item.empName">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="5" style="text-align: left;">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <div class="grid-content ">
                            <el-form-item label="所属行业" label-width="96px">
                                <el-select v-model="diaForm.tradeName" placeholder="客户所属行业" style="width: 100%;">
                                    <el-option v-for="item in tradeList" :key="item.value" :label="item.label" :value="item.label">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="handReset()">重置</el-button>
                <el-button type="primary" @click="changeKeyword(diaForm)">查询</el-button>
            </div>
        </el-dialog>

        <el-form>
            <!-- 表格化信息 -->
            <div class="contract_box_top">
                <el-table class="costTable"
                          :data="ctList"
                          border
                          :highlight-current-row="true"
                          size="small"
                          :height="tableHeight">
                    <!--<el-table-column type="expand">-->
                        <!--<template slot-scope="scope">-->
                            <!--<el-row style="text-align: left">-->
                                <!--<el-col :span="15" style="height: 30px;">-->
                                    <!--<el-form-item label="项目地址:">-->
                                        <!--{{scope.row.houseAddress}}-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <!--<el-row style="text-align: left;">-->
                                <!--<el-col :span="20" style="height: 30px;">-->
                                    <!--<el-form-item label="租用位置:">-->
                                        <!--<span v-for="(itemm,index) in scope.row.contractHouseList" :key="index">-->
                                            <!--{{itemm.bdName}}栋{{itemm.flCount}}层{{itemm.roomNumber}}、&nbsp;-->
                                        <!--</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <!--<el-row style="text-align: left;">-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="付款方式:">-->
                                        <!--<span>付{{scope.row.payRule}}押{{scope.row.depositRule}}</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="基础电费:">-->
                                        <!--<span>{{scope.row.basicElectricityPriceByMonth}}元</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="电损(%):">-->
                                        <!--<span>{{scope.row.addLoss}}元</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <!--<el-row style="text-align: left;">-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="本期租金:">-->
                                        <!--<span v-if="scope.row.currentRentReceipt">{{scope.row.currentRentReceipt}}元</span>-->
                                        <!--<span v-if="!scope.row.currentRentReceipt">0</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="本期电费:">-->
                                        <!--<span v-if="scope.row.currentElectricityReceipt">{{scope.row.currentElectricityReceipt}}元</span>-->
                                        <!--<span v-if="!scope.row.currentElectricityReceipt">0元</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="本期水费:">-->
                                        <!--<span v-if="scope.row.currentWaterReceipt">{{scope.row.currentWaterReceipt}}元</span>-->
                                        <!--<span v-if="!scope.row.currentWaterReceipt">0元</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col :span="4" style="height: 30px;">-->
                                    <!--<el-form-item label="本期停车费:">-->
                                        <!--<span v-if="scope.row.currentParkingReceipt">{{scope.row.currentParkingReceipt}}元</span>-->
                                        <!--<span v-if="!scope.row.currentParkingReceipt">0元</span>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</template>-->
                    <!--</el-table-column>-->
                    <el-table-column align="center" label="序号" type="index" width="50px">
                    </el-table-column>
                    <el-table-column prop="ctName" header-align="center" min-width="120" label="合同编号">
                    </el-table-column>
                    <el-table-column header-align="center" min-width="90" label="合同状态">
                        <template slot-scope="scope">
                            <span class="rentApply" title="查看退租情况" v-if="scope.row.ctStatus==9" @click="showApplyLog(scope.row.ctName)">{{scope.row.ctStatusName}}</span>
                            <span v-else-if="scope.row.ctStatus==4" style="color:#67c23a">{{scope.row.ctStatusName}}</span>
                            <template v-else>{{scope.row.ctStatusName}}</template>
                        </template>
                    </el-table-column>
                    <el-table-column show-overflow-tooltip header-align="center" min-width="100" label="联系人">
                        <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}</template>
                    </el-table-column>
                    <el-table-column show-overflow-tooltip header-align="center" min-width="150" label="公司名称">
                        <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="签约日期">
                        <template slot-scope="scope">{{ scope.row.contractDate | timeDate}}</template>
                    </el-table-column>
                    <el-table-column prop="contractor" header-align="center" min-width="80" label="签约代表">
                    </el-table-column>
                    <el-table-column prop="contractorPhone" header-align="center" min-width="100" label="联系电话">
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="合同开始">
                        <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" min-width="100" label="合同结束">
                        <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column show-overflow-tooltip prop="pkName" header-align="center" min-width="100" label="项目名称">
                    </el-table-column>
                    <!--<el-table-column-->
                    <!--prop="outputValue"-->
                    <!--header-align="center"-->
                    <!--min-width="100"-->
                    <!--label="产值">-->
                    <!--</el-table-column>-->
                    <!--<el-table-column-->
                    <!--prop="taxTake"-->
                    <!--header-align="center"-->
                    <!--min-width="100"-->
                    <!--label="税收金额">-->
                    <!--</el-table-column>-->
                    <el-table-column prop="createName" header-align="center" min-width="80" label="录入人">
                    </el-table-column>
                    <el-table-column header-align="center" min-width="140" label="录入时间">
                        <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column header-align="center" fixed="right" label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button v-if="showDetailTenantBtn" @click="contratDetail(scope.row.ctCode)" type="text" size="small">详情</el-button>
                            <el-button v-if="showDetailFeeBtn" @click="costDetail(scope.row.ctCode, scope.row.ctStatus)" type="text" size="small">应收</el-button>
                            <el-button @click="selectDispose(scope.row.ctCode, scope.row.ctStatus)" type="text" size="small">审核记录</el-button>

                            <el-button v-if="(scope.row.ctStatus == 4)" @click="paymentList(scope.row.ctCode)" type="text" size="small">缴费列表</el-button>
                            <!--<el-button v-if="showManualMeterBtn && scope.row.ctStatus == 4" @click="manualMeter(scope.row.ctCode)" type="text" size="small">水电抄表</el-button>-->
                            <!--<el-button v-if="showAddParkingSpaceBtn && scope.row.ctStatus == 4" @click="parkingAdd(scope.row.ctCode)" type="text" size="small">停车位</el-button>-->
                            <el-button v-if="showDetailTenantBtn && (scope.row.ctStatus == 1 || scope.row.ctStatus == 5 || scope.row.ctStatus == 6)" @click="contractDelete(scope.row.id, scope.row.ctCode, scope.row.ctStatus, scope.row.cusCode)" type="text" size="small">删除</el-button>
                            <el-button v-if="scope.row.ctStatus == 4" @click="throwLeaseApply(scope.row.ctName)" type="text" size="small">退租</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page_down">
                <el-pagination style="margin-top:10px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
        </el-form>

        <!-- 查看合同审核状态，并处理 -->
        <el-dialog title="审核记录" :visible.sync="contractVerifyWindow" style="line-height: 40px;">
            <el-table :data="conOperateHisList" border height="300" style="margin-top: -20px;">
                <el-table-column align="center" label="审核时间">
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>

                <el-table-column align="center" label="操作人" prop="createName"></el-table-column>

                <el-table-column align="center" label="审核状态">
                    <template slot-scope="scope">{{ scope.row.ctStatusName }}</template>
                </el-table-column>

                <el-table-column align="center" label="备注" prop="operateRemark"></el-table-column>

                <el-table-column header-align="center" fixed="right" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.logStatus == 5" @click="disposeVerify(scope.row.ctCode)" type="text" size="small">立即处理</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

        <!-- 删除合同填写理由 -->
        <el-dialog title="删除合同" :visible.sync="deleteContractWindow" style="line-height: 40px;">
            <el-form ref="deleteContractObj" :model="deleteContractObj" :rules="rules" label-width="150px" style="text-align: left;margin-top: -20px;">
                <el-form-item label="合同删除类型：" prop="deleteType">
                    <el-radio-group v-model="deleteContractObj.deleteType">
                        <el-radio :label="1">合同重复录入</el-radio>
                        <el-radio :label="2">合同录入错误</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="合同删除原因：" prop="deleteCause">
                    <el-input type="textarea" :rows="5" v-model="deleteContractObj.deleteCause"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" style="margin-top: -20px;">
                <el-button @click="resetForm('deleteContractObj')">取 消</el-button>
                <el-button type="primary" @click="deleteContractSubmit('deleteContractObj')">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 缴费列表弹窗 -->
        <el-dialog title="缴费列表" :visible.sync="paymentWindow" width="70%" style="line-height: 40px;">
            <el-table :data="paymentHistoryList" border height="400" style="margin-top: -20px;">
                <el-table-column align="center" label="缴费类型">
                    <template slot-scope="scope">{{ scope.row.costTypeName }}</template>
                </el-table-column>
                <el-table-column align="center" label="费用开始时间">
                    <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
                </el-table-column>
                <el-table-column align="center" label="费用结束时间">
                    <template slot-scope="scope">{{ scope.row.costEndTime | timeDate}}</template>
                </el-table-column>
                <el-table-column align="center" label="应收金额" prop="receivableRent"></el-table-column>
                <el-table-column align="center" label="实收金额" prop="realRent"></el-table-column>
                <el-table-column align="center" label="已收金额" prop="paidPrice"></el-table-column>
                <el-table-column align="center" width="160px" label="收款时间">
                    <template slot-scope="scope">{{ scope.row.payDate | time}}</template>
                </el-table-column>
                <el-table-column align="center" label="租金状态">
                    <template slot-scope="scope">{{ scope.row.payStatusName }}</template>
                </el-table-column>
                <el-table-column align="center" label="是否含税" show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.hasTax == true ? "是" : "否"}}</template>
                </el-table-column>
                <el-table-column align="center" label="税点">
                    <template slot-scope="scope">{{scope.row.taxPoint == null ? "---" : scope.row.taxPointName}}</template>
                </el-table-column>
                <el-table-column align="center" width="150px" label="税金">
                    <template slot-scope="scope">{{scope.row.taxPrice == null ? 0 : scope.row.taxPrice}}&nbsp;元</template>
                </el-table-column>
            </el-table>
        </el-dialog>

        <!-- 退租单申请 -->
        <throw-lease-apply v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsApplyObj="paramsApplyObj"></throw-lease-apply>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import throwLeaseApply from '../../../act/views/workflow/workflowTemplate/throwLeaseApply.vue';
    export default {
        components: {
            ElButton,
            ElCol,
            ElRow,
            throwLeaseApply
        },
        props: ['paramsObj'], // 接收父组件数据
        created: function() {
            let vm = this;

            //菜单和按钮权限控制
            vm.showAddTenantBtn = vm.$flagMenuStore.judgeMenu("addTenantBtn"); // 合同新增按钮
            vm.showManualMeterBtn = vm.$flagMenuStore.judgeMenu("manualMeterBtn"); // 水电抄表按钮
            vm.showDetailFeeBtn = vm.$flagMenuStore.judgeMenu("detailFeeBtn"); // 费用详情按钮
            vm.showDetailTenantBtn = vm.$flagMenuStore.judgeMenu("detailTenantBtn"); // 合同详情按钮
            vm.showAddParkingSpaceBtn = vm.$flagMenuStore.judgeMenu("addParkingSpaceBtn"); // 增加停车位按钮

            //判断进入是否有默认园区查询
            if (vm.paramsObj && vm.paramsObj.pkCode) {
                vm.pkCode = vm.paramsObj.pkCode;
            }

            // 查询合同列表
            vm.queryContract();

            //查询所有园区
            vm.selectAllPark();

            // 查询人员
            var empobj = {};
            var empOptions = {
                method: 'POST',
                data: empobj,
                url: "employee/list",
            };
            this.$ajax(
                empOptions
            ).then(function(response) {
                if (response) {
                    vm.empList = response.data.result;
                }
            }).catch(function(error) {
                vm.$alert('页面：合同列表查询人员信息失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {}
                });
            });

        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                paramsApplyObj: {}, //退租申请传参
                floatDivHeight: "",
                // 权限
                showAddTenantBtn: false, // 合同新增按钮
                showDetailTenantBtn: false, // 合同详情按钮
                showDetailFeeBtn: false, // 费用详情按钮
                showManualMeterBtn: false, // 水电抄表按钮
                showAddParkingSpaceBtn: false, // 新增停车位按钮

                deleteContractWindow: false, // 删除合同窗口
                paymentWindow: false, // 缴费列表窗口
                contractVerifyWindow: false, // 合同审核状态

                deleteContractObj: {}, // 删除合同
                paymentHistoryList: [], // 缴费列表

                currentCtStatus: "", // 当前合同状态

                pkNames: [], // 园区集合
                pkCode: 1,
                empList: [], // 人员下拉选集合，
                ctList: [],
                conOperateHisList: [], // 合同审核记录
                showThrowLeaseApply: false, //申请退租

                ctStatus: 0,
                ctStatusList: [{
                        code: 0,
                        name: "全部状态"
                    },
                    {
                        code: 1,
                        name: "待提交"
                    },
                    {
                        code: 2,
                        name: "一审提交"
                    },
                    {
                        code: 3,
                        name: "一审通过"
                    },
                    {
                        code: 4,
                        name: "二审通过"
                    },
                    {
                        code: 5,
                        name: "驳回"
                    },
                    {
                        code: 6,
                        name: "撤回"
                    },
                    {
                        code: 7,
                        name: "已退租"
                    },
                    {
                        code: 9,
                        name: "退租中"
                    },

                ],

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                dialogFormVisible: false,

                // 弹窗，记录选择的条件
                diaForm: {
                    contractDateList: [], //签约日期查询时间段
                    contractor: "", // 签约人
                    trade: "", // 行业
                    ctName: "", // 合同编号
                },

                // 更多条件
                changeData: {
                    keyword: "", // 关键词搜索
                    contractor: "", // 签约人
                    tradeName: "", // 行业
                    ctName: "", // 合同编号
                    conDateStart: "", //签约开始日
                    conDateEnd: "", //签约结束日
                },

                // 校验
                rules: {
                    deleteType: [{
                        required: true,
                        message: '请选择合同删除类型',
                        trigger: 'change'
                    }, ],
                    deleteCause: [{
                        required: true,
                        message: '请填写合同删除原因',
                        trigger: 'blur'
                    }],
                },

                tradeList: [{
                        "value": "1",
                        "label": "食品加工业"
                    }, {
                        "value": "2",
                        "label": "饮料制造业"
                    },
                    {
                        "value": "3",
                        "label": "酒加工"
                    },
                    {
                        "value": "4",
                        "label": "烟草制品业"
                    }, {
                        "value": "5",
                        "label": "纺织业"
                    },
                    {
                        "value": "6",
                        "label": "皮、绒制品业"
                    },
                    {
                        "value": "7",
                        "label": "木材加工"
                    },
                    {
                        "value": "8",
                        "label": "家具制造"
                    },
                    {
                        "value": "9",
                        "label": "纸制品业"
                    }, {
                        "value": "10",
                        "label": "印刷业"
                    },
                    {
                        "value": "11",
                        "label": "文教体育"
                    },
                    {
                        "value": "12",
                        "label": "用品制造"
                    },
                    {
                        "value": "13",
                        "label": "石油加工"
                    },
                    {
                        "value": "14",
                        "label": "化学燃料"
                    }, {
                        "value": "15",
                        "label": "医药制造"
                    },
                    {
                        "value": "16",
                        "label": "橡胶制品"
                    },
                    {
                        "value": "17",
                        "label": "塑料制品"
                    },
                    {
                        "value": "18",
                        "label": "金属制品"
                    },
                    {
                        "value": "19",
                        "label": "制造业"
                    }, {
                        "value": "20",
                        "label": "交通运输"
                    },
                    {
                        "value": "21",
                        "label": "电子设备"
                    }
                ],
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            }
        },
        mounted() {

        },
        methods: {
            // 查询所有园区
            selectAllPark() {
                let vm = this;
                let obj = {};

                let parkOptions = {
                    method: 'POST',
                    data: obj,
                    url: "park/list",
                };
                this.$ajax(
                    parkOptions
                ).then(function(response) {
                    if (!response.data.isException) {
                        let result = response.data.result;

                        let pkNameList = [];
                        let pkObj = [];

                        pkNameList.push({
                            code: 1,
                            name: "全部园区"
                        });
                        pkObj[1] = "全部园区";

                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push({
                                code: code,
                                name: name
                            });
                            pkObj[code] = name;
                        }

                        vm.pkNames = pkNameList;
                        vm.pkObj = pkObj;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '搜索房源失败',
                            type: 'error'
                        });
                    }
                }).catch(function(error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 弹框，更多条件选择
            changeKeyword(diaForm) {
                this.changeData.contractor = diaForm.contractor;
                this.changeData.tradeName = diaForm.tradeName;
                this.changeData.ctName = diaForm.ctName;
                if (diaForm.contractDateList[0]) {
                    this.changeData.conDateStart = diaForm.contractDateList[0].getTime();
                }
                if (diaForm.contractDateList[1]) {
                    this.changeData.conDateEnd = diaForm.contractDateList[1].getTime();
                }

                this.dialogFormVisible = false;

                this.searchCon();
            },

            // 更多条件重置
            handReset() {
                this.diaForm.contractDateList = []; // 签约日期查询时间段
                this.diaForm.contractor = ""; // 签约人
                this.diaForm.tradeName = ""; // 行业
                this.diaForm.ctName = ""; // 合同编号

                this.changeData.contractor = "";
                this.changeData.tradeName = "";
                this.changeData.ctName = "";
                this.changeData.conDateStart = "";
                this.changeData.conDateEnd = "";

            },

            //搜索框
            searchCon() {
                this.handleCurrentChange(1);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryContract(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryContract();
            },
            /*---------------------分页end----------------------*/

            //查询合同列表
            queryContract() {
                let vm = {};
                vm = this;

                let pkCode = "";
                if (vm.pkCode != 1) {
                    pkCode = vm.pkCode;
                }

                let ctStatus = "";
                if (vm.ctStatus != 0) {
                    ctStatus = vm.ctStatus;
                }

                let param = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    keyword: vm.changeData.keyword,
                    contractDateStart: vm.changeData.conDateStart,
                    contractDateEnd: vm.changeData.conDateEnd,
                    entity: {
                        contractor: vm.changeData.contractor,
                        tradeName: vm.changeData.tradeName,
                        ctName: vm.changeData.ctName,
                        ctStatus: ctStatus,
                        pkCode: pkCode,
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/queryDetail",
                };
                this.$ajax(
                    options
                ).then(function(response) {
                    if (response) {
                        vm.ctList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function(error) {
                    vm.$alert('页面：合同列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            /*-------------------------- 合同删除开始 --------------------------*/
            // 合同删除
            contractDelete(id, ctCode, ctStatus, cusCode) {
                if (ctStatus == '2') {
                    this.$alert('页面：该合同已经审核通过，无法删除！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                } else {
                    this.deleteContractWindow = true;
                    this.deleteContractObj.id = id;
                    this.deleteContractObj.ctCode = ctCode;
                    this.deleteContractObj.cusCode = cusCode;
                }
            },
            // 重置
            resetForm(subForm) {
                this.deleteContractWindow = false;
                this.$refs[subForm].resetFields();
            },
            // 删除合同提交
            deleteContractSubmit(subForm) {
                let vm = this;

                this.$refs[subForm].validate((valid) => {
                    if (valid) {

                        let params = {
                            entity: vm.deleteContractObj
                        };
                        let options = {
                            method: "POST",
                            data: params,
                            url: "contract/delete",
                        };
                        this.$ajax(
                            options
                        ).then(response => {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '合同删除成功',
                                    type: 'success'
                                });
                                vm.deleteContractWindow = false;
                                this.$refs[subForm].resetFields();
                                vm.queryContract();
                            }
                        }).catch(error => {
                            vm.$alert('页面：合同删除失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {}
                            });
                        })
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '信息填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
            /*-------------------------- 合同删除结束 --------------------------*/


            /*-------------------------- 路由跳转开始 --------------------------*/
            // 新增停车位
            /*目前还没有开发，先关闭入口*/
            parkingAdd(ctCode, freeParkingCount, parkingPrice, parkingSpace) {
                this.$alert('拼命开发中，敬请期待!', '温馨提示', {
                    confirmButtonText: '确定',
                    callback: action => {}
                });
                //                let data = {
                //                    menuName:"新增停车位",
                //                    item: "parkingAdd",
                //                    params: {
                //                        ctCode: ctCode,
                //                        freeParkingCount: freeParkingCount,
                //                        parkingPrice: parkingPrice,
                //                        parkingSpace: parkingSpace,
                //                    }
                //                };
                //                this.$emit("backParam",data);

            },
            // 新增租户
            createRentCus() {
                let data = {
                    flag: "add",
                    menuName: "入驻办理",
                    item: "contractCreate",
                };
                this.$emit("backParam", data);
            },
            // 手动抄表
            manualMeter(ctCode) {
                let data = {
                    flag: "add",
                    menuName: "水电表管理",
                    item: "manualMeter",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam", data);

            },
            // 费用详情
            costDetail(ctCode, ctStatus) {
                let data = {
                    flag: "add",
                    menuName: "应收详情",
                    item: "costDetail",
                    params: {
                        ctCode: ctCode,
                        ctStatus: ctStatus
                    }
                };
                this.$emit("backParam", data);

            },
            // 合同详情
            contratDetail(ctCode) {

                let data = {
                    flag: "add",
                    menuName: "合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam", data);

            },

            /*-------------------------- 路由跳转结束 --------------------------*/

            // 缴费列表
            paymentList(ctCode) {
                let vm = this;

                vm.paymentWindow = true;

                let params = {
                    entity: {
                        ctCode: ctCode
                    }
                };
                let options = {
                    method: "POST",
                    data: params,
                    url: "pay/contractPayList",
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.paymentHistoryList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：缴费列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },

            // 合同审核状态
            selectDispose(ctCode, ctStatus) {
                let vm = this;

                vm.currentCtStatus = ctStatus;

                vm.contractVerifyWindow = true;

                let params = {
                    entity: {
                        ctCode: ctCode
                    }
                };
                let options = {
                    method: "POST",
                    data: params,
                    url: "conOperateHis/list",
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.conOperateHisList = response.data.result;

                        if (vm.conOperateHisList && vm.conOperateHisList.length > 0) {
                            let listCtStatus = vm.conOperateHisList[0].ctStatus;
                            if (listCtStatus == ctStatus) {
                                vm.conOperateHisList[0].logStatus = ctStatus;
                            }
                        }
                    }
                }).catch(error => {
                    vm.$alert('页面：合同审核记录查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },
            // 处理驳回的合同
            disposeVerify(ctCode) {
                let data = {
                    flag: "add",
                    menuName: "合同变更",
                    item: "contractEdit",
                    params: {
                        ctCode: ctCode,
                        showThrowLeaseApply: true,
                    }
                };
                this.$emit("backParam", data);
            },

            //申请退租
            throwLeaseApply(ctName) {
                this.$confirm('是否确定退租?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.toThrowLeaseApply('add', ctName);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '中断退租操作'
                    });
                });
            },

            toThrowLeaseApply(type, ctName) {
                let vm = this;
                vm.showThrowLeaseApply = true;
                vm.paramsApplyObj.type = type;
                vm.paramsApplyObj.ctName = ctName;
                vm.paramsApplyObj.showThrowLeaseApply = vm.showThrowLeaseApply;
            },

            //退租填写弹窗子组件回调
            backParam(val) {
                this.showThrowLeaseApply = val.showThrowLeaseApply;
            },

            //跳转到退租申请记录
            showApplyLog(ctName) {
                let data = {
                    flag: "add",
                    menuName: "退租申请记录",
                    item: "throwLeaseApplyLog",
                    params: {
                        keyword: ctName
                    }
                };
                this.$emit("backParam", data);

            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .search_buttons {
        background-color: white;
        /*margin-top: 10px;*/
        text-align: left;
        width: 100%;
        height: 40px;
        line-height: 40px;
        margin-bottom: 6px;
    }

    .search_buttons span {
        float: left;
        margin-left: 20px;
        color: #409eff;
    }

    .search_box_create {
        float: right;
        margin-right: 20px;
    }

    /*租客信息框*/

    .contract_box_up_left_text span {
        margin-left: 20px;
    }

    .contract_code span {
        font-size: 16px;
        color: #999999;
    }

    /*分页*/
    .page_down {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        clear: both;
        display: block;
        margin: 0px auto;
        position: fixed;
        bottom: 0px;
        width: 100%;
        z-index: 10;
    }

    /*搜索框*/
    .input-with-select {
        width: 240px;
        float: left;
        margin-left: 20px;
        margin-top: 6px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 150px;
        line-height: 30px;
        margin-top: 6px;
        float: left;
        margin-left: 20px;
    }

    /*更多条件按钮*/
    .more-change {
        margin-left: 20px;
    }

    /*更多条件，标签样式*/
    .tag-style {
        max-width: 100px;
        margin: 10px 0 0 0;
    }

    /*表格*/
    .costTable {
        text-align: center;
        width: 100%;
    }

    .rentApply {
        color: #ee9900;
    }

    .rentApply:hover {
        cursor: pointer;
    }
</style>