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
            <v-form :model="registerForm">
              <v-text-field
                  v-model="registerForm.username"
                  label="Username"
                  prepend-icon="mdi-account-circle"
              />
              <v-text-field
                  v-model="registerForm.password"
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
            <v-btn color="success" block="block" class="mb-2" v-on:click="register">Register</v-btn>
            <!--            <v-btn color="success">Register</v-btn>-->
<!--            <v-spacer></v-spacser>-->
          </v-card-actions>
        </v-card>
        <div class="mt-3">Go back to login
          <v-btn color="info" class="ml-2" @click="toLogin">Login</v-btn>
        </div>
      </v-col>
    </v-layout>

  </v-container>
</template>

<script>
export default {
  name: "Register",
  data: function () {
    return {
      showPassword: false,
      registerForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    toLogin () {
      this.$router.replace('/login')
    },
    register () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
          .post('/register', {
            username: this.registerForm.username,
            password: this.registerForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              _this.$store.commit('register', _this.registerForm)
              var path = this.$route.query.redirect
              this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
            } else {
              alert("Something went wrong... Try again.")
            }
          })
      // .catch(failResponse => {
      // })
    }
  }
}
</script>