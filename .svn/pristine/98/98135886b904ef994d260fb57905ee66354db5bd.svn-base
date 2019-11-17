<template>
    <div class="not-position_root" ref="myRoot">
        <el-form label-width="100px">
            <div class="not-position_header">
                <!-- 部门筛选组件 -->
                <dept-selector @sendDeptCode="getDeptCode" class="select-dept-style"></dept-selector>
                <el-input size="small" placeholder="可搜索姓名、电话、公司、地址" @keyup.enter.native="channelChange()" v-model="channelObj.keyword" class="select-style" clearable>
                    <el-select v-model="channelObj.channelStatus" slot="prepend" placeholder="请选择" @change="channelChange()" style="width: 110px;">
                        <el-option v-for="(item) in channelStatusList"
                                   :key="item.channelStatus"
                                   :label="item.label"
                                   :value="item.channelStatus">
                        </el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="channelChange()"></el-button>
                </el-input>
                <el-button type="primary" size="small" class="channel-add-button" @click="addChannel()">渠道新增</el-button>
            </div>


            <el-table
                      size="small"
                      :data="channelList"
                      :height="tableHeight"
                      border>
                <el-table-column
                        align="center"
                        min-width="30px"
                        label="序号"
                        type="index">
                </el-table-column>
                <el-table-column
                        align="center"
                        min-width="30px"
                        label="渠道类型"
                        prop="channelStatusName">
                </el-table-column>
                <el-table-column
                        align="center"
                        min-width="40px"
                        label="姓名"
                        prop="userName">
                </el-table-column>
                <el-table-column
                        align="center"
                        min-width="40px"
                        label="电话"
                        prop="phone">
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="30px"
                        label="性别"
                        prop="sex">
                    <template slot-scope="scope">{{scope.row.sex == 1 ? "男" : "女"}}</template>
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="50px"
                        label="职务"
                        show-overflow-tooltip
                        prop="post">
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="50px"
                        label="公司名称"
                        show-overflow-tooltip
                        prop="channelCpyEntity.channelCpyName">
                </el-table-column>
                <el-table-column
                        align="center"
                        min-width="50px"
                        label="公司描述"
                        show-overflow-tooltip
                        prop="channelCpyEntity.channelCpyRemark">
                </el-table-column>
                <el-table-column
                        align="center"
                        min-width="50px"
                        label="公司地址"
                        show-overflow-tooltip
                        prop="channelCpyEntity.channelCpyAddress">
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="50px"
                        label="创建人"
                        show-overflow-tooltip
                        prop="createName">
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="50px"
                        label="录入时间"
                        show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.createTime | timeDate }}</template>
                </el-table-column>

                <el-table-column
                        align="center"
                        min-width="50px"
                        label="更新时间"
                        show-overflow-tooltip>
                    <template slot-scope="scope">{{ scope.row.lastUpdateTime | timeDate }}</template>
                </el-table-column>

                <el-table-column
                        fixed="right"
                        align="center"
                        min-width="100px"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" type="text" @click="editChannel(scope.row)">修改</el-button>
                        <el-button size="mini" type="text" @click="channelFollowupCreate(scope.row.channelCode, scope.row.companyCode)">添加跟进</el-button>
                        <el-button size="mini" type="text" @click="channelFollowupDetail(scope.row.channelCode, scope.row.userName)">跟进详情</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="table-page-div" :style="{width:floatDivWidth+'px'}">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[8, 16, 32]"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>

            <!-- 新增渠道跟进 弹窗 -->
            <el-dialog title="新增渠道跟进" style="line-height: 30px;" :visible.sync="addChannelFollowupWindow">
                <el-form :model="channelFollowupForm" :rules="rules" ref="channelFollowupForm" label-width="100px">
                    <el-row>
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" style="text-align: left;">
                            <el-form-item label="跟进类型：" prop="followupType">
                                <el-radio-group v-model="channelFollowupForm.followupType">
                                    <el-radio label="1">电话跟进</el-radio>
                                    <el-radio label="2">上门拜访</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" style="text-align: left;">
                            <el-form-item label="跟进结果：" prop="followupResult">
                                <el-input type="textarea" v-model="channelFollowupForm.followupResult"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="resetFormChannel('channelFollowupForm')">重 置</el-button>
                    <el-button type="primary" @click="submitForm('channelFollowupForm')">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 渠道列表 弹窗 -->
            <el-dialog title="渠道跟进详情" style="line-height: 30px;" :visible.sync="channelFollowupDetailWindow">
                <el-row :gutter="20" style="height: 30px;margin-top: -20px;">
                    <el-col :span="8">
                        <el-form-item label="渠道联系人：">
                            <span style="float: left;">{{ channelUserName }}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-table
                        :data="channelFollowupList"
                        border
                        style="width: 100%;">
                    <el-table-column align="center"
                                     prop="followupTypeName"
                                     label="跟进类型">
                    </el-table-column>
                    <el-table-column align="center"
                                     prop="createName"
                                     label="跟进人员"
                                     width="180">
                    </el-table-column>
                    <el-table-column align="center"
                                     prop="createTime"
                                     label="跟进时间">
                        <template slot-scope="scope">{{ scope.row.createTime | time}}</template>
                    </el-table-column>
                    <el-table-column align="center"
                                     prop="followupResult"
                                     label="跟进结果">
                    </el-table-column>
                </el-table>
            </el-dialog>
        </el-form>


        <!-- 新增渠道 弹窗 -->
        <el-dialog
                style="line-height: 30px"
                title="新增渠道"
                :visible.sync="addChannelWindow"
                width="40%"
                center>
            <el-form :model="channelForm" :rules="channelRules" ref="channelForm" label-width="100px"
                     class="demo-ruleForm">

                <el-form-item label="渠道类型" prop="channelStatus">
                    <el-select v-model="channelForm.channelStatus" placeholder="请选择"
                               style="width:100%;">
                        <el-option
                                v-for="item in channelTypeList"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="联系人姓名" prop="userName">
                    <el-input v-model="channelForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="channelForm.sex">
                        <el-radio label="1">先生</el-radio>
                        <el-radio label="2">女士</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系人电话" prop="phone">
                    <el-input v-model="channelForm.phone" @blur="checkChannelPhone"></el-input>
                </el-form-item>
                <el-form-item label="联系人职务" prop="post">
                    <el-input v-model="channelForm.post"></el-input>
                </el-form-item>

                <el-form-item label="单位名称" prop="companyCode">
                    <el-select
                            v-model="channelForm.companyCode"
                            filterable
                            placeholder="请选择单位">
                        <!--@change="selectChannelCpyByCode"-->
                        <el-option
                                v-for="item in channelCpyList"
                                :key="item.companyCode"
                                :label="item.channelCpyName"
                                :value="item.companyCode">
                        </el-option>
                    </el-select>
                    <el-button type="primary" @click="addChannelCpyWindow = true">新增单位</el-button>
                </el-form-item>

                <el-form-item style="text-align: right;">
                    <el-button @click="resetForm('channelForm')">重置</el-button>
                    <el-button type="primary" @click="submitChannelForm('channelForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>


        <el-dialog
                width="40%"
                title="新增单位"
                :visible.sync="addChannelCpyWindow"
                append-to-body>
            <el-form :model="channelCpyForm" :rules="channelCpyRules" ref="channelCpyForm" label-width="100px"
                     class="demo-ruleForm">

                <el-form-item label="单位名称" prop="channelCpyName">
                    <el-input v-model="channelCpyForm.channelCpyName" @blur="checkChannelCpyName"></el-input>
                </el-form-item>
                <el-form-item label="单位描述" prop="channelCpyRemark">
                    <el-input v-model="channelCpyForm.channelCpyRemark"></el-input>
                </el-form-item>
                <el-form-item label="单位地址" prop="channelCpyAddress">
                    <el-input v-model="channelCpyForm.channelCpyAddress"></el-input>
                </el-form-item>

                <el-form-item style="text-align: right;">
                    <el-button @click="resetForm('channelCpyForm')">重置</el-button>
                    <el-button type="primary" @click="submitChannelCpyForm('channelCpyForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <!--修改渠道-->
        <el-dialog
                style="line-height: 30px"
                title="修改渠道"
                :visible.sync="editChannelWindow"
                width="40%"
                center>
            <el-form :model="editChannelForm" :rules="channelRules" ref="editChannelForm" label-width="100px"
                     class="demo-ruleForm">

                <el-form-item label="渠道类型" prop="channelStatus">
                    <el-select v-model="editChannelForm.channelStatus" placeholder="请选择"
                               style="width:100%;">
                        <el-option
                                v-for="item in channelTypeList"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                        >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="联系人姓名" prop="userName">
                    <el-input v-model="editChannelForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="editChannelForm.sex">
                        <el-radio label="1">先生</el-radio>
                        <el-radio label="2">女士</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系人电话" prop="phone">
                    <el-input v-model="editChannelForm.phone" @blur="checkChannelPhone"></el-input>
                </el-form-item>
                <el-form-item label="联系人职务" prop="post">
                    <el-input v-model="editChannelForm.post"></el-input>
                </el-form-item>

                <el-form-item label="单位名称" prop="companyCode">
                    <el-select
                            v-model="editChannelForm.companyCode"
                            filterable
                            placeholder="请选择单位">
                        <!--@change="selectChannelCpyByCode"-->
                        <el-option
                                v-for="item in channelCpyList"
                                :key="item.companyCode"
                                :label="item.channelCpyName"
                                :value="item.companyCode">
                        </el-option>
                    </el-select>
                    <el-button type="primary" @click="addChannelCpyWindow = true">新增单位</el-button>
                </el-form-item>

                <el-form-item style="text-align: right;">
                    <el-button @click="resetForm('editChannelForm')">重置</el-button>
                    <el-button type="primary" @click="submitEditChannelForm('editChannelForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    import deptSelector from '@/common/components/deptSelector.vue'
    export default {
        components: {
            deptSelector,
        },
        data(){
            // 检查渠道电话是否存在
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (value == this.phoneFlag) {
                        callback(new Error('电话号话已存在'));
                    } else if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            // 检查渠道公司名称是否存在
            let checkCpyName = (rule, value, callback) => {
                setTimeout(() => {
                    if (value == this.channelCpyNameFlag) {
                        callback(new Error('渠道单位名称已存在'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                tableHeight: this.$store.state.tableHeight,
                pageSize: 16,// 默认每页条数
                totalCount: 10,// 总条数
                currentPage: 1,// 当前页数

                channelObj: {
                    createDeptCode: '',//创建人部门
                    channelStatus: '',// 渠道类型
                    keyword: '',// 关键词
                },

                floatDivWidth: '',
                addChannelFollowupWindow: false,// 控制渠道跟进新增弹窗
                channelFollowupDetailWindow: false,// 控制渠道跟进详情弹窗
                addChannelWindow: false,// 控制新增渠道弹窗
                addChannelCpyWindow: false,// 控制新增渠道单位弹窗
                channelUserName: '',// 渠道联系人姓名
                channelCpyName: '',// 渠道单位名称

                channelFollowupForm: {},// 渠道跟进表单
                channelForm: { // 渠道新增表单
                    channelStatus: "",
                    userName: "",
                    sex: "",
                    post: "",
                    phone: "",
                    companyCode:''
                },
                channelCpyForm: {},// 渠道单位新增表单

                channelList: [],// 渠道列表绑定数据
                channelFollowupList: [],// 渠道跟进列表
                channelCpyList: [],// 渠道单位查询

                phoneFlag: '',// 记录查询到的手机号
                channelCpyNameFlag: '',// 记录查询到的渠道单位名称

                editChannelWindow:false,  //修改渠道弹框
                editChannelForm:{  //修改渠道表单
                    channelStatus: "",
                    userName: "",
                    sex: "",
                    post: "",
                    phone: "",
                    companyCode:''
                },
                editChannelUserName: '',// 修改渠道联系人姓名
                editChannelCpyName: '',// 修改渠道单位名称

                channelStatusList: [
                    {
                        channelStatus: '',
                        label: '全部'
                    },
                    {
                        channelStatus: '1',
                        label: '个人中介'
                    },
                    {
                        channelStatus: '2',
                        label: '公司中介'
                    },
                    {
                        channelStatus: '3',
                        label: '管委会'
                    },
                    {
                        channelStatus: '4',
                        label: '房东'
                    },
                    {
                        channelStatus: '5',
                        label: '其他'
                    },
                ],

                // 渠道类型集合
                channelTypeList: [
                    {
                        value: "1",
                        label: "个人中介"
                    }, {
                        value: "2",
                        label: "公司中介"
                    }, {
                        value: "3",
                        label: "管委会"
                    }, {
                        value: "4",
                        label: "房东"
                    }, {
                        value: "5",
                        label: "其他"
                    },
                ],


                // 新增渠道跟进校验
                rules: {
                    followupType: [
                        {required: true, message: '请选择跟进类型', trigger: 'change'}
                    ],
                    followupResult: [
                        {required: true, message: '请输入跟进结果', trigger: 'blur'}
                    ],
                },
                // 新增渠道校验
                channelRules: {
                    channelStatus: [
                        {required: true, message: '请选择渠道类型', trigger: 'change'},
                    ],
                    userName: [
                        {required: true, message: '请输入联系人姓名', trigger: 'blur'},
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'},
                    ],
                    phone: [
                        {required: true, message: '请输入联系人电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    post: [
                        {required: true, message: '请输入联系人职务', trigger: 'blur'},
                    ],
                },
                // 新增渠道单位校验
                channelCpyRules: {
                    channelCpyName: [
                        {required: true, message: '请输入单位名称', trigger: 'blur'},
                        {validator: checkCpyName}
                    ],
                    channelCpyRemark: [
                        {required: true, message: '请输入单位描述', trigger: 'blur'},
                    ],
                    channelCpyAddress: [
                        {required: true, message: '请输入单位地址', trigger: 'blur'},
                    ],
                },
            }
        },
        created: function () {
            let vm = this;

            let params = {
                entity: {

                }
            };
            params.pageSize = vm.pageSize;
            params.start = 0;

            vm.queryListTemplate(vm, params);
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
            channelChange(){
                this.handleCurrentChange(1);
            },
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 查询条件筛选
            handleCurrentChange(val) {
                let vm = this;

                let pageData = {
                    entity: {
                        channelStatus: vm.channelObj.channelStatus,
                        createDeptCode: vm.channelObj.createDeptCode,
                    },
                    pageSize: vm.pageSize,
                    start: (val - 1) * (vm.pageSize),
                    keyword: vm.channelObj.keyword,
                };
                vm.queryListTemplate(vm,pageData);
            },
            // 查询客户跟进模板
            queryListTemplate(vm,params){
                let options = {
                    method: "POST",
                    data: params,
                    url: "customer/channel/query"
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.totalCount = response.data.result.totalCount;
                        vm.channelList = response.data.result.data;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面：渠道列表查询失败！',
                        type: 'error'
                    })
                })
            },
            // 添加渠道跟进
            channelFollowupCreate(channelCode, companyCode){
                this.addChannelFollowupWindow = true;
                this.channelFollowupForm.channelCode = channelCode;
                this.channelFollowupForm.companyCode = companyCode;
            },
            // 重置新增渠道表单
            resetFormChannel(formName) {
                this.$refs[formName].resetFields();
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 提交表单
            submitForm(formName){
                let vm = this;
                let param = {
                    entity: vm.channelFollowupForm,
                };

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: param,
                            url: "channelFollowup/create"
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '渠道跟进新增成功',
                                    type: 'success'
                                });
                                // 跟进新增成功后，重置表单并关闭窗口
                                vm.resetForm(formName);
                                vm.addChannelFollowupWindow = false;
                            }
                        }).catch(function (error) {
                            vm.$message({
                                showClose: true,
                                message: '页面：渠道跟进新增失败',
                                type: 'error'
                            });
                        });
                    }
                });
            },
            // 渠道跟进列表，弹窗
            channelFollowupDetail(channelCode, userName){
                let vm = this;

                vm.channelFollowupDetailWindow = true;
                vm.channelUserName = userName;

                let param = {
                    entity: {
                        channelCode: channelCode,
                    }
                };

                let options = {
                    method: "POST",
                    data: param,
                    url: "channelFollowup/list"
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.channelFollowupList = response.data.result;
//                        for (let i=0;i<vm.channelFollowupList.length;i++) {
//                            vm.channelFollowupList[i].userName = userName;
//                        }
                    }
                }).catch(error => {
                    vm.$alert('页面：渠道跟进查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },
            // 添加渠道跟进弹窗
            addChannel(){
                this.addChannelWindow = true;

                // 查询单位名称
                let vm = this;
                let options = {
                    method: 'POST',
                    data: {},
                    url: "customer/channel/cpy/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.channelCpyList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：渠道单位查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 校验渠道手机号是否唯一
            checkChannelPhone(){
                let vm = this;

                if (!vm.channelForm.phone) {
                    return;
                }

                let param = {
                    entity: {
                        phone: vm.channelForm.phone,
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: "customer/channel/getList",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let channelList = response.data.result;
                        if (channelList.length > 0) {
                            vm.phoneFlag = channelList[0].phone;
                        }
                    }
                }).catch(function (error) {
                    vm.$alert('页面：渠道信息手机号已存在', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 校验渠道单位名称是否唯一
            checkChannelCpyName(){
                let vm = this;

                if (!vm.channelCpyForm.channelCpyName) {
                    return;
                }

                let param = {
                    entity: {
                        channelCpyName: vm.channelCpyForm.channelCpyName,
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: "customer/channel/cpy/getList",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        let channelCpyList = response.data.result;
                        if (channelCpyList.length > 0) {
                            vm.channelCpyNameFlag = channelCpyList[0].channelCpyName;
                        }
                    }
                }).catch(function (error) {
                    vm.$alert('页面：渠道单位名称已存在', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
            // 渠道新增提交
            submitChannelForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let vm = this;

                        let obj = {
                            entity: vm.channelForm,
                        };
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "customer/channel/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '渠道新增成功',
                                    type: 'success'
                                });

                                // 刷新渠道列表
                                let params = {};
                                params.pageSize = vm.pageSize;
                                params.start = 0;
                                params.entity = {};
                                vm.queryListTemplate(vm, params);

                                // 渠道新增成功后，重置表单并关闭窗口
                                vm.resetFormChannel(formName);
                                vm.addChannelWindow = false;
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：新增渠道失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },
            // 渠道单位新增提交
            submitChannelCpyForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let vm = this;

                        let param = {
                            entity: vm.channelCpyForm,
                        };

                        let options = {
                            method: 'POST',
                            data: param,
                            url: "customer/channel/cpy/create",
                        };

                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '渠道新增成功',
                                    type: 'success'
                                });

                                vm.channelForm.companyCode = response.data.result;
                                vm.editChannelForm.companyCode = response.data.result;
                                vm.addChannel();// 新增渠道单位成功后，刷新渠道列表

                                // 渠道单位新增成功后，重置表单并关闭窗口
                                vm.resetForm(formName);
                                vm.addChannelCpyWindow = false;
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：新增渠道失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },

            //打开修改渠道弹出框
            editChannel(row){
                let vm = this;
                vm.editChannelWindow = true;
//                vm.editChannelForm.channelStatus = row.channelStatus;
//                vm.editChannelForm.userName = row.userName;
//                vm.editChannelForm.sex = row.sex;
//                vm.editChannelForm.phone = row.phone;
//                vm.editChannelForm.post = row.post;

                vm.editChannelForm = row;

                // 查询单位名称
                let options = {
                    method: 'POST',
                    data: {},
                    url: "customer/channel/cpy/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.channelCpyList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：渠道单位查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });

            },
            submitEditChannelForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let vm = this;

                        let obj = {
                            entity: vm.editChannelForm,
                        };
                        let options = {
                            method: 'POST',
                            data: obj,
                            url: "customer/channel/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (!response.data.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '渠道修改成功',
                                    type: 'success'
                                });

                                // 刷新渠道列表
                                let params = {};
                                params.pageSize = vm.pageSize;
                                params.start = 0;
                                vm.queryListTemplate(vm, params);

                                // 渠道修改成功后，重置表单并关闭窗口
                              //  vm.resetFormChannel(formName);
                                vm.editChannelWindow = false;
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：修改渠道失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                });
            },

            // 获取组件传递的deptCode
            getDeptCode(data) {
                let vm = this;
                vm.channelObj.createDeptCode = data;
                vm.channelChange();
            },
        },

    }
</script>

<style scoped>
    .root {
        background-color: white;
        width: 100%;
        min-height: 850px;
        margin: -40px 0 0 0;
    }

    /*导航栏div*/
    .navigation_div{
        height: 50px;
        margin: -50px 0 0 0;

        position: fixed;
        z-index: 200;
    }

    /*下拉选样式*/
    .select-style{
        float: left;
        /*margin: 10px 0 0 20px;*/
        margin-left: 20px;
        height: 30px;
        line-height: 30px;
        width: 400px;
    }

    /*渠道新增 按钮*/
    .channel-add-button{
        float: left;
        margin-left: 20px;
    }

    /*表格样式*/
    .table-style {
        line-height: 20px;
        /*border-top: white solid 1px;*/
    }

    /*分页*/
    .table-page-div {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    .el-table th,
    .el-table tr {
        height: 47px;
        background-color: #fff;
    }

    .root /deep/ .el-table__fixed-body-wrapper {
        top: 47px !important;
    }
    /*下拉选样式*/
    .select-dept-style {
        float: left;
        /*margin: 10px 0 0 20px;*/
        margin-left: 20px;
        height: 30px;
        line-height: 30px;
        width: 160px;
    }
</style>