// 在vue-config.js 中加入
const CompressionWebpackPlugin = require("compression-webpack-plugin");
const isProduction = process.env.NODE_ENV === "production";
// 作为配置文件，直接导出配置对象即可

const cdn = {
    // 生产环境
    build: {
        css: [
            "https://cdn.bootcss.com/element-ui/2.11.1/theme-chalk/index.css"
        ],
        js: [
            "https://cdn.bootcss.com/vue/2.6.10/vue.runtime.min.js",
            "https://lib.baomitu.com/vue-router/3.0.1/vue-router.min.js",
            "https://lib.baomitu.com/vuex/3.0.1/vuex.min.js",
            "https://lib.baomitu.com/axios/0.18.0/axios.min.js",
            "https://cdn.bootcss.com/element-ui/2.11.1/index.js"
        ]
    }
};

module.exports = {
    publicPath: isProduction ? "./" : "/",
    outputDir: "dist",
    configureWebpack: config => {
        if (isProduction) {
            config.externals = {
                vue: "Vue",
                "vue-router": "VueRouter",
                vuex: "Vuex",
                axios: "axios",
                "element-ui": "ELEMENT"
            };
            // 为生产环境修改配置...
            // 开启gzip压缩
            const productionGzipExtensions = /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i;
            config.plugins.push(
                new CompressionWebpackPlugin({
                    filename: "[path].gz[query]",
                    algorithm: "gzip",
                    test: productionGzipExtensions,
                    threshold: 10240,
                    minRatio: 0.8
                })
            );
        } else {
            // 为开发环境修改配置...
        }
    },
    chainWebpack: config => {
        // 对vue-cli内部的 webpack 配置进行更细粒度的修改。
        // 添加CDN参数到htmlWebpackPlugin配置中， 详见public/index.html 修改
        config.plugin("html").tap(args => {
            if (process.env.NODE_ENV === "production") {
                args[0].cdn = cdn.build;
            }
            return args;
        });
    },
    css: {
        // 是否使用css分离插件 ExtractTextPlugin
        extract: isProduction ? true : false,
        // 开启 CSS source maps?
        sourceMap: true,
        // sourceMap: false,
        // css预设器配置项
        // 启用 CSS modules for all css / pre-processor files.
        modules: false
    },
    lintOnSave: true, // default false
    // 打包时不生成.map文件
    productionSourceMap: false
};
