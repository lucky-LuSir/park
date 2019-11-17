<template>
    <div>
        <el-dialog
                class="dialog"

                :visible.sync="showEdit"
                width="80%"

                :close-on-click-modal="false"
                :before-close="closeShow"
                center>
            <el-form
                    :model="inspectInfo"
                    ref="inspectInfo"
                    label-width="100px"
                    :rules="taskRules"
                    label-suffix=":">

                <el-card class="task_select">
                    <div slot="header" style="text-align: center">
                        <span style="font-size: 25px">修改评分</span>
                    </div>
                    <el-row>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="6">
                            <el-form-item label="园区" prop="pkCode">
                                <el-select filterable  size="small" v-model="inspectInfo.pkCode" placeholder="选择园区">
                                    <el-option
                                            v-for="item in parkList"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="6">
                            <el-form-item filterable  label="责任人" prop="beInspectedEmpCode">
                                <el-select size="small" v-model="inspectInfo.beInspectedEmpCode" placeholder="选择责任人">
                                    <el-option
                                            v-for="item in employeeList"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>

                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="6">
                            <el-form-item label="检查人" prop="inspectEmpCode">
                                <el-select filterable  size="small" v-model="inspectInfo.inspectEmpCode" placeholder="选择检查人">
                                    <el-option
                                            v-for="item in employeeList"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="6">
                            <el-form-item label="检查日期" prop="inspectTime">
                                <el-date-picker
                                        style="max-width: 215px;width: auto"
                                        size="small"
                                        v-model="inspectInfo.inspectTime"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <div class="fill_in">
                        <div v-for="(item,index) in objRuleTree.children" :key="item.objRlCode">
                            <el-collapse v-model="openNode">
                                <el-collapse-item :name="item.objRlCode" :disabled="openDisable">
                                    <template slot="title">
                                        <span style="font-size: 16px">{{item.objRlName}}&nbsp;&nbsp;</span>
                                        <el-button type="primary" size="small" @click="upLoadAttachment(index,item.fileCode)">
                                            上传图片
                                            <i class="el-icon-upload el-icon--right"></i></el-button>
                                    </template>
                                    <el-row class="fill_row" v-if="item.children" v-for="(child,cindex) in item.children" :key="cindex">
                                        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                            <el-popover
                                                    class="childDescribe"
                                                    placement="top-start"
                                                    width="200"
                                                    trigger="hover"
                                                    :content="child.describe">
                                                <span slot="reference">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{child.objRlName}}({{child.grade}}分)</span>
                                            </el-popover>
                                        </el-col>
                                        <el-col :xs="8" :sm="8" :md="8" :lg="6" :xl="4">
                                            <!--<el-form-item :label="child.objRlName" :title="child.describe" prop="score">
                                                <el-rate
                                                        style="margin-top: 10px"
                                                        :max="child.grade"
                                                        v-model="child.score"
                                                        show-score
                                                        :allow-half=true
                                                        text-color="#ff9900"
                                                        score-template="{value}分">
                                                </el-rate>
                                            </el-form-item>-->
                                            <el-form-item >
                                                <el-input size="small" v-model="child.score">
                                                    <template slot="append">分</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="9" :sm="9" :md="9" :lg="10" :xl="12">
                                            <el-form-item label="备注">
                                                <el-input :rows="1" type="textarea"
                                                          v-model="child.remark"></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-collapse-item>
                            </el-collapse>
                        </div>
                    </div>
                    <div style="float: right;margin-bottom: 15px;margin-top: 15px">
                        <el-button   size="small" @click="closeShow">取消</el-button>
                        <el-button  style="margin-left: 20px"  type="primary" size="small" @click="submitInspect('inspectInfo')">提交</el-button>
                    </div>
                </el-card>
            </el-form>
        </el-dialog>
        <upload-attachment v-if="showAttachment"
                           @backParam="backParamAttachment($event)"
                           :paramAttachment = "paramAttachment">

        </upload-attachment>
    </div>
