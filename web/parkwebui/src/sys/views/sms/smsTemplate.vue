<template>
    <div class="not-position_root">
        <!-- 导航栏 -->
        <div class="not-position_header">
            <el-button @click="openSmsTemplateWindow()" type="primary" size="small" icon="el-icon-plus"
                       class="insert-button" round>新增模板
            </el-button>
        </div>

        <el-table
                :data="smsTemplateList"
                border
                :height="tableHeight"
               >
            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="templateName"
                    label="模板名称"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="templateTypeName"
                    label="模板类型"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="templateId"
                    label="模板ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="templateSignName"
                    label="模板签名"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="templateContent"
                    label="模板内容">
            </el-table-column>
            <el-table-column label="操作" width="200" fixed="right">
                <template slot-scope="scope">
                    <el-button @click="openEditSmsTemplateWindow(scope.row)" type="primary" size="small"
                               icon="el-icon-edit" title="发送短信" round>编辑
                    </el-button>
                    <el-button @click="openSendSmsWindow(scope.row)" type="success" size="small"
                               icon="el-icon-chat-round" title="发送短信" round>发短信
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页栏 -->
        <div class="page-box">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="pageSizes"
                           :page-size="defaultPageSize"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="total">
            </el-pagination>
        </div>

        <!-- 新增短信模板弹窗 -->
        <el-dialog :title="smsTemplateTitle"
                   :visible.sync="smsTemplateWindow"
                   width="60%"
                   style="text-align: center;">
            <el-form :model="smsTemplateObj" ref="smsTemplateObj" :rules="rules" label-width="100px"
                     style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="名称：" prop="templateName">
                            <el-input v-model="smsTemplateObj.templateName" placeholder="模板名称" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="类型：" prop="templateType">
                            <el-select v-model="smsTemplateObj.templateType" placeholder="请选择" clearable>
                                <el-option
                                        v-for="item in smsTemplateTypeList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="ID：">
                            <el-input v-model="smsTemplateObj.templateId" placeholder="短信服务商提供" clearable></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="签名：">
                            <el-input v-model="smsTemplateObj.templateSignName" placeholder="短信服务商提供"
                                      clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="模板内容：" prop="templateContent">
                            <el-input v-model="smsTemplateObj.templateContent" placeholder="短信模板内容" type="textarea"
                                      :rows="5" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('smsTemplateObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('smsTemplateObj')">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 发送短信弹窗 -->
        <el-dialog :title="sendSmsWindowTitle"
                   :visible.sync="sendSmsWindow"
                   width="60%"
                   :close-on-press-escape="false"
                   :close-on-click-modal="false"
                   style="text-align: center;">
            <el-form :model="sendSmsObj" ref="sendSmsObj" :rules="sendSmsRules" label-width="100px"
                     style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="收件人：" prop="showRecipients">
                            <el-input v-model="sendSmsObj.showRecipients" readonly placeholder="短信接收人，每次最多发送1000人" clearable>
                                <el-button @click="openSelectRecipientsWindow()" slot="append" icon="el-icon-search"
                                           title="点击选择收件人"></el-button>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="模板签名：">
                            {{ sendSmsObj.templateSignName }}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="模板ID：">
                            {{ sendSmsObj.templateId }}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="模板内容：">
                            {{ sendSmsObj.templateContent }}
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" v-for="(item, index) in strList" :key="index">
                        <el-form-item :label="'参数' + (index + 1) + '：'">
                            <el-input v-model="sendSmsObj.templateParam[item]" clearable :placeholder="item"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeSendSmsWindow('sendSmsObj')" icon="el-icon-close">取消</el-button>
                <el-button type="primary" icon="el-icon-s-promotion" @click="sendSms('sendSmsObj')">发送</el-button>
            </div>
        </el-dialog>

        <!-- 选择联系人 -->
        <sms-recipients v-if="recipientsObj.selectRecipientsFlag"
                        @closeDialog="receiveChildParams"
                        @submitForm="getRecipients"
                        :recipientsObj="recipientsObj">
        </sms-recipients>
    </div>
</template>

<script>
    import smsRecipients from '@/sys/components/sms/smsRecipients.vue';

    export default {
        components: {
            smsRecipients
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                /*------------------------------- 短信模板相关 -------------------------------*/
                // 新增短信模板实体
                smsTemplateObj: {
                    templateType: '1'
                },
                smsTemplateWindow: false,// 添加短信模板弹窗


                smsTemplateTitle: '',// 短信模板标题
                smsTemplateWindowFlag: '',// 编辑短信模板是新增（1）还是修改（2）

                smsTemplateList: [],// 短信模板集合

                // 分页
                currentPage: 1,
                defaultPageSize: 25,// 默认每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0,

                // 模板类型
                smsTemplateTypeList: [
                    {
                        value: '1',
                        label: '验证码'
                    }, {
                        value: '2',
                        label: '短信通知'
                    }, {
                        value: '3',
                        label: '推广短信'
                    },
                ],

                // 新增短信模板校验
                rules: {
                    templateName: [
                        {required: true, message: '请输入模板名', trigger: 'blur'}
                    ],
                    templateType: [
                        {required: true, message: '请选择模板类型', trigger: 'change'}
                    ],
                    templateContent: [
                        {required: true, message: '请输入短信模板内容', trigger: 'blur'}
                    ],
                },

                /*------------------------------- 发送短信相关 -------------------------------*/
                sendSmsWindow: false,// 发送短信弹窗
                sendSmsWindowTitle: '',// 发送短信弹窗标题
                // 发送短信实体
                sendSmsObj: {
                    showRecipients: '',// 人名和手机号，用于展示
                    phone: '',// 手机号集合字符串
                    templateSignName: '',// 模板签名（阿里云提供）
                    templateId: '',// 模板ID（阿里云提供）
                    templateContent: '',// 模板内容
                    templateParam: {},// 占位符参数
                },
                strLength: 0,// 模板内容中占位符出现的次数，用于确定参数个数
                strList: [],// 占位符参数拆分为数组

                // 发送短信验证
                sendSmsRules: {
                    showRecipients: [
                        {required: true, message: '请选择接收人手机号', trigger: 'change'}
                    ],
                },

                /*------------------------------- 选择收件人相关 -------------------------------*/
                // 收件人参数
                recipientsObj: {
                    selectRecipientsFlag: false,// 是否加载组件
                },
            }
        },
        created: function () {
            let vm = this;

            // 查询短信模板集合
            vm.selectSmsList();
        },
        methods: {
            /*------------------------------- 短信模板相关 -------------------------------*/
            // 改变每页数量
            handleSizeChange(val) {
                this.defaultPageSize = val;
                this.currentPage = 1;
                this.selectSmsList();
            },
            // 改变当前页
            handleCurrentChange(val) {
                this.currentPage = val;
                this.selectSmsList();
            },

            // 查询短信列表
            selectSmsList() {
                let vm = this;

                let params = {
                    start: (vm.currentPage - 1) * vm.defaultPageSize,
                    pageSize: vm.defaultPageSize,
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'sms/query'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.total = response.data.result.totalCount;
                        vm.smsTemplateList = response.data.result.data;
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '页面：短信模板列表查询失败！',
                        type: 'error'
                    })
                })
            },

            // 打开新增短信模板弹窗
            openSmsTemplateWindow() {
                this.smsTemplateTitle = '新增短信模板';
                this.smsTemplateObj = {};
                this.smsTemplateWindow = true;
                this.smsTemplateWindowFlag = '1';
            },

            // 打开编辑短信模板弹窗
            openEditSmsTemplateWindow(item) {
                this.smsTemplateTitle = '编辑短信模板';
                this.smsTemplateWindow = true;

                this.smsTemplateObj = JSON.parse(JSON.stringify(item));

                this.smsTemplateWindowFlag = '2';
            },

            // 重置表单
            resetForm(formName){
                this.$refs[formName].resetFields();
                this.smsTemplateWindow = false;
            },

            // 提交
            submitForm(formName){
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url = '';
                        let message = '';
                        let catchMsg = '';

                        if (vm.smsTemplateWindowFlag == '1') {
                            url = 'sms/create';
                            message = '短信模板新增成功！';
                            catchMsg = '短信模板新增失败！';
                        } else if (vm.smsTemplateWindowFlag == '2') {
                            url = 'sms/update';
                            message = '短信模板修改成功！';
                            catchMsg = '短信模板修改失败！';
                        }

                        let params = {
                            entity: vm.smsTemplateObj
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: url
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (!response.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: message,
                                    type: 'success'
                                });

                                vm.$refs[formName].resetFields();
                                vm.smsTemplateWindow = false;
                                vm.selectSmsList();// 新增或修改成功后刷新列表
                            }
                        }).catch(error => {
                            vm.$message({
                                showClose: true,
                                message: catchMsg,
                                type: 'error'
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整！',
                            type: 'warning'
                        });
                    }
                })
            },

            /*------------------------------- 发送短信相关 -------------------------------*/
            // 打开发送短信弹窗
            openSendSmsWindow(item) {
                let vm = this;

                vm.sendSmsObj = {
                    showRecipients: '',// 人名和手机号，用于展示
                    phone: '',// 手机号集合字符串
                    templateSignName: '',// 模板签名（阿里云提供）
                    templateId: '',// 模板ID（阿里云提供）
                    templateContent: '',// 模板内容
                    templateParam: {},// 占位符参数
                };

                vm.sendSmsWindowTitle = '发送【' + item.templateName + '】短信';

                vm.sendSmsObj.templateSignName = item.templateSignName;
                vm.sendSmsObj.templateId = item.templateId;
                vm.sendSmsObj.templateContent = item.templateContent;
                vm.sendSmsObj.smsCode = item.smsCode;// 模板code
                vm.sendSmsObj.templateName = item.templateName;// 模板名称
                vm.sendSmsObj.templateType = item.templateType;// 模板类型


                if (item.templateContent.length != 0) {
                    // 字符串中包含'${'代表短信模板是含有参数的
                    if (item.templateContent.indexOf('${') != -1) {
                        let regParam = /\$\{(.+?)\}/g;// 匹配占位符
//                    vm.strLength = item.templateContent.match(regParam).length;
                        let str = item.templateContent.match(regParam).join(',');

                        /**
                         * 将"${name},${pkNmae}"形式的字符串处理成name,pkName
                         */
                        let newStr = '';
                        for (let i = 0; i < str.length; i++) {
                            if (str[i] != '$' && str[i] != '{' && str[i] != '}') {
                                newStr += str[i];
                            }
                        }
                        vm.strList = newStr.split(',');

//                    let newArr = a.split(',');
//                    let obj = {};
//                    for (let i = 0; i < newArr.length; i++) {
//                        obj[newArr[i]] = '';
//                    }
                    }
                }

                vm.sendSmsWindow = true;
            },

            // 关闭发送短信弹窗
            closeSendSmsWindow(formName) {
                this.$refs[formName].resetFields();
                this.sendSmsWindow = false;
            },

            /*------------------------------- 选择收件人相关 -------------------------------*/
            // 打开选择收件人弹窗
            openSelectRecipientsWindow() {
                let vm = this;

                /**
                 * 1.父组件向子组件传递数据，用于控制窗口是否显示
                 * 2.引入的子组件，根据当前单击事件加载
                 */
                vm.recipientsObj.selectRecipientsFlag = true;
            },

            // 接收子组件传递的数据
            receiveChildParams(data) {
                let vm = this;
                vm.recipientsObj.selectRecipientsFlag = data;
            },

            // 接收子组件传递的联系人集合
            getRecipients(data) {
                let recipients = '';
                let phoneList = [];
                for (let i = 0; i < data.length; i++) {
                    recipients += data[i].name + '(' + data[i].phone + '),';
                    phoneList.push(data[i].phone);
                }
                this.sendSmsObj.showRecipients = recipients;
                this.sendSmsObj.phone = phoneList.join(',');
            },

            /*------------------------------- 发送短信 -------------------------------*/
            // 发送短信
            sendSms(formName) {
                let vm = this;

                let templateParam = JSON.stringify(vm.sendSmsObj.templateParam);

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = {
                            entity: {
                                templateSignName: vm.sendSmsObj.templateSignName,
                                templateId: vm.sendSmsObj.templateId,
                                smsCode: vm.sendSmsObj.smsCode,
                                templateName: vm.sendSmsObj.templateName,
                                templateType: vm.sendSmsObj.templateType,
                            },
                            templateParam: templateParam,
                            phoneNumbers: vm.sendSmsObj.phone
                        };

                        let options = {
                            method: 'POST',
                            data: params,
                            url: 'sms/sendSMS'
                        };

                        this.$ajax(
                            options
                        ).then(response => {
                            if (!response.isException) {
                                vm.$message({
                                    showClose: true,
                                    message: '短信发送成功！',
                                    type: 'success'
                                });
                                vm.$refs[formName].resetFields();
                                vm.sendSmsWindow = false;
                            }
                        }).catch(error => {
                            vm.$message({
                                showClose: true,
                                message: '短信发送失败！',
                                type: 'error'
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整！',
                            type: 'warning'
                        });
                    }
                })
            }
        },
    }
</script>

<style scoped>
    /*新增按钮*/
    .insert-button {
        float: left;
        margin-left: 20px;
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
    }
</style>