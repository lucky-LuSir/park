<template>
    <!-- 缴费列表弹窗 -->
    <el-dialog title="缴费列表" :visible.sync="showPayDetail" width="70%" style="line-height: 40px;"
               :before-close="beforeClose"
               :center="true">
        <el-table :data="paymentHistoryList" border height="400" style="margin-top: -20px;">

            <el-table-column align="center" label="缴费类型">
                <template slot-scope="scope">{{ scope.row.costTypeName }}</template>
            </el-table-column>
            <el-table-column align="center" label="应收开始时间">
                <template slot-scope="scope">{{ scope.row.costStartTime | timeDate}}</template>
            </el-table-column>
            <el-table-column align="center" label="应收结束时间">
                <template slot-scope="scope">{{ scope.row.costEndTime | timeDate}}</template>
            </el-table-column>
            <el-table-column align="center" label="应收金额" prop="receivableRent"></el-table-column>
            <el-table-column align="center" label="实收金额" prop="realRent"></el-table-column>
            <el-table-column align="center" width="160px" label="收款时间">
                <template slot-scope="scope">{{ scope.row.payDate | time}}</template>
            </el-table-column>


            <!--<el-table-column align="center" label="租金状态">-->
                <!--<template slot-scope="scope">{{ scope.row.payStatusName }}</template>-->
            <!--</el-table-column>-->
            <!--<el-table-column align="center" label="是否含税" show-overflow-tooltip>-->
                <!--<template slot-scope="scope">{{ scope.row.hasTax == true ? "是" : "否"}}</template>-->
            <!--</el-table-column>-->
            <!--<el-table-column align="center" label="税点">-->
                <!--<template slot-scope="scope">{{scope.row.taxPoint == null ? "-&#45;&#45;" : scope.row.taxPointName}}-->
                <!--</template>-->
            <!--</el-table-column>-->
            <!--<el-table-column align="center" width="150px" label="税金">-->
                <!--<template slot-scope="scope">{{scope.row.taxPrice == null ? 0 : scope.row.taxPrice}}&nbsp;元-->
                <!--</template>-->
            <!--</el-table-column>-->
        </el-table>
    </el-dialog>
</template>
<script>
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default{
        components: {
            ElCol,
            ElRow},
        props: ['paramsPayDetail'],
        created(){
            let vm = this;
            if(vm.paramsPayDetail){
                vm.showPayDetail = vm.paramsPayDetail.showPayDetail;
                let code = vm.paramsPayDetail.code;
                vm.paymentList(code);
            }
        },
        data(){
            return{
                showPayDetail:false,
                paymentHistoryList:[],// 缴费列表
                code:"",//应付编号
            }
        },
        methods: {
                paymentList(code){
                    let vm = this;

                    vm.paymentWindow = true;

                    let params = {
                        entity: {
                            code: code
                        }
                    };
                    let options = {
                        method: "POST",
                        data: params,
                        url: "pay/contractPayList",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        if (!response.data.isException) {
                            vm.paymentHistoryList = response.data.result;
                        }
                    }).catch(error => {
                        vm.$alert('页面：缴费列表查询失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                },
            beforeClose() {
                let vm = this;
                vm.showPayDetail = false;
                let data = {
                    showPayDetail:vm.showPayDetail,
                };
                vm.$emit('backParam',data);
            },
        },
    }
</script>