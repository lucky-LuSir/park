import Vue from 'vue'
import Vuex from 'vuex'
import deptStore from './hrm/vuex/deptStore.js'

Vue.use(Vuex)


export default new Vuex.Store({
    modules: {
        deptStore: deptStore
    },
    state: {
        /*
         请在表格里height属性, 并赋值this.$store.state.table_Height
        */
        tableHeight: `calc(100vh - 197px)`,
        notPagingTableHeight: `calc(100vh - 157px)`
    }
    // state,
    // mutations,
    // actions,
    // getters
})