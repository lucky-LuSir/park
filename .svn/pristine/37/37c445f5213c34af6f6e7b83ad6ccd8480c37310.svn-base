<template>
    <div class="contractExpire">
        <el-card shadow="hover">
            <div slot="header" style="height: 30px; line-height: 30px; text-align: left;">
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <span class="park-data-title-font">合同到期监控图看板</span>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-button class="edit-button" size="small" type="primary" @click="deleteBoard">删除</el-button>
                    </el-col>
                </el-row>
            </div>

            <el-row class="contractExpireHead">
                <el-col :span="12">
                    <p class="title">
                        合同到期监控图
                        <el-tooltip class="item" effect="dark" placement="top-start">
                            <div style="line-height: 18px;" slot="content">横轴显示从最早有到期合同的月份开始，往后推36个月结束。<br />纵轴显示到期合同份数。
                            </div>
                            <i class="el-icon-diy-wenhao" style="font-size: 15px; cursor: pointer;"></i>
                        </el-tooltip>
                    </p>
                </el-col>
                <el-col :span="12">
                    <div class="desc">
                        <span style="display: inline-block;width: 6px;height: 6px;background: #609dfc;border-radius: 6px;margin-bottom: 2px; cursor: pointer;"></span>
                        <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">每月合同到期数(份)</span>
                    </div>
                    <el-date-picker :clearable=false size="mini" v-model="contractYear" @change="contractYearChange()" type="year" placeholder="选择年">
                    </el-date-picker>
                    <div class="changeEcharts-view">
                        <i @click="toggleEcharts(1)" class="histogram iconfont el-icon-diy-zhuzhuangtu"></i>
                        <i @click="toggleEcharts(2)" class="broken iconfont el-icon-diy-tubiaozhexiantu"></i>
                    </div>
                </el-col>
            </el-row>

            <el-row class="contractExpireMain" type="flex">
                <el-col :span="24">
                    <div class="eChartsBox">
                        <div class="contractExpireEcharts" ref="contractExpireEcharts" id="contractExpireEcharts"></div>
                    </div>
                </el-col>
            </el-row>
        </el-card>

        <el-dialog center title="合同信息" :visible.sync="showContract" :close-on-click-modal="false" width="60%" append-to-body>
            <el-table size="mini" :data="contractList" height="450" style="width: 100%;">
                <el-table-column align="center" label="序号" type="index" width="50px">
                </el-table-column>
                <el-table-column prop="ctName" label="合同编号" min-width="180">
                </el-table-column>
                <el-table-column prop="companyName" label="公司名称" width="180">
                </el-table-column>
                <el-table-column min-width="100" label="截止日期">
                    <template slot-scope="scope">{{scope.row.contractEndTime | timeDate}}</template>
                </el-table-column>
                <el-table-column fixed="right" min-width="70" label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" @click="showDetail(scope.row.ctCode)">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="page_down">
                <el-pagination style="margin-top:10px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="start" :page-sizes="pageSizes" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total">
                </el-pagination>
            </div>
            <div style="clear: both"></div>
        </el-dialog>
    </div>
