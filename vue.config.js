module.exports = {
    devServer: {
      proxy: {
        '/signUp': {
          target: 'http://localhost:8080/SecureWebApp', // Change this to the URL of your backend server
          changeOrigin: true,
          pathRewrite: {
            '^/signUp': '/getprof' // Remove the '/api' prefix when forwarding the request
          }
        },
        '/signIn': {
          target: 'http://localhost:8080/SecureWebApp', // Change this to the URL of your backend server
          changeOrigin: true,
          pathRewrite: {
            '^/signIn': '/receive' // Remove the '/api' prefix when forwarding the request
          }
        }
      }
    }
  }
  