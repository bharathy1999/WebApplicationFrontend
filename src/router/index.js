import Vue from 'vue';
import VueRouter from 'vue-router';
import SignUp from '../components/SignUp.vue';
import SignIn from'../components/SignIn.vue';

Vue.use(VueRouter);

const routes=[
    {path:'/signin',component:SignIn},
    {path:'/signup',component:SignUp}
];


const router=new VueRouter({
    mode: 'history',routes
})
export default router;