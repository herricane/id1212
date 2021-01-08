<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app color="teal darken-1" width="100" :permanent="!$vuetify.breakpoint.lgAndUp">

      <div align="center" class="pa-4">
        <v-dialog v-model="dialog" width="500" @issueAdded="snackbar = true">
          <template v-slot:activator="{ on, attrs }">
            <v-btn fab dark outlined color="white" v-bind="attrs" v-on="on">
              <v-icon dark> mdi-plus </v-icon>
            </v-btn>
          </template>

          <v-card>
            <v-card-title class="headline teal--text text--darken-2">
              Add an issue
            </v-card-title>

            <v-card-text>
              <v-container :model="issueForm">
                <v-text-field label="Title" required v-model="issueForm.title" color=teal></v-text-field>
                <v-textarea label="Description" required v-model="issueForm.description" color=teal></v-textarea>
                <v-text-field label="Assign to" required v-model="issueForm.user" color=teal></v-text-field>
                <span class="header">Select status</span>
                <v-chip-group v-model="issueForm.status" active-class="teal--text text--darken-2" mandatory>
                  <v-chip
                      v-for="status in statusOption"
                      :key="status"
                      :value="status"
                  >
                    {{ status }}
                  </v-chip>
                </v-chip-group>
              </v-container>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="teal darken-1" text @click="dialog = false">
                Close
              </v-btn>
              <v-btn color="teal darken-1" text @click="dialog = false" v-on:click="add">
                Add
              </v-btn>
            </v-card-actions>
          </v-card>

        </v-dialog>
      </div>

      <template v-slot:append>
        <div align="center" class="pa-4">
          <v-avatar color="white" size="55">
            <v-icon color="teal darken-1"> mdi-account-circle </v-icon>
          </v-avatar>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="pa-0">

      <v-snackbar v-model="snackbar" :timeout="4000" top color="success">
        <span>Awesome! You added a new issue.</span>
        <v-btn depressed color="transparent" @click="snackbar = false">Close</v-btn>
      </v-snackbar>

      <v-container>
        <v-row>
          <h1 class="teal--text text--darken-4 pl-4">Board</h1>
        </v-row>

        <v-row justify="center">
          <v-col cols="12" sm="4">
            <v-card elevation="3">
              <v-card-title>Issue1</v-card-title>
              <v-card-subtitle>2021.1.4</v-card-subtitle>
              <v-card-text>Bug in pj1212</v-card-text>
            </v-card>
          </v-col>

          <v-col cols="12" sm="4">
            <v-sheet min-height="1000" rounded="lg">
              <!--  -->
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="4">
            <v-sheet rounded="lg" min-height="1000">
              <!--  -->
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "Index",
  data: function() {
    return {
      drawer: null,
      dialog: null,
      snackbar: false,
      issueForm: {
        title: '',
        description: '',
        user: '',
        status: ''
      },
      statusOption: ['TODO', 'IN_PROGRESS', 'DONE']
    }
  },
  methods: {
    closeDialog: function () {
      this.dialog = false
    },
    add () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
          .post('/user/', {
            title: this.issueForm.title,
            description: this.issueForm.description,
            user: this.issueForm.user,
            status: this.issueForm.status
          })
      .then(successResponse => {
        _this.store.commit('issue', _this.loginForm)
        _this.$emit('issueAdded')
      })
    }
  }
}

</script>
