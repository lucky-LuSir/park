<template>
    <div class="root" ref="myRoot">
        <div class="navigation_div">
            <span style="font-size: 17px;color: #409EFF;margin-left: 20px;">根据您的需要，选择相应的流程进行申请</span>
        </div>

        <el-table
                :data="processDeployList"
                border
                size="small"
                height="74vh"
                style="width: 100%;line-height: 30px;">
            <el-table-column
                    align="center"
                    label="序号"
                    type="index">
            </el-table-column>
            <el-table-column
                    label="流程名称"
                    prop="name"
                    align="center">
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    prop="id"
                    label="流程定义ID"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="流程定义key"
                    prop="key"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="最新版本"
                    prop="version"
                    align="center">
            </el-table-column>
            <el-table-column
                    fixed
                    width="150px"
                    fixed="right"
                    align="center"
                    label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="createApply(scope.row)" type="text">申请</el-button>
                </template>
            </el-table-column>
        </el-table>

        <throw-lease-apply v-if="showThrowLeaseApply" @backParam="backParam($event)" :paramsObj="paramsObj" ></throw-lease-apply>

    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import throwLeaseApply from './workflowTemplate/throwLeaseApply.vue';

    export default {
        components: {
            ElRow,
            throwLeaseApply
        },
        created(){
            let vm = this;

            vm.selectProcdefList();
        },
        data() {
            return {
                processDeployList: [],//流程列表
                showThrowLeaseApply: false,//申请退租单弹窗控制
                paramsObj: {},//父子组件传值参数对象
            }
        },
        methods: {
            // 查询已部署的流程
            selectProcdefList(){
                let vm = this;

                let params = {
                    entity: {}
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'activitiManage/procdefList'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if(response) {
                        vm.processDeployList = response.data.result;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：部署流程文件出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //创建申请
            createApply(row){
                let vm = this;
                if(row.key == 'kfwy_throw_lease_apply') {//打开申请退租单填写窗口
                    vm.showThrowLeaseApply = true;
                    vm.paramsObj.type = "add";
                    vm.paramsObj.showThrowLeaseApply = vm.showThrowLeaseApply;//控制弹窗是否展示
                    let param = {
                        key : row.key,
                        pdId : row.id
                    };
                    vm.paramsObj.param = param;
                }
            },
            //子组件回调
            backParam(val) {
                if(val.key == "kfwy_throw_lease_apply") {
                    this.showThrowLeaseApply = val.showThrowLeaseApply;
                }

                let data = {
                    menuName: '我的申请',
                    item: 'myApplication',
                };
                this.$emit('backParam', data);
            }
        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    /*------------------------------ 导航栏 ------------------------------*/
    /*导航栏DIV*/
    .navigation_div {
        height: 60px;
        line-height: 60px;
        margin-top: -50px;
        text-align: left;
    }

    /*导航栏右边按钮*/
    .navigation_button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

</style>



