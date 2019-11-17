<template>
    <div>
        <!-- 选择联系人 -->
        <el-dialog title="选择联系人"
                   @close="closeDialog"
                   :visible.sync="selectRecipientsWindow"
                   :close-on-press-escape="false"
                   :close-on-click-modal="false"
                   width="90%"
                   top="8vh"
                   style="text-align: center;">
            <div style="margin-top: -20px;">
                <div style="width: 48%; float: left;">
                    <el-card shadow="hover" class="box-card">
                        <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick" style="box-shadow: none; height: 580px;">
                            <el-tab-pane label="中介" name="first">
                                <el-row style="padding-bottom: 10px; margin-top: -5px;">
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <el-select v-model="channelObj.channelStatus"
                                                   size="small"
                                                   @change="changeChannelType()"
                                                   style="float: left; width: 150px;"
                                                   placeholder="请选择中介类型">
                                            <el-option
                                                    v-for="item in channelTypeList"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                        <el-input size="small"
                                                  placeholder="姓名、电话、公司、地址"
                                                  title="可搜索姓名、电话、公司、地址"
                                                  v-model="channelObj.keyword"
                                                  style="width: 210px; float: left; margin-left: 10px;"
                                                  clearable>
                                        </el-input>
                                        <el-button type="primary" size="small" icon="el-icon-search" class="select_button" @click="selectChannelList()">查询</el-button>
                                    </el-col>
                                </el-row>

                                <el-table
                                        height="427"
                                        size="mini"
                                        :highlight-current-row="true"
                                        border
                                        ref="channelTable"
                                        :data="channelList"
                                        tooltip-effect="dark"
                                        @select-all="channelAllSelect"
                                        @select="channelSingleSelect">
                                    <el-table-column
                                            fixed="left"
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column
                                            fixed="left"
                                            align="center"
                                            width="50px"
                                            label="序号"
                                            type="index">
                                    </el-table-column>
                                    <el-table-column
                                            fixed="left"
                                            align="center"
                                            width="80px"
                                            label="姓名"
                                            prop="userName">
                                    </el-table-column>
                                    <el-table-column
                                            fixed="left"
                                            align="center"
                                            width="120px"
                                            label="电话"
                                            prop="phone">
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="80px"
                                            label="渠道类型"
                                            prop="channelStatusName">
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="50px"
                                            label="性别"
                                            prop="sex">
                                        <template slot-scope="scope">{{scope.row.sex == 1 ? "男" : "女"}}</template>
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="70px"
                                            label="职务"
                                            show-overflow-tooltip
                                            prop="post">
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="200px"
                                            label="公司名称"
                                            show-overflow-tooltip
                                            prop="channelCpyEntity.channelCpyName">
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="150px"
                                            label="公司描述"
                                            show-overflow-tooltip
                                            prop="channelCpyEntity.channelCpyRemark">
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="300px"
                                            label="公司地址"
                                            show-overflow-tooltip
                                            prop="channelCpyEntity.channelCpyAddress">
                                    </el-table-column>
                                </el-table>
                                <div style="background-color: rgb(242, 242, 242); width: 100%;">
                                    <el-pagination background
                                                   style="padding-top: 10px;"
                                                   small
                                                   @size-change="channelHandleSizeChange"
                                                   @current-change="channelHandleCurrentChange"
                                                   :current-page.sync="channelCurrentPage"
                                                   :page-sizes="channelPageSizes"
                                                   :page-size="channelDefaultPageSize"
                                                   layout="total, prev, pager, next, jumper"
                                                   :total="channelTotal">
                                    </el-pagination>
                                </div>
                            </el-tab-pane>

                            <el-tab-pane label="业主" name="second">
                                <el-row style="padding-bottom: 10px; margin-top: -5px;">
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <el-select style="width: 150px; float: left;"
                                                   v-model="contractObj.pkCode"
                                                   placeholder="请选择园区"
                                                   filterable size="small">
                                            <el-option v-for="(item, index) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode"></el-option>
                                        </el-select>
                                        <el-input size="small"
                                                  placeholder="业主姓名、公司、合同编号"
                                                  title="可搜索业主姓名、业主公司、合同编号"
                                                  v-model="contractObj.keyword"
                                                  style="width: 210px; float: left; margin-left: 10px;"
                                                  clearable>
                                        </el-input>
                                        <el-button type="primary" size="small" icon="el-icon-search" class="select_button" @click="selectContractList()">查询</el-button>
                                    </el-col>
                                </el-row>
                                <el-table
                                        height="427"
                                        size="mini"
                                        :highlight-current-row="true"
                                        border
                                        ref="contractTable"
                                        :data="contractList"
                                        tooltip-effect="dark"
                                        @select-all="contractAllSelect"
                                        @select="contractSingleSelect">
                                    <el-table-column
                                            fixed="left"
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column
                                            fixed="left"
                                            align="center"
                                            width="50px"
                                            label="序号"
                                            type="index">
                                    </el-table-column>
                                    <el-table-column
                                            fixed="left"
                                            align="center"
                                            width="150px"
                                            label="合同编号"
                                            prop="ctName">
                                    </el-table-column>
                                    <el-table-column header-align="center"
                                                     min-width="90"
                                                     label="合同状态">
                                        <template slot-scope="scope">
                                            <span v-if="scope.row.ctStatus == 4" style="color:#67c23a">
                                                {{ scope.row.ctStatusName }}
                                            </span>
                                            <template v-else>{{ scope.row.ctStatusName }}</template>
                                        </template>
                                    </el-table-column>
                                    <el-table-column show-overflow-tooltip
                                                     header-align="center"
                                                     min-width="100"
                                                     label="联系人">
                                        <template slot-scope="scope">
                                            {{ scope.row.relateName == null ? "---" : scope.row.relateName }}
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            align="center"
                                            width="100px"
                                            label="电话"
                                            show-overflow-tooltip
                                            prop="relatePhone">
                                    </el-table-column>
                                    <el-table-column show-overflow-tooltip
                                                     header-align="center"
                                                     min-width="150"
                                                     label="公司名称">
                                        <template slot-scope="scope">
                                            {{ scope.row.companyName == null ? "---" : scope.row.companyName }}
                                        </template>
                                    </el-table-column>
                                </el-table>
                                <div style="background-color: rgb(242, 242, 242); width: 100%;">
                                    <el-pagination background
                                                   style="padding-top: 10px;"
                                                   small
                                                   @size-change="ctHandleSizeChange"
                                                   @current-change="ctHandleCurrentChange"
                                                   :current-page.sync="ctCurrentPage"
                                                   :page-sizes="ctPageSizes"
                                                   :page-size="ctDefaultPageSize"
                                                   layout="total, prev, pager, next, jumper"
                                                   :total="ctTotal">
                                    </el-pagination>
                                </div>
                            </el-tab-pane>

                            <el-tab-pane label="客户" name="third">
                                <el-row style="padding-bottom: 10px; margin-top: -5px;">
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <el-select style="width: 150px; float: left;"
                                                   v-model="cusObj.pkCode"
                                                   placeholder="请选择园区"
                                                   filterable size="small">
                                            <el-option v-for="(item, index) in parkList" :key="item.pkCode" :label="item.pkName" :value="item.pkCode"></el-option>
                                        </el-select>
                                        <el-input size="small"
                                                  placeholder="客户姓名、公司、电话"
                                                  title="可搜索客户姓名、电话及所属公司"
                                                  v-model="cusObj.keyword"
                                                  style="width: 210px; float: left; margin-left: 10px;"
                                                  clearable>
                                        </el-input>
                                        <el-button type="primary" size="small" icon="el-icon-search" class="select_button" @click="selectCustomerList()">查询</el-button>
                                    </el-col>
                                </el-row>
                                <el-table
                                        height="427"
                                        size="mini"
                                        :highlight-current-row="true"
                                        border
                                        ref="customerTable"
                                        :data="cusList"
                                        tooltip-effect="dark"
                                        @select-all="cusAllSelect"
                                        @select="cusSingleSelect">
                                    <el-table-column
                                            fixed="left"
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column align="center"
                                                     width="50"
                                                     label="序号"
                                                     type="index">
                                    </el-table-column>
                                    <el-table-column align="center"
                                                     width="80"
                                                     label="联系人"
                                                     prop="mainContactName">
                                    </el-table-column>
                                    <el-table-column align="center"
                                                     prop="mainContactPhone"
                                                     width="120"
                                                     label="联系电话">
                                    </el-table-column>
                                    <el-table-column align="center"
                                                     prop="pkName"
                                                     width="190"
                                                     label="所属园区">
                                    </el-table-column>
                                    <el-table-column show-overflow-tooltip
                                                     header-align="center"
                                                     width="150"
                                                     prop="companyName"
                                                     label="公司名称">
                                    </el-table-column>
                                    <el-table-column show-overflow-tooltip
                                                     header-align="center"
                                                     width="90"
                                                     prop="customerTypeName"
                                                     label="客户类型">
                                    </el-table-column>
                                </el-table>
                                <div style="background-color: rgb(242, 242, 242); width: 100%;">
                                    <el-pagination background
                                                   style="padding-top: 10px;"
                                                   small
                                                   @size-change="cusHandleSizeChange"
                                                   @current-change="cusHandleCurrentChange"
                                                   :current-page.sync="cusCurrentPage"
                                                   :page-sizes="cusPageSizes"
                                                   :page-size="cusDefaultPageSize"
                                                   layout="total, prev, pager, next, jumper"
                                                   :total="cusTotal">
                                    </el-pagination>
                                </div>
                            </el-tab-pane>

                            <!--<el-tab-pane label="其他" name="fourth">定时任务补偿</el-tab-pane>-->
                        </el-tabs>
                    </el-card>
                </div>

                <div style="width: 4%; float: left; line-height: 620px;">
                    <el-button type="primary"
                               icon="el-icon-arrow-right"
                               @click="addLinkman()"
                               circle title="添加">
                    </el-button>
                </div>

                <!-- 已选择的联系人 -->
                <div style="width: 48%; float: left;">
                    <el-card shadow="hover" class="box-card">
                        <el-row style="padding-bottom: 10px;">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <el-button type="primary"
                                           size="small"
                                           icon="el-icon-delete"
                                           style="float: left;"
                                           @click="clearList()">
                                    清空
                                </el-button>
                            </el-col>
                        </el-row>
                        <el-table
                                height="540px"
                                size="mini"
                                border
                                fit
                                ref="multipleTable"
                                :data="selectedRecipientsList"
                                tooltip-effect="dark">
                            <el-table-column
                                    align="center"
                                    width="60"
                                    label="序号"
                                    type="index">
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    width="100"
                                    label="姓名"
                                    prop="name">
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    width="150"
                                    label="电话"
                                    prop="phone">
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    width="90"
                                    label="类型"
                                    prop="type">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.type == 1">中介</span>
                                    <span v-if="scope.row.type == 2">业主</span>
                                    <span v-if="scope.row.type == 3">客户</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    width="250"
                                    label="公司名称"
                                    show-overflow-tooltip
                                    prop="companyName">
                            </el-table-column>
                            <el-table-column
                                    fixed="right"
                                    align="center"
                                    width="100"
                                    label="操作">
                                <template slot-scope="scope">
                                    <el-button size="mini" type="text" @click="deleteChannel(scope.$index)">移除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-card>
                </div>
                <div style="clear: both;"></div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelEvent()">取 消</el-button>
                <el-button type="primary" @click="submitForm()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow},
        props: ['recipientsObj'],// 接收父组件数据
        data() {
            return {
                activeName: 'first',// 打开默认页签
                selectRecipientsWindow: false,// 选择收件人弹窗

                // 已选择的联系人
                selectedRecipientsList: [
                    {
                        code: '',
                        name: '',
                        phone: '',
                        type: '',// 人员类型，1是中介
                        companyName: ''
                    }
                ],

                setLeft: new Set(),// 左侧勾选set集合
                setRight: new Set(),// 右侧已选择set集合

                /*------------------------------------ 渠道（中介）相关 ------------------------------------*/
                channelList: [],// 渠道列表绑定数据

                // 分页
                channelCurrentPage: 1,// 当前默认起始页
                channelDefaultPageSize: 10,// 默认每页数量
                channelPageSizes: [25, 50, 100, 150],
                channelTotal: 0,

                channelObj: {
                    channelStatus: '',// 渠道类型
                    keyword: '',// 关键词
                },

                // 渠道类型集合
                channelTypeList: [
                    {
                        value: '',
                        label: '全部'
                    }, {
                        value: '1',
                        label: '个人中介'
                    }, {
                        value: '2',
                        label: '公司中介'
                    }, {
                        value: '3',
                        label: '管委会'
                    }, {
                        value: '4',
                        label: '房东'
                    }, {
                        value: '5',
                        label: '其他'
                    },
                ],

                /*------------------------------------ 业主相关 ------------------------------------*/
                parkList: [],// 园区集合
                contractList: [],// 业主集合
                // 业主联系人查询参数
                contractObj: {
                    pkCode: '',
                    keyword: '',
                },

                // 业主联系人分页
                ctCurrentPage: 1,
                ctDefaultPageSize: 10,// 默认每页数量
                ctPageSizes: [25, 50, 100, 150],
                ctTotal: 0,

                /*------------------------------------ 客户相关 ------------------------------------*/
                cusList: [],// 客户集合
                // 客户联系人查询参数
                cusObj: {
                    pkCode: '',
                    keyword: '',
                },

                // 客户联系人分页
                cusCurrentPage: 1,
                cusDefaultPageSize: 10,// 默认每页数量
                cusPageSizes: [25, 50, 100, 150],
                cusTotal: 0,
            }
        },
        created: function () {
            let vm = this;
            if (vm.recipientsObj) {
                vm.selectRecipientsWindow = vm.recipientsObj.selectRecipientsFlag;

                vm.selectedRecipientsList = [];
                vm.selectParkList();// 查询园区集合
            }
        },
        methods: {
            // 页签切换
            handleClick(tab, event) {
                this.activeName = tab.name;
            },

            // dialog组件close回调函数
            closeDialog() {
                this.$emit('closeDialog', false);
            },

            // 移除选中的某个对象
            deleteChannel(item) {
                this.selectedRecipientsList.splice(item, 1);
            },

            // 清空集合
            clearList() {
                this.selectedRecipientsList = [];
                this.setLeft.clear();
                this.setRight.clear();
            },

            // 取消并关闭弹窗
            cancelEvent() {
                this.closeDialog();
            },

            // 确定
            submitForm() {
                this.$emit('submitForm', this.selectedRecipientsList);
                this.closeDialog();
            },

            /*------------------------------- 渠道相关 -------------------------------*/
            // 改变每页数量
            channelHandleSizeChange(val) {
                this.channelDefaultPageSize = val;
                this.channelCurrentPage = 1;
                this.selectChannelList();
            },
            // 改变当前页
            channelHandleCurrentChange(val) {
                this.channelCurrentPage = val;
                this.selectChannelList();
            },

            // 选择渠道类型，将当前页码置为初始状态
            changeChannelType() {
                this.channelCurrentPage = 1;
            },

            // 查询渠道列表
            selectChannelList(){
                let vm = this;

                if (vm.channelObj.keyword) {
                    vm.channelCurrentPage = 1;
                }

                let params = {
                    start: (vm.channelCurrentPage - 1) * vm.channelDefaultPageSize,
                    pageSize: vm.channelDefaultPageSize,
                    keyword: vm.channelObj.keyword,
                    entity: {
                        channelStatus: vm.channelObj.channelStatus,
                    }
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'customer/channel/query'
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    if (!response.data.isException) {
                        vm.channelTotal = response.data.result.totalCount;
                        vm.channelList = response.data.result.data;
                    }
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面：渠道列表查询失败！',
                        type: 'error'
                    })
                })
            },

            // 单选
            channelSingleSelect(selection, row) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let channelObj = {};

                    channelObj.code = selection[i].channelCode;
                    channelObj.name = selection[i].userName;
                    channelObj.phone = selection[i].phone;
                    channelObj.type = 1;
                    if (selection[i].channelCpyEntity) {
                        channelObj.companyName = selection[i].channelCpyEntity.channelCpyName;
                    }
                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(channelObj);

                    this.setLeft.add(strObj);
                }
            },

            // 全选
            channelAllSelect(selection) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let channelObj = {};
                    channelObj.code = selection[i].channelCode;
                    channelObj.name = selection[i].userName;
                    channelObj.phone = selection[i].phone;
                    channelObj.type = 1;

                    if (selection[i].channelCpyEntity) {
                        channelObj.companyName = selection[i].channelCpyEntity.channelCpyName;
                    }

                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(channelObj);

                    this.setLeft.add(strObj);
                }
            },

            // 添加
            addLinkman() {
                if (this.activeName === 'first') {
                    this.$refs.channelTable.clearSelection();
                } else if (this.activeName === 'second') {
                    this.$refs.contractTable.clearSelection();
                } else if (this.activeName === 'third') {
                    this.$refs.customerTable.clearSelection();
                }


                for (let i of this.setLeft) {
                    this.setRight.add(i);
                }

                let rightList = this.setRight.size;

                if (rightList > 1000) {
                    this.$alert('单次发送短信最多支持1000人！', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                } else {
                    this.selectedRecipientsList = [];

                    for (let j of this.setRight) {
                        let channelObj = JSON.parse(j);
                        this.selectedRecipientsList.push(channelObj);
                    }
                }

                this.setLeft.clear();
            },

            /*------------------------------- 业主相关 -------------------------------*/
            // 查询园区集合
            selectParkList() {
                let vm = this;

                let params = {
                    entity: {}
                };

                let options = {
                    method: 'POST',
                    data: params,
                    url: 'park/list',
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (response) {
                        vm.parkList = response.data.result;
                        vm.parkList.unshift({
                            pkCode: '',
                            pkName: '全部'
                        });
                    }
                }).catch(error => {
                    vm.$alert('页面：园区列表查询失败！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => { }
                    });
                })
            },

            // 改变每页数量
            ctHandleSizeChange(val) {
                this.ctDefaultPageSize = val;
                this.ctCurrentPage = 1;
                this.selectContractList();
            },
            // 改变当前页
            ctHandleCurrentChange(val) {
                this.ctCurrentPage = val;
                this.selectContractList();
            },

            // 查询合同集合
            selectContractList() {
                let vm = this;

                let param = {
                    start: (vm.ctCurrentPage - 1) * vm.ctDefaultPageSize,
                    pageSize: vm.ctDefaultPageSize,
                    keyword: vm.contractObj.keyword,
                    entity: {
                        pkCode: vm.contractObj.pkCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: 'contract/queryDetail',
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    if (response) {
                        vm.contractList = response.data.result.data;
                        vm.ctTotal = response.data.result.totalCount;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：合同列表查询失败！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 单选
            contractSingleSelect(selection, row) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let contractObj = {};
                    contractObj.code = selection[i].ctCode;
                    contractObj.name = selection[i].relateName;
                    contractObj.phone = selection[i].relatePhone;
                    contractObj.type = 2;
                    contractObj.companyName = selection[i].companyName;
                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(contractObj);

                    this.setLeft.add(strObj);
                }
            },

            // 全选
            contractAllSelect(selection) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let contractObj = {};
                    contractObj.code = selection[i].ctCode;
                    contractObj.name = selection[i].relateName;
                    contractObj.phone = selection[i].relatePhone;
                    contractObj.type = 2;
                    contractObj.companyName = selection[i].companyName;

                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(contractObj);

                    this.setLeft.add(strObj);
                }
            },

            /*------------------------------- 客户相关 -------------------------------*/
            // 改变每页数量
            cusHandleSizeChange(val) {
                this.cusDefaultPageSize = val;
                this.cusCurrentPage = 1;
                this.selectCustomerList();
            },
            // 改变当前页
            cusHandleCurrentChange(val) {
                this.cusCurrentPage = val;
                this.selectCustomerList();
            },

            // 查询客户集合
            selectCustomerList() {
                let vm = this;


                let param = {
                    start: (vm.cusCurrentPage - 1) * vm.cusDefaultPageSize,
                    pageSize: vm.cusDefaultPageSize,
                    keyword: vm.cusObj.keyword,
                    entity: {
                        pkCode: vm.cusObj.pkCode,
                    }
                };

                let options = {
                    method: 'POST',
                    data: param,
                    url: 'customer/selectByKeyword',
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.cusList = response.data.result.data;
                    vm.cusTotal = response.data.result.totalCount;

                }).catch(function (error) {
                    vm.$alert('页面：客户列表查询失败！', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            // 单选
            cusSingleSelect(selection, row) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let cusObj = {};
                    cusObj.code = selection[i].cusCode;
                    cusObj.name = selection[i].mainContactName;
                    cusObj.phone = selection[i].mainContactPhone;
                    cusObj.type = 3;
                    cusObj.companyName = selection[i].companyName;
                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(cusObj);

                    this.setLeft.clear();
                    this.setLeft.add(strObj);
                }
            },

            // 全选
            cusAllSelect(selection) {
                this.setLeft.clear();

                for (let i = 0; i < selection.length; i++) {
                    let cusObj = {};
                    cusObj.code = selection[i].ctCode;
                    cusObj.name = selection[i].mainContactName;
                    cusObj.phone = selection[i].mainContactPhone;
                    cusObj.type = 3;
                    cusObj.companyName = selection[i].companyName;

                    // 对象转为JSON字符串
                    let strObj = JSON.stringify(cusObj);
                    this.setLeft.add(strObj);
                }
            },
        },
    }
</script>

<style scoped>
    /*查询按钮*/
    .select_button {
        margin-left: 10px;
        float: left;
        margin-top: 2px;
    }

    /*卡片样式*/
    .box-card {
        height: 620px;
    }

    /*弹窗底部按钮*/
    .dialog-footer {
        margin-top: -10px;
    }
</style>