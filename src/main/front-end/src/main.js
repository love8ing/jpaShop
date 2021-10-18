import Vue from "vue";
import BootstrapVue from "bootstrap-vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios'; //axios 호출
import { store } from './store/store'

// https://bootstrap-vue.js.org/
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false;
Vue.use(BootstrapVue);

Vue.prototype.$axios = axios; //전역변수로 설정 컴포넌트에서 this.$axios 호출할 수 있음

new Vue({
  // 라우터 객체를 넘겨준다
  router,

  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
