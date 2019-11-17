<template>
    <div class="parkAreaAnalysis">
        <div class="not-position_header">
            <el-select   v-model="territoryType" placeholder="请选择大区" class="selected" filterable size="small" @change="selectPark()">
                <el-option v-for="item in parkRegionsList"
                           :key="item.deptCode"
                           :label="item.deptName"
                           :value="item.deptCode">
                </el-option>
            </el-select>
            <el-select  v-model="pkCode" placeholder="请选择园区" class="selected" filterable size="small">
                <el-option v-for="item in pkNames"
                           :key="item.code"
                           :label="item.name"
                           :value="item.code">
                </el-option>
            </el-select>

            <el-button style="margin-top: 6px;margin-left: 10px;"
                       size="small" type="primary" @click="selectData()" icon="el-icon-search">查询</el-button>
            <el-button style="margin-top: 6px;margin-left: 10px;"
                       size="small" type="success" @click="exportReport()" icon="el-icon-download">导出</el-button>

        </div>

        <el-table
                :data="dataList"
                border
                size="small"
                :height="tableHeight"
                style="width: 100%;line-height: 30px;">
            <el-table-column
                    align="center"
                    label="序号"
                    type="index"
                    mini-width="50px">
            </el-table-column>
            <el-table-column
                    show-overflow-tooltip
                    label="园区名称"
                    prop="pkName"
                    align="center"
                    mini-width="110">
            </el-table-column>
            <el-table-column
                    prop="bdName"
                    label="楼栋名称"
                    align="center"
                    mini-width="130">
            </el-table-column>
            <el-table-column
                    prop="thisFloor"
                    label="楼层名称"
                    align="center"
                    mini-width="130">
            </el-table-column>
            <el-table-column
                    prop="investArea"
                    label="楼层总面积/㎡"
                    align="center"
                    mini-width="130">
            </el-table-column>
            <el-table-column
                    prop="rentableArea"
                    label="楼层可租面积/㎡"
                    align="center"
                    mini-width="130">
            </el-table-column>
            <el-table-column
                    prop="rentedArea"
                    label="楼层已租面积/㎡"
                    align="center"
                    mini-width="130">
            </el-table-column>
            <el-table-column
                    prop="contractRentedArea"
                    label="楼层合同已租面积/㎡"
                    align="center"
                    mini-width="140">
            </el-table-column>
            <el-table-column
                    label="是否异常"
                    align="center"
                    mini-width="130">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.rentedArea == scope.row.contractRentedArea" type="success">正常</el-tag>
                    <el-tag v-else type="danger">异常</el-tag>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        data() {
            return {
                tableHeight: this.$store.state.notPagingTableHeight,
                dataList:[],
                pkNames: [],// 园区集合
                pkCode: "",// 园区code
                territoryType: "",//大区code
                parkRegionsList: [],//大区列表
            }
        },
        created(){
            let vm = this;
            vm.selectPark();// 查询园区
            vm.selectParkRegions();//查询大区
        },
        methods: {
            // 查询园区
            selectPark(){
                let vm = this;
                vm.pkCode = "";

                let param = {};
                let entity = {
                    territoryType:vm.territoryType,
                };
                param.entity = entity;
                let parkOptions = {
                    method: 'POST',
                    data: param,
                    url: "park/list",
                };
                this.$ajax(
                    parkOptions
                ).then(function (response) {
                    if (response) {
                        let result = response.data.result;

                        let pkNameList = [];
                        pkNameList.push(
                            {code: '', name: "全部园区"}
                        );
                        for (let s = 0; s < result.length; s++) {
                            let code = result[s].pkCode;
                            let name = result[s].pkName;
                            pkNameList.push(
                                {code: code, name: name}
                            );
                        }
                        vm.pkNames = pkNameList;
                    } else {
                        vm.$message({
                            showClose: true,
                            message: '查询园区失败',
                            type: 'error'
                        });
                    }
                }).catch(function (error) {
                    vm.$alert('页面：园区列表查询失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });
            },

            // select查询全部大区
            selectParkRegions () {
                let deptList = window.localStorage.getItem("_usedQueryDept");
                this.parkRegionsList = JSON.parse(deptList);
                this.parkRegionsList.unshift(
                    {deptCode: '', deptName: '全部大区'}
                )
            },

            selectData(){
                let vm = this;
                let params = {
                    entity:{
                        pkCode: vm.pkCode,
                        territoryType: vm.territoryType
                    }
                }
                let options = {
                    method: "POST",
                    data: params,
                    url: "parkAreaAnalysis/list"
                }
                this.$ajax(
                    options
                ).then(response => {
                    if(response){
                        vm.dataList = response.data.result;
                    }
                }).catch(error =>{
                    vm.$alert('页面：数据获取失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                })
            },


            //文件导出
            exportReport() {
                let vm = this;
                let pkCode = vm.pkCode;
                let territoryType = vm.territoryType;
                if(vm.dataList.length <= 0) {
                    vm.$alert('页面：无导出数据', '温馨提示', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                    return;
                }

                // 线下测试
//                window.location.href="http://localhost:8380/park/parkAreaAnalysis/exportParkAreaAnalysisExcel" +
//                    "?pkCode="+pkCode+"&territoryType="+territoryType;

                // 线上导出
                window.location.href="/park/parkAreaAnalysis/exportParkAreaAnalysisExcel" +
                    "?pkCode="+pkCode+"&territoryType="+territoryType;
            },
        }
    }
</script>

<style scoped>
    .selected{
        margin-left: 20px;
    }
</style>



