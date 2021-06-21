# test-repo
java and angular app

Description :
  It's Angular And Java Application which let user to login/ logout and register.
  User Can Add Products, Edit & Delete them.
  Also included spring security with JWT Authentication.
  Uses H2 in-memory database.
  Created Rest Api's
  Configure those api's in angular.
  Added Code For Sending Mail to Registered User.
  
Feature Build will include angular form validation,
for now used in-memory data later will add MySQL,
And Small/big UI Fixes..

Steps To Run Application:
 BackEnd : Make sure java or any ide install on local machine.
           open ide -> import -> existing maven project -> let it download dependencies
           once done run application
 FrontEnd : Make sure angular is installed on local machine.
            run command "npm install"
            once node_modules folder available then hit "ng serve"
            open localhost:4200 on browser
            login window will appear - insert username as Sameer (S is capital) and password is dummy.
            for now added static users but user can register to verify open localhost:8080/h2-console then test connection.
            and connect -> select user table and hit run. For now Registered user can't do login but will add in feature.
 
