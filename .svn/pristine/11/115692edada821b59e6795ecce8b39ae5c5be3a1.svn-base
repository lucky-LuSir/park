<template>
    <div class="root_css">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="title_font">我的数据</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button style="float: right;" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row :gutter="10">
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                    <el-card class="el_card_style" shadow="hover">
                        <p class="my-data-font">本月招商面积</p>
                        <p class="my-data-entity-data">{{thisMonthAreaSum}}&nbsp;平米</p>
                    </el-card>
                </el-col>
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                    <el-card class="el_card_style" shadow="hover">
                        <p class="my-data-font">本月意向客户</p>
                        <p class="my-data-entity-data">{{thisMonthFollow}}&nbsp;位</p>
                    </el-card>
                </el-col>
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left;">
                    <el-card class="el_card_style" shadow="hover">
                        <p class="my-data-font">本月新签合同</p>
                        <p class="my-data-entity-data">{{thisMonthContract}}&nbsp;份</p>
                    </el-card>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>

<script scoped>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        props: ['id','param'],
        created:function () {
            let vm = this;

            let sumData = {
                entity:{},
            };

            let options = {
                method: 'POST',
                data: sumData,
                url: 'contract/myData'
            };

            this.$ajax(
                options
            ).then(function (response) {
                vm.thisMonthAreaSum = response.data.result.areaSum;
                vm.thisMonthContract = response.data.result.contractCount;
            }).catch(function (error) {
                vm.$alert('页面:我的数据看板查看入驻人数失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            });
            let followUrl = "followup/myData";
            vm.methodTemplate(vm,sumData,followUrl);
        },
        data() {
            return {
                thisMonthAreaSum: '',// 本月招商总面积
                thisMonthContract: '',// 本月新签合同
                thisMonthFollow: '',// 本月意向客户
            }
        },
        methods: {
            methodTemplate(vm,dataObj,url){
                let options = {
                    method: "POST",
                    data: dataObj,
                    url: url
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.thisMonthFollow = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面:我的数据看板查询我的数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id:this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url:"board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        if (!response.data.isException) {
                            vm.$message({
                                showClose: true,
                                message: '删除看板成功',
                                type: 'success'
                            });
                            vm.backParent();
                        }
                    }).catch(error => {
                        vm.$alert('页面:删除我的数据看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            backParent() {
                this.$emit("backParam","1");
            },
        }
    }
</script>

<style scoped>
    .root_css {
        margin-right: 15px;
        width: 100%;

        overflow-x: hidden;
        overflow-y: hidden;
    }

    /*卡片标题*/
    .title_font {
        font-weight: bold;
        color: #409EFF
    }

    /*我的看板 - 文字样式*/
    .my-data-font{
        color: white;
        line-height: 40px;
        font-size: 20px;
        padding-top: 15px;
    }

    /*我的看板 - 绑定的实体数据样式*/
    .my-data-entity-data{
        color: #FAFAFA;
        font-size:20px;
    }

    /*卡片样式*/
    .el_card_style {
        background-color: #178679;
        width: 200px;
        height: 150px;
        text-align: center;
        margin-left: 30%;
    }
</style>