<template>
    <div class="not-position_root">
        <el-form>
            <!--悬浮栏-->
            <div class="not-position_header">
                <el-input class="input-with-select" v-model="keyword" @keyup.enter.native="handTermSearch(1)" placeholder="支持客户姓名、电话、公司搜索" size="small" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="handTermSearch(1)"></el-button>
                </el-input>
                <el-select class="change-territory" size="small" @change="changeTerritoryCode()" v-model="territoryType" placeholder="全部大区">
                    <el-option v-for="item in parkRegionsList" :key="item.deptCode" :label="item.deptName" :value="item.deptCode">
                    </el-option>
                </el-select>
                <el-select class="change-park" size="small" v-model="diaForm.pkCode" placeholder="请选择园区" @change="changeParkNameBase">
                    <el-option v-for="item in itParkArr" :key="item.pkCode" :label="item.pkName" :value="item.pkCode">
                    </el-option>
                </el-select>

                <el-button class="more-term-button" size="small" type="primary" @click="dialogFormVisible = true">
                    更多查询
                </el-button>

                <el-button class="add-customer-button" v-if="showAddCustomerBtn" type="primary" @click="addCustomer()" size="small">
                    客户新增
                </el-button>
            </div>

            <!-- 更多条件弹窗 -->
            <el-dialog title="条件查询" :visible.sync="dialogFormVisible" style="line-height: 30px;" width="50%" center>
                <el-form :model="diaForm" label-width="96px">
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <el-form-item label="创建时间" label-width="96px">
                                <el-date-picker class="data-picker" v-model="dateChangeList" type="daterange" align="right" size="small" unlink-panels :clearable="true" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
                                    <!--@change="selectReport()">-->
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="状态" label-width="96px">
                                    <!-- 客户状态 -->
                                    <el-select v-model="diaForm.cusStatus" placeholder="请选择" size="small">
                                        <el-option v-for="item in cusStatusOptions" :key="item.value" :label="item.label" :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="关键字查询" label-width="96px">
                                    <el-tooltip effect="light" content="主联系人姓名、电话、公司名" placement="bottom">
                                        <el-input v-model="keyword" placeholder="此栏为模糊查询,可输部分关键字"></el-input>
                                    </el-tooltip>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="行业" label-width="96px">
                                    <el-select v-model="diaForm.trade" placeholder="请选择" @change="changeTradeBase" style="width: 100%;">
                                        <el-option v-for="item in tradeList" :key="item.value" :label="item.label" :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="主联系人" label-width="96px">
                                    <el-tooltip effect="light" content="" placement="top" disabled>
                                        <el-input v-model="diaForm.mainContactName" placeholder="此栏输入完整名"></el-input>
                                    </el-tooltip>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <!--<el-row :gutter="5" style="text-align: left;">-->
                    <!--<el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">-->
                    <!--<div class="grid-content ">-->
                    <!--<el-form-item label="客户状态" label-width="96px">-->
                    <!--<el-select @change="changeCusStatusBase" v-model="diaForm.cusStatus" placeholder="请选择" style="width: 100%;">-->
                    <!--<el-option v-for="item in cusStatusOptions"-->
                    <!--:key="item.value"-->
                    <!--:label="item.label"-->
                    <!--:value="item.value">-->
                    <!--</el-option>-->
                    <!--</el-select>-->
                    <!--</el-form-item>-->
                    <!--</div>-->
                    <!--</el-col>-->
                    <!--</el-row>-->
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="公司名称" label-width="96px">
                                    <el-tooltip effect="light" content="" placement="top" disabled>
                                        <el-input v-model="diaForm.companyName" placeholder="此栏输入完整名"></el-input>
                                    </el-tooltip>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="5" style="text-align: left;">
                        <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                            <div class="grid-content ">
                                <el-form-item label="所属招商人员" label-width="96px">
                                    <el-select v-model="diaForm.empCode" filterable @visible-change="changeEmp" placeholder="请选择" @change="changeCreateCodeBase" style="width: 100%;">
                                        <el-option v-for="item in empAllList" :key="item.empCode" :label="item.empName" :value="item.empCode">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                </el-form>
                <div style="text-align:center;line-height: 10px;">
                    <el-button @click="handReset">重 置</el-button>
                    <el-button type="primary" @click="handTermSearch(1) ">查 询</el-button>
                </div>
            </el-dialog>

            <!-- 客户列表 -->
            <el-table :data="customerList" border :highlight-current-row="true" size="small" :height="tableHeight">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="对接人员：" class="table-label-style">
                                        <span>{{ props.row.empName }}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                                    <el-form-item label="客户来源：">
                                        <span>{{ props.row.ditchName }}</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                    <el-form-item label="渠道单位：">
                                        <span>{{ !props.row.channelCpyName ? "无数据" : props.row.channelCpyName }}</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="渠道联系人：">
                                        <span>{{ !props.row.channelUserName ? "无数据" : props.row.channelUserName}}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                    <el-form-item label="渠道电话：">
                                        <span>{{ !props.row.channelPhone ? "无数据" : props.row.channelPhone }}</span>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="序号" type="index" width="60">
                </el-table-column>
                <el-table-column align="center" prop="mainContactName" label="联系人" width="100">

                </el-table-column>
                <!--<el-table-column align="center" prop="mainContactPhone" label="联系电话"></el-table-column>-->
                <el-table-column align="center" prop="cusStatusName" label="客户状态" width="70">
                </el-table-column>
                <el-table-column align="center" prop="pkName" label="所属园区">
                </el-table-column>
                <el-table-column align="center" prop="mainArea" label="需求面积" sortable width="100">
                    <template slot-scope="scope">{{ scope.row.mainArea }}&nbsp;㎡</template>
                </el-table-column>
                <el-table-column align="center" prop="tradeName" label="所属行业" width="110">
                </el-table-column>
                <el-table-column align="center" prop="companyProduct" label="公司产品" width="110">
                </el-table-column>
                <el-table-column align="center" prop="createName" label="录入人" width="80">
                </el-table-column>
                <el-table-column align="center" label="创建时间" width="150">
                    <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column min-width="100px" fixed="right" align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="handleEdit(scope.row.cusCode)" v-if="showDetailCustomerBtn" size="mini" type="text">
                            详情
                        </el-button>
                        <el-button @click="followupInfo(scope.row.cusCode)" v-if="showCustomerFollowupDetailsBtn" size="mini" type="text">
                            跟进详情
                        </el-button>
                        <el-button @click="followupCreate(scope.row.cusCode, scope.row.ditch, scope.row.mainContactName, scope.row.mainContactPhone)" v-if="showAddCustomerFollowupBtn" size="mini" type="text">
                            添加跟进
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 跟进详情 -->
            <el-dialog title="跟进详情" :visible.sync="holdFollowupInfo" width="70%">
                <el-table :data="followupList" border height="300">
                    <el-table-column align="center" min-width="30px" label="客户姓名" prop="cusName">
                    </el-table-column>
                    <!--<el-table-column align="center" min-width="40px" label="客户电话" prop="cusPhone"></el-table-column>-->
                    <el-table-column align="center" min-width="30px" label="客户状态" prop="cusStatusName">
                    </el-table-column>
                    <el-table-column align="center" min-width="30px" label="跟进类型" prop="followupTypeName">
                    </el-table-column>
                    <el-table-column align="center" min-width="50px" label="来访园区" prop="pkName">
                    </el-table-column>
                    <el-table-column align="center" min-width="30px" label="跟进人员" prop="createName">
                    </el-table-column>
                    <el-table-column align="center" min-width="50px" label="跟进时间" show-overflow-tooltip>
                        <template slot-scope="scope">{{ scope.row.followupTime | time}}</template>
                    </el-table-column>
                    <el-table-column align="center" min-width="50px" label="录入时间" show-overflow-tooltip>
                        <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column align="center" min-width="60px" label="跟进结果" prop="followupRes">
                    </el-table-column>
                    <el-table-column align="center" min-width="60px" label="图片展示">
                        <template slot-scope="scope">
                            <img v-for="(item, index) in scope.row.attachmentList" :key="index" class="img_attachment" :src=" parkBaseUrl + 'file/read/' + item ">
                        </template>
                    </el-table-column>
                </el-table>
            </el-dialog>

            <!-- 绑定园区弹窗 -->
            <el-dialog :visible.sync="isShowParkParam" width="40%">
                <el-form ref="parkParam" :model="parkParam" label-width="80px" style="text-align: left">
                    <div style="font-size: 18px;padding-bottom: 20px">请先绑定您负责的园区，然后进行绑定</div>
                    <el-form-item label="绑定园区:" prop="pkCode">
                        <el-select v-model="parkParam.pkCode" filterable default-first-option placeholder="输入园区名称或者地址">
                            <el-option v-for="item in pkNames" :key="item.code" :label="item.name" :value="item.code">
                            </el-option>
                        </el-select>
                        <el-button type="primary" style="margin-left: 20px;" @click="onParkSubmit()">绑定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

            <!-- 分页栏 -->
            <div class="page-box">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[25, 50, 75, 100]" :page-size="pageSizeData" layout="total, sizes, prev, pager, next, jumper" :total="totalCount" v-if="totalCount != 10">
                </el-pagination>
            </div>
        </el-form>

        <!-- 添加跟进 -->
        <el-dialog title="添加跟进" :visible.sync="followupWindow" width="50%" :before-close="beforeFollowClose">
            <el-form :model="customerFollowup" :rules="followupRules" ref="customerFollowup" label-width="130px">
                <el-row :gutter="20" class="one">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="跟进类型：" prop="followupType">
                            <el-radio-group v-model="customerFollowup.followupType">
                                <el-radio label="1">电话拜访</el-radio>
                                <el-radio label="2">客户来访</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20" class="one">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="one">
                        <el-form-item label="客户状态：" prop="cusStatus">
                            <el-radio-group v-model="customerFollowup.cusStatus">
                                <el-radio label="1">跟进中</el-radio>
                                <el-radio label="2">谈判中</el-radio>
                                <el-radio label="3">已签约</el-radio>
                                <el-radio label="4">不合适</el-radio>
                                <el-tooltip class="item" content="表示已入驻非本公司的园区" placement="top">
                                    <el-radio label="5">在外入驻</el-radio>
                                </el-tooltip>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                        <el-form-item label="来访/跟进时间：" prop="followupTime">
                            <el-date-picker style="width: 100%;" v-model="customerFollowup.followupTime" type="datetime" placeholder="选择日期时间" align="right">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div v-if="customerFollowup.followupType == 2">
                    <el-row :gutter="20">
                        <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15">
                            <el-form-item label="来访园区：" prop="pkCode">
                                <el-select style="width: 100%;" v-model="customerFollowup.pkCode" clearable filterable placeholder="请选择园区" @change="nameByCode">
                                    <el-option v-for="item in parkList" :key="item.code" :label="item.name" :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <el-row :gutter="20">
                    <div v-if="customerFollowup.cusStatus == 4">
                        <el-col :sm="24" :md="24" :lg="24" :xl="24" class="one">
                            <el-form-item label="跟进结果：" prop="improperReason">
                                <el-radio-group v-model="customerFollowup.improperReason">
                                    <el-radio label="1">园区不符合客户的要求</el-radio>
                                    <el-radio label="2">客户不符合园区的要求</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </div>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                        <el-form-item label="描述说明：" prop="followupRes">
                            <el-input type="textarea" v-model="customerFollowup.followupRes"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div class="block_box_img_special">
                <div class="video_title">
                    <span>上传照片:</span>
                </div>
                <div class="video_div">
                    <div v-for="(img,index) in outPictureList" :key="index">
                        <img class="video" :src=" parkBaseUrl + 'file/read/' + img ">
                        <i class="el-icon-remove-outline" style="float: left" @click="deleteOutPicture(index)"></i>
                    </div>
                    <div class="video_content">
                        <file @selectFile="selectOutPicture"></file>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>

            <div slot="footer">
                <el-button @click="resetForm('customerFollowup')">重 置</el-button>
                <el-button type="primary" @click="submitForm('customerFollowup')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import Vue from 'vue'
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElFormItem from "../../../../node_modules/element-ui/packages/form/src/form-item";
    import file from '../../../common/components/file';

    export default {
        components: {
            ElFormItem,
            ElButton,
            ElCol,
            ElRow,
            file
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                outPictureList: [], //上传附件列表


                // 权限控制
                showAddCustomerBtn: false, // 添加客户
                showDetailCustomerBtn: false, // 客户详情
                showAddCustomerFollowupBtn: false, // 添加客户跟进
                showCustomerFollowupDetailsBtn: false, // 客户跟进详情

                // 分页
                pageSizeData: 25,
                totalCount: 10,
                currentPage: 1,

                // 控制弹窗参数
                isShowParkParam: false, //控制园区绑定弹窗
                holdFollowupInfo: false, // 详情跟进弹窗
                dialogFormVisible: false, // 更多条件弹窗
                followupWindow: false, // 添加跟进弹窗

                dateChangeList: [], // 选择的开始日期和结束日期

                parkRegionsList: [], // 大区列表

                parkList: [], // 园区集合，添加跟进使用
                territoryType: '',
                pkObj: {}, // 园区集合，添加跟进使用

                channelList: [], // 渠道list

                pkNames: [], //园区集合
                followupList: [], // 客户跟进
                itParkArr: [], // 所有园区集合
                empAllList: [], // 人员集合

                keyword: '',
                parkParam: {
                    pkCode: ""
                },

                // 更多条件弹窗
                diaForm: {
                    trade: '', // 行业
                    tradeName: '',
                    mainContactName: '',
                    cusStatus: '',
                    cusStatusName: '',
                    companyName: '',
                    createCode: '',
                    empCode: '',
                    createName: '',
                    pkCode: '',
                    pkName: '',
                },

                // 客户信息列表
                customerList: [
                {
                    id: "",
                    cusCode: "",
                    customerType: "",
                    customerTypeName: "",
                    companyName: "",
                    mainContactName: "",
                    mainContactPhone: "",
                    hasMainContact: "",
                    customerStat: "",
                    hopePrice: "",
                    cusStatus: "", // 客户状态,默认1为洽谈中
                    cusStatusName: "",

                    // 对接人员
                    empCode: "",
                    empName: "",
                }, ],

                // 跟进信息
                customerFollowup: {
                    followupType: '', // 跟进(来访)类型
                    cusStatus: '', // 跟进客户状态名称
                    followupTime: '', // 看房时间
                    pkCode: '',
                    pkName: '', // 客户意向房源（来访园区）
                    improperReason: '', // 不合适原因
                    followupRes: '', // 来访结果
                    receptionType: '', // 带看类型

                    markDitch: '', // 客户来源渠道

                    cusCode: '', // 客户编码
                    cusName: '', // 客户联系人姓名
                    cusPhone: '', // 客户联系人电话
                    cusChannel: '', // 渠道来源
                    cusIntentPark: '', // 意向房源
                    attachment: '', //附件
                },

                // 客户状态
                cusStatusOptions: [
                {
                    value: '',
                    label: '全部状态'
                }, {
                    value: '1',
                    label: '跟进中'
                }, {
                    value: '2',
                    label: '谈判中'
                }, {
                    value: '3',
                    label: '已签约'
                }, {
                    value: '4',
                    label: '不合适'
                }, {
                    value: '5',
                    label: '在外入住'
                }],
                // 行业信息
                tradeList: [
                {
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
                    label: "纸质品业"
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
                }],

                // 校验规则
                followupRules: {
                    receptionType: [
                        { required: true, message: '请选择跟进类型', trigger: 'change' }
                    ],
                    followupType: [
                        { required: true, message: '请选择跟进类型', trigger: 'change' }
                    ],
                    cusStatus: [
                        { required: true, message: '请选择客户状态', trigger: 'change' }
                    ],
                    followupTime: [
                        { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    pkCode: [
                        { required: true, message: '请选择园区', trigger: 'change' }
                    ],
                    followupRes: [
                        { required: true, message: '请填写来访结果', trigger: 'blur' }
                    ],
                    improperReason: [
                        { required: true, message: '请填写不合适原因', trigger: 'blur' }
                    ]
                },
            }
        },
        props: ['paramsObj'], // 接收父组件数据
        created() {
            let vm = this;

            // 菜单和按钮权限控制
            vm.showAddCustomerBtn = vm.$flagMenuStore.judgeMenu("addCustomerBtn");
            vm.showDetailCustomerBtn = vm.$flagMenuStore.judgeMenu("detailCustomerBtn");
            vm.showAddCustomerFollowupBtn = vm.$flagMenuStore.judgeMenu("addCustomerFollowupBtn");
            vm.showCustomerFollowupDetailsBtn = vm.$flagMenuStore.judgeMenu("customerFollowupDetailsBtn");

            vm.selectAllPark(); // 查询所有园区
            vm.selectParkRegions(); //查询所有大区

            if (vm.paramsObj) {
                // 从园区列表跳转，查询某个园区入驻的客户
                if (vm.paramsObj.pkCode) {
                    vm.diaForm.pkCode = vm.paramsObj.pkCode;
                }
                if (vm.paramsObj.cusStatus) {
                    vm.diaForm.cusStatus = vm.paramsObj.cusStatus;
                }
                vm.handTermSearch(1);
            } else {
                vm.handTermSearch(1); // 查询客户集合
            }
        },
        methods: {
            // 公共方法：加载所有园区
            selectAllPark() {
                let vm = this;

                let sendObj = {
                    entity: {
                        territoryType: vm.territoryType
                    },
                };

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "park/list",
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itParkArr = response.data.result;
                    let aObj = { pkCode: "", pkName: "全部园区" };
                    vm.itParkArr.unshift(aObj);

                    // 招商人员才处理
                    if (vm.itParkArr.createDeptCode == '18091434ebbdefa9524a' || vm.itParkArr.createDeptCode == '180904b99416ead81c42') {
                        vm.getpkCodeNow(); // 获取默认园区
                    }
                }).catch(function (error) {
                    vm.$message.error('页面：下拉框，选择园区，获取数据失败！');
                });
            },
            // 客户新增
            addCustomer() {
                let data = {
                    menuName: '客户新增',
                    item: 'customerCreate',
                };
                this.$emit('backParam', data);
            },
            // 客户详情
            handleEdit(cusCode) {
                let data = {
                    menuName: '客户详情',
                    item: 'customerDetail',
                    params: {
                        cusCode: cusCode
                    }
                };
                this.$emit('backParam', data);
            },
            // 跟进详情
            followupInfo(cusCode) {
                let vm = this;

                this.holdFollowupInfo = true;
                let followupObj = {
                    entity: {
                        cusCode: cusCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: followupObj,
                    url: "followup/followupList",
                };
                this.$ajax(
                    options
                ).then(response => {
                    vm.followupList = response.data.result;

                    for (let i = 0; i < vm.followupList.length; i++) {
                        if (vm.followupList[i].attachment) {
                            let attachmentList = vm.followupList[i].attachment.split(",");
                            vm.followupList[i].attachmentList = attachmentList;
                        }
                    }


                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '查询客户跟进信息失败',
                        type: 'error'
                    });
                })
            },
            // 获取默认园区
            getpkCodeNow() {
                let vm = this;

                // 查询出员工信息
                vm.changeEmp();

                let flag = true;
                if (flag) { //flag留后面表单验证
                    let sendObj = {};
                    sendObj.entity = {};
                    let options = {
                        method: 'POST',
                        data: sendObj,
                        url: "parkButtJoint/queryPkcode",
                    };

                    this.$ajax(
                        options
                    ).then(function (response) {
                        let buttArr = response.data.result;
                        let i = 0;
                        i = buttArr.length;

                        if (i == null || i == 0) {
                            vm.isShowParkParam = true;

                            //房源搜索栏
                            let pk_options = {
                                method: 'POST',
                                data: {},
                                url: "park/list",
                            };
                            vm.$ajax(
                                pk_options
                            ).then(function (response) {
                                let result = response.data.result;
                                let pkNameList = [];
                                let pkObj = [];
                                for (let s = 0; s < result.length; s++) {
                                    let code = result[s].pkCode;
                                    let name = result[s].pkName;
                                    pkNameList.push({ code: code, name: name });
                                    pkObj[code] = name;
                                }
                                vm.pkNames = pkNameList;
                                vm.pkObj = pkObj;
                            }).catch(function (error) {
                                vm.$alert('页面：电费异常看板房源搜索失败', '系统异常', {
                                    confirmButtonText: '确定',
                                    callback: action => {}
                                });
                            });
                        }

                        if (i == 1) {
                            vm.diaForm.pkCode = buttArr[0].pkCode;
                            let pkCode = vm.diaForm.pkCode;
                            //赋值name
                            let tempObj = {};
                            tempObj = vm.itParkArr.find((item) => {
                                return item.pkCode === pkCode;
                            });
                            vm.diaForm.pkName = tempObj.pkName;
                            vm.handTermSearch(1);
                        }

                        if (i >= 2) {
                            vm.diaForm.pkCode = buttArr[0].pkCode;
                            let pkCode = vm.diaForm.pkCode;
                            // 赋值name
                            let tempObj = {};
                            tempObj = vm.itParkArr.find((item) => {
                                return item.pkCode === pkCode;
                            });
                            vm.diaForm.pkName = tempObj.pkName;
                            vm.handTermSearch(1);
                            vm.$notify({
                                title: '警告',
                                message: '已对接2个及以上园区！自动选了一个默认园区！',
                                type: 'warning'
                            });
                        }
                    }).catch(function (error) {
                        vm.$message.error('页面：客户初始化，查询园区编码，获取数据失败！');
                    });
                }
            },
            // 绑定园区
            onParkSubmit() {
                let vm = {};
                vm = this;
                let param = {};
                param.entity = {};
                param.entity.pkCode = vm.parkParam.pkCode;

                // 获取登录存在cookie中的empCode
                let empCode = Vue.prototype.$cookieStore.getCookie("empCode");

                for (let i = 0; i < vm.empAllList.length; i++) {
                    if (vm.empAllList[i].empCode == empCode) {
                        param.entity.empCode = vm.empAllList[i].empCode;
                        param.entity.buttJointType = 1;
                        param.entity.postName = vm.empAllList[i].postName;
                    }
                }

                let pbj_option = {
                    method: 'POST',
                    data: param,
                    url: 'parkButtJoint/createByPkCode'
                };
                this.$ajax(
                    pbj_option
                ).then(function (response) {
                    let pkCode = param.entity.pkCode;
                    vm.diaForm.pkCode = pkCode;
                    // 赋值name
                    let tempObj = {};
                    tempObj = vm.itParkArr.find((item) => {
                        return item.pkCode === pkCode;
                    });
                    vm.diaForm.pkName = tempObj.pkName;
                    vm.handTermSearch(1);

                    vm.isShowParkParam = false;
                }).catch(function (error) {
                    vm.$alert('页面：招商人员绑定园区失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // select查询全部大区
            selectParkRegions() {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift({ deptCode: '', deptName: '全部大区' })
            },

            // 请选择园区
            changeParkNameBase(aval) {
                //赋值name
                let tempObj = {};
                tempObj = this.itParkArr.find((item) => {
                    return item.pkCode === aval;
                });
                this.diaForm.pkName = '';
                this.handTermSearch(1);
            },
            // 所属招商人员
            changeCreateCodeBase(aval) {
                //赋值name
                let tempObj = {};
                tempObj = this.empAllList.find((item) => {
                    return item.empCode === aval;
                });
                this.diaForm.createName = tempObj.empName;
            },
            // 客户状态
            changeCusStatusBase(aval) {
                // 赋值name
                let tempObj = {};
                tempObj = this.cusStatusOptions.find((item) => {
                    return item.value === aval;
                });
                this.diaForm.cusStatusName = tempObj.label;
            },
            // 按钮：更多条件 > 行业选择
            changeTradeBase(aval) {
                // 赋值行业name
                let tempObj = {};
                tempObj = this.tradeList.find((item) => {
                    return item.value === aval;
                });
                this.diaForm.tradeName = tempObj.label;
            },
            // 查询人员信息
            changeEmp() {
                let vm = this;

                let sendObj = {};
                sendObj.entity = {};
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "employee/list",
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    vm.empAllList = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面：获取人员信息数据失败！');
                });
            },

            handReset() {
                this.keyword = '';
                this.diaForm.trade = '';
                this.diaForm.tradeName = '';
                this.diaForm.mainContactName = '';
                this.diaForm.cusStatus = '';
                this.diaForm.cusStatusName = '';
                this.diaForm.companyName = '';
                this.diaForm.createCode = '';
                this.diaForm.createName = '';
                this.diaForm.empCode = '';
                this.territoryType = '';
                this.dateChangeList = [];
                this.handTermSearch(1);
            },
            // 添加跟进
            followupCreate(cusCode, ditch, mainContactName, mainContactPhone) {
                let vm = this;

                vm.customerFollowup.cusCode = cusCode;
                vm.customerFollowup.markDitch = ditch; //客户来源渠道
                vm.customerFollowup.cusChannel = ditch; //客户来源渠道
                vm.customerFollowup.cusName = mainContactName; //客户主联系人姓名
                vm.customerFollowup.cusPhone = mainContactPhone; //客户主联系人电话

                vm.followupWindow = true; // 添加跟进弹窗展示

                let options = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let result = response.data.result;

                        let pkNameList = [];
                        let pkObj = [];

                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push({ code: code, name: name });
                            pkObj[code] = name;
                        }
                        vm.parkList = pkNameList;
                        vm.pkObj = pkObj;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '搜索房源失败',
                            type: 'error'
                        });
                    }
                })
            },
            // 添加跟进 保存按钮
            submitForm(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // 客户带看类型处理
                        if (this.customerFollowup.markDitch == 3) { // 客户来源渠道等于3为中介
                            this.customerFollowup.receptionType = "2"; // 中介带看
                        } else {
                            this.customerFollowup.receptionType = "1"; // 主动获客
                        }

                        vm.customerFollowup.attachment = vm.outPictureList.join(",");

                        let obj = {
                            entity: vm.customerFollowup,
                        };

                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "followup/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '新增客户跟进成功',
                                    type: 'success',
                                });

                                // 跟进新增成功后，重置表单并关闭窗口
                                vm.customerFollowup.followupty = '';
                                vm.customerFollowup.cusStatus = '';
                                vm.customerFollowup.followupTime = '';
                                vm.customerFollowup.pkCode = '';
                                vm.customerFollowup.pkName = '';
                                vm.customerFollowup.improperReason = '';
                                vm.customerFollowup.followupRes = '';
                                vm.customerFollowup.receptionType = '';
                                vm.customerFollowup.markDitch = '';

                                vm.resetForm(formName);
                                vm.followupWindow = false;
                            }
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '信息不完整',
                            type: 'error'
                        });
                        return false;
                    }
                });
            },
            // 重置，添加跟进
            resetForm(formName) {
                this.outPictureList = [];
                this.$refs[formName].resetFields();
            },
            nameByCode(pkCode) {
                if (!this.pkObj[pkCode]) {
                    this.customerFollowup.pkCode = '';
                }
                this.customerFollowup.pkName = this.pkObj[pkCode];
            },
            handTermSearch(val) {
                let vm = this;

                let sendObj = {};
                if (val == 1) {
                    this.currentPage = 1;
                }
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (vm.currentPage - 1) * (sendObj.pageSize);
                sendObj.keyword = vm.keyword;
                sendObj.entity = {};
                vm.diaForm.pkName = '';
                sendObj.entity = vm.diaForm;
                sendObj.territoryType = vm.territoryType;
                if (vm.dateChangeList[0]) {
                    sendObj.startDate = vm.dateChangeList[0].getTime();
                }
                if (vm.dateChangeList[1]) {
                    sendObj.endDate = vm.dateChangeList[1].getTime();
                }
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "customer/selectByKeyword",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.customerList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;

                    // 悬浮栏，园区选择下拉选
                    /*if (vm.customerList) {
                        for (let i = 0; i < vm.customerList.length; i++) {
                            let obj = vm.customerList[i];

                            for (let j = 0; j < vm.itParkArr.length; j++) {
                                if (vm.customerList[i].pkCode == vm.itParkArr[j].pkCode) {
                                    let pkName = vm.itParkArr[j].pkName;
                                    obj.pkName = pkName;
                                }
                            }
                        }
                    }*/
                    vm.dialogFormVisible = false; // 请求完毕后,关闭对话框
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 分页：当前页发生改变
            handleCurrentChange(val) {
                let vm = this;
                this.currentPage = val
                //        localStorage.setItem('pageStart', [sendObj.start,sendObj.pageSize]);
                //        vm.selectAllPark();// 查询所有园区
                vm.handTermSearch(); // 查询客户
            },

            changeTerritoryCode() {
                this.diaForm.pkCode = '';
                this.selectAllPark();
                this.handTermSearch(1);
            },

            //选择附件
            selectOutPicture(urls) {
                if (urls) {
                    for (var i = 0; i < urls.length; i++) {
                        if (urls[i].fileType != 'image') {
                            this.$message({
                                showClose: true,
                                message: '仅支持图片格式',
                                type: 'error'
                            });
                            this.outPictureList = [];
                            return;
                        }
                        this.outPictureList.push(urls[i].fileCode);
                    }
                }
            },

            deleteOutPicture(index) {
                this.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.outPictureList.splice(index, 1);
                    this.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },

            beforeFollowClose() {
                this.resetForm("customerFollowup");
                this.followupWindow = false;
            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    /**************************************************************************/

    /*搜索框*/
    .input-with-select {
        width: 270px;
        float: left;
        margin-left: 20px;
    }

    /*更多条件按钮*/
    .more-term-button {
        float: left;
        margin-left: 20px;
    }

    /*选择园区下拉选*/
    .change-park {
        float: left;
        margin-left: 20px;
        width: 150px;
    }

    /*选择园区下拉选*/
    .data-picker {
        width: 50%;
    }

    .change-territory {
        float: left;
        margin-left: 20px;
        height: 30px;
        line-height: 30px;
        width: 150px;
    }

    /*客户新增按钮*/
    .add-customer-button {
        float: right;
        margin-right: 20px;
    }

    /*已选条件展示*/
    .term-tag {
        float: left;
        margin-top: 5px;
        margin-left: 10px;
        font-size: 14px;
        background-color: white;
    }

    /**************************************************************************/

    /*表格展开后内容样式*/
    .demo-table-expand {
        font-size: 0;
        text-align: left;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }

    /**************************************************************************/

    /*分页栏样式*/
    .page-box {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    /*添加跟进样式*/
    .one {
        text-align: left;
    }

    .navigation_date_select {
        width: 200px;
    }

    /*视频信息特殊样式*/
    .block_box_img_special {
        min-height: 40px;
        background-color: white;
        padding-bottom: 30px;
        margin-bottom: 30px;
        margin-top: -29px;
        margin-left: 60px;
    }

    /*清除浮动*/
    .clear {
        clear: both;
    }

    /*视频列标题*/
    .video_title {
        height: 100%;
        width: 220px;
        color: #fe980f;
        text-align: left;
        margin-left: -13px;
    }

    /*视频列内容*/
    .video_content {
        float: left;
        width: 240px;
        padding-top: 50px;
        padding-bottom: 50px;
        background-color: #f1f1f1;
        /*margin-left: 20px;*/
        margin-top: 20px;
        text-align: center;
    }

    .video {
        width: 240px;
        float: left;
        margin-left: 20px;
        height: 160px;
        margin-top: 20px;
    }

    .img_attachment {
        width: 50px;
        height: 50px;
    }
</style>