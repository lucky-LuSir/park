<template>
    <div id="wangeditor">
<!--        <file @selectFile="selectImg" style="margin-bottom: 10px;"></file>-->
        <div ref="editorElem" style="text-align:left"></div>
    </div>
</template>

<script>
    import E from 'wangeditor';
    import file from '@/common/components/file.vue';

    export default {
        components: {
            file
        },
        name: 'editorElem',
        props: ['setContent'],// 接收父组件的数据
        data () {
            return {
                editor: null,
            }
        },
        mounted () {
            this.editor = new E(this.$refs.editorElem);
            
            this.editor.customConfig.pasteTextHandle = function (content){
                content = content.replace(/<!--[\w\W\r\n]*?-->/gmi, '');
                return content;
            };

            this.editor.customConfig.onchange = (html) => {
                this.$emit('editorContent', html);
            };

            // 菜单配置
            this.editor.customConfig.menus = [
                'head',// 标题
                'bold',// 粗体
                'fontSize',// 字号
                'fontName',// 字体
                'italic',// 斜体
                'underline',// 下划线
                'strikeThrough',// 删除线
                'foreColor',// 文字颜色
                'backColor',// 背景颜色
                'link',// 插入链接
                'list',// 列表
                'justify',// 对齐方式
                'quote',// 引用
                // 'emoticon',// 表情
                'image',// 插入图片
                'table',// 表格
                'code',// 插入代码
                'undo',// 撤销
                'redo',// 重复
            ];

            // 配置字体
            this.editor.customConfig.fontNames = [
                '宋体',
                '微软雅黑',
                "黑体",
                "楷体",
                'Arial',
                'Tahoma',
                'Verdana'
            ];

            // 自定义表情
            // this.editor.customConfig.emotions = [
            //     {
            //         title: '默认',// tab 的标题
            //         type: 'image',// type -> 'emoji' / 'image'
            //         // content -> 数组
            //         content: [
            //             {
            //                 alt: '[doge]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a1/2018new_doge02_org.png'
            //             }, {
            //                 alt: '[二哈]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/22/2018new_erha_org.png'
            //             }, {
            //                 alt: '[喵喵]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7b/2018new_miaomiao_thumb.png'
            //             }, {
            //                 alt: '[费解]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/2a/2018new_wenhao_thumb.png'
            //             }, {
            //                 alt: '[允悲]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/83/2018new_kuxiao_org.png'
            //             }, {
            //                 alt: '[摊手]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/62/2018new_tanshou_org.png'
            //             }, {
            //                 alt: '[挖鼻]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9a/2018new_wabi_thumb.png'
            //             }, {
            //                 alt: '[衰]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a2/2018new_shuai_org.png'
            //             }, {
            //                 alt: '[佩奇]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c6/hot_pigpeiqi_org.png'
            //             }, {
            //                 alt: '[乔治]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/33/hot_pigqiaozhi_org.png'
            //             }, {
            //                 alt: '[心]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8a/2018new_xin_thumb.png'
            //             }, {
            //                 alt: '[赞啊]',
            //                 src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/00/lxhzan_thumb.gif'
            //             },
            //         ]
            //     },
            //     {
            //
            //         title: 'emoji',// tab的标题
            //         type: 'emoji',// type -> 'emoji' / 'image'
            //         // content -> 数组
            //         content: ['😀', '😃', '😄', '😁', '😆', '😅', '😂', '😊', '😇', '🙂', '🙃', '😉',
            //                   '😓', '😪', '😴', '🙄', '🤔', '😱', '😬', '🤐', '🤑', '🤷', '👍', '✌',
            //                   '👌', '❤', '🔥', '💳', '📝', '📁', '📌', '💰', '📄', '🐂', '🍺', '🐎']
            //     }
            // ];

            this.editor.create();// 创建富文本实例

            if (this.setContent) {
                this.editor.txt.html(this.setContent);// 设置编辑器内容
            }
        },
        methods: {
            // 选择图片
            selectImg(urls){
                for (let i = 0; i < urls.length; i++) {
                    if (urls[i].fileType == 'image') {
                        let url = this.parkBaseUrl + 'file/read/' + urls[i].fileCode;
                        this.editor.cmd.do('insertHTML', '<img src="' + url + '" style="max-width:100%;"/>');
                    } else {
                        this.$alert('该文本框只能插入图片！', '提示', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                }
            },
        }
    }
</script>
<style scoped>
    #wangeditor {
        width: 100%;
    }
</style>