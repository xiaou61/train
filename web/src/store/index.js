import {createStore} from 'vuex'

const MEMBER = "MEMBER"

export default createStore({
    state: {
        member: window.SessionStorage.get(MEMBER) || {}//可以避免空指针异常
    },
    getters: {},
    mutations: {
        setMember(state, _member) {
            state.member = _member
            window.SessionStorage.set(MEMBER,_member)
        }
    },
    actions: {},
    modules: {}
})
