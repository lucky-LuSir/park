<template>
    <div class="not-position_root" ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <el-date-picker v-if="isShowProfile === 1" class="proMonth" v-model="proMonth" size="small" type="month" placeholder="选择月份">
                </el-date-picker>
                <el-input v-if="isShowProfile === 2" v-model="changeData.keyword" @keyup.enter.native="searchCon()" placeholder="支持乙方名、录入人搜索" class="input-with-select" size="small" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
                </el-input>
                <el-select clearable @change="searchCon" v-model="pkCode" placeholder="请选择园区" class="select_park" filterable size="small">
                    <el-option v-for="item in pkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>
                <el-button class="more-change" size="small" type="primary" @click="searchCon()" icon="el-icon-search">查询</el-button>
                <el-button v-if="isShowProfile === 2" class="more-change" size="small" type="primary" @click="moreTermWindow = true">
                    更多条件
                </el-button>

                <!-- 批量抄表 -->
                <waterMeterBatchRecord v-if="isShowProfile === 2" :ctList="multipleSelection" class="son_components"></waterMeterBatchRecord>

                <el-button v-if="isShowProfile === 2" type="warning" size="small" @click="createBill()" class="more-change">
                    生成账单
                </el-button>

                <!-- 切换 -->
                <div class="iconBox">
                    <div class="toggleTableWater toggleIcon" @click="toggleTable()">
                        <el-tooltip class="item" effect="dark" content="切换为表格模式" placement="top">
                            <i class="iconfont el-icon-diy-moshiqiehuan"></i>
                        </el-tooltip>
                    </div>
                    <div class="toggleProfileWater toggleIcon active" @click="toggleProfile()">
                        <el-tooltip class="item" effect="dark" content="切换为剖面图模式" placement="top">
                            <i class="iconfont el-icon-diy-moshi"></i>
                        </el-tooltip>
                    </div>
                </div>
            </div>
            <!-- 更多条件 -->
            <el-dialog :visible.sync="moreTermWindow" width="42%" center>
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
                                    <el-date-picker v-model="diaForm.contractDateList" type="daterange" align="right" unlink-panels :clearable="false" range-separator="至" start-placeholder="搜索开始日" end-placeholder="搜索结束日">
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
            <div class="page_down">
                <el-pagination style="margin-top:10px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
        </el-form>

        <!-- 刨面图 -->
        <div v-if="isShowProfile === 1" class="profile-main">
            <h2 style="margin-bottom: 10px;" v-if="isShowProfileTitle">
                水表监控图
                <div class="profile-title-info">
                    <i style="background-color: #f78989;"></i><span>未装表</span>
                    <i style="background-color: #728db0"></i><span>未抄表</span>
                    <i style="background-color: #BFE06C"></i><span>已抄表</span>
                </div>
            </h2>
            <ul class="profile-view clearfix">
                <li class="profile-row" v-for="(item, index) in dataList" :key="index">
                    <div class="row-user">{{item.companyName}}</div>
                    <div class="row-item">
                        <div v-if="item.waterMeterList.length === 0" class="item-column notBiao" style="width: 100%; background: #f78989;">
                            <el-button type="danger" size="mini" round @click="addWaterMeter(item)">未添加或未启用
                            </el-button>
                        </div>
                        <div v-else-if="item.waterMeterList.length < 3" v-for="(v, i) in item.waterMeterList" :key="i" :style="{'width': (100/item.waterMeterList.length) + '%'}" class="item-column">
                            <div v-if="!v.waterRecordEntity.recordMonth" style="background: #728db0" class="notChaoBiao" @mouseover="showBtn(v)" @mouseleave="hideBtn()">
                                <div class="biao-info1">
                                    <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                    <p>使用度数：<span>无</span></p>
                                    <p>本次抄表时间：<span>无</span></p>
                                    <p>是否生成账单：<span>无</span></p>
                                </div>
                                <div class="manual_mater_btn" v-show="isShowBtn == v.id">
                                    <waterMeterBatchRecordProfile style="display: inline-block;" :ctObj="item" :meterObj="v" @queryContract="queryContract">
                                    </waterMeterBatchRecordProfile>
                                </div>
                            </div>
                            <div v-if="v.waterRecordEntity.recordMonth" style="background: #BFE06C">
                                <div class="biao-info">
                                    <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                    <p>使用度数：<span>{{v.waterRecordEntity.realWater}}&nbsp;m³</span></p>
                                    <p>本次抄表时间：<span>{{v.waterRecordEntity.currentMeterRecordDate | timeDate}}</span></p>
                                    <p>
                                        是否生成账单：<span v-text="v.waterRecordEntity.hasBill === true ? '是' : '否'"></span>
                                    </p>
                                </div>
                                <!-- <el-button style=" margin-left: 10px;" @click="addWaterMeter(item)" size="mini" round type="primary">
                                    表管理
                                </el-button> -->
                            </div>
                        </div>
                        <div v-else v-for="(v, i) in item.waterMeterList" :key="i" :style="{'width': (100/item.waterMeterList.length) + '%'}" class="item-column">
                            <div v-if="!v.waterRecordEntity.recordMonth" style="background: #728db0" class="notChaoBiao" @mouseover="showBtn(v)" @mouseleave="hideBtn()">
                                <div class="biao-info1">
                                    <el-popover placement="top" width="200" trigger="hover">
                                        <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                        <p>使用度数：<span>无</span></p>
                                        <p>本次抄表时间：<span>无</span></p>
                                        <p>是否生成账单：<span>无</span></p>
                                        <div slot="reference">
                                            <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                            <p>使用度数：<span>无</span></p>
                                            <p>本次抄表时间：<span>无</span></p>
                                            <p>是否生成账单：<span>无</span></p>
                                        </div>
                                    </el-popover>
                                </div>
                                <div class="manual_mater_btn" v-show="isShowBtn == v.id">
                                    <waterMeterBatchRecordProfile style="display: inline-block;" :ctObj="item" :meterObj="v" @queryContract="queryContract">
                                    </waterMeterBatchRecordProfile>
                                </div>
                            </div>
                            <div v-if="v.waterRecordEntity.recordMonth" style="background: #BFE06C">
                                <div class="biao-info">
                                    <el-popover placement="top" width="200" trigger="hover">
                                        <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                        <p>使用度数：<span>{{v.waterRecordEntity.realWater}}&nbsp;m³</span></p>
                                        <p>本次抄表时间：<span>{{v.waterRecordEntity.currentMeterRecordDate | timeDate}}</span></p>
                                        <p>
                                            是否生成账单：<span v-text="v.waterRecordEntity.hasBill === true ? '是' : '否'"></span>
                                        </p>
                                        <div slot="reference">
                                            <p>表名：<span>{{v.waterRecordEntity.waterMeterName}}</span></p>
                                            <p>使用度数：<span>{{v.waterRecordEntity.realWater}}&nbsp;m³</span></p>
                                            <p>本次抄表时间：<span>{{v.waterRecordEntity.currentMeterRecordDate | timeDate}}</span></p>
                                            <p>
                                                是否生成账单：<span v-text="v.waterRecordEntity.hasBill === true ? '是' : '否'"></span>
                                            </p>
                                        </div>
                                    </el-popover>
                                </div>
                                <!-- <el-button style=" margin-left: 10px;" @click="addWaterMeter(item)" size="mini" round type="primary">
                                    表管理
                                </el-button> -->
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <!-- 表格信息 -->
        <el-table v-if="isShowProfile === 2" ref="multipleTable" style="border-top: white;" :data="contractList" border :highlight-current-row="true" size="small" @selection-change="handleSelectionChange" :height="tableHeight">
            <el-table-column type="selection" :selectable="selectable" width="55">
            </el-table-column>
            <el-table-column align="center" label="序号" type="index" width="50px">
            </el-table-column>
            <el-table-column prop="ctName" header-align="center" width="130" label="合同编号">
            </el-table-column>
            <el-table-column header-align="center" width="170" label="水表名">
                <template slot-scope="scope">
                    <span v-if="scope.row.waterMeterName != null">{{ scope.row.waterMeterName }}</span>
                    <el-button type="danger" size="mini" round v-if="scope.row.waterMeterName == null" @click="addWaterMeter(scope.row)">未添加或未启用
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column show-overflow-tooltip header-align="center" label="公司名称">
                <template slot-scope="scope">{{scope.row.companyName == null ? "---" : scope.row.companyName}}
                </template>
            </el-table-column>
            <el-table-column show-overflow-tooltip header-align="center" label="联系人">
                <template slot-scope="scope">{{scope.row.relateName == null ? "---" : scope.row.relateName}}
                </template>
            </el-table-column>
            <el-table-column show-overflow-tooltip prop="pkName" header-align="center" label="项目名">
            </el-table-column>
            <el-table-column header-align="center" fixed="right" label="操作" width="130">
                <template slot-scope="scope">
                    <waterMeterBatchRecord style="display: inline-block;" v-show="showManualMeterBtn && scope.row.waterMeterName != null" :ctObj="scope.row">
                    </waterMeterBatchRecord>
                    <el-button v-show="showManualMeterBtn && scope.row.waterMeterName != null" style=" margin-left: 10px;" @click="addWaterMeter(scope.row)" type="text" size="mini">
                        表管理
                    </el-button>
                    <el-button v-show="showManualMeterBtn && scope.row.waterMeterName == null" style="color: rgb(245, 108, 108);" @click="addWaterMeter(scope.row)" type="text" size="mini">
                        添加水表
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 生成应收 -->
        <el-dialog title="生成应收" style="text-align: center;" :visible.sync="createBillWindow" width="50%">
            <el-form :model="waterReceiptObj" ref="waterReceiptObj" labelSuffix="：" :rules="receiptRules" style="text-align: left;" label-width="130px">
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="收款截止时间" prop="receivingDate">
                            <el-date-picker v-model="waterReceiptObj.receivingDate" type="date" placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="所属月份">
                            <el-date-picker v-model="waterReceiptObj.recordMonth" type="month" placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="createBillWindow = false">取 消</el-button>
                <el-button type="primary" @click="submitBill('waterReceiptObj')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import waterMeterBatchRecord from "../components/waterMeterBatchRecord.vue";
    import waterMeterBatchRecordProfile from "../components/waterMeterBatchRecordProfile.vue";

    export default {
        components: {
            ElCol,
            ElRow,
            waterMeterBatchRecord,
            waterMeterBatchRecordProfile
        },
        data() {
            return {
                defaultPkCode: '', // 默认园区code
                isShowBtn: '',
                isShowProfileTitle: false,
                proMonth: '',
                dataList: [],
                isShowProfile: 1,
                tableHeight: this.$store.state.tableHeight,
                pkCode: '',
                // 权限
                showManualMeterBtn: false, // 水电抄表权限按钮

                multipleSelection: [], // 合同表格多选

                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                pkList: [], // 园区集合
                empList: [], // 人员下拉选集合
                contractList: [],

                /**
                 * 生成账单
                 */
                createBillWindow: false, // 生成账单弹窗
                waterReceiptObj: {}, // 电费应收
                receiptRules: {
                    receivingDate: [{
                        required: true,
                        message: '请选择截止日期',
                        trigger: 'blur'
                    }, ],
                },

                moreTermWindow: false, // 更多条件弹窗
                // 更多条件
                changeData: {
                    keyword: "", // 关键词搜索
                    contractor: "", // 签约人
                    tradeName: "", // 行业
                    ctName: "", // 合同编号
                    conDateStart: "", //签约开始日
                    conDateEnd: "", //签约结束日
                },
                // 弹窗，记录选择的条件
                diaForm: {
                    contractDateList: [], //签约日期查询时间段
                    contractor: "", // 签约人
                    trade: "", // 行业
                    ctName: "", // 合同编号
                },

                // 行业集合
                tradeList: [{
                        value: "1",
                        label: "食品加工业"
                    }, {
                        value: "2",
                        label: "饮料制造业"
                    },
                    {
                        value: "3",
                        label: "酒加工"
                    },
                    {
                        value: "4",
                        label: "烟草制品业"
                    }, {
                        value: "5",
                        label: "纺织业"
                    },
                    {
                        value: "6",
                        label: "皮、绒制品业"
                    },
                    {
                        value: "7",
                        label: "木材加工"
                    },
                    {
                        value: "8",
                        label: "家具制造"
                    },
                    {
                        value: "9",
                        label: "纸制品业"
                    }, {
                        value: "10",
                        label: "印刷业"
                    },
                    {
                        value: "11",
                        label: "文教体育"
                    },
                    {
                        value: "12",
                        label: "用品制造"
                    },
                    {
                        value: "13",
                        label: "石油加工"
                    },
                    {
                        value: "14",
                        label: "化学燃料"
                    }, {
                        value: "15",
                        label: "医药制造"
                    },
                    {
                        value: "16",
                        label: "橡胶制品"
                    },
                    {
                        value: "17",
                        label: "塑料制品"
                    },
                    {
                        value: "18",
                        label: "金属制品"
                    },
                    {
                        value: "19",
                        label: "制造业"
                    }, {
                        value: "20",
                        label: "交通运输"
                    },
                    {
                        value: "21",
                        label: "电子设备"
                    }
                ],
            }
        },
        async created() {
            let vm = this;
            this.proMonth = new Date();
            let userCode = vm.$cookieStore.getCookie("userCode");
            let params = {
                entity: {
                    empCode: userCode
                }
            };
            const res = await this.$ajax.post(`/parkButtJoint/query`, params);
            let result = res.data.result;

            if (result.length > 0) {
                vm.defaultPkCode = result[0].pkCode;
                this.searchCon();
                vm.pkCode = vm.defaultPkCode
            }
            // 菜单和按钮权限控制
            vm.showManualMeterBtn = vm.$flagMenuStore.judgeMenu("manualMeterBtn"); // 水电抄表按钮

            //            vm.queryContract();// 查询二审通过的合同列表
            vm.selectAllPark(); // 查询所有园区
            vm.selectAllEmpList(); // 查询人员
        },

        methods: {
            showBtn(v) {
                this.isShowBtn = v.id;
            },
            hideBtn() {
                // this.isShowBtn = false;
            },
            // 切换成表格
            toggleTable() {
                this.isShowProfile = 2;
                this.$nextTick(() => {
                    var toggleTableWater = document.querySelector(".toggleTableWater");
                    var toggleProfileWater = document.querySelector(".toggleProfileWater");
                    toggleTableWater.classList.add("active");
                    toggleProfileWater.classList.remove("active");
                    this.searchCon();
                })
            },
            // 切换刨面图
            toggleProfile() {
                this.isShowProfile = 1;
                this.$nextTick(() => {
                    var toggleTableWater = document.querySelector(".toggleTableWater");
                    var toggleProfileWater = document.querySelector(".toggleProfileWater");
                    toggleProfileWater.classList.add("active");
                    toggleTableWater.classList.remove("active");
                    this.searchCon();
                })
            },
            // 查询所有园区
            selectAllPark() {
                let vm = this;

                let parkOptions = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };

                this.$ajax(
                    parkOptions
                ).then(function(response) {
                    if (response) {
                        vm.pkList = response.data.result;
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
            // 查询所有人员
            selectAllEmpList() {
                let empObj = {};

                let empOptions = {
                    method: 'POST',
                    data: empObj,
                    url: "employee/list",
                };

                this.$ajax(
                    empOptions
                ).then(response => {
                    if (response) {
                        this.empList = response.data.result;
                    }
                }).catch(error => {
                    this.$alert('页面：合同列表查询人员信息失败', '系统异常', {
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

                this.moreTermWindow = false;

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
                this.isShowProfileTitle = true;
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
            // 查询二审通过的合同列表
            async queryContract() {
                let vm = this;
                let pkCode = '';
                if (this.pkCode != 1) {
                    pkCode = this.pkCode;
                }

                let param = {};
                let res = {};
                if (this.isShowProfile === 1) {
                    if (!this.pkCode) {
                        pkCode = this.defaultPkCode;
                    }
                    param = {
                        start: (vm.start - 1) * vm.pageSize,
                        pageSize: vm.pageSize,
                        recordMonth: this.proMonth,
                        entity: {
                            pkCode: pkCode,
                        }
                    };
                    res = await this.$ajax.post(`contract/findWaterMeterRecord`, param);
                    this.dataList = res.data.result.data;
                } else {
                    param = {
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
                            ctStatus: '4'
                        }
                    };
                    res = await this.$ajax.post(`contract/queryMeter`, param);
                    vm.contractList = res.data.result.data;
                    vm.total = res.data.result.totalCount;
                }
            },
            // 添加水表
            addWaterMeter(item) {
                let data = {
                    flag: "add",
                    menuName: "水电表管理",
                    item: "manualMeter",
                    params: {
                        ctCode: item.ctCode,
                        companyName: item.companyName
                    }
                };
                this.$emit("backParam", data);
            },
            // 批量抄电表，勾选
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 勾选禁用
            selectable(row, index) {
                if (row.waterMeterName == null) {
                    return false;
                } else {
                    return true;
                }
            },
            // 打开账单生成弹窗
            createBill() {
                if (this.multipleSelection.length === 0) {
                    this.$alert('请勾选需要生成账单的合同！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }
                this.createBillWindow = true;
            },
            // 生成账单提交
            submitBill(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: {
                                recordMonth: this.waterReceiptObj.recordMonth
                            },
                            receivingDate: this.waterReceiptObj.receivingDate,
                            contractList: this.multipleSelection
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: 'waterRecord/createBill'
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (response) {
                                this.$message({
                                    showClose: true,
                                    message: '生成账单成功！',
                                    type: 'success'
                                });
                                this.createBillWindow = false;
                                this.$refs.multipleTable.clearSelection();

                                //生成账单后跳转到电费应收列表中
                                let data = {
                                    flag: "add",
                                    menuName: "水费应收报表",
                                    item: "waterReceivableReport",
                                    params: {
                                        receivingDate: this.waterReceiptObj.receivingDate,
                                        pkCode: this.pkCode
                                    }
                                };
                                this.$emit("backParam", data);


                            }
                        }).catch(error => {
                            if (response) {
                                this.$message({
                                    showClose: true,
                                    message: '账单生成失败！',
                                    type: 'error'
                                });
                            }
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整！',
                            type: 'warning'
                        });
                    }
                })
            }
        }
    }
</script>

<style scoped lang="less">
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
        width: 250px;
        float: left;
        margin-left: 20px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 214px;
        line-height: 30px;
        float: left;
        margin-left: 20px;
    }

    /*更多条件按钮*/
    .more-change {
        margin-left: 20px;
        float: left;
    }

    /*子组件*/
    .son_components {
        margin-left: 20px;
        display: flex;
    }

    /* 刨面图样式 */

    .not-position_root {
        overflow: auto;
        min-width: initial;
    }

    .not-position_header {
        border-bottom: 1px solid #ddd;
    }


    .proMonth {
        width: 110px;
        margin-left: 20px;
        line-height: normal;

        /deep/ .el-input__inner {
            padding-right: 15px;
        }
    }

    .profile-main {
        padding: 10px 15px 20px;
    }

    .profile-view {
        position: relative;
    }

    .profile-row {
        width: 50%;
        float: left;
        font-size: 14px;
        list-style: none;
        box-sizing: border-box;
        margin-bottom: 5px;
        position: relative;
        padding-left: 113px;
        padding-right: 10px;
    }

    .profile-row:nth-child(2n) {
        padding-right: 0;
    }

    .row-user {
        border-left: 4px solid transparent;
        line-height: 23px;
        width: 113px;
        box-sizing: border-box;
        height: 100px;
        border: 1px solid #ddd;
        border-right: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        position: absolute;
        left: 0;
        top: 0;
    }

    @media screen and (max-width: 1366px) {
        .profile-row {
            padding-left: 80px;
        }

        .row-user {
            width: 80px !important;
        }
    }

    .row-item {
        width: 100%;
        display: flex;
    }

    .item-column {
        min-width: 4px;
        border: 1px solid #bababa;
        cursor: pointer;
        height: 100px;
        overflow: hidden;
        position: relative;
        margin-right: 4px;
        color: #353b4b;
        line-height: 24px;
        text-align: center;

        >div {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    }

    .toggleIcon {
        width: 28px;
        height: 28px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }

    .toggleIcon.active {
        background-color: #e7e7e7;
    }

    .iconfont {
        font-size: 16px;
    }

    .iconBox {
        margin-left: auto;
        display: flex;
        margin-right: 20px;
    }

    .notBiao {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .biao-info {
        line-height: 24px;
        padding-left: 5px;
        font-size: 12px;
        width: 100%;

        p {
            -webkit-box-orient: vertical;
            display: -webkit-box;
            -webkit-box-align: left !important;
            -webkit-line-clamp: 1;
            overflow: hidden;
            text-align: left;

            >span {
                color: #333;
            }
        }
    }

    .notChaoBiao {
        display: flex;
        align-items: center;
        justify-content: space-between;
        position: relative;

    }

    .biao-info1 {
        line-height: 24px;
        padding-left: 5px;
        font-size: 12px;
        width: 100%;
        height: 100%;

        p {
            text-align: left;
            -webkit-box-orient: vertical;
            display: -webkit-box;
            -webkit-box-align: left !important;
            -webkit-line-clamp: 1;
            overflow: hidden;

            >span {
                color: #333;
            }
        }

        /deep/ .el-popover__reference {
            width: 100%;
            height: 100%;
        }
    }

    .manual_mater_btn {
        display: flex;
        align-items: flex-end;
        padding-bottom: 10px;
        /*margin-right: 10px;*/
        /*border: 1px solid;*/
        position: absolute;
        right: 0;
        bottom: 0;
    }

    .profile-title-info {
        float: right;

        >i {
            width: 16px;
            height: 10px;
            display: inline-block;
            background-color: #ccc;
            margin: 0 5px;
        }

        >span {
            font-size: 12px;
        }
    }
</style>