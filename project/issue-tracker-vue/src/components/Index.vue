<!--<template>-->
<!--  <div>-->
<!--    Hello Sylvia!-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: 'Index'-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->

<template>
  <v-app id="inspire">

    <v-navigation-drawer
        v-model="drawer"
        app
        color="teal darken-1"
        width="100"
    >
      
      <div align="center" class="pa-4">
        <v-avatar color="white" size="55">
          <v-icon
              color="teal darken-1"
          >
            mdi-account-circle
          </v-icon>
        </v-avatar>
      </div>

      <template v-slot:append>
        <div align="center" class="pa-4">
          <v-btn
              fab
              dark
              outlined
              color="white"
              @click="dialog = !dialog"
          >
            <v-icon dark>
              mdi-plus
            </v-icon>
          </v-btn>
        </div>
      </template>

    </v-navigation-drawer>

    <v-main class="pa-0">
      <v-container>
        <v-row>
          <h1 class="teal--text text--darken-4 pl-4">Board</h1>
        </v-row>

        <v-row justify="center">
          <v-col
              cols="12"
              sm="4"
          >
              <v-card
                  elevation="3"
              >
                <v-card-title>Issue1</v-card-title>
                <v-card-subtitle>2021.1.4</v-card-subtitle>
                <v-card-text>Bug in pj1212</v-card-text>

              </v-card>
              <!--  -->
          </v-col>

          <v-col
              cols="12"
              sm="4"
          >
            <v-sheet
                min-height="1000"
                rounded="lg"
            >
              <!--  -->
            </v-sheet>
          </v-col>

          <v-col
              cols="12"
              sm="4"
          >
            <v-sheet
                rounded="lg"
                min-height="1000"
            >
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
  name: 'Index',
  data: () => ({
    cards: ['Today', 'Yesterday'],
    drawer: null,
    links: [
      ['mdi-inbox-arrow-down', 'Inbox'],
      ['mdi-send', 'Send'],
      ['mdi-delete', 'Trash'],
      ['mdi-alert-octagon', 'Spam'],
    ],
  }),
}
</script>