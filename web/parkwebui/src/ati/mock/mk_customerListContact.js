import Mock from 'mockjs';

export default Mock.mock('http://www.testMock.com/park/customer/query/fenctact', {
    "isSuccess": true,
    "code": "200",
    "message": "操作成功!",
    "isException": false,
    "result": {
        "start": 0,
        "totalCount": 20,
        "pageSize": 20,
        "data|2-3": [{
            "leaf": "NO",
            "level":1,

            "contactPhone": "181",
            "contactName": "王",
            "contactSex": "aa",
            "contactJob": "aa",

        },




        ],
        "currentPageNo": 1,
        "hasNextPage": false,
        "totalPageCount": 1,
        "dataSizeOfCurrentPage": 2,
        "hasPreviousPage": false
    }
});
