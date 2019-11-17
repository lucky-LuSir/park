<template>
    <div class="root">
        <el-container>
            <el-aside width="320px">
                <div class="templateBox">
                    <span>选择模板: </span>
                    <el-select size="small" @change="templateChangeTask()" v-model="templateValue" placeholder="请选择">
                        <el-option v-for="item in templateOptions" :key="item.templateCode" :label="item.templateName" :value="item.templateCode">
                        </el-option>
                    </el-select>
                    <el-button type="primary" size="small" icon="el-icon-plus" @click="addTask()">新建任务</el-button>
                </div>
                <el-menu default-active="1" class="el-menu-vertical-demo">
                    <el-menu-item index="1" :class="active == index ? 'addclass' : '' " v-for="(item, index) in taskList" @click="handleClickMenu(item, index)" :key="index">
                        <span>{{item.taskName}}</span>
                        <!-- <div class="btnBox">
                            <i v-if="showBtn == item.id" @click="editTemplate(item)" class="el-icon-edit"></i>
                            <i v-if="showBtn == item.id" @click.stop="delTemplate(item)" class="el-icon-delete"></i>
                        </div> -->
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <div class="ruleBtnBox" style="margin-bottom: 30px;">
                    <el-button type="primary" size="small" @click="preserve()">保存</el-button>
                </div>
                <el-tree :data="treeList" ref="tree" show-checkbox node-key="id" :expand-on-click-node="true" highlight-current :default-expand-all="false" :props="defaultProps">
                </el-tree>
            </el-main>
        </el-container>
        <!-- 新增模板弹出框 -->
        <el-dialog title="新建任务" :visible.sync="addTaskDialogVisible" width="40%">
            <el-form label-position="right" label-width="80px" ref="addtemplateProps" :model="addTaskProps">
                <el-form-item label="选择模板">
                    <el-select @change="templateChangeTask()" v-model="templateValue" placeholder="请选择">
                        <el-option v-for="item in templateOptions" :key="item.templateCode" :label="item.templateName" :value="item.templateCode">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="任务名称">
                    <el-input v-model="addTaskProps.taskName"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="addTaskProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addTaskDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addTaskDialog()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                addTaskDialogVisible: false,
                templateOptions: [],
                templateValue: "",
                taskList: [],
                addTaskProps: {
                    taskName: "",
                    describe: ""
                },
                treeList: [],
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                isClickMenu: false,
                active: -1,
                // isNewAddTask: false,
            };
        },
        mounted() {
            this.getTemplateList();
        },
        methods: {
            // 保存按钮
            async preserve() {
                if (!this.isClickMenu) {
                    this.$message({
                        type: "warning",
                        message: "请选择任务"
                    });
                    return;
                }
                var taskCode = window.localStorage.getItem("taskCode");
                var treeCheckList = this.$refs.tree.getCheckedNodes();
                var treeChecValueList = [];
                treeCheckList.forEach(item => {
                    treeChecValueList.push(item.value);
                });
                treeChecValueList = treeChecValueList.join(","); // 数组转字符串, 以 "," 隔开
                var entity = {
                    taskCode: taskCode,
                    objRlCode: treeChecValueList
                };
                this.isClickMenu = false;
                const res = await this.$ajax.post(`inspectTaskObj/save`, {
                    entity
                });

                if (res.data.isSuccess) {
                    this.$message({
                        type: "success",
                        message: res.data.message
                    })
                }
            },
            // 获取任务的检查事项
            async handleClickMenu(item, index) {
                this.isClickMenu = true;
                // this.$refs.menuItem.addClass = "active"
                this.active = index;
                var taskCode = item.taskCode;
                window.localStorage.setItem("taskCode", item.taskCode);
                var entity = {};
                const res = await this.$ajax.post(`inspectObjectRule/taskObjRule`, {
                    taskCode: taskCode,
                    entity
                });
                if (res.data.isSuccess) {
                    const result = res.data.result;
                    this.$refs.tree.setCheckedNodes(result);
                }
            },
            // 新建任务ajax
            async addTaskDialog() {
                var entity = {
                    templateCode: this.templateValue,
                    taskName: this.addTaskProps.taskName,
                    describe: this.addTaskProps.describe
                };
                const res = await this.$ajax.post(`inspectTask/create`, {
                    entity
                });
                if (res.data.isSuccess) {
                    this.$message({
                        type: "success",
                        message: res.data.message
                    });
                    this.addTaskDialogVisible = false;
                    // this.isNewAddTask = true;
                    window.localStorage.setItem("templateCodeTask", this.templateValue);
                    this.getTaskList();
                    this.getTreeRule();
                }
            },
            // 新建任务
            addTask() {
                this.addTaskDialogVisible = true;
            },
            // 选模板下拉框来获取任务列表
            async templateChangeTask() {
                var templateCode = this.templateValue;
                window.localStorage.setItem("templateCodeTask", this.templateValue);
                this.getTaskList();
                this.getTreeRule();
            },
            // 获取任务列表
            async getTaskList() {
                var entity = {
                    templateCode: this.templateValue
                };
                const res = await this.$ajax.post(`inspectTask/list`, {
                    entity
                });
                this.taskList = res.data.result;
            },
            // 获取树形检查事项
            async getTreeRule() {
                var entity = {
                    templateCode: this.templateValue
                };
                const res = await this.$ajax.post(`inspectObjectRule/cascader`, {
                    entity
                });
                this.treeList = [res.data.result];
            },
            // 获取模板列表
            async getTemplateList() {
                const res = await this.$ajax.post(`inspectTemplate/list`, {
                    entity: {}
                });
                this.templateOptions = res.data.result; // 模板列表数据
                this.templateValue =
                    window.localStorage.getItem("templateCodeTask") ||
                    res.data.result[0].templateCode;
                this.getTaskList();
                this.getTreeRule();
            }
        }
    };
</script>

<style scoped>
    .root {
        border: 0 !important;
        margin-top: 0 !important;
        padding: 10px;
        height: calc(100vh - 142px);
    }

    .root /deep/ .el-dialog__header {
        font-weight: 700;
        text-align: center;
        padding: 20px 20px 10px;
        text-align: center;
    }

    .el-container {
        height: 100%;
    }

    .el-aside {
        height: 100%;
        border-right: 5px solid #f2f2f2;
    }

    .root /deep/ .el-dialog__header .el-dialog__title {
        line-height: 24px;
        font-size: 18px;
        color: #606266;
        font-weight: 700;
    }

    .el-menu {
        border: 1px solid #ccc;
        background-color: #666666;
        width: 260px;
    }

    .root /deep/ .el-menu-item {
        border-bottom: 1px dashed #ccc;
        display: flex;
        align-items: center;
        padding: 0 !important;
        color: #ffffff;
        background-color: #666666;
        justify-content: space-between;
    }

    .root /deep/ .el-menu-item:hover {
        background-color: #525252;
    }

    .addclass {
        /* color: #409EFF; */
        color: #ffc64f !important;
    }

    .templateBox>span {
        font-size: 18px;
    }

    .el-button--small,
    .el-button--small.is-round {
        padding: 8px 12px;
    }

    .el-menu-item:last-child {
        border-bottom: 0px;
    }

    .templateBox {
        margin-bottom: 20px;
    }

    .el-main {
        padding: 0 0 0 30px;
    }

    .el-select {
        width: 110px;
        margin-left: 3%;
        margin-right: 5%;
    }
</style>