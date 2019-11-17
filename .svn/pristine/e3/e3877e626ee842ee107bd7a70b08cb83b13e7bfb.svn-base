// const BASE_URL = "http://localhost:8380/park/";
// module.exports = {
//     BASE_URL: BASE_URL
// };

// let BASE_URL = ""; //这里是一个默认的url，可以没有
// switch (process.env.NODE_ENV) {
//     case "dev":
//         BASE_URL = "http://localhost:8380/park/"; //开发环境url
//         break;
//     case "build":
// BASE_URL = "http://poms.yuanquwuyou.com/park/"; //生产环境url
//         break;
// }
// console.log(BASE_URL);
// export default { BASE_URL };
let BASE_URL = ""; //这里是一个默认的url，可以没有

if (process.env.NODE_ENV === "production") {
    // console.log("设置生产环境api接口url");
    BASE_URL = "https://poms.yuanquwuyou.com/park/"; //生产环境url
} else if (process.env.NODE_ENV === "test") {
    BASE_URL = "http://mg.test.yuanquwuyou.com/park/"; //测试环境url
} else {
    //dev 开发环境
    // console.log("开发环境api接口url");
    BASE_URL = "http://localhost:8380/park/"; //开发环境url
}
export default { BASE_URL };
