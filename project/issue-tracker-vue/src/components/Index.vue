<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
      color="teal darken-1"
      width="100"
      :permanent="!$vuetify.breakpoint.lgAndUp"
    >
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
                <v-text-field
                  label="Title"
                  required
                  v-model="issueForm.title"
                  color="teal"
                ></v-text-field>
                <v-textarea
                  label="Description"
                  required
                  v-model="issueForm.description"
                  color="teal"
                ></v-textarea>
                <!-- <v-text-field label="Assign to" required v-model="issueForm.user" color=teal></v-text-field> -->
                <v-select
                  :items="users"
                  item-text="username"
                  item-value="id"
                  label="Assign to"
                  @click="getAllUsers"
                  required
                  v-model="issueForm.user"
                ></v-select>
                <span class="header">Select status</span>
                <v-chip-group
                  v-model="issueForm.status"
                  active-class="teal--text text--darken-2"
                  mandatory
                >
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

              <v-btn color="teal darken-1" text v-on:click="add"> Add </v-btn>
              <v-btn color="teal darken-1" text @click="dialog = false">
                Close
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

      <template v-slot:append>
        <div align="center" class="pa-4">
          <!-- <v-avatar color="white" size="55">
            <v-icon color="teal darken-1"> mdi-exit-to-app </v-icon>
          </v-avatar> -->
          <v-btn fab dark outlined color="white" @click="exit">
            <v-icon dark> mdi-exit-to-app </v-icon>
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="pa-0">
      <v-container>
        <v-snackbar v-model="snackbar" :timeout="4000" top color="success">
          <span>Awesome! You added a new issue.</span>
          <v-btn depressed color="transparent" @click="snackbar = false">
            Close
          </v-btn>
        </v-snackbar>

        <v-row>
          <h1 class="teal--text text--darken-4 pl-4">Board</h1>
        </v-row>

        <v-row justify="center">
          <v-col cols="12">
            <v-card v-for="(issue, i) in issues" :key="i" class="ma-4">
              <v-card-title v-text="issue.title"></v-card-title>
              <v-card-subtitle v-text="issue.timestamp"></v-card-subtitle>
              <v-card-text>
                <!-- <div v-text="issue.id"></div>
                <br /> -->
                <div v-text="issue.description"></div>
                <br />
                <div>Assign to: <span v-text="issue.user.username"></span></div>
                <div>Status: <span v-text="issue.status"></span></div>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <!-- <v-btn color="teal darken-1" text>Modify</v-btn> -->

                <v-dialog v-model="modifyDialog[i]" width="500">
                  <template v-slot:activator="{ on }">
                    <v-btn color="teal darken-1" text v-on="on">Modify</v-btn>
                  </template>

                  <v-card>
                    <v-card-title class="headline teal--text text--darken-2">
                      Modify
                    </v-card-title>

                    <v-card-text>
                      <v-container :model="modifyForm">
                        <v-text-field
                          label="Title"
                          required
                          v-model="modifyForm.title"
                          color="teal"
                        ></v-text-field>
                        <v-textarea
                          label="Description"
                          required
                          v-model="modifyForm.description"
                          color="teal"
                        ></v-textarea>
                        <v-select
                          :items="users"
                          item-text="username"
                          item-value="id"
                          label="Assign to"
                          @click="getAllUsers"
                          required
                          v-model="modifyForm.user"
                        ></v-select>
                        <span class="header">Select status</span>
                        <v-chip-group
                          v-model="modifyForm.status"
                          active-class="teal--text text--darken-2"
                          mandatory
                        >
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
                      <v-btn
                        color="teal darken-1"
                        text
                        v-on:click="modify(issue.id)"
                      >
                        Modify
                      </v-btn>
                      <v-btn
                        color="teal darken-1"
                        text
                        @click="modifyDialog = false"
                      >
                        Close
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>

                <v-btn color="red" text v-on:click="del(issue.id)"
                  >Delete</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      drawer: null,
      dialog: null,
      modifyDialog: [],
      snackbar: false,
      issueForm: {
        title: "",
        description: "",
        user: "",
        status: "",
      },
      modifyForm: {
        title: "",
        description: "",
        user: "",
        status: "",
      },
      statusOption: ["TODO", "IN_PROGRESS", "DONE"],
      users: [],
      issues: [],
      username: null,
      id: null,
    };
  },
  mounted() {
    this.getAllIssues();
  },
  methods: {
    add() {
      var _this = this;
      console.log(this.$store.state);
      this.$axios
        .post("/users/" + this.issueForm.user + "/issues", {
          title: this.issueForm.title,
          description: this.issueForm.description,
          status: this.issueForm.status,
        })
        .then(() => {
          _this.$emit("issueAdded");
        });
      location.reload();
    },
    modify(id) {
      this.$axios.put("/issues/" + id, {
        title: this.modifyForm.title,
        description: this.modifyForm.description,
        status: this.modifyForm.status,
      });
      this.$axios.put(`/issues/${id}/${this.modifyForm.user}`);
      location.reload();
    },
    del(id) {
      this.$axios.delete("/issues/" + id).then(location.reload());
    },
    getAllUsers() {
      this.$axios.get("/users").then((response) => {
        this.users = response.data;
      });
    },
    getAllIssues() {
      this.$axios.get("/issues").then((response) => {
        this.issues = response.data;
      });
    },
    exit() {
      this.$router.replace('/login')
    },
  },
};
</script>
