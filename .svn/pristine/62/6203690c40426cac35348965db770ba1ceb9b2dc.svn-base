import axios from 'axios'

export function usedQueryDept() {
    let params = {
        entity:{
            hasUseQuery: true
        }
    };

    let option = {
        method: 'POST',
        data: params,
        url: 'dept/list'
    };
    axios(
        option
    ).then(response => {
        let usedQueryDept = [];
        for(let i = 0; i < response.data.result.length; i++){
            let deptCode = response.data.result[i].deptCode;
            let deptName = response.data.result[i].deptName;
            let deptInfo = {deptCode: deptCode,deptName:deptName}
            usedQueryDept.push(deptInfo);
        }
        window.localStorage.setItem("_usedQueryDept",JSON.stringify(usedQueryDept));
    }).catch(error =>{
        this.$message({
            showClose: true,
            message: '获取可检索部门失败!',
            type: 'error'
        });
    })
}