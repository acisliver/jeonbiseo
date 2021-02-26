<template>
  <v-app-bar app flat>
    <v-container>

      <v-row align="center">
        <v-app-bar-title>전비서</v-app-bar-title>
        <v-spacer />
        <v-toolbar-items class="hidden-sm-and-down">
          <router-link to="/">Home</router-link>

          <router-link to="/about">About</router-link>

          <v-menu offset-y v-if="isLogin">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  text
                  dark
                  v-bind="attrs"
                  v-on="on"
                  icon
              >
                <v-icon color="green lighten-2">mdi-dialpad</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title @click="$router.push({name:'Mypage'})">Mypage</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title
                  @click="$store.dispatch('logout')"
                >Logout</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <router-link v-else to="/login" >Login</router-link>

        </v-toolbar-items>
      </v-row>

    </v-container>
  </v-app-bar>
</template>

<script>
import{ mapState, mapActions } from 'vuex'

export default {
  name: "AppBar",
  data() {
    return{

    }
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    ...mapActions(['logout'])
  }
}
</script>

<style lang="scss">

.v-toolbar__content{
  padding: 30px;
  button{
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;
    padding: 10px;
  }
  a {
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;
    padding: 10px;

    &.router-link-exact-active {
       color: #42b983;
     }
  }
}
</style>