</template>
<script>
    import ElDialog from "../../../../node_modules/element-ui/packages/dialog/src/component";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    export default {
        components: {
            ElButton,
            ElDialog,
        },
        props: ["id", "param"],
        data() {
            return {
                // 分页
                start: 1,
                pageSize: 10,
                pageSizes: [10, 15, 20],
                total: 0,
                mmAndYY: '',
                paramContractDatil: {},
                contractList: [],
                showContract: false,
                UserStatusOptions: [],
                UserStatusValue: "",
                contractYear: "2019",
                contractExpireEchartsOptions: {},
                toggleEchartsCode: 1, // 1是柱状图, 2是折线图
            };
        },
        created() {},
        mounted() {
            this.$nextTick(() => {
                this.$forceUpdate();
                this.getContractExpire();
            });
        },
        updated() {
            document.querySelector("#contractExpireEcharts").style.width = window.innerWidth - 320 + "px";
        },
        methods: {
            toggleEcharts(code) {
                this.toggleEchartsCode = code;
                this.contractYearChange();
            },
            contractYearChange() {
                let contractYear = new Date(this.contractYear).getFullYear();
                this.getContractExpire(contractYear);
            },
            showContractExpireEcharts(xAxisMsg = [], seriesMsg = []) {
                if (this.toggleEchartsCode === 1) {
                    this.contractExpireEchartsOptions = {
                        width: "99%",
                        color: ["#02c2ff"],
                        xAxis: [{
                            type: "category",
                            data: xAxisMsg,
                            axisLabel: {
                                interval: 0,
                                // rotate: 40, // 文字倾斜
                                textStyle: {
                                    align: "center"
                                },
                                color: function(value) {
                                    return (value = "#02c2ff");
                                },
                                formatter(value) {
                                    // 不是01月份的全部把年份给替换掉
                                    if (value.slice(0, 2) !== "01") {
                                        return value.replace(value.slice(2, 7), "");
                                    }
                                    if (value.slice(0, 2) === "01") {
                                        // 将年份换行到下面
                                        return value.replace("-", "\n");
                                    }
                                }
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        tooltip: {
                            axisPointer: {
                                type: ""
                            },
                            formatter(params) {
                                return (
                                    params.name.slice(3, 7) +
                                    params.name.slice(2, 3) +
                                    params.name.slice(0, 2) +
                                    "月" +
                                    " <br/> " +
                                    "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                    "&nbsp;" +
                                    params.value +
                                    "份&nbsp;&nbsp;&nbsp;"
                                );
                            },
                            textStyle: {
                                align: "left"
                            }
                        },
                        grid: {
                            left: 5,
                            right: 5,
                            top: 30,
                            bottom: 20,
                            containLabel: true,
                            tooltip: {
                                //---鼠标焦点放在图形上，产生的提示框
                                show: true,
                                trigger: "item", //---触发类型
                                textStyle: {
                                    color: "yellow"
                                }
                            }
                        },
                        yAxis: [{
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: true
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }],
                        series: [{
                            name: "2019",
                            data: seriesMsg,
                            type: "bar",
                            barCategoryGap: "60%", //调整柱子宽度
                            itemStyle: {
                                normal: {
                                    // shadowBlur: 30,
                                    // shadowColor: 'rgba(255,227,42,0.3)', 
                                    // shadowOffsetX: -5,
                                    // shadowOffsetY: 5,
                                    barBorderRadius: [10, 10, 10, 10],
                                    color: new this.$echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [{
                                                offset: 0,
                                                color: "#55c8eb"
                                            },
                                            {
                                                offset: 0.5,
                                                color: "#4387fd"
                                            },
                                            {
                                                offset: 1,
                                                color: "#5866e0"
                                            }
                                        ]
                                    ),
                                    opacity: 0.85
                                },
                                emphasis: {
                                    color: "#ff8d1f"
                                }
                            }
                        }]
                    };
                } else {
                    this.contractExpireEchartsOptions = {
                        color: "#4f85fd",
                        grid: {
                            top: '13%', // 等价于 y: '16%'
                            left: '4%',
                            right: '6%',
                            bottom: '4%',
                            containLabel: true
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                lineStyle: {
                                    color: "#1875f0",
                                    type: "dashed"
                                }
                            },
                            formatter(params) {
                                params = params[0];
                                return (
                                    params.name.slice(3, 7) +
                                    params.name.slice(2, 3) +
                                    params.name.slice(0, 2) +
                                    "月" +
                                    " <br/> " +
                                    "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                    "&nbsp;" +
                                    params.value +
                                    "份&nbsp;&nbsp;&nbsp;"
                                );
                            },
                            textStyle: {
                                color: 'yellow',
                                decoration: 'none',
                                fontFamily: 'Verdana, sans-serif',
                            },
                        },
                        xAxis: [{
                            type: "category",
                            data: xAxisMsg,
                            axisLabel: {
                                interval: 0,
                                rotate: 40, // 文字倾斜
                                color: function(value) {
                                    return (value = "#4f85fd");
                                },
                                textStyle: {
                                    align: "right",
                                    fontSize: 14
                                }
                            },
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: false
                            }
                        }],
                        yAxis: {
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: { // Y轴设置为虚线
                                show: true,
                                lineStyle: {
                                    type: "dashed"
                                }
                            },
                            axisLine: {
                                show: false
                            }
                        },
                        series: [{
                            // smooth: true, // 设置折线图是曲线
                            data: seriesMsg,
                            type: "line",
                            itemStyle: {
                                emphasis: {
                                    color: "#4f85fd"
                                },
                                normal: {
                                    label: {
                                        show: true
                                    }
                                }
                            },
                        }]
                    };
                }
                let vm = this;

                let contractExpireEcharts = this.$refs.contractExpireEcharts;
                
                contractExpireEcharts = this.$echarts.init(contractExpireEcharts);
                contractExpireEcharts.setOption(this.contractExpireEchartsOptions, true);
                contractExpireEcharts.on('click', function(params) {
                    vm.showContract = true;
                    vm.mmAndYY = params.name;
                    vm.queryContract();
                })
                window.addEventListener("resize", () => {
                    var contractExpireMain = document.querySelector(".contractExpireMain");
                    if (contractExpireMain) {
                        document.querySelector("#contractExpireEcharts").style.width = window.innerWidth - 320 + "px";
                        contractExpireEcharts.resize();
                    }
                })
            },
            // 通过当前年份来获取合同数
            async getContractExpire(contractYear) {
                var myDate = new Date();
                var year = myDate.getFullYear();
                let res = await this.$ajax.post(`/maturity/selectMaturityCon`, {
                    entity: {
                        maturityYear: contractYear || year
                    }
                });
                let result = res.data.result;
                let xAxisMsg = [];
                let seriesMsg = [];
                result.filter((item, index, arr) => {
                    xAxisMsg.push(item.maturityMonth + "-" + item.maturityYear);
                    seriesMsg.push(item.maturityCount);
                });
                this.showContractExpireEcharts(xAxisMsg, seriesMsg);
            },
            // 删除看板
            deleteBoard() {
                this.$confirm("此操作将永久删除该看板, 是否继续?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                    .then(() => {
                        let vm = this;
                        let param = {};
                        let entity = {
                            id: this.id
                        };
                        param.entity = entity;
                        let options = {
                            method: "POST",
                            data: param,
                            url: "board/delete"
                        };
                        this.$ajax(options)
                            .then(response => {
                                if (response) {
                                    vm.$message({
                                        showClose: true,
                                        message: "删除看板成功",
                                        type: "success"
                                    });
                                    // this.$router.replace('/back');
                                    vm.backParent();
                                }
                            })
                            .catch(error => {
                                vm.$alert("页面:数据统计看板失败", "系统异常", {
                                    confirmButtonText: "确定",
                                    callback: action => {}
                                });
                            });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除"
                        });
                    });
            },
            backParent() {
                this.$emit("backParam", "1");
            },
            /*---------------------分页start---------------------*/
            handleSizeChange(val) {
                this.pageSize = val;
                this.start = 1;
                this.queryContract(1);
            },
            handleCurrentChange(val) {
                this.start = val;
                this.queryContract();
            },
            /*---------------------分页end----------------------*/
            // 合同详情
            showDetail(ctCode) {
                let vm = this;
                vm.showContract = false;

                let data = {
                    params: {
                        ctCode: ctCode
                    }
                };
                vm.$emit("backParam", data);

            },

            queryContract() {
                let vm = this;
                let params = {
                    start: (vm.start - 1) * vm.pageSize,
                    pageSize: vm.pageSize,
                    mmAndYY: vm.mmAndYY,
                    entity: {}
                }
                let options = {
                    method: "post",
                    data: params,
                    url: "contract/expairDetail"
                }
                this.$ajax(
                    options
                ).then(res => {
                    if (res) {
                        vm.contractList = res.data.result.data;
                        vm.total = res.data.result.totalCount;
                    }
                })
            },

        },
    };
