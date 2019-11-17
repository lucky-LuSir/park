<template>
    <div class="not-position_root" ref="myRoot">
        <el-form>
            <!-- 悬浮框 -->
            <div class="not-position_header">
                <div class="search_buttons">
                    <div style="float: left;">
                        <el-input
                                v-model="keyword"
                                @keyup.enter.native="searchCon()"
                                placeholder="车主、车牌号"
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
                                @change="searchCon"
                                size="small">
                            <el-option v-for="item in pkNames"
                                       :key="item.code"
                                       :label="item.name"
                                       :value="item.code">
                            </el-option>
                        </el-select>
                    </div>
                    <div class="search_box_create">
                        <el-button  class="add-button" type="primary" @click="createInfo" size="small">
                            新增
                        </el-button>
                    </div>
                </div>
            </div>

            <!-- 表格化信息 -->
            <div class="contract_box_top">
                <el-table class="costTable"
                          :data="dataList"
                          border
                          :highlight-current-row="true"
                          size="small"
                          :height="tableHeight">
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            min-width="50px">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="licenseNum"
                            label="车牌号"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="carOwnerName"
                            label="车主"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="carOwnerPhone"
                            label="联系电话"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="carOwnerIdCard"
                            label="身份证号"
                            align="center"
                            min-width="100">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="pkName"
                            label="所属园区"
                            align="center"
                            min-width="150">
                    </el-table-column>
                    <el-table-column
                            show-overflow-tooltip
                            prop="company"
                            label="所属客户"
                            align="center"
                            min-width="150">
                    </el-table-column>
                    <el-table-column align="center" min-width="120px" label="驾驶证">
                        <template slot-scope="scope">
                            <img v-for="(item, index) in scope.row.drivingLicenceList" :key="index" class="img_attachment" :src=" parkBaseUrl + 'file/read/' + item ">
                        </template>
                    </el-table-column>
                    <el-table-column align="center" min-width="120px" label="行驶证">
                        <template slot-scope="scope">
                            <img v-for="(item, index) in scope.row.vehicleLicenseList" :key="index" class="img_attachment" :src=" parkBaseUrl + 'file/read/' + item ">
                        </template>
                    </el-table-column>


                    <el-table-column
                            header-align="center"
                            fixed="right"
                            label="操作"
                            min-width="100">
                        <template slot-scope="scope">
                            <el-button @click="editRow(scope.row.id)" type="text" size="small">修改</el-button>
                            <el-button @click="deleteRow(scope.row.id)" type="text" size="small">删除</el-button>
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
        <car-manager-create v-if="showCreate" @backParam="backCreate($event)" :paramsCreate="paramsCreate"></car-manager-create>
        <car-manager-edit v-if="showEdit" @backParam="backEdit($event)" :paramsEdit="paramsEdit"></car-manager-edit>
    </div>
</template>

<script>
    import carManagerCreate from './carManagerCreate'
    import carManagerEdit from './carManagerEdit'
    export default {
        components:{
            carManagerCreate,
            carManagerEdit
        },
        data(){
            return {
                tableHeight: this.$store.state.tableHeight,
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
                keyword: '',
                pkCode: '',
                pkNames: [],
                dataList: [],
                showCreate: false,
                paramsCreate: {},

                showEdit: false,
                paramsEdit: {},

            }
        },
        created: function () {
            this.searchCon();
            this.selectPark();
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
                let param = {
                    pageSize: vm.pageSize,
                    start: (vm.currentPage - 1) * vm.pageSize,
                    keyword: vm.keyword,
                    entity:{
                        pkCode: vm.pkCode,
                    }
                };
                let parkOptions = {
                    method: 'POST',
                    data: param,
                    url: "carManager/query",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    vm.dataList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;

                    if (vm.dataList) {
                        for (let i = 0; i < vm.dataList.length; i++) {
                            if (vm.dataList[i].drivingLicence){
                                let drivingLicenceList = vm.dataList[i].drivingLicence.split(",");
                                vm.dataList[i].drivingLicenceList = drivingLicenceList;
                            }
                            if (vm.dataList[i].vehicleLicense){
                                let vehicleLicenseList = vm.dataList[i].vehicleLicense.split(",");
                                vm.dataList[i].vehicleLicenseList = vehicleLicenseList;
                            }
                        }
                    }

                }).catch(function (error) {
                    vm.$alert('页面：查询失败', '系统异常', {
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

            //删除
            deleteRow(id){
                let vm = this;
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() =>{
                    let params = {
                        entity: {
                            id: id,
                        }
                    }
                    let options = {
                        method: "POST",
                        data: params,
                        url: "carManager/delete"
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
                }).catch(() => {
                    vm.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                })
            },
            createInfo(){
                this.showCreate = true;
                this.paramsCreate.showCreate = this.showCreate;
            },
            backCreate(val){
                this.showCreate = val.showCreate;
                this.selectInfo();
            },

            editRow(id){
                this.showEdit = true;
                this.paramsEdit.showEdit = this.showEdit;
                this.paramsEdit.id = id;
            },
            backEdit(val){
                this.showEdit = val.showEdit;
                this.selectInfo();
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
        width: 200px;
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
    /deep/ .el-input{
        width: 215px ;
    }
    /deep/ .el-pagination__editor.el-input {
        width: 50px;
    }
    /*图片展示*/
    .img_attachment {
        width: 50px;
        height: 50px;
    }
</style>