<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button class="save-button" size="small" type="primary" @click="submitForm('empObj')">
                提交
            </el-button>
        </div>
        <el-form class="has-position_main" :model="empObj" :rules="rules" ref="empObj" label-width="110px">


            <div class="common-title-div" style="margin-top: 10px;">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="emp-info-root-div">
                <div v-if="fileType == 'image'" class="img-div1" :class="{imgDiv2:!empObj.empImg || !imgCode}">
                    <template v-if="!empObj.empCode">
                        <i @click="removeImg()" class="el-icon-circle-close" style="float: right;"
                           v-if="empObj.empImg"></i>
                        <img height="100%" width="100%" :src=" parkBaseUrl + 'file/read/' + empObj.empImg "
                             v-if="empObj.empImg">
                        <file @selectFile="selectImg" class="file-button"
                              v-if="!empObj.empImg"></file>
                    </template>
                </div>

                <div class="emp-info-div">
                    <el-row :gutter="10" class="common-el-row-style" v-if="cpyCode">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="工号：" prop="workNumber">
                                    <el-input clearable v-model="empObj.workNumber"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="姓名：" prop="empName">
                                    <el-input clearable v-model="empObj.empName"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="性别：" prop="sex">
                                    <el-radio-group v-model="empObj.sex">
                                        <el-radio label="1">先生</el-radio>
                                        <el-radio label="2">女士</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="联系电话：" prop="empPhone">
                                    <el-input v-model.number="empObj.empPhone" @blur="checkPhone()"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="身份证号：" prop="card">
                                    <el-input clearable v-model="empObj.card"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="出生日期：" prop="birthDate">
                                    <el-date-picker style="width: 100%;"
                                                    v-model="empObj.birthDate"
                                                    type="date"
                                                    placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="婚姻状况：">
                                    <el-radio-group v-model="empObj.hasMarry">
                                        <el-radio :label=true>已婚</el-radio>
                                        <el-radio :label=false>未婚</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="籍贯：">
                                    <el-input clearable v-model="empObj.householdLocation"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="9">
                            <div class="grid-content">
                                <el-form-item label="户籍类型：">
                                    <el-radio-group v-model="empObj.householdType">
                                        <el-radio label="1">农业</el-radio>
                                        <el-radio label="2">非农业</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="16">
                            <div class="grid-content">
                                <el-form-item label="毕业学校：">
                                    <el-input clearable v-model="empObj.graduate"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="21">
                            <div class="grid-content">
                                <el-form-item label="最高学历：">
                                    <el-radio-group v-model="empObj.education">
                                        <el-radio label="1">博士后</el-radio>
                                        <el-radio label="2">博士</el-radio>
                                        <el-radio label="3">硕士</el-radio>
                                        <el-radio label="4">本科</el-radio>
                                        <el-radio label="5">专科</el-radio>
                                        <el-radio label="6">高中</el-radio>
                                        <el-radio label="7">初中</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div style="clear: both;"></div>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">紧急联系人</span>
            </div>
            <div class="contact-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="姓名：">
                                <el-input clearable v-model="empObj.urgName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="联系电话：">
                                <el-input v-model.number="empObj.urgPhone"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">岗位信息</span>
            </div>
            <div class="contact-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="所属部门：" prop="deptCode">
                                <el-select v-model="empObj.deptCode" @change="queryDept" filterable placeholder="请选择">
                                    <el-option v-for="(item,index) in deptList"
                                               :key="item.code"
                                               :label="item.name"
                                               :value="item.code"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="岗位职称：" prop="postCode">
                                <el-select v-model="empObj.postCode" @change="queryPost" filterable placeholder="请选择">
                                    <el-option v-for="(item,index) in postList"
                                               :key="item.code"
                                               :label="item.name"
                                               :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">劳务信息</span>
            </div>
            <div class="labor-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同开始：">
                                <el-date-picker style="width: 100%;"
                                                v-model="empObj.contractStartDate"
                                                type="date"
                                                placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同结束：">
                                <el-date-picker style="width: 100%;"
                                                v-model="empObj.contractEndDate"
                                                type="date"
                                                placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="缴社保地：">
                                <el-input clearable v-model="empObj.socialSecurityCity"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="开户行：">
                                <el-input clearable v-model="empObj.openBank"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="银行卡号：">
                                <el-input v-model="empObj.bankCard"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="has-user">
                <el-row :gutter="18">
                    <el-col :span="8" style="margin-top: 5px;">
                        <div class="grid-content">
                            <el-form-item label="是否生成登陆账号：" prop="hasUser" label-width="160px">
                                <el-radio-group v-model="empObj.hasUser">
                                    <el-radio :label=true>是</el-radio>
                                    <el-radio :label=false>否</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <!-- 末尾提交、重置按钮 -->
            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('empObj')">
                    提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('empObj')">
                    重置
                </el-button>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue'

    export default{
        components: {
            ElCol,
            ElRow,
            ElOption,
            file
        },
        created: function () {
            let vm = this;

            // 查询部门
            let options = {
                method: "POST",
                data: {},
                url: "dept/queryDeptList"
            };
            this.$ajax(options).then(function (response) {
                if (response) {
                    let result = response.data.result.deptList;
                    let deptNames = [];
                    let depts = {};
                    for (let i = 0; i < result.length; i++) {
                        let code = result[i].deptCode;
                        let name = result[i].deptName;
                        deptNames.push(
                            {code: code, name: name}
                        );
                        depts[code] = name;
                    }
                    vm.deptList = deptNames;
                    vm.depts = depts;

                    if(result[0] && result[0].cpyCode == '180904868bbcb902de4b') {
                        vm.cpyCode = true;
                    }
                }
            }).catch(function (error) {
                vm.$alert('页面：部门列表加载失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });

            // 查询职位
            let options1 = {
                method: "POST",
                data: {},
                url: "post/queryPostList"
            };
            this.$ajax(options1).then(function (response) {
                if (response) {
                    let result = response.data.result;
                    let postNames = [];
                    let posts = {};
                    for (let i = 0; i < result.length; i++) {
                        let code = result[i].postCode;
                        let name = result[i].postName;
                        postNames.push(
                            {code: code, name: name}
                        );
                        posts[code] = name;
                    }
                    vm.postList = postNames;
                    vm.posts = posts;
                }
            }).catch(function (error) {
                vm.$alert('页面：职位列表加载失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            })
        },
        data(){
            // 校验电话号码
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 检查人员电话是否存在
            let checkEmpPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (value == this.checkPhoneNum) {
                        callback(new Error('电话号话已存在'));
                    } else if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 校验身份证
            let checkIdCard = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/.test(value))) {
                        callback(new Error('请输入正确的身份证号'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 检验银行卡号只能为整数
            let checkBankCard = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('银行卡号只能为数字'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 检验工号只能为整数
            let checkWorkNumber = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('工号只能为数字'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                cpyCode:false,//公司编号
                checkPhoneNum: '',
                depts: {},
                empObj: {
                    empCode: '',
                    workNumber: '',
                    empName: '',
                    empImg: '',
                    sex: '',
                    empPhone: '',
                    card: '',
                    birthDate: '',
                    householdLocation: '',
                    householdType: '',
                    graduate: '',
                    education: '',
                    hasMarry: '',
                    urgName: '',
                    urgPhone: '',
                    urgRelation: '',
                    deptCode: '',
                    deptName: '',
                    postCode: '',
                    postName: '',
                    manageArea: '',
                    contractStartDate: '',
                    contractEndDate: '',
                    socialSecurityCity: '',
                    openBank: '',
                    bankCard: '',
                    hasUser: '',
                },
                imgCode: '',
                fileType: 'image',
                deptList: [],
                postList: [],
                rules: {
                    workNumber: [
                        {required: true, message: '请输入工号', trigger: 'blur'},
                        {validator: checkWorkNumber}
                    ],
                    empName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    empPhone: [
                        {required: true, message: '请输入电话', trigger: 'blur'},
                        {validator: checkEmpPhone}
                    ],
                    card: [
                        {required: true, message: '请输入身份证', trigger: 'blur'},
                        {validator: checkIdCard}
                    ],
                    birthDate: [
                        {required: true, message: '请选择出生日期', trigger: 'change'}
                    ],
                    householdLocation: [
                        {required: true, message: '请输入籍贯', trigger: 'blur'}
                    ],
                    householdType: [
                        {required: true, message: '请选择户籍类型', trigger: 'change'}
                    ],
                    graduate: [
                        {required: true, message: '请输入毕业学校', trigger: 'blur'}
                    ],
                    education: [
                        {required: true, message: '请选择学历', trigger: 'change'}
                    ],
                    hasMarry: [
                        {required: true, message: '请选择婚姻状况', trigger: 'change'}
                    ],
                    urgName: [
                        {required: true, message: '请输入紧急联系人姓名', trigger: 'blur'}
                    ],
                    urgPhone: [
                        {required: true, message: '请输入紧急联系人电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    urgRelation: [
                        {required: true, message: '请输入与紧急联系人的关系', trigger: 'blur'}
                    ],
                    deptCode: [
                        {required: true, message: '请选择所属部门', trigger: 'change'}
                    ],
                    postCode: [
                        {required: true, message: '请选择岗位职称', trigger: 'change'}
                    ],
                    contractStartDate: [
                        {required: true, message: '请选择合同开始日期', trigger: 'change'}
                    ],
                    contractEndDate: [
                        {required: true, message: '请选择合同结束日期', trigger: 'change'}
                    ],
                    socialSecurityCity: [
                        {required: true, message: '请输入缴纳社保地', trigger: 'blur'}
                    ],
                    openBank: [
                        {required: true, message: '请输入开户行', trigger: 'blur'}
                    ],
                    bankCard: [
                        {required: true, message: '请输入银行卡号', trigger: 'blur'},
                        {min: 12, max: 21, message: '银行卡号长度在 12 到 21 个字符', trigger: 'blur'},
                        {validator: checkBankCard},
                    ],
                    hasUser: [
                        {required: true, message: '请选择是否生成登陆账号', trigger: 'change'},
                    ],
                },
            }
        },
        methods: {
            // 头像上传
            selectImg(urls){
                if (urls.length == 1) {
                    this.empObj.empImg = urls[0].fileCode;
                    this.imgCode = urls[0].fileCode;
                } else {
                    this.$message({
                        showClose: true,
                        message: '仅支持上传一张照片',
                        type: 'warning'
                    });
                    return;
                }
            },
            // 新增时移除图片
            removeImg(){
                this.empObj.empImg = '';
            },
            // change事件
            queryDept(val){
                this.empObj.deptCode = val;
                this.empObj.deptName = this.depts[val];
            },
            queryPost(val){
                this.empObj.postCode = val;
                this.empObj.postName = this.posts[val];
            },
            // 重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 提交
            submitForm(formName){
                let vm = this;

                let obj = {};
                obj.entity = vm.empObj;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let options = {
                            method: "POST",
                            data: obj,
                            url: "employee/create"
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if (response) {
                                vm.$message({
                                    showClose: true,
                                    message: '人员新增成功',
                                    type: 'success'
                                });

                                // 新增成功后打开人员详情页面
                                let data = {
                                    menuName: '人员详情',
                                    item: 'empDetails',
                                    params: {
                                        empCode: response.data.result
                                    }
                                };
                                vm.$emit('backParam', data);

                                // 关闭当前新增人员页签
                                let dataDelObj = {
                                    flag: 'del',
                                    menuName: '人员新增',
                                    item: 'empCreate'
                                };
                                vm.$emit('backParam', dataDelObj);
                            }
                        }).catch(function (error) {
                            vm.$alert('页面：人员新增失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                });
            },
            // 检查手机号是否存在
            checkPhone(){
                let obj = {
                    entity: {
                        empPhone: this.empObj.empPhone
                    }
                };
                if (this.empObj.empPhone) {
                    let options = {
                        method: "POST",
                        data: obj,
                        url: "employee/details"
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response && response.data.result) {
                            this.checkPhoneNum = response.data.result.empPhone;
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>
    /*根元素  div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
    }

    .root_form {
        height: 82vh;
        overflow-y: auto;
        overflow-x: hidden;
    }
    /******************************************************************/
    /*  title  公共div */
    .common-title-div {
        background-color: white;
        margin-top: 10px;
        text-align: left;
        height: 50px;
        width: 100%;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*  title  公共文字  */
    .common-title-font {
        font-size: 17px;
        color: #409EFF;
        margin-left: 20px;
    }

    .common-el-row-style {
        margin-top: 15px;
    }

    /******************************************************************/
    /*返回按钮  根div*/
    .back-button-root-div {
        background-color: white;
        height: 50px;
        position: fixed;
        z-index: 200;
        margin-top: -50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*人员详情  文字*/
    .back-button-title {
        line-height: 50px;
        margin-left: 20px;
        font-size: 17px;
        color: #409EFF;
        float: left;
    }

    .save-button-div {
        margin-right: 250px;
    }

    /*保存按钮*/
    .save-button {
        float: right;
        margin-left: 20px;
    }

    /******************************************************************/
    /*基本信息  根div*/
    .emp-info-root-div {
        background-color: white;
        width: 100%;
    }

    /*照片*/
    .photo-div {
        width: 220px;
        /*height: 100%;*/
        float: left;
        border: solid 1px;
    }

    .img-div1 {
        float: left;
        height: 200px;
        line-height: 200px;
        width: 200px;
        margin-top: 15px;
    }

    /*判断是是否上传了头像,如果没有加边框,反之不加*/
    .imgDiv2 {
        border: 1px solid rgb(242, 242, 242);
        margin-left: 10px;
    }

    /*圆形头像样式*/
    .img1 {
        width: 100%;
        height: 100%;
        margin-top: 0px;

        /*注意順序*/
        moz-border-radius: 100%;
        /*firefox*/
        webkit-border-radius: 100%;
        /*Safari, Chrome*/
        border-radius: 100%;
    }

    /*基本信息  div*/
    .emp-info-div {
        float: left;
        width: 77%;
        text-align: left;
        /*border: solid 1px red;*/
        margin-left: 10px;
    }

    /*各项输入框div*/
    .grid-content {
        height: 40px;
        margin-bottom: 20px;
        /*border: solid 1px;*/
    }

    /******************************************************************/
    /*紧急联系人  div*/
    .contact-root-div {
        background-color: white;
        height: 75px;
        text-align: left;
    }

    /******************************************************************/
    /*劳务信息  div*/
    .labor-root-div {
        background-color: white;
        height: 140px;
        text-align: left;
    }

    /*是否生成User*/
    .has-user {
        background-color: white;
        height: 50px;
        margin-top: 10px;
        text-align: left;
    }


</style>