<template>
    <div class="root" ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="search_buttons">
                <el-input v-model="changeData.keyword"
                          @keyup.enter.native="searchCon()"
                          placeholder="支持乙方名、录入人搜索"
                          class="input-with-select" size="small" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
                </el-input>

                <el-select @change="searchCon" v-model="pkCode" placeholder="请选择园区" class="select_park"
                           filterable size="small">
                    <el-option v-for="item in pkNames"
                               :key="item.code"
                               :label="item.name"
                               :value="item.code">
                    </el-option>
                </el-select>

                <el-button class="more-change" size="small" type="primary" @click="dialogFormVisible = true">
                    更多条件
                </el-button>
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
                                    <el-date-picker
                                            v-model="diaForm.contractDateList"
                                            type="daterange"
                                            align="right"
                                            unlink-panels
                                            :clearable="false"
                                            range-separator="至"
                                            start-placeholder="搜索开始日"
                                            end-placeholder="搜索结束日"
                                            :picker-options="pickerOptions">
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
                                    <el-select v-model="diaForm.contractor" filterable
                                               placeholder="甲方签单人" style="width: 100%;">
                                        <el-option
                                                v-for="item in empList"
                                                :key="item.empCode"
                                                :label="item.empName"
                                                :value="item.empName">
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
                                    <el-select v-model="diaForm.tradeName"
                                               placeholder="客户所属行业" style="width: 100%;">
                                        <el-option
                                                v-for="item in tradeList"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.label">
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


            <!-- 表格化信息 -->
            <div>
                <el-table
                        class="costTable"
                        :data="ctList"
                        border
                        :highlight-current-row="true"
                        size="small"
                        height="72vh">
                    <el-table-column type="expand">
                        <template slot-scope="scope">
                            <el-row style="text-align: left">
                                <el-col :span="15" style="height: 30px;">
                                    <el-form-item label="项目地址:">
                                        {{scope.row.houseAddress}}
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row style="text-align: left;">
                                <el-col :span="20" style="height: 30px;">
                                    <el-form-item label="租用位置:">
                                        <span v-for="(itemm,index) in scope.row.contractHouseList">
                                        {{itemm.bdName}}栋{{itemm.flCount}}层{{itemm.roomNumber}}、&nbsp;
                                    </span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row style="text-align: left;">
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="付款方式:">
                                        <span>付{{scope.row.payRule}}押{{scope.row.depositRule}}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="基础电费:">
                                        <span>{{scope.row.basicElectricityPriceByMonth}}元</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="电损(%):">
                                        <span>{{scope.row.addLoss}}元</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row style="text-align: left;">
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="本期租金:">
                                        <span v-if="scope.row.currentRentReceipt">{{scope.row.currentRentReceipt}}元</span>
                                        <span v-if="!scope.row.currentRentReceipt">0</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="本期电费:">
                                        <span v-if="scope.row.currentElectricityReceipt">{{scope.row.currentElectricityReceipt}}元</span>
                                        <span v-if="!scope.row.currentElectricityReceipt">0元</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="本期水费:">
                                        <span v-if="scope.row.currentWaterReceipt">{{scope.row.currentWaterReceipt}}元</span>
                                        <span v-if="!scope.row.currentWaterReceipt">0元</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="4" style="height: 30px;">
                                    <el-form-item label="本期停车费:">
                                        <span v-if="scope.row.currentParkingReceipt">{{scope.row.currentParkingReceipt}}元</span>
                                        <span v-if="!scope.row.currentParkingReceipt">0元</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            prop="ctName"
                            header-align="center"
                            min-width="120"
                            label="合同编号">
                    </el-table-column>
                    <el-table-column
                            prop="ctStatusName"
                            header-align="center"
                            min-width="100"
                            label="合同状态">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            header-align="center"
                            min-width="100"
                            label="联系人">
                        <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}</template>
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            header-align="center"
                            min-width="150"
                            label="公司名称">
                        <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="签约日期">
                        <template slot-scope="scope">{{ scope.row.contractDate | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            prop="contractor"
                            header-align="center"
                            min-width="100"
                            label="签约代表">
                    </el-table-column>
                    <el-table-column
                            prop="contractorPhone"
                            header-align="center"
                            min-width="100"
                            label="联系电话">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="合同开始">
                        <template slot-scope="scope">{{ scope.row.contractStartTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="100"
                            label="合同结束">
                        <template slot-scope="scope">{{ scope.row.contractEndTime | timeDate}}</template>
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="pkName"
                            header-align="center"
                            min-width="100"
                            label="项目名称">
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
                    <el-table-column
                            prop="createName"
                            header-align="center"
                            min-width="80"
                            label="录入人">
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            min-width="90"
                            label="录入时间">
                        <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            fixed="right"
                            label="操作"
                            width="200">
                        <template slot-scope="scope">
                            <el-button v-if="showDetailTenantBtn" @click="contratDetail(scope.row.ctCode)" type="text"
                                       size="small">详情
                            </el-button>
                            <el-button @click="selectDispose(scope.row.ctCode, scope.row.ctStatus)" type="text" size="small">审核记录
                            </el-button>
                            <el-button v-if="showDetailFeeBtn" @click="costDetail(scope.row.ctCode, scope.row.ctStatus)"
                                       type="text" size="small">应收
                            </el-button>
                            <el-button v-if="showDetailFeeBtn && (scope.row.ctStatus == 4 || scope.row.ctStatus == 5)"
                                       @click="paymentList(scope.row.ctCode)" type="text" size="small">缴费列表
                            </el-button>
                            <el-button v-if="showManualMeterBtn && scope.row.ctStatus == 4"
                                       @click="manualMeter(scope.row.ctCode)" type="text" size="small">水电抄表
                            </el-button>
                            <el-button v-if="showAddParkingSpaceBtn && scope.row.ctStatus == 4"
                                       @click="parkingAdd(scope.row.ctCode)" type="text" size="small">停车位
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page_down" v-bind:style="{width:floatDivWidth+'px'}">
                <el-pagination style="margin-top:10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>
        </el-form>

        <!-- 缴费列表弹窗 -->
        <el-dialog title="缴费列表" :visible.sync="paymentWindow" width="70%" style="line-height: 40px;">
            <el-table :data="paymentHistoryList" border height="400" style="margin-top: -20px;">
                <el-table-column align="center" label="缴费类型">
                    <template slot-scope="scope">{{ scope.row.costTypeName }}</template>
                </el-table-column>
                <el-table-column align="center" label="应收开始时间">
                    <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
                </el-table-column>
                <el-table-column align="center" label="应收结束时间">
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
                    <template slot-scope="scope">{{scope.row.taxPoint == null ? "---" : scope.row.taxPointName}}
                    </template>
                </el-table-column>
                <el-table-column align="center" width="150px" label="税金">
                    <template slot-scope="scope">{{scope.row.taxPrice == null ? 0 : scope.row.taxPrice}}&nbsp;元
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

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

                <el-table-column
                        header-align="center"
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.logStatus == 5" @click="disposeVerify(scope.row.ctCode)" type="text" size="small">立即处理</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow
        },
        created: function () {
            let vm = this;

            //菜单和按钮权限控制
            vm.showAddTenantBtn = vm.$flagMenuStore.judgeMenu("addTenantBtn");// 合同新增按钮
            vm.showManualMeterBtn = vm.$flagMenuStore.judgeMenu("manualMeterBtn");// 水电抄表按钮
            vm.showDetailFeeBtn = vm.$flagMenuStore.judgeMenu("detailFeeBtn");// 应收详情按钮
            vm.showDetailTenantBtn = vm.$flagMenuStore.judgeMenu("detailTenantBtn");// 合同详情按钮
            vm.showAddParkingSpaceBtn = vm.$flagMenuStore.judgeMenu("addParkingSpaceBtn");// 增加停车位按钮

            //判断进入是否有默认园区查询
            let pkCode = vm.$route.query.pkCode;
            if (pkCode) {
                vm.pkCode = pkCode;
            }

            // 查询“一审提交”的合同列表
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
            ).then(function (response) {
                if (response) {
                    vm.empList = response.data.result;
                }
            }).catch(function (error) {
                vm.$alert('页面：合同列表查询人员信息失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });

        },
        data() {
            return {
                floatDivWidth: "",

                // 权限
                showAddTenantBtn: false,// 合同新增按钮
                showDetailTenantBtn: false,// 合同详情按钮
                showDetailFeeBtn: false,// 应收详情按钮
                showManualMeterBtn: false,// 水电抄表按钮
                showAddParkingSpaceBtn: false,// 新增停车位按钮

                deleteContractWindow: false,// 删除合同窗口
                paymentWindow: false,// 缴费列表窗口
                contractVerifyWindow: false,// 合同审核状态

                deleteContractObj: {},// 删除合同
                paymentHistoryList: [],// 缴费列表

                pkNames: [],// 园区集合
                pkCode: 1,
                empList: [],// 人员下拉选集合，
                ctList: [],
                conOperateHisList: [],// 合同审核记录

                currentCtStatus: '',// 当前合同审核状态

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                dialogFormVisible: false,

                // 弹窗，记录选择的条件
                diaForm: {
                    contractDateList: [], //签约日期查询时间段
                    contractor: "",// 签约人
                    trade: "",// 行业
                    ctName: "",// 合同编号
                },

                // 更多条件
                changeData: {
                    keyword: "",// 关键词搜索
                    contractor: "",// 签约人
                    tradeName: "",// 行业
                    ctName: "",// 合同编号
                    conDateStart: "",//签约开始日
                    conDateEnd: "",//签约结束日
                },

                tradeList: [
                    {
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
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        methods: {
            // 查询所有园区
            selectAllPark(){
                let vm = this;
                let obj = {};

                let parkOptions = {
                    method: 'POST',
                    data: obj,
                    url: "park/list",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;

                        let pkNameList = [];
                        let pkObj = [];

                        pkNameList.push(
                            {code: 1, name: "全部园区"}
                        );
                        pkObj[1] = "全部园区";

                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push(
                                {code: code, name: name}
                            );
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
                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 弹框，更多条件选择
            changeKeyword(diaForm){
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
            handReset(){
                this.diaForm.contractDateList = [];// 签约日期查询时间段
                this.diaForm.contractor = "";// 签约人
                this.diaForm.tradeName = "";// 行业
                this.diaForm.ctName = "";// 合同编号

                this.changeData.contractor = "";
                this.changeData.tradeName = "";
                this.changeData.ctName = "";
                this.changeData.conDateStart = "";
                this.changeData.conDateEnd = "";

            },

            //搜索框
            searchCon(){
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

            // 查询“一审通过”的合同列表
            queryContract() {
                let vm = {};
                vm = this;

                let pkCode = "";
                if (vm.pkCode != 1) {
                    pkCode = vm.pkCode;
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
                        pkCode: pkCode,
                        ctStatus: '3'
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "contract/queryDetail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.ctList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：合同列表查询信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            /*-------------------------- 路由跳转开始 --------------------------*/
            // 新增停车位
            parkingAdd(ctCode, freeParkingCount, parkingPrice, parkingSpace){
//                this.$router.push({
//                    name: 'parkingAdd',
//                    query: {
//                        ctCode: ctCode,
//                        freeParkingCount: freeParkingCount,
//                        parkingPrice: parkingPrice,
//                        parkingSpace: parkingSpace,
//                    }
//                })
            },
            // 手动抄表
            manualMeter(ctCode) {
                let data = {
                    flag: "add",
                    menuName:"水电抄表",
                    item: "manualMeter",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam",data);
            },
            // 应收详情
            costDetail(ctCode, ctStatus) {

                let data = {
                    flag: "add",
                    menuName:"应收详情",
                    item: "costDetail",
                    params: {
                        ctCode: ctCode,
                        ctStatus: ctStatus
                    }
                };
                this.$emit("backParam",data);
            },
            // 合同详情
            contratDetail(ctCode){

                let data = {
                    flag: "add",
                    menuName:"合同详情",
                    item: "contractDetails",
                    params: {
                        ctCode: ctCode
                    }
                };
                this.$emit("backParam",data);
            },
            /*-------------------------- 路由跳转结束 --------------------------*/

            // 缴费列表
            paymentList(ctCode){
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
                    if (!response.data.isException) {
                        vm.paymentHistoryList = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：缴费列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },
            // 合同审核状态
            selectDispose(ctCode, ctStatus){
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
                    if (!response.data.isException) {
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
                        callback: action => {
                        }
                    });
                })
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
        background-color: rgb(242, 242, 242);
    }

    /*导航栏*/
    .search_buttons {
        margin-top: -50px;
        position: fixed;
        z-index: 200;
    }

    .search_buttons span {
        float: left;
        margin-left: 20px;
        color: #409EFF;
    }

    .search_box_create {
        float: right;
        margin-right: 20px;
    }

    .contract_box_up_left_text span {
        margin-left: 20px;
    }

    .contract_code span {
        font-size: 16px;
        color: #999999;
    }

    /*分页*/
    .page_down {
        text-align: center;
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
        margin-top: 11px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 150px;
        line-height: 30px;
        margin-top: 11px;
        float: left;
        margin-left: 20px;
    }

    /*更多条件按钮*/
    .more-change {
        margin-left: 20px;
        margin-top: 10px;
        float: left;
    }

    /*更多条件，标签样式*/
    .tag-style {
        max-width: 100px;
        margin: 10px 0 0 0;
    }

    /*表格*/
    .costTable {
        line-height: 40px;
        text-align: center;
        width: 100%;
    }
</style>