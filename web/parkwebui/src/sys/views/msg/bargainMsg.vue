<template>
    <div class="not-position_root" ref="myRoot">
        <div class="not-position_header">
            <el-button type="primary" size="small" @click="addMsgWindow()">
                添加消息
            </el-button>
        </div>

        <el-table :data="bargainMsgList" :height="tableHeight" border>
            <el-table-column type="index" width="50">
            </el-table-column>
            <el-table-column label="日期" width="180">
                <template slot-scope="scope">{{ scope.row.bargainDate | timeDate }}</template>
            </el-table-column>
            <el-table-column prop="pkName" label="园区" width="180">
            </el-table-column>
            <el-table-column prop="empName" label="签单人">
            </el-table-column>
            <el-table-column prop="area" label="出租面积">
                <template slot-scope="scope">{{ scope.row.area }}&nbsp;㎡</template>
            </el-table-column>
            <el-table-column prop="rent" label="年租金">
                <template slot-scope="scope">{{ scope.row.rent }}&nbsp;元</template>
            </el-table-column>
            <el-table-column prop="channelName" label="来源渠道">
            </el-table-column>
            <el-table-column min-width="100px" fixed="right" align="center" label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="editMsgWindow(scope.row.bmCode)" size="mini" type="text">
                        修改
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :title="windowTitle" :visible.sync="addWindow" :close-on-press-escape="false" :close-on-click-modal="false" width="70%">
            <el-form :model="bargainMsgObj" :rules="rules" ref="bargainMsgObj" labelSuffix="：" label-width="100px" style="text-align: left;">
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="成交日期" prop="bargainDate">
                            <el-date-picker v-model="bargainMsgObj.bargainDate" type="date" placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="园区" prop="pkCode">
                            <el-select v-model="bargainMsgObj.pkCode" @change="getParkObj" placeholder="请选择">
                                <el-option v-for="(item, index) in parkList" :key="index" :label="item.pkName" :value="item.pkCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="签单人" prop="empCode">
                            <el-select v-model="bargainMsgObj.empCode" @change="getEmpObj" placeholder="请选择">
                                <el-option v-for="(item, index) in empList" :key="index" :label="item.empName" :value="item.empCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="出租面积" prop="area">
                            <el-input v-model="bargainMsgObj.area" clearable>
                                <template slot="append">㎡</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" class="input-col1">
                        <el-form-item label="年租金" prop="rent">
                            <el-input v-model="bargainMsgObj.rent" clearable>
                                <template slot="append">元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="input-col1">
                        <el-form-item label="来源渠道" prop="channel">
                            <el-select v-model="bargainMsgObj.channel" placeholder="请选择">
                                <el-option v-for="(item) in channelList" :key="item.channel" :label="item.channelName" :value="item.channel">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <!-- 添加通知 -->
            <div slot="footer" class="dialog-footer" v-if="flag == 1">
                <el-button @click="resetForm('bargainMsgObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('bargainMsgObj')">确 定</el-button>
            </div>

            <!-- 修改通知 -->
            <div slot="footer" class="dialog-footer" v-if="flag == 2">
                <el-button @click="resetForm('bargainMsgObj')">取 消</el-button>
                <el-button type="primary" @click="submitForm('bargainMsgObj')">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 分页栏 -->
        <div class="page-box">
            <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import ElRow from "element-ui/packages/row/src/row";
