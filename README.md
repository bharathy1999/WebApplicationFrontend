In this project there is a SignIn and SignUp page.
When a new user sign up from this web page the credentials will be stored in database using encryption and hashing.
AES algorithm is used for encryption.It is a Asymetric algorithm.Both encryption and decryption using same key.
SHA-256 is used for hashing.
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-19-27.png)
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-21-38.png)
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-24-42.png)
Encrypted data is then decrypted whenever the credential needs.
The password is hashed password stored in database.whenever the user SignIn ,the enter password is hashed with same algorithm and then compare the hashed password of user with database hashed password for authentication. 
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-19-27.png)
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-19-27.png)
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-19-27.png)
![1stimage](https://github.com/bharathy1999/WebApplicationFrontend/blob/main/Screenshot%20from%202024-05-02%2011-19-27.png)
After the authentication process completed ,(Two-Factor Authentication)a random OTP will be send to user email.The user should enter the otp in prompt box.The otp is verfied that he/she is legitimate user.
