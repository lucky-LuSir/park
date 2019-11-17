<template>
    <div class="has-position_root" ref="myRoot">
        <div class="has-position_header">
            <!--引入添加看板组件-->
            <board class="add-data-box" @backParam="backParam($event)"></board>
        </div>
        <div class="has-position_main">
            <div class="comm-box" ref="myRoot">
                <!-- 合同到期 -->
                <div v-for="item in contractExpires" :key="item.id" class="comm-box-div">
                    <contract-Expire :id="item.id" :param="item.param" @backParam="backParamExpair($event)"></contract-Expire>
                </div>

                <!-- 数据统计看板 -->
                <div v-for="item in totalBoards" :key="item.id" class="comm-box-div">
                    <total-Board :id="item.id" :param="item.param" @backParam="backParam($event)"></total-Board>
                </div>

                <!-- 去划面积看板-->
                <div v-for="item in sellingDetails" :key="item.id" class="comm-box-div">
                    <selling-detail :id="item.id" :param="item.param" @backParam="backParam($event)"></selling-detail>
                </div>

                <!-- 毛利率统计看板-->
                <div v-for="item in grossProfits" :key="item.id" class="comm-box-div">
                    <gross-profit :id="item.id" :param="item.param" @backParam="backParam($event)"></gross-profit>
                </div>

                <!-- 应收情况总览看板-->
                <div v-for="item in receivableCases" :key="item.id" class="comm-box-div">
                    <receivable-case :id="item.id" :param="item.param" @backParam="backParam($event)"></receivable-case>
                </div>

                <!--mine看板-->
                <div v-for="item in mines" :key="item.id" class="comm-box-div">
                    <mine :id="item.id" :param="item.param" @backParam="backParam($event)"></mine>
                </div>

                <!--园区去化看板-->
                <div v-for="item in removals" :key="item.id" class="comm-box-div">
                    <removal :id="item.id" :param="item.param" @backParam="backParam($event)"></removal>
                </div>

                <!--招商人员去化看板-->
                <div v-for="item in investDatas" :key="item.id" class="comm-box-div">
                    <invest-data :id="item.id" :param="item.param" @backParam="backParam($event)"></invest-data>
                </div>

                <!--招商人员任务数据看板-->
                <div v-for="item in todayTasks" :key="item.id" class="comm-box-div">
                    <today-task :id="item.id" :param="item.param" @backParam="backParam($event)"></today-task>
                </div>

                <!--应收租金费用异常看板-->
                <div v-for="item in rentExceptions" :key="item.id" class="comm-box-div">
                    <rent-exception :id="item.id" :param="item.param" @backParam="backParam($event)"></rent-exception>
                </div>

                <!-- 应收电费用异常看板-->
                <div v-for="item in eleExceptions" :key="item.id" class="comm-box-div">
                    <ele-exception :id="item.id" :param="item.param" @backParam="backParam($event)"></ele-exception>
                </div>

                <!-- 应收水费用异常看板-->
                <div v-for="item in waterExceptions" :key="item.id" class="comm-box-div">
                    <water-exception :id="item.id" :param="item.param" @backParam="backParam($event)"></water-exception>
                </div>

                <!-- 应收停车费用异常看板-->
                <div v-for="item in parkingExceptions" :key="item.id" class="comm-box-div">
                    <parking-exception :id="item.id" :param="item.param" @backParam="backParam($event)"></parking-exception>
                </div>
            </div>
        </div>

        <div class="side"></div>
    </div>
</template>

