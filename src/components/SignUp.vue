<template>

    <div id="con">

           <h1>Sign Up</h1>


           <div id="int">
               <label for="name">Name <input type="text" v-model="userName" id="name"></label>
               
               <label for="pass">Password <input :type="inputtype" v-model="password" id="pass"></label>
               
               <label for="mail">Email <input type="email" v-model="email" id="mail"></label>
               <button class="btn btn-success" @click="register">SIGN UP</button>
               <span @click="showpass">{{ showtext }}</span>
           </div>

       
           <div class="resinfo" id="success" v-show="success">Your Account Created Successfully.</div>
           <div class="resinfo" id="error" v-show="unsuccess">Try Again Something Went Wrong</div>

    </div>    
   
    

</template>
<script>
import axios from 'axios';



export default{
    data(){
        return{
     userName:'',
     password:'',
     email:'',
     showpassword:false,
     success:false,
     unsuccess:false,      
        }
    },
    computed:{
         inputtype(){
            return this.showpassword?"text":"password";
         },
         showtext(){
            return this.showpassword?"Hide":"Show";
         }
    },
    methods:{
        register(){
             axios.post("/signUp",{
                userName:this.userName,
                password:this.password,
                email:this.email} 
             ,{headers:{ 'Content-Type': 'application/x-www-form-urlencoded'}})
             .then(response =>{
              if(response.data=='SUCCESS'){
                  this.success=true;
                  this.userName='';
                  this.password='';
                  this.email='';
                  setTimeout(()=>{
                    this.success=false;
                  },5000);
              }
              else{
                   this.unsuccess=true;
                   setTimeout(()=>{
                    this.unsuccess=false;
                  },5000);
              }

             }
             )
             .catch(error =>
             {
                console.log(error);   
                
             });
        }
        ,showpass(){
          this.showpassword=!this.showpassword;
        }
    }
}
</script>
<style scoped>
h1{
    text-align: center;
    color: rgb(182, 223, 241);
   
}
#con{
    width: 600px;
    height: 600px;
    background-color: rgb(44, 52, 59);
    padding-top: 100px;
}
#int{
    display: flex;
    flex-direction: column;
    gap: 20px;
    position: absolute;
    top: 40%;
    left: 40%;
    transform: translate(-50%, -50%);
    margin-top: 100px;
}
input{
    outline: none;
    width: 450px;
    font-size: 20px;
}
label{
    font-size: 25px;
    color: rgb(149, 241, 241);
}
.btn{
    color: rgb(245, 250, 250);
    background-color: rgb(5, 82, 9);
    width: 200px;
    font-size: 25px;
    outline: none;
    border: none;
}
span{
    color: antiquewhite;
    font-size: 25px;
    position: absolute;
    right: 0px;
    top: 100px;
}
#success{
    font-size: 20px;
    color: rgb(8, 122, 46);
}
#error{
    font-size: 20px;
    color: rgb(226, 14, 24);
}
.resinfo{
    position: absolute;
    left: 50%;
    bottom: 1%;
    z-index: 1;
}
</style>