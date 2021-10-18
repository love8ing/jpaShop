<template>
  <div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader" />
    <div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>이름</th>
          <th>도시</th>
          <th>주소</th>
          <th>우편번호</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="member in memberList" v-bind:key="member">
          <td>{{member.id}}</td>
          <td>{{member.name}}</td>
          <td>{{member.address.city}}</td>
          <td>{{member.address.street}}</td>
          <td>{{member.address.zipcode}}</td>
        </tr>

        </tbody>
      </table>
    </div>
    <div th:replace="fragments/footer :: footer" />
  </div> <!-- /container -->
</template>

<script>
export default {
  name: "memberList",
  data: function () {
    return {
      memberList: []
    }
  },
  created: function () {
    this.getList()
  },
  methods: {
    getList: function () {
      this.$axios.get('http://localhost:8080/members').then(response => {
        console.log('### ' + JSON.stringify(response))
        this.memberList = response.data
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>