<script>
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import board from "../../common/components/board.vue";
    import rentException from "./subBoard/rentException.vue";
    import eleException from "./subBoard/eleException.vue";
    import waterException from "./subBoard/waterException.vue";
    import parkingException from "./subBoard/parkingException.vue";
    import investData from "./subBoard/investDataBoard.vue";
    import todayTask from "./subBoard/todayTask.vue";
    import mine from "./subBoard/mine.vue";
    import removal from "./subBoard/removal.vue";
    import receivableCase from "./subBoard/receivablecase.vue";
    import grossProfit from "./subBoard/grossProfit.vue";
    import sellingDetail from "./subBoard/sellingDetail.vue";
    import totalBoard from "./subBoard/totalBoard.vue";
    import contractExpire from "./subBoard/contractExpire.vue";

    export default {
        components: {
            ElButton,
            board,
            mine,
            removal,
            investData,
            todayTask,
            rentException,
            eleException,
            waterException,
            parkingException,
            receivableCase,
            grossProfit,
            sellingDetail,
            totalBoard,
            contractExpire
        },
        data() {
            return {
                dialogVisible: false,
                /*--------------------------- 看板集合 ---------------------------*/
                mines: [],
                removals: [],
                investDatas: [],
                todayTasks: [],
                rentExceptions: [],
                eleExceptions: [],
                waterExceptions: [],
                parkingExceptions: [],
                receivableCases: [],
                grossProfits: [],
                sellingDetails: [],
                totalBoards: [],
                contractExpires: []
            };
        },
        created: function() {
            this.initCreate();
        },
        methods: {
            initCreate() {
                let vm = this;
                vm.mines = [];
                vm.removals = [];
                vm.investDatas = [];
                vm.todayTasks = [];
                vm.rentExceptions = [];
                vm.eleExceptions = [];
                vm.waterExceptions = [];
                vm.parkingExceptions = [];
                vm.receivableCases = [];
                vm.grossProfits = [];
                vm.sellingDetails = [];
                vm.totalBoards = [];
                vm.contractExpires = [];

                let param = {};
                let options = {
                    method: "POST",
                    data: param,
                    url: "board/list"
                };

                this.$ajax(options)
                    .then(function(response) {
                        //填充看板集合
                        let boards = response.data.result;
                        for (let i = 0; i < boards.length; i++) {
                            let board = boards[i];
                            if (board.pgCode === "001") {
                                vm.mines.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "002") {
                                vm.removals.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "003") {
                                vm.investDatas.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "004") {
                                vm.todayTasks.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "005") {
                                vm.rentExceptions.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "006") {
                                vm.eleExceptions.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "007") {
                                vm.waterExceptions.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "008") {
                                vm.parkingExceptions.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "009") {
                                vm.receivableCases.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "010") {
                                vm.grossProfits.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "011") {
                                vm.sellingDetails.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "012") {
                                vm.totalBoards.push({
                                    id: board.id,
                                    param: board.param
                                });
                            } else if (board.pgCode === "013") {
                                vm.contractExpires.push({
                                    id: board.id,
                                    param: board.param
                                });
                            }
                        }
                    })
                    .catch(function(error) {
                        vm.$alert("页面:首页看板查询信息失败", "系统异常", {
                            confirmButtonText: "确定",
                            callback: action => {}
                        });
                    });
            },
            backParam(data) {
                this.initCreate();
            },

            backParamExpair(val) {
                if (val.params && val.params.ctCode) {
                    let data = {
                        flag: "add",
                        menuName: "合同详情",
                        item: "contractDetails",
                        params: {
                            ctCode: val.params.ctCode
                        }
                    };
                    this.$emit("backParam", data);
                } else {
                    this.initCreate();
                }
            }
        }
    };
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }

    /*根 div*/
    .root {
        /*height: 76vh;*/
        height: 86vh;
        /* min-width: 1000px; */
        background-color: rgb(242, 242, 242);
        overflow-y: scroll;
        /*border: solid 1px red;*/
    }

    /*添加看板按钮*/
    .comm-title-div {
        float: left;
        height: 50px;
        line-height: 50px;
        color: #409eff;
        margin-top: -52px;

        position: fixed;
        z-index: 200;
    }

    /*公共元素  每个div结束后分隔的时候用*/
    .comm-box-div {
        margin-bottom: 20px;
    }

    /*添加看板*/
    .add-data-box {
        float: right;
    }

    .ul-park-data li {
        float: left;
        margin-left: 5%;
        color: #919191;
    }

    .side {
        height: 100px;
    }

    .comm-box {
        margin-top: 10px;
    }
</style>