</script>

<style lang="less" scoped>
    .contractExpire {
        background-color: #fff;
        overflow-x: hidden;

        .contractExpireHead {
            width: 100%;
            text-align: left;
            /*padding-left: 20px;*/
            overflow: hidden;
            padding-bottom: 12px;

            .title {
                float: left;
                /*margin-left: 2%;*/
            }

            .desc {
                float: right;
                margin-right: 8%;
                line-height: 25px;
                padding-top: 4px;
            }

            .el-date-editor {
                float: right;
                width: 100px;
                margin-right: 25px;
            }

            .el-input {
                min-width: 80px;
                width: 90px;
            }

            .changeEcharts-view {
                float: right;
                margin-right: 30px;
                .iconfont {
                    color: #4494f0;
                    cursor: pointer;
                }
            }
        }

        .contractExpireMain {
            text-align: left;
            /*padding-left: 20px;*/
            padding-right: 20px;
        }

        #contractExpireEcharts {
            width: 100%;
            height: 450px;
        }
    }

    .rece-cost-exception-div {
        height: 50px;
    }

    /*-------------------------------公共元素  每个div结束后分隔的时候用----------------------------------------*/

    /*园区去化看板文字*/
    .park-data-title-font {
        font-weight: bold;
        color: #409EFF
    }

    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-right: 10px;
    }

    .page_down {
        float: left;
    }

    /* -----------------------------------头部区域---------------------------------------- */
</style>