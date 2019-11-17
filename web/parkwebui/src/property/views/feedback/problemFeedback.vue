<template>
    <div class="not-position_root" ref="myRoot">
        <div class="not-position_header">
            <el-button class="insert-button" size="small" type="primary" @click="openAddFeedbackWindow()">
                添加
            </el-button>
            <el-input class="input-with-select" v-model="keyword" @keyup.enter.native="initInfo()" placeholder="问题编码、标题、创建人搜索" size="small" clearable>
                <el-button slot="append" icon="el-icon-search" @click="initInfo()"></el-button>
            </el-input>
        </div>

        <el-form class="root_style">
            <el-card class="box-card" v-for="(item, index) in feedbackList" :key="index" shadow="hover">
                <el-avatar v-if="item.fbStatus == '1'" shape="circle" :size="100" fit="scale-down" class="el_card_img">
                    <span style="font-size: 22px;">待处理</span>
                </el-avatar>
                <el-avatar v-if="item.fbStatus == '2'" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #f7d856">
                    <span style="font-size: 22px;">已受理</span>
                </el-avatar>
                <el-avatar v-if="item.fbStatus == '3'" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: green">
                    <span style="font-size: 22px;">已解决</span>
                </el-avatar>

                <div class="company_info">
                    <el-row :gutter="20">
                        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="el_col_style">
                            <el-form-item label="问题标题：">
                                <span>{{ item.fbTitle }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-button @click="openFeedbackDetailWindow(item)" icon="el-icon-s-promotion" type="success" round size="small">详情</el-button>
                            <el-button @click="openEditFeedbackWindow(item.fbCode)" v-if="item.fbStatus == '1'" icon="el-icon-edit" type="primary" round size="small">编辑</el-button>
                            <!--<el-button @click="openCpyAuthWindow(item)" icon="el-icon-s-promotion" type="warning" round size="small">处理</el-button>-->
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="问题编号：">
                                <span>{{ item.fbCode }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="反馈类型：">
                                <span>{{ item.fbTypeName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <div class="feedback_list_under">{{item.askQuestionName}}&nbsp;提交于&nbsp;{{item.createTime | time}}</div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" class="el_col_style">
                            <el-form-item label="反馈描述：">
                                <div class="des_pre" :title="item.fbDescription">{{ item.fbDescription }}</div>
                                <!--<span>{{ item.fbDescription }}</span>-->
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <div style="clear: both"></div>
            </el-card>

            <!-- 分页栏 -->
            <div class="page-box">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
        </el-form>

        <!-- 添加问题反馈、修改问题反馈弹窗 -->
        <el-dialog class="insert-card-div hasfooter-top10-dialog" width="80%" top="10vh" style="text-align: center;" :title="windowTitle" :visible.sync="addFeedbackWindow" :close-on-click-modal="false">
            <el-form :model="feedbackObj" :rules="rules" ref="feedbackObj" label-width="110px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="提问人：" prop="askQuestionName">
                            <el-input clearable v-model="feedbackObj.askQuestionName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="手机号：" prop="askQuestionPhone">
                            <el-input clearable v-model="feedbackObj.askQuestionPhone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20" v-if="windowFlag == '2'">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label-width="110px" label="处理人">
                            <el-select filterable v-model="feedbackObj.dealQuestionCode" @change="changeEmp" placeholder="选择处理人">
                                <el-option v-for="item in employeeList" :key="item.code" :label="item.name" :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="手机号：">
                            <el-input clearable v-model="feedbackObj.dealQuestionPhone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="问题标题：" prop="fbTitle">
                            <el-input clearable v-model="feedbackObj.fbTitle"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="反馈类型：" prop="fbType">
                            <el-select clearable v-model="feedbackObj.fbType" placeholder="请选择" style="width:100%;">
                                <el-option v-for="(item,index) in fbTypeList" :key="item.value" :label="item.label" :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <el-form-item label="反馈描述：" prop="fbDescription">
                            <el-input clearable :autosize="{ minRows: 4, maxRows: 8}" type="textarea" v-model="feedbackObj.fbDescription" suffix-icon="el-icon-location"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="图片：">
                            <file @selectFile="selectImg" style="text-align: left;"></file>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="feedback_img" v-for="(item,index) in feedbackImgList" :key="index">
                            <el-avatar shape="square" :size="100" fit="scale-down" :src="parkBaseUrl + 'file/read/' + item" style="width: 100px; height: 100px; margin-left: 20px;"></el-avatar>

                            <el-button type="text" @click="deleteImg(index)">
                                <i class="el-icon-delete">&nbsp;&nbsp;删除</i>
                            </el-button>
                        </div>

                    </el-col>

                </el-row>

            </el-form>

            <!-- 内部弹窗：询问是否分配处理人 -->
            <el-dialog width="60%" title="分配处理人" :visible.sync="chooseDealQuestionWindow" append-to-body :close-on-click-modal="false">
                <el-form>
                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label-width="110px" label="处理人：">
                                <el-select filterable v-model="feedbackObj.dealQuestionCode" @change="changeEmp" placeholder="选择处理人">
                                    <el-option v-for="item in employeeList" :key="item.code" :label="item.name" :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label-width="90px" label="手机号：" prop="checkPhone">
                                <el-input clearable v-model="feedbackObj.dealQuestionPhone"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="submitForm('feedbackObj','no')">暂不分配</el-button>
                    <el-button type="primary" @click="submitForm('feedbackObj','yes')">分配提交</el-button>
                </div>
            </el-dialog>

            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('feedbackObj')">取 消</el-button>
                <el-button type="primary" @click="beforeSubmitForm('feedbackObj')">提 交</el-button>
            </div>
        </el-dialog>

        <!-- 问题反馈详情弹窗 -->
        <el-dialog width="80%" class="hasfooter-dialog_wrapper" title="反馈问题详情" top="10vh" :visible.sync="showFeedbackDetailWindow" :close-on-click-modal="false">
            <div class="feedback_detail">

                <el-card style="margin-bottom: 20px;">
                    <el-form>
                        <el-avatar v-if="oneFeedbackObj.fbStatus == '1'" shape="circle" :size="100" fit="scale-down" class="el_card_img">
                            <span style="font-size: 22px;">待处理</span>
                        </el-avatar>
                        <el-avatar v-if="oneFeedbackObj.fbStatus == '2'" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: #f7d856">
                            <span style="font-size: 22px;">已受理</span>
                        </el-avatar>
                        <el-avatar v-if="oneFeedbackObj.fbStatus == '3'" shape="circle" :size="100" fit="scale-down" class="el_card_img" style="background-color: green">
                            <span style="font-size: 22px;">已解决</span>
                        </el-avatar>

                        <div class="company_info">
                            <el-row :gutter="20">
                                <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="el_col_style">
                                    <el-form-item label="问题标题：">
                                        <span>{{ oneFeedbackObj.fbTitle }}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style" style="text-align: right;padding-right: 32px;">
                                    <el-button v-if="oneFeedbackObj.fbStatus == 1" @click="acceptFeedback(oneFeedbackObj.fbCode)" type="warning" size="small" icon="el-icon-s-opportunity" round>受理</el-button>
                                    <el-button v-if="oneFeedbackObj.fbStatus == 2" @click="showInnerWindow" type="primary" size="small" icon="el-icon-message" round>回复</el-button>
                                    <el-button v-if="oneFeedbackObj.fbStatus == 2" @click="solveFeedback(oneFeedbackObj.fbCode)" type="success" size="small" icon="el-icon-check" round>解决</el-button>
                                </el-col>
                            </el-row>

                            <el-row :gutter="20">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                                    <el-form-item label="问题编号：">
                                        <span>{{ oneFeedbackObj.fbCode }}</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                                    <el-form-item label="反馈类型：">
                                        <span>{{ oneFeedbackObj.fbTypeName }}</span>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">-->
                                <!--<el-form-item label="反馈状态：">-->
                                <!--<span>{{ oneFeedbackObj.fbStatusName }}</span>-->
                                <!--</el-form-item>-->
                                <!--</el-col>-->
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style" style="text-align: right">
                                    <div class="feedback_list_under">{{oneFeedbackObj.askQuestionName}}&nbsp;提交于&nbsp;{{oneFeedbackObj.createTime | time}}</div>
                                </el-col>
                            </el-row>

                            <el-row :gutter="24">
                                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="fankui_col">
                                    <el-form-item label="反馈描述：">
                                        <div :title="oneFeedbackObj.fbDescription">
                                            {{ oneFeedbackObj.fbDescription }}
                                        </div>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                    <div class="feedback_img" v-for="(item,index) in feedbackImgList" :key="index">
                                        <el-avatar shape="square" :size="100" fit="scale-down" :src="parkBaseUrl + 'file/read/' + item" style="width: 100px; height: 100px; margin-left: 20px;"></el-avatar>
                                    </div>
                                </el-col>
                            </el-row>
                        </div>
                    </el-form>
                </el-card>

                <div class="deal_qus_card_div">
                    处理人：{{oneFeedbackObj.dealQuestionName}}<span class="deal_qus_card_span">联系方式：{{oneFeedbackObj.dealQuestionPhone}}</span>
                </div>

                <el-timeline>
                    <el-timeline-item v-for="(item, index) in fbReplyList" :key="index" :timestamp="index+1+'楼'" placement="top">
                        <el-card>
                            <h4>{{item.replyDescription}}</h4>
                            <div class="feedback_img" v-for="(item2,index2) in dealFeedbackReplyImgMap.get(item.replyCode)" :key="index2">
                                <el-avatar shape="square" :size="100" fit="scale-down" :src="parkBaseUrl + 'file/read/' + item2" style="width: 100px; height: 100px; margin-left: 20px;"></el-avatar>
                            </div>
                            <div class="timeline_under">{{item.createName}}&nbsp;回复于&nbsp;{{item.createTime | time}}</div>
                        </el-card>
                    </el-timeline-item>
                </el-timeline>
            </div>

            <el-dialog width="60%" class="fbReplyObjDialog" title="问题回复" :visible.sync="showFbReplyWindow" append-to-body :close-on-click-modal="false">

                <el-form :model="fbReplyObj" :rules="rules" ref="fbReplyObj" label-width="110px" style="text-align: left;">
                    <el-row :gutter="20">
                        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                            <el-form-item label="反馈描述：" prop="replyDescription">
                                <el-input clearable :autosize="{ minRows: 4, maxRows: 8}" type="textarea" v-model="fbReplyObj.replyDescription" suffix-icon="el-icon-location"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-form-item label="图片：">
                                <file @selectFile="selectReplyImg" style="text-align: left;"></file>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <div class="feedback_img" v-for="(item,index) in fbReplyImgList" :key="index">
                                <el-avatar shape="square" :size="100" fit="scale-down" :src="parkBaseUrl + 'file/read/' + item" style="width: 100px; height: 100px; margin-left: 20px;"></el-avatar>

                                <el-button type="text" @click="deleteReplyImg(index)">
                                    <i class="el-icon-delete">&nbsp;&nbsp;删除</i>
                                </el-button>
                            </div>
                        </el-col>
                    </el-row>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('fbReplyObj')">取 消</el-button>
                    <el-button type="primary" @click="submitReplyForm('fbReplyObj')">提 交</el-button>
                </div>

            </el-dialog>
        </el-dialog>

    </div>
</template>

<script>
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue';
    import ElTimeline from "../../../../node_modules/element-ui/packages/timeline/src/main";

    export default {
        components: {
            ElTimeline,
            ElOption,
            file,
        },
        data() {
            //校验电话号码
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!(pattern.test(value))) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                addFeedbackWindow: false, // 新增问题反馈弹窗
                windowTitle: '', // 弹窗标题
                windowFlag: '',

                employeeList: [], //人员列表
                employeeInfo: [],

                showFeedbackDetailWindow: false, //控制详情弹窗
                showFbReplyWindow: false, //控制回复弹窗

                chooseDealQuestionWindow: false, //控制分配处理人弹窗

                feedbackList: [], // 问题反馈列表

                oneFeedbackObj: {}, //一条问题反馈对象\

                feedbackImgList: [], //问题反馈图片集合
                fbReplyImgList: [], //回复图片集合

                dealFeedbackReplyImgMap: {}, //回复列表中各个回复的图片集

                // 添加问题实体
                feedbackObj: {
                    fbCode: '',
                    fbType: '',
                    fbTypeName: '',
                    fbTitle: '',
                    fbDescription: '',
                    fbImg: '',
                    fbStatus: '',
                    fbStatusName: '',
                    askQuestionName: '',
                    askQuestionPhone: '',
                    dealQuestionCode: '',
                    dealQuestionName: '',
                    dealQuestionPhone: '',
                },

                //回复实体
                fbReplyObj: {
                    fbCode: "",
                    replyDescription: "",
                    replyImg: "",
                },

                fbReplyList: [], //回复集合

                fbTypeList: [{
                        value: "1",
                        label: "硬件设备相关"
                    },
                    {
                        value: "2",
                        label: "软件系统相关"
                    },
                    {
                        value: "3",
                        label: "服务相关"
                    },
                    {
                        value: "4",
                        label: "其他"
                    },
                ],

                // 分页
                start: 1,
                pageSize: 25, // 默认每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0,
                keyword: "", //关键字

                rules: {
                    fbTitle: [{
                        required: true,
                        message: '请输入问题标题',
                        trigger: 'blur'
                    }],
                    fbType: [{
                        required: true,
                        message: '请选择反馈类型',
                        trigger: 'blur'
                    }],
                    fbDescription: [{
                        required: true,
                        message: '请输入反馈描述',
                        trigger: 'change'
                    }],
                    replyDescription: [{
                        required: true,
                        message: '请输入回复反馈描述',
                        trigger: 'change'
                    }],
                    askQuestionName: [{
                        required: true,
                        message: '请输入提问人姓名',
                        trigger: 'blur'
                    }],
                    askQuestionPhone: [{
                            required: true,
                            message: '请输入提问人手机号',
                            trigger: 'blur'
                        },
                        {
                            validator: checkPhone
                        }
                    ],
                    dealQuestionCode: [{
                        required: true,
                        message: '请选择处理人',
                        trigger: 'blur'
                    }, ],
                    dealQuestionPhone: [{
                            required: true,
                            message: '请输入处理人手机号',
                            trigger: 'blur'
                        },
                        {
                            validator: checkPhone
                        }
                    ],
                    checkPhone: [{
                        validator: checkPhone
                    }],

                },
            }
        },
        created: function() {
            this.initInfo();
            //查询人员列表
            this.getEmployee();
        },
        methods: {
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.initInfo();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.initInfo();
            },

            // 初始化页面
            initInfo() {
                let vm = this;

                let params = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    keyword: vm.keyword,
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'feedback/query'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.feedbackList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(error => {
                    vm.$alert('页面：公司列表加载失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            /*********************查询人员列表*******************/
            //获取所有员工
            getEmployee() {
                let vm = this;
                let params = {
                    entity: {}
                };
                let options = {
                    method: "POST",
                    data: params,
                    url: "employee/list"
                };
                this.$ajax(
                    options
                ).then(response => {
                    let employeeList = response.data.result;
                    for (let i = 0; i < employeeList.length; i++) {
                        vm.employeeList.push({
                            code: employeeList[i].empCode,
                            name: employeeList[i].empName
                        });
                        vm.employeeInfo[employeeList[i].empCode] = employeeList[i];
                    }
                }).catch(error => {
                    this.$alert("页面:获取员工失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {}
                    });
                })
            },

            //选择人员
            changeEmp(empCode) {
                let vm = this;
                if (vm.employeeInfo[empCode]) {
                    vm.feedbackObj.dealQuestionName = vm.employeeInfo[empCode].empName;
                    vm.feedbackObj.dealQuestionPhone = vm.employeeInfo[empCode].empPhone;
                }

            },

            // 打开添加问题弹窗
            openAddFeedbackWindow() {
                let vm = this;

                vm.feedbackObj = {
                    fbCode: '',
                    fbType: '',
                    fbTypeName: '',
                    fbTitle: '',
                    fbDescription: '',
                    fbImg: '',
                    fbStatus: '',
                    fbStatusName: '',
                    askQuestionName: '',
                    askQuestionPhone: '',
                    dealQuestionCode: '',
                    dealQuestionName: '',
                    dealQuestionPhone: '',
                    device: '1' // 设备类型设为1（PC）
                };

                vm.feedbackImgList = [];

                vm.windowFlag = '1';
                vm.windowTitle = '新增问题反馈';
                vm.addFeedbackWindow = true;
            },

            // 修改问题反馈信息
            openEditFeedbackWindow(fbCode) {
                let vm = this;

                vm.windowTitle = '修改问题反馈';
                vm.windowFlag = '2';
                vm.addFeedbackWindow = true;

                let obj = {};
                obj.entity = {};
                let option = {
                    method: 'POST',
                    data: {
                        fbCode: fbCode
                    },
                    url: "feedback/list"
                };

                this.$ajax(
                    option
                ).then(response => {
                    if (response) {
                        vm.feedbackObj = response.data.result[0];
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: "分配处理出现错误",
                        type: 'error'
                    });
                });


                vm.feedbackImgList = [];

                //处理照片
                if (vm.feedbackObj.fbImg) {
                    vm.feedbackImgList = vm.feedbackObj.fbImg.split(",");
                }

            },

            //(添加/编辑)  提交前判断
            beforeSubmitForm(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (vm.windowFlag == '1') {
                            vm.chooseDealQuestionWindow = true;
                        } else if (vm.windowFlag == '2') {
                            vm.submitForm(formName);
                        }
                    }
                });
            },

            //分配处理人
            chooseDealSubmit(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let obj = {};
                        obj.entity = {};
                        let option = {
                            method: 'POST',
                            data: obj,
                            url: "feedback/update"
                        };

                        //                        this.$ajax(
                        //                            option
                        //                        ).then(response => {
                        //                            if(response) {
                        //
                        //                            }
                        //                        }).catch(error=> {
                        //                            vm.$message({
                        //                                showClose: true,
                        //                                message: "分配处理出现错误",
                        //                                type: 'error'
                        //                            });
                        //                        });

                    }
                });
            },

            // (添加/编辑)  提交
            submitForm(formName, flag) {
                let vm = this;

                //分配处理人提交
                if (flag == 'yes') {
                    //校验是否选择了处理人
                    if (!(vm.feedbackObj.dealQuestionCode && vm.feedbackObj.dealQuestionName && vm.feedbackObj.dealQuestionPhone)) {
                        vm.$alert('请完善处理人信息', '友情提示', {
                            confirmButtonText: '确定',
                            callback: action => {}
                        });
                        return;
                    }

                } else if (flag == 'no') { //不分配处理人提交
                    vm.feedbackObj.dealQuestionCode = "";
                    vm.feedbackObj.dealQuestionName = "";
                    vm.feedbackObj.dealQuestionPhone = "";
                }

                this.chooseDealQuestionWindow = false;

                //将上传的图片code平成字符串
                let imgUrl = "";
                for (let i = 0; i < vm.feedbackImgList.length; i++) {
                    imgUrl = imgUrl + vm.feedbackImgList[i] + ","
                }

                imgUrl = imgUrl.substring(0, imgUrl.length - 1);
                vm.feedbackObj.fbImg = imgUrl;

                let obj = {
                    entity: vm.feedbackObj
                };


                if (vm.feedbackObj) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            let url = '';
                            let msg = '';
                            let msgError = '';

                            if (vm.windowFlag == '1') {
                                url = 'feedback/create';
                                msg = '反馈问题成功！';
                                msgError = '反馈问题失败！';
                            } else if (vm.windowFlag == '2') {
                                url = 'feedback/update';
                                msg = '修改问题成功！';
                                msgError = '修改问题失败！';
                            }

                            let options = {
                                method: 'POST',
                                data: obj,
                                url: url
                            };

                            this.$ajax(
                                options
                            ).then(response => {
                                if (response) {
                                    vm.$message({
                                        showClose: true,
                                        message: msg,
                                        type: 'success'
                                    });

                                    this.$refs[formName].resetFields();
                                    this.addFeedbackWindow = false;

                                    // 刷新列表
                                    vm.initInfo(vm.keyword);
                                }
                            }).catch(error => {
                                vm.$message({
                                    showClose: true,
                                    message: msgError,
                                    type: 'error'
                                });
                            });
                        } else {
                            this.$message({
                                showClose: true,
                                message: '数据填写不完整',
                                type: 'warning'
                            });
                        }
                    })
                }
            },

            selectImg(urls) {
                for (let i = 0; i < urls.length; i++) {
                    this.feedbackImgList.push(urls[i].fileCode);
                }
            },

            selectReplyImg(urls) {
                for (let i = 0; i < urls.length; i++) {
                    this.fbReplyImgList.push(urls[i].fileCode);
                }
            },

            deleteImg(index) {
                let vm = this;

                vm.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    vm.feedbackImgList.splice(index, 1);
                    vm.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },

            //删除回复图片
            deleteReplyImg(index) {
                let vm = this;

                vm.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    vm.fbReplyImgList.splice(index, 1);
                    vm.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },

            // 重置表单
            resetForm(formName) {
                this.$refs[formName].resetFields();
                if (formName == "feedbackObj") {
                    this.addFeedbackWindow = false;
                } else if (formName == "fbReplyObj") {
                    this.showFbReplyWindow = false;
                }

            },

            /***************************详情************************************/

            //查询一条问题反馈
            selectOneFeedback(fbCode) {
                let vm = this;

                let obj = {};
                obj.entity = {
                    fbCode: fbCode
                };

                let option = {
                    method: 'POST',
                    data: obj,
                    url: "feedback/list"
                };

                this.$ajax(
                    option
                ).then(response => {
                    if (response && response.data.result.length == 1 && response.data.result[0]) {
                        vm.oneFeedbackObj = response.data.result[0];
                    } else {
                        vm.$message({
                            showClose: true,
                            message: "查询一条问题反馈返回数据有误",
                            type: 'error'
                        });
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: "查询一条问题反馈出错",
                        type: 'error'
                    });
                });
            },

            //点击问题反馈详情
            openFeedbackDetailWindow(item) {
                let vm = this;
                vm.showFeedbackDetailWindow = true;

                vm.feedbackImgList = [];

                vm.oneFeedbackObj = item;

                if (vm.oneFeedbackObj.fbImg) {
                    vm.feedbackImgList = vm.oneFeedbackObj.fbImg.split(",");
                }

                vm.selectReplyList(vm.oneFeedbackObj.fbCode);
            },

            //查询回复集合
            selectReplyList(fbCode) {
                let vm = this;

                let obj = {};
                obj.entity = {
                    fbCode: fbCode
                };

                let option = {
                    method: 'POST',
                    data: obj,
                    url: "feedbackReply/list"
                };

                this.$ajax(
                    option
                ).then(response => {
                    if (response) {
                        vm.fbReplyList = response.data.result;

                        //处理回复中的图片
                        vm.dealFeedbackReplyImg(vm.fbReplyList);

                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: "查询回复集合出错",
                        type: 'error'
                    });
                });
            },

            //处理回复中的图片
            dealFeedbackReplyImg(fbReplyList) {
                let vm = this;
                let map = new Map();
                for (let i = 0; i < fbReplyList.length; i++) {
                    if (fbReplyList[i].replyImg) {
                        let imgList = fbReplyList[i].replyImg.split(",");
                        map.set(fbReplyList[i].replyCode, imgList);
                    }
                }

                vm.dealFeedbackReplyImgMap = map;

            },

            //点击问题回复
            showInnerWindow() {
                this.showFbReplyWindow = true;
                this.fbReplyImgList = [];
            },

            //问题回复提交
            submitReplyForm(formName) {
                let vm = this;

                //将上传的图片code平成字符串
                let imgUrl = "";
                for (let i = 0; i < vm.fbReplyImgList.length; i++) {
                    imgUrl = imgUrl + vm.fbReplyImgList[i] + ","
                }

                imgUrl = imgUrl.substring(0, imgUrl.length - 1);
                vm.fbReplyObj.replyImg = imgUrl;

                vm.fbReplyObj.fbCode = vm.oneFeedbackObj.fbCode;

                let obj = {
                    entity: vm.fbReplyObj
                };

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "feedbackReply/create"
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: "回复成功!",
                                    type: 'success'
                                });

                                vm.$refs[formName].resetFields();
                                vm.showFbReplyWindow = false;
                                vm.selectReplyList(vm.fbReplyObj.fbCode);

                            }
                        }).catch(error => {
                            vm.$message({
                                showClose: true,
                                message: msgError,
                                type: 'error'
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                })

            },


            /******************状态更改*************************/

            //解决
            solveFeedback(fbCode) {

                let fbStatus = "3";
                let msg = "解决";
                this.changeFeedbackStatus(fbCode, fbStatus, msg);
            },

            //受理
            acceptFeedback(fbCode) {

                let fbStatus = "2";
                let msg = "受理";
                this.changeFeedbackStatus(fbCode, fbStatus, msg);
            },

            //改变状态公用方法
            changeFeedbackStatus(fbCode, fbStatus, msg) {
                let vm = this;

                vm.$confirm('确定' + msg + '"' + vm.oneFeedbackObj.createName + '"提交的【' + vm.oneFeedbackObj.fbTitle + '】问题反馈吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    vm.oneFeedbackObj.fbStatus = fbStatus;

                    if (fbStatus == '2') {
                        let userCode = vm.$cookieStore.getCookie("userCode");

                        if (vm.employeeInfo[userCode]) {
                            vm.oneFeedbackObj.dealQuestionCode = userCode;
                            vm.oneFeedbackObj.dealQuestionName = vm.employeeInfo[userCode].empName;
                            vm.oneFeedbackObj.dealQuestionPhone = vm.employeeInfo[userCode].empPhone;
                        }
                    }

                    let obj = {};
                    obj.entity = vm.oneFeedbackObj;

                    let options = {
                        method: 'POST',
                        data: obj,
                        url: "feedback/update"
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response) {
                            vm.$message({
                                type: 'success',
                                message: msg + '成功!'
                            });

                            vm.selectOneFeedback(fbCode);

                        }
                    }).catch(error => {
                        vm.$message({
                            showClose: true,
                            message: msg + "失败",
                            type: 'error'
                        });
                    });
                }).catch(() => {});
            },


        },
    }
