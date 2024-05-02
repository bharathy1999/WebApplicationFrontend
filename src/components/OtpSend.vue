<template>
    <div id="otpid">
        <label for="otp">Enter a OTP</label>
        <input id="otp" pattern="{\d*}" maxlength="6" v-model="OtpValue">
        <button @click="send">Send</button>
    </div>
</template>
<script>
import axios from 'axios';
export default{
    data(){
        return{
            OtpValue:''
        }
    },
    methods:{
        send(){
            axios.post("/sign",this.OtpValue).then(response=>{
                console.log(response.data);
            }).catch(error=>{
                console.error(error);
            });
            
        }
    }
}
</script>
<style scoped>
#otpid{
    width: 500px;
    height: 100px;
    background-color: rgb(100, 240, 158);
    display: flex;
    gap: 20px;
    align-items: center;
    justify-content: center;
}
#otpid input{
    width: 100px;
    height: 30px;
    border: 2px solid rgb(234, 243, 229);
    outline: none;
}
#otpid label{
    font-size: 25px;
}
#otpid button{
    width: 100px;
    height: 30px;
    background-color: rgb(42, 139, 13);
    font-size: 20px;
    border: none;
    color: white;
}
</style>