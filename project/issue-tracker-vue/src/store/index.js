import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        // user: {
        //     username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
        // }
        user: window.sessionStorage.getItem("user")
    },
    mutations: {
        // login (state, user) {
        //     state.user = user
        //     window.localStorage.setItem('user', JSON.stringify(user))
        // }
        login(state, user) {
            state.user = user
            window.sessionStorage.setItem('user', user)
        },
        logout(state) {
            state.user = null
            window.sessionStorage.removeItem('user')
        }
    }
})