import file from "../../../common/components/file.vue";
export default {
    components: {
        ElRow,
        file    },
    data() {
        // 校验数字0～99999999.99
        let checkNumber = (rule, value, callback) => {
            setTimeout(() => {
                if (value < 0) {
                    callback(new Error('数字值不能为负数'));
                } else if (value > 99999999.99) {
                    callback(new Error('请输入0～99999999.99数字值'));
                } else {
                    callback();
                }
            }, 100);
        };

        // 校验数字只能是正整数或小数
        let checkIntOrFloat = (rule, value, callback) => {
            setTimeout(() => {
                if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                    callback(new Error('只能输入正整数或小数'));
                } else {
                    callback();
                }
            }, 100);
        };

        // 校验数字只能为整数
        let checkInteger = (rule, value, callback) => {
            setTimeout(() => {
                if (!(/^[0-9]\d*$/.test(value))) {
                    callback(new Error('楼层数只能为正整数'));
                } else {
                    callback();
                }
            }, 100);
        };

        return {
            tableHeight: this.$store.state.tableHeight,
            bargainMsgList: [],// 消息集合

            empList: [],// 人员集合
            parkList: [],// 园区集合

            windowTitle: '',// 弹窗标题
            addWindow: false,// 是否打开弹窗
            flag: '',// 用于判断是新增还是修改

            bargainMsgObj: {},// 添加消息实体

            // 分页
            start: 1,
            pageSize: 50,
            pageSizes: [25, 50, 100, 150],
            total: 0,

            // 渠道
            channelList: [
                {
                    channel: '1',
                    channelName: '58端口'
                },
                {
                    channel: '2',
                    channelName: '户外横幅'
                },
                {
                    channel: '3',
                    channelName: '小广告/传单'
                },
                {
                    channel: '4',
                    channelName: '微信朋友圈'
                },
                {
                    channel: '5',
                    channelName: '园区建筑广告'
                },
                {
                    channel: '6',
                    channelName: '电话拨打'
                },
                {
                    channel: '7',
                    channelName: '中介'
                },
                {
                    channel: '8',
                    channelName: '兼职/转介绍'
                },
                {
                    channel: '9',
                    channelName: '其他'
                },
                {
                    channel: '10',
                    label: '库房无忧'
                },
            ],

            rules: {
                bargainDate: [
                    { required: true, message: '请选择成交日期', trigger: 'change' }
                ],
                pkCode: [
                    { required: true, message: '请选择园区', trigger: 'change' }
                ],
                empCode: [
                    { required: true, message: '请选择签约人', trigger: 'change' }
                ],
                area: [
                    { required: true, message: '请输入出租面积', trigger: 'blur' },
                    { validator: checkNumber },
                    { validator: checkIntOrFloat },
                ],
                rent: [
                    { required: true, message: '请输入年租金', trigger: 'blur' },
                    { validator: checkNumber },
                    { validator: checkIntOrFloat },
                ],
                channel: [
                    { required: true, message: '选择客户来源渠道', trigger: 'change' },
                ],
            },
        }
    },
    created() {
        let vm = this;

        vm.selectMsg();
        vm.selectEmpList();
        vm.selectParkList();
    },
    methods: {
        // 分页
        handleSizeChange(val) {
            this.pageSize = val;
            this.start = 1;
            this.selectMsg();
        },
        handleCurrentChange(val) {
            this.start = val;
            this.selectMsg();
        },

        // 查询消息
        selectMsg() {
            let vm = this;

            let params = {
                start: (vm.start - 1) * vm.pageSize,
                pageSize: vm.pageSize,
                entity: {}
            };

            let options = {
                method: 'POST',
                data: params,
                url: 'bargain/query',
            };

            this.$ajax(
                options
            ).then(response => {
                if (response) {
                    vm.bargainMsgList = response.data.result.data;
                    vm.total = response.data.result.totalCount;
                }
            }).catch(error => {
                vm.$alert('页面：消息查询失败！', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            })
        },

        // 添加通知窗口
        addMsgWindow() {
            this.windowTitle = '添加通知';
            this.addWindow = true;
            this.flag = 1;

            this.bargainMsgObj = {};

            this.selectEmpList();
            this.selectParkList();
        },

        // 修改通知窗口
        editMsgWindow(bmCode) {
            let vm = this;

            vm.windowTitle = '修改通知';
            vm.addWindow = true;
            vm.flag = 2;

            let params = {
                entity: {
                    bmCode: bmCode,
                }
            };

            let options = {
                method: 'POST',
                data: params,
                url: 'bargain/detail',
            };

            this.$ajax(
                options
            ).then(response => {
                if (response) {
                    vm.bargainMsgObj = response.data.result;
                }
            }).catch(error => {
                vm.$alert('页面：通知详情查询失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            })
        },

        // 查询签约人员
        selectEmpList() {
            let options = {
                method: 'POST',
                data: {},
                url: 'employee/list',
            };

            this.$ajax(
                options
            ).then(response => {
                if (response) {
                    this.empList = response.data.result;
                }
            }).catch(error => {
                this.$alert('页面：人员查询失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            })
        },
        // 得到签约人Code和Name
        getEmpObj(val) {
            let obj = {};
            obj = this.empList.find((item) => {
                return item.empCode === val;// 筛选出匹配数据
            });
            this.bargainMsgObj.empName = obj.empName;
        },

        // 查询所有园区
        selectParkList() {
            let options = {
                method: 'POST',
                data: {},
                url: 'park/list',
            };

            this.$ajax(
                options
            ).then(response => {
                if (response) {
                    this.parkList = response.data.result;
                }
            }).catch(error => {
                this.$alert('页面：人员查询失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            })
        },
        // 得到园区pkCode和pkName
        getParkObj(val) {
            let obj = {};
            obj = this.parkList.find((item) => {
                return item.pkCode === val;// 筛选出匹配数据
            });
            this.bargainMsgObj.pkName = obj.pkName;
        },

        // 重置表单
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.addWindow = false;
        },

        // 提交
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let obj = {
                        entity: this.bargainMsgObj
                    };

                    let url = '';
                    if (this.flag == 1) {
                        url = 'bargain/create'
                    } else if (this.flag == 2) {
                        url = 'bargain/update'
                    }

                    let options = {
                        method: 'POST',
                        data: obj,
                        url: url
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        if (response) {
                            this.$message({
                                showClose: true,
                                message: '通知新增成功',
                                type: 'success'
                            });
                            this.$refs[formName].resetFields();
                            this.addWindow = false;
                            this.selectMsg();
                        }
                    }).catch(error => {
                        this.$alert('页面：合同新增失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }
            });
        },
    }
}
</script>

<style scoped>
.not-position_header {
    padding-left: 10px;
}
/*输入框 第一行*/
.input-col1 {
    margin-top: 10px;
    height: 60px;
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
}
</style>