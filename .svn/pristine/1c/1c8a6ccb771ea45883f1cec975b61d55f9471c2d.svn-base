<template>
    <div class="not-position-notPaging_root">
        <div class="not-position_header">
            <el-button type="primary" size="small" icon="el-icon-plus" @click="addTask()">
                检查清单
            </el-button>
            <el-button icon="el-icon-setting" style="margin-left: 20px" type="info" @click="templateManager" size="small">
                检查项目
            </el-button>
        </div>
        <el-container class="Taskmain">
            <el-aside width="320px">
                <div class="templateBox">
                    <span class="span_type">类型: </span>
                    <el-select class="inspectTypeSelect" size="mini" v-model="inspectTypeValue" @change="inspectTypeSelect" placeholder="请选择">
                        <el-option v-for="item in inspectTypeOption" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                    <div>
                        <span>项目: </span>
                        <el-select size="mini" @click.native="getNewTemplate()" @change="templateChangeTask()" v-model="templateValue" placeholder="请选择">
                            <el-option v-for="item in templateOptions" :key="item.templateCode" :label="item.templateName" :value="item.templateCode">
                            </el-option>
                        </el-select>
                    </div>
                </div>
                <el-menu default-active="1" class="el-menu-vertical-demo">
                    <el-menu-item index="1" :class="active == index ? 'menu_item_active' : '' " v-for="(item, index) in taskList" @mouseenter.native="menuList_itemEnter(item.id)" @mouseleave.native="menuList_itemLeave()" @click="handleClickMenu(item, index)" :key="index">
                        <span>{{item.taskName}}</span>
                        <div class="btnBox">
                            <i v-if="showBtn == item.id" @click="editTask(item)" class="el-icon-edit"></i>
                            <i v-if="(deleteInspectTask || item.createCode == userCode) && showBtn == item.id" @click.stop="delTask(item)" class="el-icon-delete"></i>
                        </div>

                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <div class="ruleBtnBox" style="margin-bottom: 30px;">
                    <el-button type="primary" size="small" icon="el-icon-plus" @click="preserve()">保存</el-button>
                </div>
                <el-tree :data="treeList" highlight-current :default-expand-all="true" ref="tree" @check="treeClick" show-checkbox node-key="id" :expand-on-click-node="true" :props="defaultProps">
                </el-tree>
            </el-main>
        </el-container>
        <!-- 新增任务弹出框 -->
        <el-dialog title="新建检查清单" :visible.sync="addTaskDialogVisible" width="40%">
            <el-form label-position="right" :rules="rules" label-width="80px" ref="addTaskProps" :model="addTaskProps">
                <el-form-item label="选择项目" prop="addInspectTypeValue">
                    <el-select v-model="addInspectTypeValue" @change="addInspectTypeValueSelect" placeholder="请选择">
                        <el-option v-for="item in addInspectTypeOption" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="选择项目" prop="addTemplateValue">
                    <el-select @change="addTemplateChangeTaskSelect()" v-model="addTemplateValue" placeholder="请选择">
                        <el-option v-for="item in addTemplateOptions" :key="item.templateCode" :label="item.templateName" :value="item.templateCode">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="名称" prop="taskName">
                    <el-input v-model="addTaskProps.taskName"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="describe">
                    <el-input v-model="addTaskProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addTaskDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addTaskDialog()">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 修改模板弹出框 -->
        <el-dialog title="修改检查清单" :visible.sync="editTaskDialogVisible" width="40%">
            <el-form label-position="right" :rules="rules" label-width="80px" ref="editTaskProps" :model="editTaskProps">
                <!-- <el-form-item label="选择项目" prop="templateValue">
                    <el-select @change="templateChangeTask()" v-model="templateValue" placeholder="请选择">
                        <el-option v-for="item in templateOptions" :key="item.templateCode" :label="item.templateName" :value="item.templateCode">
                        </el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item label="名称" prop="taskName">
                    <el-input v-model="editTaskProps.taskName"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="describe">
                    <el-input v-model="editTaskProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editTaskDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editTaskDialog()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        props: ["paramsObj"],
        data() {
            return {
                deleteInspectTask: false,
                userCode: '',

                addTemplateOptions: [],
                addTemplateValue: '',
                isAddTemplate: false,
                isAddInspectTypeSelect: false,
                addInspectTypeOption: [
                    {
                        value: "1",
                        label: '卫生检查'
                    },
                    {
                    value: "2",
                    label: '安全巡检'
                    }
                ],
                addInspectTypeValue: "",
                inspectTypeOption: [{
                        value: "1",
                        label: '卫生检查'
                    },
                    {
                        value: "2",
                        label: '安全巡检'
                    }
                ],
                // inspectType: 1,
                inspectTypeValue: '卫生检查',
                inspectType: '',
                addTaskDialogVisible: false,
                editTaskDialogVisible: false,
                templateOptions: [],
                templateValue: "",
                taskList: [],
                addTaskProps: {
                    taskName: "",
                    describe: ""
                },
                editTaskProps: {
                    taskName: "",
                    describe: "",
                    id: "",
                    taskCode: ""
                },
                treeList: [],
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                nodeObj: '',
                isClickMenu: false,
                active: -1,
                showBtn: false,
                // isNewAddTask: false,
                rules: {
                    taskName: [{
                        required: true,
                        message: "检查单名称不能为空",
                        trigger: "blur"
                    }]
                    // describe: [{
                    //     required: true,
                    //     message: '描述不能为空',
                    //     trigger: 'blur'
                    // }],
                }
            };
        },
        created() {
            this.userCode = this.$cookieStore.getCookie("userCode");
            this.deleteInspectTask = this.$flagMenuStore.judgeMenu("deleteInspectTask");

            this.inspectType = this.paramsObj.inspectType;
            if (this.inspectType == 1) {
                this.inspectTypeValue = "卫生检查";
                this.addInspectTypeValue = "卫生检查"
            } else if (this.inspectType == 2) {
                this.inspectTypeValue = "安全巡检";
                this.addInspectTypeValue = "安全巡检"
            }
            this.getTemplateList();
        },
        methods: {
            // 点击项目select框重新获取template
            async getNewTemplate() {
                var entity = {
                    inspectType: this.inspectType
                };
                const res = await this.$ajax.post(`inspectTemplate/list`, {entity});
                this.templateOptions = res.data.result; // 模板列表数据
            },
            treeClick(node) {
                this.nodeObj = node;
            },
            // 新增任务
            addTemplateChangeTaskSelect() {
                this.isAddTemplate = true;
            },
            // 新增任务的select框
            async addInspectTypeValueSelect() {
                var entity = {
                    inspectType: this.addInspectTypeValue
                };
                const res = await this.$ajax.post(`inspectTemplate/list`, {entity});
                this.addTemplateOptions = res.data.result; // 模板列表数据
                if (this.addTemplateOptions.length > 0) {
                    this.addTemplateValue =  this.addTemplateOptions[0].templateName;
                } else {
                    this.addTemplateValue = '';
                }
                this.isAddInspectTypeSelect = true;
            },
            // 检查类型选择
            inspectTypeSelect() {
                this.inspectType = this.inspectTypeValue;
                this.addInspectTypeValue = this.inspectTypeValue;
                this.active = -1;
                this.getTemplateList();
            },
            // 修改任务模态框
            editTaskDialog() {
                this.$refs.editTaskProps.validate(async valid => {
                    if (valid) {
                        var entity = this.editTaskProps;
                        entity.inspectType = this.inspectType;
                        const res = await this.$ajax.post(`inspectTask/update`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.editTaskDialogVisible = false;
                            this.$message({
                                type: "success",
                                message: res.data.message
                            });
                            this.getTaskList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 修改任务按钮
            editTask(item) {
                this.editTaskProps.taskName = item.taskName;
                this.editTaskProps.describe = item.describe;
                this.editTaskProps.id = item.id;
                this.editTaskProps.taskCode = item.taskCode;
                this.editTaskDialogVisible = true;
            },
            // 删除任务
            delTask(item) {
                var entity = {
                    id: item.id,
                    taskCode: item.taskCode
                };
                this.$confirm("此操作将永久删除该模板, 是否继续?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                    .then(async () => {
                        const res = await this.$ajax.post(`inspectTask/delete`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            });
                            this.active = -1;
                            this.getTaskList();
                        }
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除"
                        });
                    });
            },
            // 鼠标移出事件隐藏删除按钮
            menuList_itemLeave() {
                this.showBtn = false;
            },
            // 鼠标移入事件显示删除按钮
            menuList_itemEnter(id) {
                this.showBtn = id;
            },
            // 保存按钮
            async preserve() {
                if (this.active < 0) {
                    this.$message({
                        type: "warning",
                        message: "请选择左边检查单"
                    });
                    return;
                }
                // if (this.nodeObj.type === "1" && this.nodeObj.children === null) {
                //     this.$message({
                //         type: "warning",
                //         message: "请在检查项目管理处新建节点"
                //     })
                //     return;
                // }
                var treeCheckList = this.$refs.tree.getCheckedNodes();
                var treeHalfCheckList = this.$refs.tree.getHalfCheckedNodes();
                var treeChecValueList = [];
                var treeHalfChecValueList = [];
               
                treeHalfCheckList.forEach(item => {
                    treeHalfChecValueList.push(item.value);
                });
                treeCheckList.forEach(item => {
                    treeChecValueList.push(item.value);
                });
               
                var treeValueList = treeHalfChecValueList.concat(treeChecValueList);
                treeValueList = treeValueList.join(","); // 数组转字符串, 以 "," 隔开
                var entity = {
                    taskCode: this.taskCode,
                    objRlCode: treeValueList
                };
                const res = await this.$ajax.post(`inspectTaskObj/save`, {
                    entity
                });
                if (res.data.isSuccess) {
                    this.$message({
                        type: "success",
                        message: res.data.message
                    });
                }
            },
            // 新建任务ajax
            async addTaskDialog() {
                this.$refs.addTaskProps.validate(async valid => {
                    if (valid) {
                        var entity = {
                            taskName: this.addTaskProps.taskName,
                            describe: this.addTaskProps.describe,
                        };
                        if (this.isAddInspectTypeSelect) {
                            entity.inspectType = this.addInspectTypeValue;
                            this.inspectType = this.addInspectTypeValue;
                            this.active = -1;
                        } else {
                            entity.inspectType = this.inspectType;
                            this.active = 0;
                        }
                        if (this.isAddTemplate) {
                            entity.templateCode = this.addTemplateValue;
                            this.templateValue = this.addTemplateValue;
                        } else {
                            entity.templateCode = this.templateValue;
                            this.templateValue = this.templateValue;
                        }
                        const res = await this.$ajax.post(`inspectTask/create`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            });
                            this.addTaskDialogVisible = false;
                            var entity = {
                                inspectType: this.inspectType
                            };
                            const res1 = await this.$ajax.post(`inspectTemplate/list`, {entity});
                            this.templateOptions = res1.data.result; // 模板列表数据
                            this.inspectTypeValue = this.inspectType;
                            this.getTaskList();
                            this.getTreeList();
                            this.$refs.addTaskProps.resetFields();
                            this.active = -1;
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 新建任务
            addTask() {
                this.addTemplateOptions = this.templateOptions;
                this.addTemplateValue = this.templateValue;
                this.addTaskDialogVisible = true;
            },
            // 选模板下拉框来获取任务列表
            async templateChangeTask() {
                this.active = -1;
                this.isClickMenu = false;
                this.getTaskList();
                this.getTreeList();
            },
            // 点击菜单
            async handleClickMenu(item, index) {
                this.isClickMenu = true;
                this.active = index;
                this.taskCode = item.taskCode
                this.getTreeList();
            },
            // 获取任务的检查事项
            async getTaskRule() {
                if (this.taskCode != null) {
                    var entity = {};
                    const res = await this.$ajax.post(`inspectObjectRule/taskObjRule`, {
                        taskCode: this.taskCode,
                        entity
                    });
                    if (res.data.isSuccess) {
                        var result = res.data.result;
                        let resData = [];
                        for (let i = 0; i < result.length; i++) {
                            let flag = true;
                            for (let j = 0; j < result.length; j++) {
                                if (result[i].objRlCode == result[j].parentCode) {
                                    flag = false;
                                    break;
                                }
                            }
                            if(flag){
                                resData.push(result[i]);
                            }
                        }

                        result = resData;
                        this.$refs.tree.setCheckedNodes(result);
                    }
                } else {
                    return;
                }
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
            // 获取树形列表
            async getTreeList() {
                var entity = {
                    templateCode: this.templateValue
                };
                const res = await this.$ajax.post(`inspectObjectRule/cascader`, {
                    entity
                });
                this.treeList = [res.data.result];
                this.getTaskRule();
            },
            // 获取模板列表
            async getTemplateList() {
                var entity = {
                    inspectType: this.inspectType
                };
                const res = await this.$ajax.post(`inspectTemplate/list`, {entity});
                this.templateOptions = res.data.result; // 模板列表数据
                if (this.templateOptions.length > 0) {
                    if(this.paramsObj.templateCode){
                        this.templateValue = this.paramsObj.templateCode;
                    }else if (this.active < 0) {
                        this.templateValue = res.data.result[0].templateCode;
                    } else {
                        this.templateValue = res.data.result[this.active].templateCode;
                    }
                } else {
                    this.taskList = [];
                    return;
                }
                this.getTaskList();
                this.getTreeList();
                this.getTaskRule();
            },
            //模板管理
            templateManager(){
                let vm = this;
                let data = {
                    menuName: '检查项目管理',
                    item: 'proTemplateManager',
                    params: {
                        inspectType:this.inspectType,
                    }
                };
                vm.$emit('backParam', data);
            },
        }
    };
</script>

<style scoped>

    .not-position_header {
        border-bottom: 1px solid #f2f2f2;
        padding-left: 10px;
    }

    .Taskmain {
        margin-top: 10px;
        background-color: #fff;
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
        padding-left: 10px;
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

    .menu_item_active {
        color: #ffc64f !important;
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

    .templateBox .el-select {
        /* width: 110px; */
        margin-left: 3%;
        margin-right: 5%;
    }

    .ruleBtnBox {
        margin-top: 2px;
    }
</style>