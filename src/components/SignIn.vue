<template>
<div>

    <div id="container">
        <span id="invalidText"></span>


        <div v-show="showForm">
            <form @submit.prevent="submitForm">
            <h1>Sign In</h1>
            <div>
                 <div>
                     <label for="name">Name </label>
                     <input id="name" type="text" v-model="name">
                 </div>
                 <div>

                     <label for="password">Password </label>
                     <input id="password" type="password" v-model="password">
                 </div>

            </div>
           <button class="btn btn-success" type="submit">SIGN IN</button>
        </form>
        </div>
       
        <div v-show="showOtp"  id="otpid" >
            <label for="otp">Enter a OTP</label>
            <input id="otp" pattern="{\d*}" maxlength="6" v-model="OtpValue">
            <button @click="send" class="btn">Send</button>
        </div>
    
        <div v-show="verified" class="afterOtp">
            Account Verified Successfully.
        </div>
        <div v-show="unverified"  class="afterOtp">
           Your Account is Not Verified!!
        </div>

    </div>    
</div>
   
    

</template>
<script>
import axios from 'axios';

export default{
    data(){
        return{
           
                name:'',
                password:'',
                OtpValue:'',
                showOtp:false,
                showForm:true,
                verified:false,
                unverified:false
        }
    },
    methods:{
        submitForm(){
            axios.post("/signIn",{name:this.name,password:this.password},
            {headers:{ 'Content-Type': 'application/x-www-form-urlencoded'}})
            .then(response=>{
                {
                    if(response.data=="SUCCESS"){
                        this.showOtp=true;
                        this.showForm=false;
                    }
                    if(response.data=="FAILURE"){
                        document.getElementById("invalidText").innerText="Please enter a valid Username and Password";
                        setTimeout(()=>{
                            document.getElementById("invalidText").innerText="";
                        },5000);

                    }
                    
                }
            })
            .catch(error=>{
                console.error(error);
            });
           
        } ,
        send(){
            axios.post("/signIn",`name=${this.name}&otp=${this.OtpValue}`,{headers:{ 'Content-Type': 'application/x-www-form-urlencoded'}})
            .then(response=>{
                if(response.data=='SUCCESS'){
                      this.verified=true;
                }
                else{
                      this.unverified=true;
                }
            }).catch(error=>{
                document.getElementById("invalidText").innerText="Try Again Something Went Wrong !!";

                console.error(error);
            });
            
        }  
    }
}
</script>
<style scoped>

#otpid{
   background-color: rgb(61, 58, 58);
   width: 700px;
   height: 200px;
   display: flex;
   gap: 20px;
   align-items: center;
   justify-content: center;
}
#otpid input{
   width: 200px;
   height: 30px;
}
#otpid label{
  color: white;
}
#otpid .btn{
background-color: rgb(8, 94, 94);
color: white;

}
h1{
    color: white;
    margin-top: 50px;
}
form{
    display: flex;
    flex-direction: column;
    gap: 20px;
    align-items: center;
    width: 500px;
    height: 500px;
    background-color: rgb(44, 52, 59);
    
}

form div div{
    display: flex;
    gap: 20px;
}


form div{
    display: flex;
    flex-direction: column;
    gap: 30px;
}
.btn-success{
    margin-top: 50px;
    width: 150px;
    height: 50px;
    border: none;
    border-radius: 5px;
    color: white;
    font-size: 25px;
    background-color: rgb(43, 100, 43);
    letter-spacing: 2px;
}
input{
    outline: none;
    border: none;
    
}
label{
    font-size: 35px;
    color: rgb(213, 213, 238);
}

#invalidText{
    font-size: 35px;
    color: red;
    margin-bottom: 50px;
}
.afterOtp{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
}
</style>