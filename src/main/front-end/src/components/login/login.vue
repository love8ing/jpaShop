<template>
<div>
<!--    <label for="inputEmail" class="sr-only">Email address</label>-->

<!--    <input type="email" v-model="email" id="inputEmail"  class="form-control" placeholder="Email address" required autofocus >-->
<!--    <label for="inputPassword" class="sr-only">Password</label>-->
<!--    <input type="password" v-model="pw" id="inputPassword"  class="form-control" placeholder="Password" required>-->


<!--    <button class="btn btn-lg btn-primary btn-block" v-on:click="getList">Sign in</button>-->

<!--    <div class="checkbox mb-3">-->
<!--        <button class="btn btn-lg btn-primary btn-block" v-on:click="clickForSignUp">sign up</button>-->
<!--    </div>-->

  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar dark color="primary">
            <v-toolbar-title>Login form</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-card-text>
            <v-form>
              <v-text-field  v-model="email" label="아이디" type="text"></v-text-field>
              <v-text-field  v-model="pw" label="비밀번호" type="password"></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="getList">로그인</v-btn>
            <v-btn color="primary" @click="clickForSignUp">회원가입</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>

</div>
  
</template>

<script>
import { mapMutations } from 'vuex'

export default {
  data: function () {
    return {

      email : "",
      pw : "",
      resultData : [],

    }
  },

   methods: {

     ...mapMutations({
       setPkId: 'setPkId',

     }),

    clickForSignUp() {
      this.$router.push({
        path: `/createMemberForm`
      });
    },
    getList() {

      if(this.email == ''){
        alert("email은 필수입니다.");
        return;
      }
      if(this.pw == ''){
        alert("비밀번호는 필수입니다.");
        return;
      }

      var params = new URLSearchParams();
      params.append('inputEmail', this.email);
      params.append('inputPassword', this.pw);
      this.$axios.post('http://localhost:8080/signIn'
          ,params)
          .then((res) => {
              this.resultData = res.data;
              this.setPkId(this.resultData[0]);
              if(this.resultData[1] == "home_admin") {
                  this.$router.push({name: 'home_admin'});
              }
              else if(this.resultData[1]== 'home'){
                this.$router.push({name: 'home'});
              }
              else if(this.resultData[1] == 'login/loginFail'){
                this.$router.push({path: `/loginFail`});
              }
              else{
                this.$router.push({name: `NotFound`});
              }

          }).catch((err)=>{
              console.log(err);
          })
		}
  }
}
</script>

<style>



</style>