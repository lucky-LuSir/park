<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button v-show="showEditEmployeeBtn" class="update-button" size="small" type="primary"
                       @click="editEmp(empObj.empCode)">
                人员修改
            </el-button>
        </div>
        <el-form class="has-position_main" label-width="100px">


            <div class="common-title-div">
                <span class="common-title-font">基本信息</span>
            </div>

            <div class="emp-info-root-div">
                <div class="photo-div" v-if="empObj.empImg">
                    <img style="width: 100%;height: 100%;" :src=" parkBaseUrl + 'file/read/' + empObj.empImg ">
                </div>
                <div class="photo-div" v-if="!empObj.empImg">
                    <img style="width: 100%;height: 100%;" src="./img/TIM20180716114924.jpg">
                </div>

                <div class="emp-info-div">
                    <el-row :gutter="20" class="common-el-row-style" v-if="cpyCode">
                        <el-col>
                            <div class="grid-content">
                                <el-form-item label="工号：" prop="workNumber">
                                    <span>{{ empObj.workNumber }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="姓名：" prop="empName">
                                    <span>{{ empObj.empName }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="性别：" prop="sex">
                                    <span>{{ empObj.sexName }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="联系电话：" prop="empPhone">
                                    <span>{{ empObj.empPhone }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="身份证号：" prop="card">
                                    <span>{{ empObj.card }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="出生日期：" prop="birthDate">
                                    <span>{{ empObj.birthDate | timeDate }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="籍贯：" prop="householdLocation">
                                    <span>{{ empObj.householdLocation }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="户籍类型：" prop="householdType">
                                    <span>{{ empObj.householdTypeName }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="毕业学校：" prop="graduate">
                                    <span>{{ empObj.graduate }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="最高学历：" prop="education">
                                    <span>{{ empObj.educationName }}</span>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="婚姻状况：" prop="hasMarry">
                                    <span v-if="empObj.hasMarry">已婚</span>
                                    <span v-if="!empObj.hasMarry">未婚</span>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content" style="margin-left: 20px;">
                                <!--<el-form-item label="是否在职：">-->
                                    <el-switch
                                            style="display: block"
                                            v-model="empObj.empIsWork"
                                            active-color="#13ce66"
                                            inactive-color="#ff4949"
                                            active-text="在职"
                                            inactive-text="离职"
                                            @change="empIsWorkOpenOff($event,empObj)">
                                    </el-switch>
                                    <!--<el-switch-->
                                            <!--v-model="empObj.empIsWork"-->
                                            <!--active-color="#13ce66"-->
                                            <!--inactive-color="#ff4949"-->
                                            <!--@change="empIsWorkOpenOff($event,empObj)">-->
                                    <!--</el-switch>-->
                                <!--</el-form-item>-->

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
                <el-row :gutter="50">
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="姓名：" prop="urgName">
                                <span>{{ empObj.urgName }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="联系电话：" prop="urgPhone">
                                <span>{{ empObj.urgPhone }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="两人关系：" prop="urgRelation">
                                <span>{{ empObj.urgRelation }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">岗位信息</span>
            </div>

            <div class="contact-root-div">
                <el-row :gutter="50">
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="所属部门：" prop="deptCode">
                                <span>{{ empObj.deptName }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="岗位职称：" prop="postCode">
                                <span>{{ empObj.postName }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">劳务信息</span>
            </div>

            <div class="labor-root-div">
                <el-row :gutter="50">
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同开始：" prop="contractStartDate">
                                <span>{{ empObj.contractStartDate | timeDate }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同结束：" prop="contractEndDate">
                                <span>{{ empObj.contractEndDate | timeDate }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="缴社保地：" prop="socialSecurityCity">
                                <span>{{ empObj.socialSecurityCity }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="50">
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="开户行：" prop="openBank">
                                <span>{{ empObj.openBank }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="grid-content">
                            <el-form-item label="银行卡号：" prop="bankCard">
                                <span>{{ empObj.bankCard }}</span>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";

    export default{
        //接受父组件传过来的参数
        props: ['paramsObj'],

        components: {
            ElCol,
            ElOption},
        created: function () {
            let vm = this;

            // 权限控制
            vm.showEditEmployeeBtn = vm.$flagMenuStore.judgeMenu("editEmployeeBtn");

            if (vm.paramsObj && vm.paramsObj.empCode) {
                let empCode = vm.paramsObj.empCode;

                let empParams = {
                    entity: {
                        empCode: empCode
                    }
                };

                // 查询详情
                let options = {
                    method: "POST",
                    data: empParams,
                    url: "employee/details"
                };
                this.$ajax(options).then(function (response) {
                    if (response) {
                        vm.empObj = response.data.result;
                        if(vm.empObj && vm.empObj.cpyCode == '180904868bbcb902de4b') {
                            vm.cpyCode = true;
                        }
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查看人员详情失败!',
                        type: 'error'
                    })
                })
            } else {
                this.$router.go(-1);
            }
        },
        data(){
            return {
                cpyCode:false,//公司编号
                showEditEmployeeBtn: false,
                empObj: {
                    empCode: "",
                    workNumber: "",
                    empName: "",
                    sex: "",
                    sexName: "",
                    empPhone: "",
                    card: "",
                    birthDate: "",
                    householdLocation: "",
                    householdType: "",
                    householdTypeName: "",
                    graduate: "",
                    education: "",
                    educationName: "",
                    hasMarry: "",
                    urgName: "",
                    urgPhone: "",
                    urgRelation: "",
                    deptCode: "",
                    deptName: "",
                    postCode: "",
                    postName: "",
                    manageArea: "",
                    contractStartDate: "",
                    contractEndDate: "",
                    socialSecurityCity: "",
                    openBank: "",
                    bankCard: "",
                    empIsWork: "",
                }
            }
        },
        methods: {
            // 修改详情
            editEmp(empCode){
                let data = {
                    menuName: "人员修改",
                    item: "empEdit",
                    params: {
                        empCode: empCode
                    }
                };
                this.$emit("backParam",data);
            },

            //员工是否在职开关
            empIsWorkOpenOff(val,item) {
                let vm = this;

                let msg = "";

                if(val) {//打开为在职状态
                    msg = "在职";
                } else{
                    msg = "离职";
                }

                vm.$confirm('此操作将把该员工改为'+msg+'状态, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = {
                        entity: item
                    };

                    let options = {
                        method: "POST",
                        data: params,
                        url: "employee/openCloseAccount"
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (!response.data.isException) {
                            vm.$message({
                                showClose: true,
                                message: '该员工改为'+msg+'状态成功',
                                type: 'success'
                            });
                        }
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面：该员工改为'+msg+'状态失败',
                            type: 'error'
                        });
                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消修改'
                    });

                    vm.empObj.empIsWork = !val;
                });
            }
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
        font-weight: bold;
    }

    .common-el-row-style {
        margin-top: 15px;
        text-align: left;
    }

    /******************************************************************/
    /*返回按钮  根div*/
    .back-button-root-div {
        background-color: white;
        height: 50px;
        position: fixed;
        margin-top: -50px;
        z-index: 200;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*返回按钮*/
    .back-button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    /*人员详情  文字*/
    .back-button-title {
        line-height: 50px;
        margin-left: 30px;
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        float: left;
    }

    /*保存按钮*/
    .update-button {
        float: right;
        margin: 20px;
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
        height: 220px;
        float: left;
        margin-top: 29px;
        margin-left: 20px;
        /*border: solid 1px;*/
    }

    /*圆形头像*/
    .img1 {
        width: 250px;
        height: 250px;
        margin: 20px;

        /*注意順序*/
        moz-border-radius: 100%;
        /*firefox*/
        webkit-border-radius: 100%;
        /*Safari, Chrome*/
        border-radius: 100%;
    }

    /*基本信息  div*/
    .emp-info-div {
        float: right;
        width: 74%;
        text-align: left;
        margin-left: 15px;
        /*border: solid 1px;*/
    }

    /*各项输入框div*/
    .grid-content {
        height: 40px;
        margin-bottom: 20px;
    }

    /******************************************************************/
    /*紧急联系人  div*/
    .contact-root-div {
        background-color: white;
        height: 75px;
    }

    /******************************************************************/
    /*劳务信息  div*/
    .labor-root-div {
        background-color: white;
        height: 140px;
        text-align: left;
    }


</style>