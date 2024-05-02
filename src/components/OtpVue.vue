<!-- Login.vue -->
<template>
    <div>
      <h1>Login</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="username">Username:</label>
          <input v-model="formData.username" type="text" id="username" required />
        </div>
        <div>
          <label for="password">Password:</label>
          <input v-model="formData.password" type="password" id="password" required />
        </div>
        <div v-if="showMFA">
          <label for="otp">OTP:</label>
          <input v-model="formData.otp" type="text" id="otp" required />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        formData: {
          username: '',
          password: '',
          otp: ''
        },
        showMFA: false
      };
    },
    methods: {
      submitForm() {
        const config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };
  
        const formData = new URLSearchParams();
        for (const key in this.formData) {
          formData.append(key, this.formData[key]);
        }
  
        axios.post('/login', formData, config)
          .then(response => {
            console.log(response.data);
  
            // If MFA is required, show the OTP input
            if (response.data.mfaRequired) {
              this.showMFA = true;
            }
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
  };
  </script>
  