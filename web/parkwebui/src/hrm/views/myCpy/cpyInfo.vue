<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <span class="nav-title">{{ cpyObj.cpyName }}</span>
        </div>

        <el-form :model="cpyObj" ref="cpyObj"  class="has-position_main">
            <el-card style="margin-top: 10px;"
                     shadow="hover">
                <el-avatar v-if="cpyObj.cpyImg"
                           shape="square"
                           :size="100"
                           fit="scale-down"
                           :src="parkBaseUrl + 'file/read/' + cpyObj.cpyImg"
                           class="el_card_img"></el-avatar>

                <el-avatar v-if="!cpyObj.cpyImg" shape="square" :size="100" fit="scale-down" class="el_card_img">
                    <span style="font-size: 35px;">无</span>
                </el-avatar>

                <div class="company_info">
                    <el-row :gutter="20">
                        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="el_col_style">
                            <el-form-item label="公司名称：">
                                <span>{{ cpyObj.cpyName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-button type="primary" @click="openEditCpyWindow(cpyObj)" round size="small">
                                <i class="el-icon-edit"></i>&nbsp;&nbsp;编辑
                            </el-button>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="el_col_style">
                            <el-form-item label="公司地址：">
                                <span>{{ cpyObj.address }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="管理员姓名：">
                                <span>{{ cpyObj.userName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="性别：">
                                <span>{{ cpyObj.sexName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="手机：">
                                <span>{{ cpyObj.phone }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <div style="clear: both"></div>
            </el-card>
        </el-form>

        <el-dialog class="edit-card-div" width="70%" style="text-align: center;" title="修改公司信息" :visible.sync="editCpyWindow">
            <el-form :model="editCpyObj" :rules="rules" ref="editCpyObj" label-width="110px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="公司名称：" prop="cpyName">
                            <el-input clearable v-model="editCpyObj.cpyName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="管理员姓名：" prop="userName">
                            <el-input clearable v-model="editCpyObj.userName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="性别：" prop="sex">
                            <el-radio-group v-model="editCpyObj.sex">
                                <el-radio label="1">男</el-radio>
                                <el-radio label="2">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="手机：" prop="phone">
                            <el-input v-model="editCpyObj.phone" @blur="checkPhone()" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <el-form-item label="公司地址：" prop="address">
                            <el-input clearable v-model="editCpyObj.address" suffix-icon="el-icon-location"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="公司Logo：">
                            <file @selectFile="selectImg" style="text-align: left;"></file>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row v-if="editCpyObj.cpyImg">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-avatar shape="square"
                                   :size="100"
                                   fit="scale-down"
                                   :src="parkBaseUrl + 'file/read/' + editCpyObj.cpyImg"
                                   style="width: 100px; height: 100px; margin-left: 20px;">
                        </el-avatar>

                    </el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-top: -40px; margin-left: 35px;">
                        <el-button type="text" @click="deleteImg()">
                            <i class="el-icon-delete">&nbsp;&nbsp;删除</i>
                        </el-button>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('editCpyObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('editCpyObj')">提 交</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue';

    export default {
        components: {
            ElOption,
            file
        },
        created: function () {
            this.initInfo();
        },
        data(){
            // 检查人员电话是否存在
            let checkAdminPhone = (rule, value, callback) => {
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
            return {
                showCpyList: false,// 公司列表

                editCpyWindow: false,// 添加公司弹窗

                phoneFlag: '',// 手机号标记，用于检测手机号是否存在

                // 添加公司实体
                cpyObj: {},

                // 修改公司
                editCpyObj: {},

                // 分页
                start: 1,
                pageSize: 25,// 默认每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0,

                rules: {
                    cpyName: [
                        {required: true, message: '请输入公司名', trigger: 'blur'}
                    ],
                    adminName: [
                        {required: true, message: '请输入公司管理员姓名', trigger: 'blur'}
                    ],
                    sex: [
                        {required: true, message: '请输入选择管理员性别', trigger: 'change'}
                    ],
                    phone: [
                        {required: true, message: '请输入管理员电话', trigger: 'blur'},
                        {validator: checkAdminPhone},
                    ],
                    address: [
                        {required: true, message: '请输入公司地址', trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            // 初始化页面
            initInfo(){
                let vm = this;

                vm.showCpyList = vm.$flagMenuStore.judgeMenu('cpyList');

                let cpyCode = JSON.parse(window.sessionStorage.getItem("_mainObj")).userObj.cpyCode;

                let params = {
                    entity: {
                        cpyCode: cpyCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'company/detail'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.cpyObj = response.data.result;
                    }
                }).catch(error => {
                    vm.$alert('页面：公司信息加载失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 修改公司信息
            openEditCpyWindow(item) {
                let vm = this;
                vm.editCpyWindow = true;
                vm.editCpyObj = JSON.parse(JSON.stringify(item));
            },

            // 重置表单
            resetForm(formName){
                this.$refs[formName].resetFields();
                this.editCpyWindow = false;
            },

            // 修改
            submitForm(formName){
                let vm = this;

                let obj = {
                    entity: vm.editCpyObj
                };

                if (vm.editCpyObj) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            let options = {
                                method: 'POST',
                                data: obj,
                                url: 'company/update'
                            };

                            this.$ajax(
                                options
                            ).then(response => {
                                if (!response.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: '公司修改成功',
                                        type: 'success'
                                    });

                                    this.$refs[formName].resetFields();
                                    this.editCpyWindow = false;

                                    // 刷新列表
                                    vm.initInfo();
                                }
                            }).catch(error => {
                                vm.$message({
                                    showClose: true,
                                    message: '公司修改失败',
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

            selectImg(urls){
                if (urls.length > 1) {
                    this.$message({
                        showClose: true,
                        message: '只能选择一张图片，请重新选择',
                        type: 'error'
                    });
                } else {
                    this.editCpyObj.cpyImg = urls[0].fileCode;
                }
            },

            deleteImg(){
                let vm = this;

                vm.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    vm.editCpyObj.cpyImg = '';
                    vm.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {
                });
            },

            // 检查手机号是否存在
            checkPhone() {
                let vm = this;

                let obj = {
                    entity: {
                        empPhone: vm.editCpyObj.phone
                    }
                };

                if (vm.cpyObj.phone) {
                    let options = {
                        method: 'POST',
                        data: obj,
                        url: 'employee/details'
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response && response.data.result) {
                            let empCode = response.data.result.empCode;

                            if (empCode != vm.cpyObj.userCode) {
                                vm.phoneFlag = response.data.result.empPhone;
                            }
                        }
                    });
                }
            },
        },
    }
</script>

<style scoped>
    /*导航栏名字*/
    .nav-title {
        float: left;
        line-height: 50px;
        margin-left: 20px;
        margin-top: 1px;
        color: rgb(102, 177, 255);
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

    /*公司信息*/
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
    .edit-card-div {
        margin-top: 0px;
        text-align: left;
        line-height: 60px;
    }
</style>