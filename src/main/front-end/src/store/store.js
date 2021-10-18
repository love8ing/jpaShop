import Vue from 'vue'
import Vuex from 'vuex'
import * as  mutations from './mutations'



Vue.use(Vuex);

export const store = new Vuex.Store({
    state:{
        PK_id : '',
        showModal : false,
        modalMessage : ''
    },
    mutations:mutations
});