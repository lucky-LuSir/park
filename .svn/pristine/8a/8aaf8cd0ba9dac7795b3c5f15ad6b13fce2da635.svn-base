<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <el-button v-if="showAddCpy"
                       class="insert-button"
                       size="small" type="primary"
                       @click="openAddCpyWindow()">
                添加
            </el-button>
        </div>

        <el-form class="root_style">
            <el-card class="box-card" style="margin-top: 10px;" v-for="(item, index) in cpyList" :key="index" shadow="hover">
                <el-image v-if="item.cpyImg"
                          class="el_image_style"
                          :src="parkBaseUrl + 'file/read/' + item.cpyImg"
                          fit="scale-down">
                </el-image>

                <el-avatar v-if="!item.cpyImg" shape="square" :size="100" fit="scale-down" class="el_card_img">
                    <span style="font-size: 25px;">未添加</span>
                </el-avatar>

                <div class="company_info">
                    <el-row :gutter="20">
                        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" class="el_col_style">
                            <el-form-item label="公司名称：">
                                <span>{{ item.cpyName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-button @click="openEditCpyWindow(item)" v-if="showEditCpyBtn" icon="el-icon-edit" type="primary" round size="small">编辑</el-button>

                            <el-button @click="openCpyAuthWindow(item)" v-if="showAddCpyAuthBtn" icon="el-icon-s-promotion" type="warning" round size="small">分配权限</el-button>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="el_col_style">
                            <el-form-item label="公司地址：">
                                <span>{{ item.address }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="管理员姓名：">
                                <span>{{ item.userName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="性别：">
                                <span>{{ item.sexName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" class="el_col_style">
                            <el-form-item label="手机：">
                                <span>{{ item.phone }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </div>
                <div style="clear: both"></div>
            </el-card>

            <!-- 分页栏 -->
            <div class="page-box">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>
        </el-form>

        <!-- 添加公司弹窗 -->
        <el-dialog class="insert-card-div" width="70%" style="text-align: center;" title="添加公司"
                   :visible.sync="addCpyWindow">
            <el-form :model="cpyObj" :rules="rules" ref="cpyObj" label-width="110px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="公司名称：" prop="cpyName">
                            <el-input clearable v-model="cpyObj.cpyName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="管理员姓名：" prop="userName">
                            <el-input clearable v-model="cpyObj.userName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="性别：" prop="sex">
                            <el-radio-group v-model="cpyObj.sex">
                                <el-radio label="1">男</el-radio>
                                <el-radio label="2">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="手机：" prop="phone">
                            <el-input v-model="cpyObj.phone" @blur="checkPhone()" clearable></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                        <el-form-item label="公司地址：" prop="address">
                            <el-input clearable v-model="cpyObj.address" suffix-icon="el-icon-location"></el-input>
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

                <el-row v-if="cpyObj.cpyImg">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-avatar shape="square"
                                   :size="100"
                                   fit="scale-down"
                                   :src="parkBaseUrl + 'file/read/' + cpyObj.cpyImg"
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
                <el-button @click="closeDialog('cpyObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('cpyObj')">提 交</el-button>
            </div>
        </el-dialog>

        <!-- 修改公司弹窗 -->
        <el-dialog class="insert-card-div" width="70%" style="text-align: center;" title="修改公司"
                   :visible.sync="editCpyWindow">
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
                        <el-image style="width: 100px; height: 100px; background: #C0C4CC; margin-left: 20px;"
                                  :src="parkBaseUrl + 'file/read/' + editCpyObj.cpyImg"
                                  fit="scale-down">
                        </el-image>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-top: -40px; margin-left: 35px;">
                        <el-button type="text" @click="deleteImg()">
                            <i class="el-icon-delete">&nbsp;&nbsp;删除</i>
                        </el-button>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDialog('editCpyObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('editCpyObj')">提 交</el-button>
            </div>
        </el-dialog>

        <!-- 分配公司管理员权限弹窗 -->
        <el-dialog title="分配权限" center :visible.sync="cpyAuthWindow">
            <el-card class="box-card" shadow="hover">
                <div slot="header" style="text-align: left;">
                    <span>{{ cpyAuthObj.cpyName }}</span>
                    <el-button style="float: right;" type="primary" size="small" @click="saveAuth()">保存</el-button>
                </div>

                <el-tree
                        :data="treeMenuList"
                        style="line-height: 50px;"
                        show-checkbox
                        :default-expand-all="false"
                        :default-expanded-keys="[1]"
                        :expand-on-click-node="false"
                        node-key="id"
                        ref="tree"
                        highlight-current
                        :props="defaultProps">
                </el-tree>
            </el-card>
        </el-dialog>
    </div>
</template>

<script>
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue';

    export default {
        components: {
            ElOption,
            file,
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
                showAddCpy: false,// 公司新增
                showEditCpyBtn: false,// 公司修改
                showAddCpyAuthBtn: false,// 公司权限分配

                addCpyWindow: false,// 添加公司弹窗
                windowTitle: '',// 弹窗标题
                windowFlag: '',

                cpyList: [],// 公司集合

                phoneFlag: '',// 手机号标记，用于检测手机号是否存在

                // 添加公司实体
                cpyObj: {
                    cpyName: '',
                    userCode: '',
                    userName: '',
                    sex: '',
                    phone: '',
                    address: '',
                    cpyImg: '',
                },

                editCpyWindow: false,// 修改公司弹窗
                // 修改公司实体
                editCpyObj: {
                    cpyName: '',
                    userCode: '',
                    userName: '',
                    sex: '',
                    phone: '',
                    address: '',
                    cpyImg: '',
                },

                // 分页
                start: 1,
                pageSize: 25,// 默认每页数量
                pageSizes: [25, 50, 100, 150],
                total: 0,

                /**
                 * 分配权限
                 */
                cpyAuthWindow: false,// 分配权限弹窗
                defaultProps: {
                    children: 'children',
                    label: 'menuName'
                },
                treeMenuList: [],// 菜单树
                // 分配权限实体
                cpyAuthObj: {
                    cpyName: '',
                },
                postObj: {},// 岗位封装菜单

                rules: {
                    cpyName: [
                        {required: true, message: '请输入公司名', trigger: 'blur'}
                    ],
                    userName: [
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
        created: function () {
            this.initInfo();

            this.selectAllMenu();// 查询所有菜单
        },
        methods: {
            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.initInfo();
            },
            handleCurrentChange(val) {
                this.start = val;
                this.initInfo();
            },

            // 初始化页面
            initInfo(){
                let vm = this;

                vm.showAddCpy = vm.$flagMenuStore.judgeMenu('addCpyBtn');
                vm.showEditCpyBtn = vm.$flagMenuStore.judgeMenu('editCpyBtn');
                vm.showAddCpyAuthBtn = vm.$flagMenuStore.judgeMenu('addCpyAuthBtn');

                let params = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'company/query'
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.cpyList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }
                }).catch(error => {
                    vm.$alert('页面：公司列表加载失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 打开添加公司弹窗
            openAddCpyWindow() {
                let vm = this;

                vm.cpyObj = {
                    cpyName: '',
                    userName: '',
                    sex: '',
                    phone: '',
                    address: '',
                    cpyImg: '',
                };

                vm.windowFlag = '1';
                vm.addCpyWindow = true;
            },

            // 打开修改公司弹窗
            openEditCpyWindow(cpyObj) {
                let vm = this;

                vm.windowFlag = '2';
                vm.editCpyWindow = true;

                vm.editCpyObj = JSON.parse(JSON.stringify(cpyObj));
            },

            // 关闭弹窗
            closeDialog(formName) {
                this.$refs[formName].resetFields();
                if (this.windowFlag == '1') {
                    this.addCpyWindow = false;
                } else if (this.windowFlag == '2') {
                    this.editCpyWindow = false;
                }
            },

            // 添加
            submitForm(formName){
                let vm = this;

                if (vm.cpyObj) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {

                            let url = '';
                            let msg = '';
                            let msgError = '';
                            let obj = {};

                            if (vm.windowFlag == '1') {
                                url = 'company/create';
                                msg = '公司新增成功！';
                                msgError = '公司新增失败！';
                                obj = {
                                    entity: vm.cpyObj
                                };
                            } else if (vm.windowFlag == '2') {
                                url = 'company/update';
                                msg = '公司修改成功！';
                                msgError = '公司修改失败！';
                                obj = {
                                    entity: vm.editCpyObj
                                };
                            }

                            let options = {
                                method: 'POST',
                                data: obj,
                                url: url
                            };

                            this.$ajax(
                                options
                            ).then(response => {
                                if (!response.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: msg,
                                        type: 'success'
                                    });

                                    this.$refs[formName].resetFields();

                                    if (vm.windowFlag == '1') {
                                        vm.addCpyWindow = false;
                                        this.cpyObj.cpyImg = '';
                                    } else if (vm.windowFlag == '2') {
                                        vm.editCpyWindow = false;
                                        this.editCpyObj.cpyImg = '';
                                    }

                                    // 刷新列表
                                    vm.initInfo();
                                }
                            }).catch(error => {
                                vm.$message({
                                    showClose: true,
                                    message: msgError,
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

            // 选择图片
            selectImg(urls){
                if (urls.length > 1) {
                    this.$message({
                        showClose: true,
                        message: '只能选择一张图片，请重新选择',
                        type: 'error'
                    });
                } else {
                    if (this.windowFlag === '1') {
                        this.cpyObj.cpyImg = urls[0].fileCode;
                    } else if (this.windowFlag === '2') {
                        this.editCpyObj.cpyImg = urls[0].fileCode;
                        this.editCpyObj = JSON.parse(JSON.stringify(this.editCpyObj));
                    }
                }
            },

            deleteImg(){
                let vm = this;

                vm.$confirm('确定移除这张图片?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if (this.windowFlag === '1') {
                        vm.cpyObj.cpyImg = '';
                    } else if (this.windowFlag === '2') {
                        this.editCpyObj.cpyImg = '';
                    }

                    vm.$message({
                        type: 'success',
                        message: '移除成功!'
                    });
                }).catch(() => {});
            },

            // 检查手机号是否存在
            checkPhone() {
                let vm = this;

                let obj = {
                    entity: {
                        empPhone: vm.cpyObj.phone
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

            // 重置表单
            resetForm(formName){
                this.$refs[formName].resetFields();
                this.addCpyWindow = false;
            },

            // 查询所有菜单
            selectAllMenu() {
                let vm = this;

                let params = {
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'menu/query',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    vm.treeMenuList = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },

            // 打开公司分配权限窗口
            openCpyAuthWindow(cpyObj) {
                let vm = this;
                vm.cpyAuthWindow = true;
                vm.cpyAuthObj.cpyName = cpyObj.cpyName;

                let params = {
                    entity: {},
                    userCode: cpyObj.userCode
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'post/cpyAuth',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    vm.postObj = response.data.result;

                    vm.$refs.tree.setCheckedNodes(vm.postObj.menus);
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },

            // 设置勾选的节点
            setCheckedNodes() {
                this.$refs.tree.setCheckedNodes([
                    {
//                        id: 12,
//                        isDeleted: false,
//                        menuCode: "3-2",
//                        menuName: "合同新增",
//                        parentCode: "3",
//                        menuTypeName: "找不到指定词典",
//                        level: 2,
//                        children: []
                    },
                ]);
            },

            // 保存权限
            saveAuth() {
                let menuObjList = this.$refs.tree.getCheckedNodes();

                let menuCodeList = [];

                for (let i = 0; i < menuObjList.length; i++) {
                    let aMenuCode = menuObjList[i].menuCode;
                    menuCodeList.push(aMenuCode);
                }

                this.basicEditPost(this.postObj, menuCodeList);
            },

            // 分配权限
            basicEditPost(aValPost, menuCodeList){
                let vm = this;

                let sendObj = {
                    entity: aValPost,
                    menuCodes: menuCodeList
                };

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: 'post/update',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$message({
                        message: response.data.message,
                        type: 'success'
                    });
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
        },
    }
</script>

<style scoped>
    /*根div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
    }

    .root_style {
        height: 78vh;
        overflow-y: auto;
        overflow-x: hidden;
        text-align: left;
    }

    /*导航栏 根div*/
    .nav-root-div {
        color: #409EFF;
        margin-top: -53px;

        position: fixed;
        z-index: 200;
    }

    /*新增按钮*/
    .insert-button {
        float: left;
        margin-left: 20px;
    }

    /*------------------------------------------------------------------------*/

    /*公司logo*/
    .el_image_style {
        width: 100px;
        height: 100px;
        background: #C0C4CC;
        margin-top: 10px;
        float: left;
    }

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
    .insert-card-div {
        margin-top: 0px;
        text-align: left;
        line-height: 60px;
    }

    /*分页栏样式*/
    .page-box {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
        /*border: solid 1px;*/
        margin-left: -10px;
    }
</style>