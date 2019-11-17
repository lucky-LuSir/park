<template>
    <div class="not-position-notPaging_root">
        <div class="not-position_header">
            <el-button type="primary" size="small" icon="el-icon-plus" @click="addTemplate()">
                新增项目
            </el-button>
        </div>
        <el-container class="Taskmain">
            <el-aside width="230px">
                <div class="templateBox">
                    <span class="span_type">类型:</span>
                    <el-select class="inspectTypeSelect" size="mini" v-model="inspectTypeValue" @change="inspectTypeSelect"  placeholder="请选择">
                        <el-option
                        v-for="item in inspectTypeOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </div>
                <el-menu default-active="1" class="el-menu-vertical-demo">
                    <el-menu-item index="1" :class="active == index ? 'menu_item_active' : '' " v-for="(item, index) in menuList" @mouseenter.native="menuList_itemEnter(item)" @mouseleave.native="menuList_itemLeave()" @click="handleClickMenu(item, index)" :key="index">
                        <span style="width: 160px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap" :title="item.templateName">{{item.templateName}}</span>
                        <div class="btnBox">
                            <i title="检查清单" v-if="showBtn == item.id" @click="proTaskManager(item)" class="el-icon-s-order"></i>
                            <i title="编辑" v-if="showBtn == item.id" @click="editTemplate(item)" class="el-icon-edit"></i>
                            <i title="删除" v-if="(deleteInspectTemplate || item.createCode == userCode)  && showBtn == item.id " @click.stop="delTemplate(item)" class="el-icon-delete"></i>
                        </div>
                        <!-- <div class="descBox">
                            <span v-if="isShowDescBox">描述: {{desc}}</span>
                        </div> -->
                    </el-menu-item>
                </el-menu>

            </el-aside>
            <el-main>
                <div class="ruleBtnBox" style="margin-bottom: 30px;">
                    <el-button v-if="isTypeFenlei" type="primary" size="small" @click="addTreeRule()">新增检查项分类</el-button>
                    <el-button v-if="isTypeShixiang" type="primary" size="small" @click="addTreeRule()">新增检查项</el-button>
                    <el-button type="primary" size="small" @click="editTreeRule()">修改</el-button>
                    <el-button type="primary" size="small" @click="delTreeRule()">删除</el-button>

                </div>
                <el-tree :data="treeList" node-key="id" @node-click="treeClick" @click="() => remove(node, data)" :expand-on-click-node="false" highlight-current :default-expand-all="true" :props="defaultProps">
                </el-tree>

            </el-main>
        </el-container>
        <!-- 新增模板弹出框 -->
        <el-dialog title="新增项目" :visible.sync="addTemplateDialogVisible" width="40%">
            <el-form label-position="right" :rules="rules" label-width="80px" ref="addTemplateProps" :model="addTemplateProps">
                <el-form-item label="检查类型" prop="addInspectTypeValue">
                    <el-select v-model="addInspectTypeValue" @change="addInspectTypeValueSelect"  placeholder="请选择">
                        <el-option
                        v-for="item in addInspectTypeOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="项目名称" prop="templateName">
                    <el-input v-model="addTemplateProps.templateName"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="describe">
                    <el-input v-model="addTemplateProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addTemplateDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addTemplateDialog()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 修改模板弹出框 -->
        <el-dialog title="修改项目" :visible.sync="editTemplateDialogVisible" width="40%">
            <el-form label-position="right" ref="editTemplateProps" :rules="rules" label-width="80px" :model="editTemplateProps">
                <el-form-item label="项目名称" prop="templateName">
                    <el-input v-model="editTemplateProps.templateName"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="describe">
                    <el-input v-model="editTemplateProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editTemplateDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editTemplateDialog()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 新建模板分类节点弹出框 -->
        <el-dialog title="新增检查项分类" :visible.sync="addTreeRuleFenleiDialogVisible" width="40%" @close="closeAddTreeRuleDialogVisible()">
            <el-form label-position="right" :rules="rules" label-width="80px" ref="addTreeRuleFormFenlei" :model="addTreeRuleProps">
                <el-form-item label="上级节点" prop="parentValue">
                    <el-select v-model="addTreeRuleProps.parentValue" placeholder="请选择">
                        <el-option v-for="item in parentValueOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="名称" prop="objRlName">
                    <el-input v-model="addTreeRuleProps.objRlName"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="describe">
                    <el-input v-model="addTreeRuleProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="closeAddTreeRuleDialogVisible()">取 消</el-button>
                <el-button type="primary" @click="addTreeRuleFenleiDialog()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 新建模板事项节点弹出框 -->
        <el-dialog title="新增检查项" :visible.sync="addTreeRuleShixiangDialogVisible" width="40%" @close="closeAddTreeRuleDialogVisible()">
            <el-form label-position="right" :rules="rules" label-width="80px" ref="addTreeRuleFormShixiang" :model="addTreeRuleProps">
                <el-form-item label="上级节点" prop="parentValue">
                    <el-select @change="parentValueChange()" v-model="addTreeRuleProps.parentValue" placeholder="请选择">
                        <el-option v-for="item in parentValueOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="名称" prop="objRlName">
                    <el-input v-model="addTreeRuleProps.objRlName"></el-input>
                </el-form-item>
                <el-form-item label="分数" prop="grade">
                    <el-input v-model="addTreeRuleProps.grade"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="describe">
                    <el-input v-model="addTreeRuleProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="closeAddTreeRuleDialogVisible()">取 消</el-button>
                <el-button type="primary" @click="addTreeRuleShixiangDialog()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 修改模板分类节点弹出框 -->
        <el-dialog title="修改检查项分类" :visible.sync="editTreeRuleFenleiDialogVisible" width="40%" @close="closeEditTreeRuleDialogVisible()">
            <el-form label-position="right" ref="editTreeRuleProps" :rules="rules" label-width="80px" :model="editTreeRuleProps">
                <el-form-item label="名称" prop="objRlName">
                    <el-input v-model="editTreeRuleProps.objRlName"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="describe">
                    <el-input v-model="editTreeRuleProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editTreeRuleFenleiDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editTreeRuleFenleiDialog()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 修改模板事项节点弹出框 -->
        <el-dialog title="修改检查项" :visible.sync="editTreeRuleShixiangDialogVisible" width="40%" @close="closeEditTreeRuleDialogVisible()">
            <el-form label-position="right" ref="editTreeRuleProps" :rules="rules" label-width="80px" :model="editTreeRuleProps">
                <el-form-item label="名称" prop="objRlName">
                    <el-input v-model="editTreeRuleProps.objRlName"></el-input>
                </el-form-item>
                <el-form-item label="分数" prop="grade">
                    <el-input v-model="editTreeRuleProps.grade"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="describe">
                    <el-input v-model="editTreeRuleProps.describe"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editTreeRuleShixiangDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="editTreeRuleShixiangDialog()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        props: ["paramsObj"],
        data() {
            return {
                userCode: '',
                deleteInspectTemplate: false,
                isAddSelect: false,
                inspectTypeOption: [
                    {
                    value: '1',
                    label: '卫生检查'
                    },
                    {
                    value: '2',
                    label: '安全巡检'
                    }
                ],
                inspectType: 1,
                inspectTypeValue: '卫生检查',
                isShowDescBox: false,
                desc: '',
                isTypeFenlei: true, // 是否是第一级节点
                isTypeShixiang: false,
                parentValueOptions: [],
                templateCode: "", // 模板编号
                radio: '1',
                menuList: [],
                addTemplateDialogVisible: false,
                editTemplateDialogVisible: false,
                addTreeRuleFenleiDialogVisible: false, // 分类模态框
                addTreeRuleShixiangDialogVisible: false, // 事项模态框
                editTreeRuleFenleiDialogVisible: false, // 修改分类节点
                editTreeRuleShixiangDialogVisible: false, // 修改事项节点
                type: '',
                addTemplateProps: {
                    templateName: '',
                    describe: '',
                },
                editTemplateProps: {
                    templateName: '',
                    describe: '',
                    id: '',
                    templateCode: '',
                },
                showBtn: false, // 展示删除菜单栏按钮
                treeList: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                addTreeRuleProps: {
                    parentValue: "", // 父节点名称
                    objRlName: '',
                    type: '',
                    parentCode: '', // 父节点编号
                    grade: '',
                    describe: '',
                },
                isClickTree: false, // 用户必须先点击树节点再点击新增模板
                editTreeRuleProps: {
                    objRlName: '',
                    type: '',
                    parentCode: '', // 父节点编号
                    grade: '',
                    describe: '',
                    id: '', // 节点id
                },
                parentTreeRuleProps: {
                    objRlCode: '', // 父节点编号
                },
                nodeObj: {}, // 点击该节点的所有数据
                isNewAddMenu: false, // 是否是新建的菜单
                active: -1,
                level: 0,
                id: "",
                rules: {
                    templateName: [{
                        required: true,
                        message: '模板名称不能为空',
                        trigger: 'blur'
                    }],
                    objRlName: [{
                        required: true,
                        message: '节点名称不能为空',
                        trigger: 'blur'
                    }],
                    grade: [{
                            required: true,
                            message: '分数不能为空',
                            trigger: 'change'
                        },
                        {
                            pattern: /^[0-9]+?$/,
                            message: '分数必须为非负整数',
                            trigger: 'change'
                        }
                    ],
                    // describe: [{
                    //     required: true,
                    //     message: '描述不能为空',
                    //     trigger: 'blur'
                    // }],
                },
                addInspectTypeValue: "",
                addInspectTypeOption: [
                    {
                        value: '1',
                        label: '卫生检查'
                    },
                    {
                    value: '2',
                    label: '安全巡检'
                    }
                ],
            }
        },
        created() {
            this.userCode = this.$cookieStore.getCookie("userCode");
            this.deleteInspectTemplate = this.$flagMenuStore.judgeMenu("deleteInspectTemplate");
            this.inspectType = this.paramsObj.inspectType;
            if (this.inspectType == 1) {
                this.inspectTypeValue = "卫生检查";
                this.addInspectTypeValue = "卫生检查"
            } else if (this.inspectType == 2) {
                this.inspectTypeValue = "安全巡检";
                this.addInspectTypeValue = "安全巡检"

            }
            this.getMenuList();
        },
        // mounted()
        methods: {
            // 删除模板节点按钮
            delTreeRule() {
                if (!this.isClickTree) {
                    this.$message({
                        type: "warning",
                        message: "请选择节点",
                        showClose: true
                    })
                    return;
                }
                if (this.nodeObj.level === 1) {
                    this.$message({
                        type: "warning",
                        message: "您不能删除一级节点",
                        showClose: true
                    })
                    return;
                }
                var entity = {
                    id: this.id,
                    objRlCode: this.parentTreeRuleProps.objRlCode
                }
                this.$confirm('此操作将永久删除该项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const res = await this.$ajax.post(`inspectObjectRule/delete`, {
                        entity
                    });
                    if (res.data.isSuccess) {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getMenuList();
                        this.isClickTree = false;
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除',
                        showClose: true,
                    });
                });
            },
            // 修改模板分类节点模态框ajax
            editTreeRuleFenleiDialog() {
                var entity = {
                    objRlName: this.editTreeRuleProps.objRlName,
                    type: this.editTreeRuleProps.type,
                    parentCode: this.editTreeRuleProps.parentCode,
                    describe: this.editTreeRuleProps.describe,
                    templateCode: this.templateCode,
                    id: this.editTreeRuleProps.id,
                }
                this.$refs.editTreeRuleProps.validate(async (valid) => {
                    if (valid) {
                        const res = await this.$ajax.post(`inspectObjectRule/update`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            })
                            this.editTreeRuleFenleiDialogVisible = false;
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 修改模板事项节点模态框ajax
            editTreeRuleShixiangDialog() {
                var entity = {
                    objRlName: this.editTreeRuleProps.objRlName,
                    type: this.editTreeRuleProps.type,
                    parentCode: this.editTreeRuleProps.parentCode,
                    grade: this.editTreeRuleProps.grade,
                    describe: this.editTreeRuleProps.describe,
                    templateCode: this.templateCode,
                    id: this.editTreeRuleProps.id,
                }
                this.$refs.editTreeRuleProps.validate(async (valid) => {
                    if (valid) {
                        const res = await this.$ajax.post(`inspectObjectRule/update`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            })
                            this.editTreeRuleShixiangDialogVisible = false;
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 修改模板节点按钮
            async editTreeRule() {
                if (!this.isClickTree) {
                    this.$message({
                        type: "warning",
                        message: "请选择节点",
                        showClose: true
                    })
                    return;
                }
                var entity = {
                    objRlCode: this.parentTreeRuleProps.objRlCode
                }
                const res = await this.$ajax.post(`inspectObjectRule/list`, {
                    entity
                });
                let result = res.data.result;
                this.editTreeRuleProps = result[0]; // 获取到当前节点的所有数据 来回显
                var BrotherNodes = this.nodeObj.parent.childNodes; // 获取到所有的兄弟节点
                BrotherNodes.map((item) => {
                    var obj = {
                        label: item.label,
                        value: item.id
                    }
                    this.parentValueOptions.push(obj); // 给父级节点数组赋值
                })
                if (this.level === 1) {
                    this.editTreeRuleProps.type = "1";
                    this.editTreeRuleFenleiDialogVisible = true;
                } else if (this.level === 2) {
                    this.editTreeRuleProps.type = "1";
                    this.editTreeRuleFenleiDialogVisible = true;

                } else if (this.level === 3) {
                    this.editTreeRuleProps.type = "2";
                    this.editTreeRuleShixiangDialogVisible = true;
                }
            },
            // 关闭修改模板节点模态框
            closeEditTreeRuleDialogVisible() {
                this.editTreeRuleFenleiDialogVisible = false;
                this.editTreeRuleShixiangDialogVisible = false;
                this.parentValueOptions = [];
            },
            // 父节点发生改变
            parentValueChange() {
                var treeListObj = this.treeList;
                var arrList = treeListObj[0].children; // 只会遍历第二层的节点, 第一层和第三层是固定的
                var arr = arrList.filter(item => item.$treeNodeId === this.addTreeRuleProps.parentValue);
                arr = arr[0];
                this.addTreeRuleProps.parentCode = arr.value;
            },
            // 新建模板分类节点模态框ajax
            addTreeRuleFenleiDialog() {
                var entity = {
                    objRlName: this.addTreeRuleProps.objRlName,
                    type: this.addTreeRuleProps.type,
                    parentCode: this.addTreeRuleProps.parentCode,
                    describe: this.addTreeRuleProps.describe,
                    templateCode: this.templateCode
                };
                this.$refs.addTreeRuleFormFenlei.validate(async (valid) => {
                    if (valid) {
                        const res = await this.$ajax.post(`inspectObjectRule/create`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            })
                            this.$refs.addTreeRuleFormFenlei.resetFields();
                            this.addTreeRuleFenleiDialogVisible = false;
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 新建模板事项节点模态框ajax
            addTreeRuleShixiangDialog() {
                var entity = {
                    objRlName: this.addTreeRuleProps.objRlName,
                    type: this.addTreeRuleProps.type,
                    parentCode: this.addTreeRuleProps.parentCode,
                    grade: this.addTreeRuleProps.grade,
                    describe: this.addTreeRuleProps.describe,
                    templateCode: this.templateCode
                };
                this.$refs.addTreeRuleFormShixiang.validate(async (valid) => {
                    if (valid) {
                        const res = await this.$ajax.post(`inspectObjectRule/create`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.$message({
                                type: "success",
                                message: res.data.message
                            })
                            this.$refs.addTreeRuleFormShixiang.resetFields();
                            this.addTreeRuleShixiangDialogVisible = false;
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 关闭新建模板节点模态框
            closeAddTreeRuleDialogVisible() {
                this.addTreeRuleFenleiDialogVisible = false;
                this.addTreeRuleShixiangDialogVisible = false;
                this.parentValueOptions = [];
            },
            // 新建模板节点按钮
            addTreeRule() {
                if (!this.isClickTree) {
                    this.$message({
                        type: "warning",
                        message: "请选择节点",
                        showClose: true
                    })
                    return;
                }
                if (this.nodeObj.level >= 3) {
                    this.$message({
                        type: "warning",
                        message: "只能新建三层节点",
                        showClose: true
                    })
                    return;
                }
                var BrotherNodes = this.nodeObj.parent.childNodes; // 获取到所有的兄弟节点
                BrotherNodes.map((item) => {
                    var obj = {
                        label: item.label,
                        value: item.id
                    }

                    this.parentValueOptions.push(obj); // 给父级节点数组赋值
                })
                this.addTreeRuleProps.parentValue = this.nodeObj.label;
                if (this.level === 1) {
                    this.addTreeRuleProps.type = "1";
                    this.addTreeRuleFenleiDialogVisible = true;
                } else if (this.level === 2) {
                    this.addTreeRuleProps.type = "2";
                    this.addTreeRuleShixiangDialogVisible = true;
                }
            },
            // 点击tree节点
            treeClick(currentVal, node) {
                if (node.level === 3) {
                    this.isTypeFenlei = false;
                    this.isTypeShixiang = false;
                    this.type = "2";
                    this.level = 3;

                } else if (node.level === 1) {
                    this.isTypeFenlei = true;
                    this.isTypeShixiang = false;
                    this.type = "1";
                    this.level = 1;

                } else if (node.level === 2) {
                    this.isTypeFenlei = false;
                    this.isTypeShixiang = true;
                    this.level = 2;
                }
                this.nodeObj = node;
                this.id = currentVal.id
                this.addTreeRuleProps.parentCode = currentVal.value; // 父节点编号
                this.parentTreeRuleProps.objRlCode = currentVal.value; // 父节点编号
                this.isClickTree = true;
            },
            // ================================================下是模板, 上是节点====================================
            // 修改模板ajax
            editTemplateDialog() {
                this.$refs.editTemplateProps.validate(async (valid) => {
                    if (valid) {
                        var entity = this.editTemplateProps;
                        entity.inspectType = this.inspectType;
                        const res = await this.$ajax.post(`inspectTemplate/update`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.editTemplateDialogVisible = false;
                            this.$message({
                                type: 'success',
                                message: res.data.message
                            })
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 修改模板弹出框
            editTemplate(item) {
                this.editTemplateProps.templateName = item.templateName;
                this.editTemplateProps.describe = item.describe;
                this.editTemplateProps.id = item.id;
                this.editTemplateProps.templateCode = item.templateCode;
                this.editTemplateDialogVisible = true;
            },
            // 输入移入事件隐藏删除按钮
            menuList_itemLeave() {
                this.showBtn = false;
                // this.isShowDescBox = false;
            },
            // 输入移入事件显示删除按钮
            menuList_itemEnter(item) {
                this.desc = item.describe;
                this.showBtn = item.id;
                this.isShowDescBox = true;
            },
            // 删除模板
            delTemplate(item) {
                var entity = {
                    id: item.id,
                    templateCode: item.templateCode,
                    inspectType: this.inspectType
                }
                this.$confirm('此操作将永久删除该项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const res = await this.$ajax.post(`inspectTemplate/delete`, {
                        entity
                    });
                    if (res.data.isSuccess) {
                        this.$message({
                            type: 'success',
                            message: '项目删除成功!'
                        });
                        this.active--;
                        this.getMenuList();
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 新增项目的select框
            addInspectTypeValueSelect() {
                this.isAddSelect = true;
            },
            // 检查类型选择
            inspectTypeSelect() {
                this.inspectType = this.inspectTypeValue;
                this.addInspectTypeValue = this.inspectTypeValue;
                this.active = -1;
                this.getMenuList();
            },
            // 新建模板ajax
            async addTemplateDialog() {
                this.$refs.addTemplateProps.validate(async (valid) => {
                    if (valid) {
                        var entity = {};
                        if (this.isAddSelect) {
                            entity = {
                                templateName: this.addTemplateProps.templateName,
                                describe: this.addTemplateProps.describe,
                                inspectType: this.addInspectTypeValue
                            }
                            this.inspectType = this.addInspectTypeValue;
                            
                            this.active = -1;
                        } else {
                            entity = {
                                templateName: this.addTemplateProps.templateName,
                                describe: this.addTemplateProps.describe,
                                inspectType: this.inspectType
                            }
                            this.inspectType = this.inspectType;
                            this.active = 0;
                        }

                        const res = await this.$ajax.post(`inspectTemplate/create`, {
                            entity
                        });
                        if (res.data.isSuccess) {
                            this.addTemplateDialogVisible = false;
                            this.inspectTypeValue = this.inspectType;
                            this.$message({
                                type: "success",
                                message: res.data.message
                            })
                            this.$refs.addTemplateProps.resetFields();
                            this.isNewAddMenu = true;
                            this.getMenuList();
                        }
                    } else {
                        return false;
                    }
                });
            },
            // 新建模板显示模态框
            addTemplate() {
                this.addTemplateDialogVisible = true;
            },
            // 点击菜单
            async handleClickMenu(item, index) {
                this.isShowDescBox = true;
                this.isClickTree = false; // 点击菜单就让节点是否点击取消
                this.active = index;
                this.getMenuList();
            },
            // 获取树形结构
            async getTreeRule(templateCode) {
                var entity = {
                    templateCode: templateCode
                }
                const res = await this.$ajax.post(`inspectObjectRule/cascader`, {
                    entity
                });
                this.treeList = [res.data.result];
            },
            // 获取菜单模板
            async getMenuList() {
                let entity = {
                    inspectType: this.inspectType
                }
                let res = await this.$ajax.post(`inspectTemplate/list`, {
                    entity
                });
                this.menuList = res.data.result; // 菜单列表

                if (res.data.result.length > 0) {
                    if (this.active < 0) {
                        this.templateCode = res.data.result[0].templateCode;
                    } else {
                        this.templateCode = res.data.result[this.active].templateCode;
                    }
                } else {
                    this.treeList = [];
                    return;
                }
                this.getTreeRule(this.templateCode); // 获取树形结构
            },
            //任务管理
            proTaskManager(item){
                let vm = this;
                let data = {
                    menuName: '检查清单管理',
                    item: 'proTaskManager',
                    params: {
                        inspectType: this.inspectType,
                        templateCode: item.templateCode,
                    }
                };
                vm.$emit('backParam', data);
            },
        }
    }
</script>

<style scoped>
    .root {
        border: 0 !important;
        margin-top: 0 !important;
        padding: 10px 0;
        background: rgb(242, 242, 242);
        height: calc(100vh - 142px);
    }
    .Taskmain {
        margin-top: 10px;
        background-color: #fff;
    }
    .not-position_header {
        border-bottom: 1px solid #f2f2f2;
        padding-left: 10px;
    }

    .root /deep/ .el-dialog__header {
        font-weight: 700;
        text-align: center;
        padding: 20px 20px 10px;
        text-align: center;
    }

   .inspectTypeSelect {
        width: 100px;
    }

    .el-container {
        height: 100%;
    }

    .el-aside {
        width: 280px!important;
        height: 100%;
        border-right: 5px solid #f2f2f2;
    }

    .span_type {
        margin-left: 10px;
        margin-right: 10px;
    }

    .el-menu {
        border: 1px solid #ccc;
        /* background-color: #666666; */
        width: 100%;
    }

    .root /deep/ .el-dialog__header .el-dialog__title {
        line-height: 24px;
        font-size: 18px;
        color: #606266;
        font-weight: 700;
    }

    .el-menu {
        border: 1px solid #ccc;
    }

    .root /deep/ .el-menu-item {
        border-bottom: 1px dashed #ccc;
        display: flex;
        align-items: center;
        /* padding: 0 !important; */
        justify-content: space-between;
        padding: 0 0 0 3px !important;
    }

    .el-button--small,
    .el-button--small.is-round {
        padding: 8px 12px;
    }

    .templateBox {
        margin-bottom: 20px;
    }

    .el-main {
        padding: 0 0 0 50px;
    }

    .root /deep/ .el-tree-node__content:hover {
        background-color: #ecf5ff !important;
    }

    .root /deep/ .el-tree--highlight-current .el-tree-node.is-current>.el-tree-node__content {
        background-color: #e7eaec;
        color: #abbf20;
    }

    .menu_item_active {
        color: #ffc64f !important;
    }

    .templateBox {
        position: relative;
    }


    .descBox {
        position: absolute;
        background-color: #303133;
        left: 8px;
        top: 40px;
        z-index: 999;
        font-size: 13px;
        color: #f2fff2;
        padding: 0 5px;
        -webkit-animation-name: fadeInOut;
        -webkit-animation-timing-function: ease-in-out;
        -webkit-animation-iteration-count: infinite;
        -webkit-animation-duration: 8s;
        -webkit-animation-direction: alternate;
    }

    @-webkit-keyframes fadeInOut {
        0% {
            opacity: 1;
        }

        25% {
            opacity: 1;
        }

        50% {
            opacity: 0;
        }

        75% {
            opacity: 1;
        }
    }
</style>