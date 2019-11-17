<template>
    <div class="not-position_root" ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <el-input v-model="keyword"
                          @keyup.enter.native="searchCon()"
                          placeholder="访问人、被访者、车牌号"
                          class="input-with-select" size="small" clearable>
                    <el-button slot="append" icon="el-icon-search" @click="searchCon()"></el-button>
                </el-input>

                <el-select  v-model="pkCode" placeholder="请选择园区" class="select_park"
                           filterable size="small">
                    <el-option v-for="item in pkNames"
                               :key="item.code"
                               :label="item.name"
                               :value="item.code">
                    </el-option>
                </el-select>

                <el-form-item class="search_time">
                    <el-date-picker
                            style="width: 250px"
                            size="small"
                            v-model="dateList"
                            type="daterange"
                            align="right"
                            unlink-panels
                            :clearable="false"
                            range-separator="至"
                            start-placeholder="搜索开始日"
                            end-placeholder="搜索结束日">
                    </el-date-picker>
                </el-form-item>

                <el-button  size="small" type="primary" icon="el-icon-search" @click="searchCon" style="float: left;margin-left: 20px;">搜索</el-button>
                <el-button  size="small" type="success" icon="el-icon-plus" @click="createInfo" style="float: left;">新建</el-button>
            </div>

            <!-- 表格信息 -->
            <el-table
                    :height="tableHeight"
                    :data="tableList"
                    border
                    :highlight-current-row="true"
                    size="small">
                <el-table-column
                        align="center"
                        label="序号"
                        type="index"
                        width="50px">
                </el-table-column>
                <el-table-column
                        prop="pkName"
                        header-align="center"
                        min-width="120"
                        label="园区">
                </el-table-column>
                <el-table-column
                        prop="licenseNum"
                        header-align="center"
                        min-width="80"
                        label="车牌号">
                </el-table-column>
                <el-table-column
                        prop="visitorNum"
                        header-align="center"
                        min-width="80"
                        label="来访人数">
                </el-table-column>
                <el-table-column
                        prop="visitor"
                        header-align="center"
                        min-width="80"
                        label="访问人">
                </el-table-column>
                <el-table-column
                        prop="visitorPhone"
                        header-align="center"
                        min-width="100"
                        label="访问人手机">
                </el-table-column>

                <el-table-column
                        header-align="center"
                        min-width="100"
                        label="被访者">
                    <template slot-scope="scope">
                        <template v-if="scope.row.company">{{scope.row.company}}</template>
                        <template v-else>{{scope.row.otherCompany}}</template>
                    </template>
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        label="进入时间">
                    <template slot-scope="scope">{{scope.row.inTime | time}}</template>
                </el-table-column>
                <el-table-column
                        header-align="center"
                        min-width="100"
                        label="离开时间">
                    <template slot-scope="scope">{{scope.row.outTime | time}}</template>
                </el-table-column>
                <el-table-column
                        prop="inOutTypeName"
                        header-align="center"
                        min-width="80"
                        label="进出类型">
                </el-table-column>
                <el-table-column
                        prop="visitPurpose"
                        header-align="center"
                        min-width="120"
                        label="来访目的">
                </el-table-column>
                <el-table-column
                        prop="remark"
                        header-align="center"
                        min-width="120"
                        label="备注">
                </el-table-column>
                <el-table-column align="center"
                                 fixed
                                 width="180px"
                                 fixed="right"
                                 label="操作">
                    <template slot-scope="scope">
                        <el-button icon="el-icon-edit" type="text" plain size="small" @click="editInfo(scope.row,'edit')">编辑</el-button>
                        <el-button v-if="!scope.row.outTime" icon="el-icon-upload2" type="text" plain size="small" @click="editInfo(scope.row,'out')">离开</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page_down">
                <el-pagination style="margin-top:10px;"
                               background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="start"
                               :page-sizes="pageSizes"
                               :page-size="pageSize"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
            </div>

            <!--新建-->
            <car-in-out-manager-create v-if="showCreate" @backParam="backParamCreate($event)" :paramsCreate="paramsCreate"></car-in-out-manager-create>
            <!--编辑-->
            <car-in-out-manager-edit v-if="showEdit" @backParam="backParamEdit($event)" :paramsEdit="paramsEdit"></car-in-out-manager-edit>
        </el-form>

    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import carInOutManagerCreate from "./carInOutManagerCreate.vue";
    import carInOutManagerEdit from "./carInOutManagerEdit.vue";

    export default {
        components: {
            ElButton,
            ElCol,
            ElRow,
            carInOutManagerCreate,
            carInOutManagerEdit,
        },
        data() {
            return {
                tableHeight: this.$store.state.tableHeight,
                // 分页
                start: 1,
                pageSize: 50,
                pageSizes: [25, 50, 100, 150],
                total: 0,

                dateList: [],//日期
                tableList: [],//数据
                keyword:'',//关键词
                outDateStart:'',
                outDateEnd: '',

                pkCode: '',
                pkNames: [],

                showCreate: false,
                paramsCreate: {},

                showEdit: false,
                paramsEdit: {},
            }
        },
        created: function () {
            let vm = this;
            vm.selectAllPark();
            vm.searchCon();
        },

        methods: {
            //搜索框
            searchCon(){
                this.handleCurrentChange(1);
            },

            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryDate(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryDate();
            },
            /*---------------------分页end----------------------*/
            queryDate(){
                let vm = this;
                if(vm.dateList[0]){
                    vm.outDateStart = vm.dateList[0].getTime();
                }
                if(vm.dateList[1]){
                    vm.outDateEnd = vm.dateList[1].getTime();
                }

                let params = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    keyword: vm.keyword,
                    outDateStart: vm.outDateStart,
                    outDateEnd: vm.outDateEnd,
                    entity: {
                        pkCode: vm.pkCode
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "/car/query"
                }
                this.$ajax(
                    options
                ).then(response =>{
                    vm.tableList = response.data.result.data;
                    vm.total = response.data.result.totalCount;
                }).catch(error =>{
                    vm.$alert('页面：查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                })
            },

            // 查询所有园区
            selectAllPark(){
                let vm = this;

                let parkOptions = {
                    method: 'POST',
                    data: {},
                    url: "park/list",
                };

                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (response) {
                        let result = response.data.result;
                        let pkNameList = [];
                        pkNameList.push(
                            {code: "", name: "全部园区"}
                        );
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push(
                                {code: code, name: name}
                            );
                        }
                        vm.pkNames = pkNameList;
                    }
                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },

            //新建
            createInfo(){
                let vm = this;
                vm.showCreate = true;
                vm.paramsCreate.showCreate = true;
            },

            backParamCreate(val){
                let vm = this;
                vm.showCreate = val.showCreate;
                vm.queryDate();
            },

            backParamEdit(val){
                let vm = this;
                vm.showEdit = val.showEdit;
                vm.queryDate();
            },

            //编辑
            editInfo(row,type){
                let vm = this;
                vm.showEdit = true;
                vm.paramsEdit.dataObj = row;
                vm.paramsEdit.showEdit = vm.showEdit;
                vm.paramsEdit.type = type;
            }

        }
    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }
    /*分页*/
    .page_down {
        text-align: left;
        background-color: rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        clear: both;
        display: block;
        margin: 0px auto;
        position: fixed;
        bottom: 0px;
        width: 100%;
        z-index: 10;
    }

    /*搜索框*/
    .input-with-select {
        width: 245px;
        float: left;
        margin-left: 20px;
    }

    /*园区选择下拉选*/
    .select_park {
        width: 200px;
        line-height: 30px;
        float: left;
        margin-left: 20px;
    }

    .search_time{
        float: left;
        margin-left: 20px;
    }
    /deep/ .el-form-item{
        margin-bottom: 0;
    }
</style>