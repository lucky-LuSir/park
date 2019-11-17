<template>
    <div>
        <el-cascader
                :options="deptList"
                :props="{ checkStrictly: true }"
                v-model="deptCodeList"
                @change="getDeptCode()"
                size="small"
                placeholder="部门筛选"
                clearable>
        </el-cascader>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                // 部门集合
                deptList: [
                    {
                        label: '',
                        value: '',
                        children: [
                            {
                                label: '',
                                value: '',
                                children: []
                            }
                        ]
                    }
                ],
                deptCodeList: [],// 部门编码
            }
        },
        created(){
            let vm = this;
            vm.selectAllDept();// 查询所有部门
        },
        methods: {
            // 查询所有部门
            selectAllDept(){
                let vm = this;

                let options = {
                    method: 'POST',
                    data: {},
                    url: 'dept/cascaderDeptTree',
                };
                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        vm.deptList = response.data.result;
                    }
                }).catch(error => {
                    vm.$message({
                        showClose: true,
                        message: '查询部门失败',
                        type: 'error'
                    });
                })
            },

            // 获取deptCode
            getDeptCode() {
                let vm = this;

                /**
                 * el-cascader级联组件获取的值为数组
                 * 当数组的长度为1时，说明选择的是CEO部门，此时查询的数据是全部部门的数据
                 * 数组长度大于1，获取数组中最后一个元素，代表当前部门
                 */
                let deptCode = '';
                if (vm.deptCodeList.length > 1) {
                    deptCode = vm.deptCodeList[vm.deptCodeList.length - 1];
                }

                vm.$emit('sendDeptCode', deptCode);
            },
        },
    }
</script>

<style scoped>

</style>