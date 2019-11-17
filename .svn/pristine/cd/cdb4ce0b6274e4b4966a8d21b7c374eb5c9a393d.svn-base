<template>
    <div class="not-position_root">
        <el-form>
            <div class="not-position_header">
                <file @selectFile="selectFile" style="margin-left: 20px;"></file>
            </div>

            <el-table
                    :data="processDeployList"
                    border
                    size="small"
                    :height="tableHeight"
                    >
                <el-table-column
                        align="center"
                        label="序号"
                        type="index">
                </el-table-column>
                <el-table-column
                        show-overflow-tooltip
                        prop="id"
                        label="流程ID"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="流程名称"
                        prop="name"
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
                        min-width="100px"
                        fixed="right"
                        align="center"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" type="text" @click="showActivitiPicture(scope.row.id, scope.row.name)">查看流程图</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <el-dialog :title="activitiName" :visible.sync="activitiPictureWindow" width="90%">
                <img width="100%" height="100%" :src="activitiPicture"/>
            </el-dialog>
        </el-form>
    </div>
</template>

<script>
    // 引入文件选择器
    import file from '../../../common/components/file.vue'
    import ElForm from "../../../../node_modules/element-ui/packages/form/src/form";

    export default {
        components: {
            ElForm,
            file
        },
        created(){
            let vm = this;

            vm.selectProcdefList();
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                processDeployList: [],//部署工作流列表
                activitiPicture: '',// 图片
                activitiPictureWindow: false,// 显示流程图窗口
                activitiName: '',// 工作流名称
            }
        },
        methods: {
            //接受文件组件返回的参数
            selectFile(fileCodes) {
                let vm = this;

                if(!fileCodes || !fileCodes[0] || !fileCodes[0].filePath) {
                    vm.$alert('页面：选择部署文件出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                let filePath = fileCodes[0].filePath;

                let obj = {};
                obj.filePath = filePath;
                obj.entity = {};

                let option = {
                    method: "POST",
                    data: obj,
                    url: "activitiManage/deployment"
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if(response) {
                        vm.$message({
                            message: '流程部署成功！',
                            type: 'success'
                        });
                        vm.selectProcdefList();
                    }
                }).catch(function (error) {
                    vm.$alert('页面：部署流程文件出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
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

            // 显示流程图片
            showActivitiPicture(id, name){
                let vm = this;
                vm.activitiPictureWindow = true;

                let params = {
                    entity: {
                        processDefinitionId: id
                    }
                };

                let option = {
                    method: 'POST',
                    data: params,
                    url: 'activitiManage/showActivitiPicture'
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if(response) {
                        let pic = response.data.result;
                        vm.activitiName = name;
                        vm.activitiPicture = "data:image/png;base64," + pic;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：部署流程文件出错', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },
        }
    }
</script>
