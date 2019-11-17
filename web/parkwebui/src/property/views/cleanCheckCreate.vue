<template>
    <div class="=root">
        <el-dialog
                :visible.sync="showCreate"
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
                        <span style="font-size: 25px">新建评分</span>
                    </div>
                    <el-row>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="园区" prop="pkCode">
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
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="责任人" prop="beInspectedEmpCode">
                                <el-select filterable  size="small" v-model="inspectInfo.beInspectedEmpCode" placeholder="选择责任人">
                                    <el-option
                                            v-for="item in employeeList"
                                            :key="item.code"
                                            :label="item.name"
                                            :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="检查人" prop="inspectEmpCode">
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

                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="选择项目" prop="templateCode">
                                <el-select filterable  size="small" v-model="inspectInfo.templateCode" placeholder="选择项目" @change="getTask">
                                    <el-option
                                            v-for="item in templateList"
                                            :key="item.templateCode"
                                            :label="item.templateName"
                                            :value="item.templateCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="选择检查清单" prop="taskCode">
                                <el-select filterable  size="small" v-model="inspectInfo.taskCode" placeholder="选择检查清单" @change="getTaskInfo">
                                    <el-option
                                            v-for="item in taskList"
                                            :key="item.taskCode"
                                            :label="item.taskName"
                                            :value="item.taskCode">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12"  :lg="12" :xl="8">
                            <el-form-item label-width="110px" label="检查日期" prop="inspectTime">
                                <el-date-picker
                                        style="max-width: 215px;width: auto;"
                                        size="small"
                                        v-model="inspectInfo.inspectTime"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <div class="fill_in">
                        <div v-for="(item,index) in objRuleTree" :key="item.objRlCode">
                            <el-collapse v-model="openNode">
                                <el-collapse-item :name="item.objRlCode" :disabled="openDisable">
                                    <template slot="title">
                                        <span style="font-size: 16px">{{item.objRlName}}&nbsp;&nbsp;</span>
                                        <el-button type="primary" size="small" @click="upLoadAttachment(item.objRlCode)">
                                            上传图片
                                            <i class="el-icon-upload el-icon--right"></i></el-button>
                                    </template>
                                    <el-row class="fill_row" v-if="item.children" v-for="(child,cindex) in item.children" :key="cindex">
                                        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" >
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
                                                        v-model="scoreList[child.objRlCode]"
                                                        show-score
                                                        :allow-half=true
                                                        text-color="#ff9900"
                                                        score-template="{value}分">
                                                </el-rate>-->
                                            <el-form-item>
                                                <el-input size="small" v-model="scoreList[child.objRlCode]">
                                                    <template slot="append">分</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="9" :sm="9" :md="8" :lg="10" :xl="12">
                                            <el-form-item label="备注">
                                                <el-input  :rows="1" type="textarea"
                                                          v-model="remarkList[child.objRlCode]"></el-input>
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
        props:["paramsCreate"],
        created: function(){
            let vm = this;
            if(vm.paramsCreate){
                vm.showCreate = vm.paramsCreate.showCreate
                vm.inspectInfo.inspectType = vm.paramsCreate.inspectType;
            }

            this.getTemplate();
            this.getPark();
            this.getEmployee();
          //  this.getTask();
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


                showCreate: false,
                //提交时返回的数据
                inspectInfo:{
                    templateCode: '',
                    taskCode: '',
                    pkCode: '',
                    pkName: '',
                    beInspectedEmpCode: '',
                    beInspectedEmpName: '',
                    inspectEmpCode: '',
                    inspectEmpName: '',
                    inspectTime:'',
                    scoreEntityList:[],
                    inspectType: '',
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
                    templateCode: [
                        {required: true, message: '请选择模板', trigger: 'change'}
                    ],
                    taskCode: [
                        {required: true, message: '请选择模板', trigger: 'change'}
                    ],
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
            //获取任务所有模板
            getTemplate(){
                let vm = this;
                let params = {
                    entity:{
                        inspectType: vm.inspectInfo.inspectType
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "inspectTemplate/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        vm.templateList = response.data.result;
                    }
                }).catch(error => {
                    this.$alert("页面:获取模板失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {
                        }
                    });
                })
            },
            //根据模板获取任务
            getTask(){
                let vm = this;
                if(!vm.inspectInfo.templateCode){
                    return false;
                }

                vm.objRuleTree = [];
                let params = {
                    entity:{
                        templateCode: vm.inspectInfo.templateCode
                    }
                }
                let options = {
                    method: "post",
                    data: params,
                    url: "inspectTask/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    vm.taskList = response.data.result;
                    vm.inspectInfo.taskCode = "";
                }).catch(error => {
                    this.$alert("页面:获取任务失败", "系统异常", {
                        confirmButtonText: "确定",
                        callback: action => {
                        }
                    });
                })
            },

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

            //获取任务信息,包含节点树
            getTaskInfo(){
              let vm = this;
              if(!vm.inspectInfo.taskCode){
                  return false;
              }

              vm.clearObj();//清空数据

              let params = {
                  entity:{
                      taskCode: vm.inspectInfo.taskCode
                  }
              }
              let options = {
                  method: "POST",
                  data: params,
                  url: "inspectTask/treeList"
              }
              this.$ajax(
                  options
              ).then(response => {
                  if(response){
                      vm.taskInfo = response.data.result;
                      vm.rootObjRl.objRlCode = vm.taskInfo.objectRuleTree.objRlCode;
                      vm.rootObjRl.onjRlName = vm.taskInfo.objectRuleTree.objRlName;
                      vm.rootObjRl.parentCode = vm.taskInfo.objectRuleTree.parentCode;
                      vm.rootObjRl.type = vm.taskInfo.objectRuleTree.type;
                      vm.rootObjRl.describe = vm.taskInfo.objectRuleTree.describe;
                      if(vm.taskInfo.objectRuleTree.children){
                          vm.objRuleTree = vm.taskInfo.objectRuleTree.children;
                          for(let i = 0; i < vm.objRuleTree.length; i++){
                              vm.openNode.push(vm.objRuleTree[i].objRlCode);
                          }
                      }else{
                          vm.objRuleTree = [];
                      }

                  }

              })
            },

            //清空实体
            clearObj(){
                let vm = this;
                vm.scoreList = [];//得分列表 scoreList[objRlCode] = score
                vm.remarkList = []; //备注列表 describeList[objRlCode] = describe
                vm.fileCodeList = [];
                vm.objRuleTree = [];
                vm.rootObjRl = {};
                vm.openNode = [];
            },

            //关闭弹窗回调
            closeShow(){
                let vm = this;
                vm.showCreate = false;
                let data = {
                    showCreate :vm.showCreate,
                };
                vm.$emit('backParam',data);
            },

            submitInspect(formName) {
                let vm = this;
                /*if(!vm.inspectInfo.templateCode || !vm.inspectInfo.taskCode){
                    this.$message({
                        showClose: true,
                        message: '请选择模板及任务',
                        type: 'warning'
                    });
                    return false;
                }*/
                this.$refs[formName].validate((valid) => {
                    if(!valid){
                        return false;
                    }

                    //判断任务是否有检查事项
                    if(!vm.objRuleTree || vm.objRuleTree.length < 1){
                        this.$message({
                            showClose: true,
                            message: '检查清单未分配检查事项！',
                            type: 'warning'
                        });
                        vm.inspectInfo.scoreEntityList = [];
                        return false;
                    }
                    vm.inspectInfo.scoreEntityList.push(vm.rootObjRl);
                    for (let i = 0; i < vm.objRuleTree.length; i++) {
                        let topObj = vm.objRuleTree[i];
                        let fileCode = vm.fileCodeList[topObj.objRlCode];

                        let topTaskScoreInfo = {};
                        if(fileCode){
                            topTaskScoreInfo.fileCode = fileCode;
                        }
                        topTaskScoreInfo.objRlCode = topObj.objRlCode;
                        topTaskScoreInfo.objRlName = topObj.objRlName;
                        topTaskScoreInfo.type = topObj.type;
                        topTaskScoreInfo.parentCode = topObj.parentCode;
                        topTaskScoreInfo.describe = topObj.describe;
                        vm.inspectInfo.scoreEntityList.push(topTaskScoreInfo);

                        if (vm.objRuleTree[i].children) {
                            for (let j = 0; j < vm.objRuleTree[i].children.length; j++) {
                                let taskScoreInfo = {};

                                let child = vm.objRuleTree[i].children[j];
                                let remark = vm.remarkList[child.objRlCode];
                                taskScoreInfo.objRlCode = child.objRlCode;
                                taskScoreInfo.objRlName = child.objRlName;
                                taskScoreInfo.grade = child.grade;

                                let score = vm.scoreList[child.objRlCode];

                                if(!score){
                                    this.$message({
                                        showClose: true,
                                        message: '分数不能为空！',
                                        type: 'warning'
                                    });
                                    vm.inspectInfo.scoreEntityList = [];
                                    return false;
                                }
                                //校验分数是否为正整数
                                if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(score))) {
                                    this.$message({
                                        showClose: true,
                                        message: '分数只能为正整数或小数!',
                                        type: 'warning'
                                    });
                                    vm.inspectInfo.scoreEntityList = [];
                                    return false;
                                }
                                if(score > child.grade){
                                    this.$message({
                                        showClose: true,
                                        message: '填写分数大于事项评分!',
                                        type: 'warning'
                                    });
                                    vm.inspectInfo.scoreEntityList = [];
                                    return false;
                                }
                                taskScoreInfo.score = score;

                                taskScoreInfo.type = child.type;
                                taskScoreInfo.parentCode = child.parentCode;
                                taskScoreInfo.describe = child.describe;
                                if (remark) {
                                    taskScoreInfo.remark = remark;
                                }
                                vm.inspectInfo.scoreEntityList.push(taskScoreInfo);

                            }
                        }
                    }
                    vm.inspectInfo.pkName = vm.parkNames[vm.inspectInfo.pkCode];
                    vm.inspectInfo.beInspectedEmpName = vm.employeeNames[vm.inspectInfo.beInspectedEmpCode];
                    vm.inspectInfo.inspectEmpName = vm.employeeNames[vm.inspectInfo.inspectEmpCode];

                    let params = {
                        entity: vm.inspectInfo
                    }
                    let options = {
                        method: "POST",
                        url: "inspectInfo/create",
                        data: params
                    }
                    this.$ajax(
                        options
                    ).then(response => {
                        if(response){
                            this.$message({
                                message: '创建成功！',
                                type: 'success'
                            });
                        }
                        this.closeShow();
                    }).catch(error => {
                        vm.inspectInfo.scoreEntityList = [];
                        this.$alert("页面:添加失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {
                            }
                        });
                    })

                });
            },

            //上传附件
            upLoadAttachment(objRlCode){
                let vm = this;

                vm.openDisable = true;

                vm.showAttachment = true;
                let fileString = vm.fileCodeList[objRlCode];
                let fileList = [];
                if(fileString){
                    fileList = fileString.split("，");
                }
                vm.paramAttachment.fileList = fileList;
                vm.paramAttachment.showAttachment = vm.showAttachment;
                vm.paramAttachment.objRlCode = objRlCode;
            },

            //接收上传附件返回
            backParamAttachment(val){
                let vm = this;
                vm.openDisable = false;
                vm.showAttachment = val.showAttachment;
                let objRlCode = val.objRlCode;
                let fileList = val.fileList;

                if(fileList.length > 0){
                    let fileString = fileList.join("，");
                    vm.fileCodeList[objRlCode] = fileString;
                }
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