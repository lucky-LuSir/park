<template>
    <div class="not-position_root" ref="myRoot" id="contractList">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <div class="search_buttons">
                    <div style="float: left;">
                        <el-input
                                v-model="keyword"
                                @keyup.enter.native="searchCon()"
                                placeholder="责任人"
                                class="selectInfo"
                                size="small"
                                clearable>
                            <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
                        </el-input>
                        <el-select
                                class="selectInfo"
                                v-model="pkCode"
                                placeholder="请选择园区"
                                filterable
                                size="small">
                            <el-option v-for="item in pkNames"
                                       :key="item.code"
                                       :label="item.name"
                                       :value="item.code">
                            </el-option>
                        </el-select>
                        <el-date-picker
                                style="margin-left: 20px;width: 200px;"
                                v-model="inspectTime"
                                size="small"
                                type="daterange"
                                align="right"
                                unlink-panels
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                        <el-button icon="el-icon-search" style="margin-left: 20px" type="primary" @click="searchCon" size="small">
                            搜索
                        </el-button>
                        <el-button icon="el-icon-setting" style="margin-left: 20px" type="info" @click="templateManager" size="small">
                            检查项目
                        </el-button>
                        <el-button icon="el-icon-s-order" style="margin-left: 20px" type="info" @click="proTaskManager" size="small">
                            检查清单
                        </el-button>
                    </div>
                    <div class="search_box_create">
                        <el-button  class="add-button" type="primary" @click="createInspect" size="small">
                            新建卫生检查
                        </el-button>
                    </div>
                </div>
            </div>

            <!-- 表格化信息 -->
            <div class="contract_box_top">
                <el-table
                          :height="tableHeight"
                          :data="inspectInfo"
                          border
                          :highlight-current-row="true"
                          size="small">
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            min-width="50px">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="pkName"
                            label="园区名"
                            align="center"
                            min-width="130">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="beInspectedEmpName"
                            label="责任人"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="totalGrade"
                            label="总分"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="totalScore"
                            label="得分"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="inspectEmpName"
                            label="检查人"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            label="检查日期"
                            align="center"
                            min-width="110">
                        <template slot-scope="scope">
                            <span v-if="scope.row.inspectTime == null">---</span>
                            <span v-else>{{ scope.row.inspectTime | timeDate }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            header-align="center"
                            fixed="right"
                            label="操作"
                            min-width="200">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="inspectDetail(scope.row.inspectCode)" type="text">详情</el-button>
                            <el-button @click="inspectEdit(scope.row.inspectCode)" type="text" size="small">修改</el-button>
                            <el-button v-if="showDelete || scope.row.createCode == userCode" @click="notifyDelete(scope.row.id)" type="text" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

<!--            <div style="height: 50px;width: 10px;"></div>-->

            <div class="emp-page-div">
                <el-pagination style="padding-top: 10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[25, 50, 150, 200]"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>
        </el-form>
        <clean-check-create v-if="showCreate" @backParam="backCreate($event)" :paramsCreate="paramsCreate"></clean-check-create>
        <clean-check-detail v-if="showDetail" @backParam="backDetail($event)" :paramsDetail="paramsDetail"></clean-check-detail>
        <clean-check-edit v-if="showEdit" @backParam="backEdit($event)" :paramsEdit="paramsEdit"></clean-check-edit>
    </div>
</template>

<script>
    import cleanCheckCreate from "./cleanCheckCreate";
    import cleanCheckDetail from "./cleanCheckDetail";
    import cleanCheckEdit from "./cleanCheckEdit";
    export default {
        components:{
            cleanCheckCreate,
            cleanCheckDetail,
            cleanCheckEdit,
        },
        data(){
            return {
                tableHeight: this.$store.state.tableHeight,
                userCode: '',
                showDelete: '',


                inspectInfo:[],
                pkCode:'',
                pkNames: '',
                inspectTime:[],
                keyword: "",

                taskCode: '', //任务编号
                showCreate: false,
                showDetail: false,
                showEdit: false,
                paramsCreate:{},
                paramsDetail:{},
                paramsEdit:{},

                pageSize: 25,// 默认每页条数
                totalCount: 0,// 总条数
                currentPage: 1,// 当前页数

                //最近日期
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            }
        },
        created: function () {
            this.selectPark();
            this.selectInfo();
            this.userCode = this.$cookieStore.getCookie("userCode");
            this.showDelete = this.$flagMenuStore.judgeMenu("deleteInspectInfo");
        },
        methods: {
            // 搜索框
            searchCon() {
                this.handleCurrentChange(1);
            },

            // 分页
            handleSizeChange(val) {
                this.pageSize = val;
                this.handleCurrentChange(1);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.selectInfo();
            },

            selectInfo(){
                let vm = this;
                let inspectStartTime = '';
                let inspectEndTime = '';
                if(vm.inspectTime){
                    inspectStartTime = vm.inspectTime[0];
                    inspectEndTime = vm.inspectTime[1];
                }


                let param = {
                    pageSize: vm.pageSize,
                    start: (vm.currentPage - 1) * vm.pageSize,
                    keyword: vm.keyword,
                    inspectStartTime: inspectStartTime,
                    inspectEndTime: inspectEndTime,
                    entity:{
                        pkCode: vm.pkCode,
                        inspectType: '1',
                    }
                };
                let parkOptions = {
                    method: 'POST',
                    data: param,
                    url: "inspectInfo/query",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    vm.inspectInfo = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                }).catch(function (error) {
                    vm.$alert('页面：检查列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // 查询所有园区
            selectPark(){
                let vm = this;
                let param = {
                    entity:{

                    }
                };
                let parkOptions = {
                    method: 'POST',
                    data: param,
                    url: "park/list",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    let result = response.data.result;

                    let pkNameList = [];
                    pkNameList.push(
                        {code: '', name: "全部园区"}
                    );
                    for(let i = 0; i < result.length; i++){
                        pkNameList.push({
                            code: result[i].pkCode,
                            name: result[i].pkName
                        })
                    }
                    vm.pkNames = pkNameList;

                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            //新建检查
            createInspect(){
                let vm = this;
                vm.showCreate = true;
                vm.paramsCreate.showCreate = vm.showCreate;
                vm.paramsCreate.inspectType = '1';
            },

            //新建检查返回
            backCreate(val){
                this.showCreate = val.showCreate;
                this.selectInfo();
            },

            //检查详情
            inspectDetail(inspectCode){
                let vm = this;
                vm.showDetail = true;
                vm.paramsDetail.showDetail = vm.showDetail;
                vm.paramsDetail.inspectCode = inspectCode;
            },

            //检查详情返回
            backDetail(val){
                this.showDetail = val.showDetail;
            },

            //编辑
            inspectEdit(inspectCode){
                let vm = this;
                vm.showEdit = true;
                vm.paramsEdit.showEdit = vm.showEdit;
                vm.paramsEdit.inspectCode = inspectCode;

            },

            //编辑返回
            backEdit(val){
                this.showEdit = val.showEdit;
                this.selectInfo();
            },

            //提示删除
            notifyDelete(id){
                this.$confirm('确定删除该文件?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.inspectDelete(id);
                })
            },

            //删除
            inspectDelete(id){
                let vm = this;
                let params = {
                    entity: {
                        id: id,
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "inspectInfo/delete"
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        vm.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        vm.selectInfo();
                    }
                })
            },

            //模板管理
            templateManager(){
                let vm = this;
                let data = {
                    menuName: '检查项目管理',
                    item: 'proTemplateManager',
                    params: {
                        inspectType:"1",
                    }
                };
                vm.$emit('backParam', data);
            },

            //任务管理
            proTaskManager(){
                let vm = this;
                let data = {
                    menuName: '检查清单管理',
                    item: 'proTaskManager',
                    params: {
                        inspectType:"1",
                    }
                };
                vm.$emit('backParam', data);
            }
        }
    }

</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    .root {
        min-width: 1000px;
        height: 78vh;
        background-color: rgb(242, 242, 242);
        background-color: #ffffff;
    }

    .search_box {
        width: 100%;
        margin-top: -50px;
    }

    .search_buttons {
        background-color: white;
        text-align: left;
        width: 100%;
        height: 40px;
        line-height: 40px;
        margin-bottom: 6px;
        /*z-index: 200;*/
        /*border-bottom: 1px solid rgb(242, 242, 242);*/
    }

    .search_buttons span {
        float: left;
        margin-left: 20px;
        color: #409eff;
    }

    .search_box_create {
        float: right;
        margin-right: 20px;
    }

    /*租客信息框*/

    .contract_box_up_left_text span {
        margin-left: 20px;
    }

    .contract_code span {
        font-size: 16px;
        color: #999999;
    }

    /*表格*/
    .costTable {
        line-height: 40px;
        text-align: center;
        width: 100%;
    }

    .selectInfo{
        margin-left: 20px;
        width: 150px;
    }

    /*分页*/
    .emp-page-div {
        padding-top: 10px;
        text-align: left;
        background-color: rgb(242, 242, 242);

        height: 47px;
        width: 100%;
        line-height: 40px;

        display: block;
        position: fixed;
        bottom: 0px;
    }
</style>