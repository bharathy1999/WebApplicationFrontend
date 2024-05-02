
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
import axios from 'axios';
const store = new Vuex.Store({
  state: {
    employees: [],
    selectedemp:null,
    ShowNewuser:false,
    deluser:false
  },
  mutations: {
    setdata(state,retdata){
      state.employees=retdata;
    },
    selemp(state,emp){
       state.selectedemp=emp;
    },
    showtrue(state){
          state.ShowNewuser=true;
    },
    showfalse(state){
      state.ShowNewuser=false;
    },
    showdel(state){
      state.deluser=true;
    },
    candel(state){
      state.deluser=false;
    }
  },
  getters :{
    getEmployee : state =>  state.employees
  },
  actions:{
    async init({commit}){
     await axios.get('/api').then((response)=>{     
          commit('setdata', response.data);
      }).
      catch((error)=>{
        console.log(error +"   retrieve data error");
      })
    }
  }
  }
);

export default store;