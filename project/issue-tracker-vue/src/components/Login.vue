<template>
  <v-container fill-height="fill-height">
    <v-layout align-center="align-center" justify-center="justify-center">
      <v-col md="5" class="mx-auto text-center mb-16">
        <div class="display-1 mb-3">
          <v-icon class="mr-4" x-large="x-large" color="teal">mdi-sticker-emoji</v-icon>
          <div class="d-inline teal--text text--darken-3">My Issue Tracker</div>
        </div>
        <v-card class="mt-5 mx-a">
          <v-card-text>
            <v-form :model="loginForm">
              <v-text-field 
                  v-model="loginForm.username"
                  label="Username"
                  prepend-icon="mdi-account-circle"
              />
              <v-text-field
                  v-model="loginForm.password"
                  :type="showPassword ? 'text' : 'password'"
                  label="Password"
                  prepend-icon="mdi-lock"
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  @click:append="showPassword = !showPassword"
              />
            </v-form>
          </v-card-text>
<!--          <v-divider></v-divider>-->
          <v-card-actions>
            <v-btn color="success" block="block" class="mb-2" v-on:click="login">Login</v-btn>
            <!--            <v-btn color="success">Register</v-btn>-->
<!--            <v-spacer></v-spacer>-->
          </v-card-actions>
        </v-card>
        <div class="mt-3">Don't have an account?
          <v-btn color="info" class="ml-2" @click="toRegister">Sign up</v-btn>
        </div>
      </v-col>
    </v-layout>

  </v-container>
</template>

<script>
export default {
  name: "Login",
  data: function () {
    return {
      showPassword: false,
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    toRegister () {
      this.$router.replace('/register')
    },
    login () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(response => {
            if (response.data.code === 200) {
              _this.$store.commit('login', response.data.response)
              var path = this.$route.query.redirect
              this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
            } else {
              alert("Something went wrong... Try again.")
            }
          })
    }
  }
}
</script>