</template>
<script>
    import uploadAttachment from "../../ati/views/customer/uploadAttachment"
    export default {
        components:{
            uploadAttachment
        },
        props:["paramsEdit"],
        created: function(){
            let vm = this;
            if(vm.paramsEdit){

                vm.showEdit = vm.paramsEdit.showEdit;
                vm.inspectCode = vm.paramsEdit.inspectCode;
            }


            this.getPark();
            this.getEmployee();
            this.getInspectInfo();
        },
        data(){
            // 校验数字只能是正整数或小数
            let checkIntOrFloat = (rule, value, callback) => {
                if(value){
                    setTimeout(() => {
                        if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                            callback(new Error('只能输入正整数或小数'));
                        } else {
                            callback();
                        }
                    }, 100);
                }
            };
            return {
                inspectCode: '',

                openDisable: false,
                showAttachment: false, //上传附件
                paramAttachment:{}, //附件参数


                openNode: [], //第一个node节点,使其默认展开
                templateList:[],
                taskList:[],
                parkList:[],
                parkNames:[],
                employeeList:[],
                employeeNames: [],

                rootObjRl:{}, //根节点
                objRuleTree:[],//任务包含的节点,不包含根节点
                taskInfo:{},

                scoreList:[],//得分列表 scoreList[objRlCode] = score
                remarkList:[], //备注列表 describeList[objRlCode] = describe
                fileCodeList:[],//文件列表


                showEdit: false,
                //提交时返回的数据
                inspectInfo:{
                    id: '',
                    inspectCode: '',
                    templateCode: '',
                    taskCode: '',
                    pkCode: '',
                    pkName: '',
                    beInspectedEmpCode: '',
                    beInspectedEmpName: '',
                    inspectEmpCode: '',
                    inspectEmpName: '',
                    inspectTime:'',
                    scoreEntityTree:[],
                },

                taskRules: {
                    pkCode: [
                        {required: true, message: '请选择所属园区', trigger: 'change'}
                    ],
                    beInspectedEmpCode: [
                        {required: true, message: '请选择责任人', trigger: 'change'}
                    ],
                    inspectEmpCode: [
                        {required: true, message: '请选择检查人员', trigger: 'change'}
                    ],
                    // templateCode: [
                    //     {required: true, message: '请选择模板', trigger: 'change'}
                    // ],
                    // taskCode: [
                    //     {required: true, message: '请选择模板', trigger: 'change'}
                    // ],
                    inspectTime: [
                        {required: true, message: '请选择检查日期', trigger: 'change'}
                    ],
                    // score: [
                    //     {validator: checkIntOrFloat},
                    // ]
                },
            }
        },

        methods:{
            //获取所有园区
            getPark(){
                let vm = this;
                let params = {
                    entity:{}
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "park/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    let parkList = response.data.result;
                    for(let i = 0; i < parkList.length; i++){
                        vm.parkList.push(
                            {code: parkList[i].pkCode,name: parkList[i].pkName}
                        );
                        vm.parkNames[parkList[i].pkCode] = parkList[i].pkName;
                    }
                }).catch(error => {
                    this.$alert("页面:获取园区失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {
                        }
                    });
                })
            },

            //获取所有员工
            getEmployee(){
                let vm = this;
                let params = {
                    entity:{}
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "employee/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    let employeeList = response.data.result;
                    for(let i = 0; i < employeeList.length; i++){
                        vm.employeeList.push(
                            {code: employeeList[i].empCode,name: employeeList[i].empName}
                        );
                        vm.employeeNames[employeeList[i].empCode] = employeeList[i].empName;
                    }
                }).catch(error => {
                    this.$alert("页面:获取员工失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {
                        }
                    });
                })
            },


            //关闭弹窗回调
            closeShow(){
                let vm = this;
                vm.showEdit = false;
                let data = {
                    showEdit :vm.showEdit,
                };
                vm.$emit('backParam',data);
            },

            //提交
            submitInspect(formName) {
                let vm = this;

                this.$refs[formName].validate((valid) => {
                    //校验得分是否为正整数
                    for(let i = 0; i < vm.objRuleTree.children.length; i++){
                        for(let j =0; j < vm.objRuleTree.children[i].children.length; j++){
                            let score = vm.objRuleTree.children[i].children[j].score;
                            if(!score){
                                this.$message({
                                    showClose: true,
                                    message: '分数不能为空!',
                                    type: 'warning'
                                });
                                return false;
                            }

                            if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(score))){
                                this.$message({
                                    showClose: true,
                                    message: '分数只能为正整数或小数!',
                                    type: 'warning'
                                });
                                return false;
                            }
                            let grade = vm.objRuleTree.children[i].children[j].grade;
                            if(score > grade){
                                this.$message({
                                    showClose: true,
                                    message: '填写分数大于事项评分!',
                                    type: 'warning'
                                });
                                return false;
                            }
                        }
                    }

                    vm.inspectInfo.scoreEntityTree = vm.objRuleTree;
                    vm.inspectInfo.pkName = vm.parkNames[vm.inspectInfo.pkCode];
                    vm.inspectInfo.beInspectedEmpName = vm.employeeNames[vm.inspectInfo.beInspectedEmpCode];
                    vm.inspectInfo.inspectEmpName = vm.employeeNames[vm.inspectInfo.inspectEmpCode];


                    let params = {
                        entity: vm.inspectInfo
                    }
                    let options = {
                        method: "POST",
                        url: "inspectInfo/update",
                        data: params
                    }
                    this.$ajax(
                        options
                    ).then(response => {
                        if(response){
                            this.$message({
                                message: '修改成功！',
                                type: 'success'
                            });
                        }
                        this.closeShow();
                    }).catch(error => {
                        this.$alert("页面:添加失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {
                            }
                        });
                    })

                });
            },

            //上传附件
            upLoadAttachment(index,fileCode){
                let vm = this;

                vm.openDisable = true;

                vm.showAttachment = true;

                let fileList = [];
                if(fileCode){
                    fileList = fileCode.split("，");
                }
                vm.paramAttachment.fileList = fileList;
                vm.paramAttachment.showAttachment = vm.showAttachment;
                vm.paramAttachment.index = index;
            },

            //接收上传附件返回
            backParamAttachment(val){
                let vm = this;
                vm.openDisable = false;
                vm.showAttachment = val.showAttachment;
                let index = val.index;

                let fileList = val.fileList;

                if(fileList.length > 0){
                    let fileString = fileList.join("，");
                    vm.objRuleTree.children[index].fileCode = fileString;
                }
            },

            //获取评分信息
            getInspectInfo(){
                let vm = this;
                let params = {
                    entity: {
                        inspectCode: vm.inspectCode
                    }
                }
                let options = {
                    method: "POST",
                    url: "inspectInfo/infoWithScoreTree",
                    data: params,
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        vm.inspectInfo = response.data.result;
                        if (vm.inspectInfo.scoreEntityTree) {
                            vm.objRuleTree = vm.inspectInfo.scoreEntityTree;
                            if(vm.objRuleTree.children){
                                for(let i = 0; i < vm.objRuleTree.children.length; i++){
                                    vm.openNode.push(vm.objRuleTree.children[i].objRlCode);
                                }
                            }
                        }

                    }
                })
            },

        }
    }
</script>
<style scoped>
    .task_select{
        width: 100%;
    }
    .fill_in{
        max-height: 350px;
        overflow:auto;
    }
    .fill_row {
        display: flex;
        align-items: center;
    }
    .el-form-item {
        margin-bottom: 0px;
    }
    .el-textarea{
        display: block;
    }
    /deep/ .el-dialog__header {
        padding: 0;
    }
    /deep/ .el-dialog__headerbtn {
        top: 10px;
        right: 5px;
    }
    .childDescribe:hover{
        cursor: pointer;
    }
</style>