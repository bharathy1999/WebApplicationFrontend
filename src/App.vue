<template>
  <div id="app">
    
    <div id="link">
      <button @click="navigateTo('/signin')" class="remove-link">Sign In</button>
    
  
      <button @click="navigateTo('/signup')" class="remove-link">Sign Up</button>      
    </div>

   <div>
        <router-view></router-view>
   </div>

  </div>
</template>

<script>

export default {
    name: 'App',
    methods:{
      navigateTo(path){
          this.$router.push(path);
      }
    }
}
</script>

<style scoped>


#link{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  background-color: rgb(92, 88, 88);
  height: 100px;
  width: 100vw;
}
#app div:nth-child(2){
  position:absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);

}
#link button{
  height: 50px;
  width: 200px;
  background-color: rgba(0, 255, 255, 0.233);
  border-radius: 5px;
  outline: none;
  border: none;
  font-size: 25px;
  font-weight: bolder;
}
.remove-link{
  text-decoration: none;
  color: rgb(250, 250, 250);
}
</style>