</script>

<style scoped>

    /*根div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
    }

    .root_style {
        height: 78vh;
        overflow-y: auto;
        overflow-x: hidden;
        text-align: left;
    }

    /*导航栏 根div*/
    .nav-root-div {
        color: #409EFF;
        margin-top: -53px;

        position: fixed;
        z-index: 200;
    }

    /*新增按钮*/
    .insert-button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    /*------------------------------------------------------------------------*/

    /*公司图标卡片*/
    .el_card_img {
        height: 100px;
        width: 100px;
        float: left;
        margin-top: 10px;
    }

    .company_info {
        float: left;
        width: 80%;
        margin-left: 20px;
    }

    /*行样式*/
    .el_col_style {
        height: 40px;
        line-height: 40px;
    }

    /*------------------------------------------------------------------------*/

    /*新增公司弹窗*/
    .insert-card-div {
        margin-top: 0px;
        text-align: left;
        line-height: 60px;
    }

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
        /*border: solid 1px;*/
        margin-left: -10px;
    }

    .feedback_img {
        float: left;
    }

    .timeline_under {
        clear: both;
        float: right;
        color: #999;
    }

    /*搜索框*/
    .input-with-select {
        width: 280px;
        float: left;
        margin-left: 20px;
        margin-top: 10px;
    }

    .box-card {
        margin-top: 10px;
    }

    .fankui_col {
        height: inherit;
        /* line-height: 20px; */
    }

    .des_pre {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .deal_qus_card_div {
        height: 50px;
        line-height: 50px;
        border: 1px solid #d9d9d9;
        padding-left: 20px;
        margin-bottom: 10px;
        font-size: 16px;
        font-weight: bolder;
        color: blue;
    }

    .deal_qus_card_span {
        margin-left: 50px;
    }

    .feedback_list_under {
        color: #999;
        font-size: 12px;
    }

    /deep/ .el-form-item__label {
        line-height: initial;
    }

    /deep/ .el-form-item__content {
        line-height: initial;
    }
</style>