![alt text](https://img.shields.io/badge/dv--android--sdk--1.0-official-blue.svg)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7b3ca66eec8045158528d57a29ed37ad)](https://www.codacy.com/app/charlesagyemang/dv-android-sdk-1.0?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DevlessTeam/dv-android-sdk-1.0&amp;utm_campaign=Badge_Grade)

[![](https://jitpack.io/v/DevlessTeam/dv-android-sdk-1.0.svg)](https://jitpack.io/#DevlessTeam/dv-android-sdk-1.0/1.0)

# Devless_Android_SDK(dv-android-sdk-1.0)

## SETUP
#### Step One
Add the JitPack repository to your build file if it does not already exist
Add it in your root build.gradle (Project: project_name) at the end of repositories:
```Java
  allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
  		}
  	}
```

#### Step two
Add the dependency to your build.gradle(Module: app)
```Java
  	dependencies {
		...
	        compile 'com.github.DevlessTeam:dv-android-sdk-1.0:1.0'
	}
```

The above should complete the DevLess integration process.

## Usage

### Get these four things before starting.
Get your **app url**, **the service name**, **devless token** and **table name** From your DevLess Instance

## Create a devless instance like this
```Java

  /**
    String appUrl = ""; //put your app url here without an end slash
    String devlessToken = ""; //put the devless token right here

    //example below
  */
  //Create a shared preference like this
   SharedPreferences sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

  //Now go ahead and set devless up
  String appUrl = "http://afterpush.herokuapp.com";  // remember no slash after the com just the absolute URL
  String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6"; //this is my token

  //This is how to create a devless instance
  Devless devless = new Devless(this, appUrl, devlessToken);

  /*
  setUpDevlessUserToken Right under the instance and pass in the
  name of your shared preference variable. Im my case I called my shared preference *sp*
  */
  devless.addUserToken(sp);

```

#### We will be using our instance we created to **query** **post**, **edit**, **delete** and **sign users up** and even **log in**. lets start with querying data from a particular table.

## Querying Data From your database in any of your services

```Java

/**
//call the getData Method on the devless instance. It takes three parameters(serviceName, tableName, //new new GetDataResponse) ie service name, table name and callback. Your response will be in the //void OnSuccess method  //Do what ever you want with the response.

String serviceName = "";// Your service name here
String tableName = "";// Your table name here
devless.getData(serviceName, tableName, new GetDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload responsePayload) {
                // success
            }
            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                // failure
            }
            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {   
             // user not authenticated
            }
        });
 //watch this example below      
*/  

String serviceName = "new_service";//replace with your serviceName
String tableName = "names";//replace with your tableName

devless.getData(serviceName, tableName, new GetDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload responsePayload) {
                //Do what you want with the data here
                //lets toast it
                Log.e("GetDataSuccessResponse", responsePayload.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                //Error trying to get from table
                //lets toast the error message
                Log.e("GetDataFailedResponse", errorMessage.toString());

            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {
                //This part will run when user is not authenticated and the table is auth protected
                //You cansend the user to the login page to login but lets just toast the erro as usual
                Log.e("UserNotAuthError", errorMessage.toString());

            }
        });
```
# CRUD part of devless

## Adding Data To Your Backend

#### Create a Map<String, Object> with the key being your field and the value being what you want to put in that field. So to post this sample data  ***{"name" : "Abigail", "email" : "obengabigail@gmail.com"}*** to our names table because our table has two fields ie ***name*** and ***email***.

```Java
    //Create the HashMap of the fields the value you wanna post
    Map<String, Object> dataToPost = new HashMap<>();
    dataToPost.put("name", "Abigail");
    dataToPost.put("email", "obengabigail@gmail.com");

    /*
    call the postData method on the devless
    instance you created earlier and specify the service name and the table name
    */
    String serviceName = "new_service";//your service name
    String tableName = "names";//your table name

    devless.postData(serviceName, tableName, dataToAdd, new PostDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload responsePayload) {
                //Post successful
                //lets toast the success payload
                Log.e("PostDataSuccessResponse", responsePayload.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                //Error trying to get from table
                //lets toast the error message
                Log.e("PostDataFailedResponse", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {
                //This part will run when user is not authenticated and the table is auth protected
                //You cansend the user to the login page to login but lets just toast the erro as usual
                Log.e("UserNotAuthResponse", errorMessage.toString());
            }
        });
```

## Editing/Patching data in your backend
#### Create a Map<String, Object> with the key being your field and the value being what you want to change in your backend. So If we want to change the name field of what we already entered which was  ***{"name" : "Abigail", "email" : "obengabigail@gmail.com"}*** in our names table we will create a Map<String, Object> and set the new fields. This is how you will do it.

```Java
//Create a hashmap with the value to change
Map<String, Object> dataToChange = new HashMap<>();
      dataToChange.put("name", "Nana Akua");

      /*
      Call the edit method on the devless instance you already created and pass in the service name, table name, dataToChange, id of the recored and a new EditDataResponse() to process your response to see whether it has been updated or not
      */

      String serviceName = "new_service";//your service name
      String tableName = "names";//your table name
      String id = "16"// replace with the id you want to delete

      devless.edit(serviceName, tableName, patch, "6", new EditDataResponse() {
            @Override
            public void OnSuccess(ResponsePayload response) {
                // Edit was successful do what you want here
                // Lets just toast the success response
                Log.e("EditDataSuccessResponse", response.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                // Error whiles editing or updating
                Log.e("EditDataFailedResponse", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage message) {
                //This part will run when user is not authenticated and the table is auth protected
                //You cansend the user to the login page to login but lets just toast the erro as usual
                Log.e("UserNotAuth", message.toString());
            }
        });
```

## Deleting data from your table
#### All you need is the id of the record you want to delete and just call the delete ,method on the devless instance we already created. Lets say we want to delete an item with id "2" from the table. this is exactly how we will go about it.

```Java
String serviceName = "new_service";//your service name
String tableName = "names";//your table name
String id = "1";// replace with the id you want to delete

devless.delete(serviceName, tableName, "6", new DeleteResponse() {
            @Override
            public void OnSuccess(ResponsePayload responsePayload) {
                // Delete was successful do what you want here
                // Lets just toast the success response
                Log.e("DeleteDataSuccess", responsePayload.toString());
            }

            @Override
            public void OnFailed(ErrorMessage errorMessage) {
                // Error whiles Deleting or updating
                Log.e("DeleteDataSFailed", errorMessage.toString());
            }

            @Override
            public void UserNotAuthenticated(ErrorMessage errorMessage) {
                //This part will run when user is not authenticated and the table is auth protected
                //You cansend the user to the login page to login but lets just toast the erro as usual
                Log.e("UserNotAuth", errorMessage.toString());
            }
        });
```

## Deleting all the data in the table at once
#### This is quite easy to do because the only parameter required is the tableName. lets delete all the records in our names table under the service ***new_service***.

```Java
  /*
  Call the deleteAll method on the instance and pass in the
  service name, table name and a new instance Devless.RequestResponse()
  */

  String serviceName = "new_service";//your service name
  String tableName = "names";//your table name

  devless.deleteAll(serviceName, tableName, new Devless.RequestResponse() {
           @Override
           public void OnSuccess(String s) {
             //Do what you want with the data here
             //lets toast it
             Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

             //lest log the response
             Log.v("-----Get Response-----", s);

             //Do whatever you want with the data
           }
       });
```

# Authenticating with DevLess

### Signing up with Email and Password
#### All you need is the email and password of the user to sign them up. lets sign abgail up with the email ***abigailobeng@gmail.com*** and password ***passwordOne***.

```Java
  /*
  Call the signUpWithEmailAndPassword method
  on the devless instance and pass in the email, password, SharedPreferences and callback.
  */

        String email = "abigailobeng@gmail.com";
        String password = "passwordOne";

        devless.signUpWithEmailAndPassword(email, password, sp, new SignUpResponse() {
            @Override
            public void OnSignUpSuccess(Payload payload) {
                //SignUp is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully sign up
                //Lets just toast our payload

                Log.e("==SignUpResponse==", payload.toString());
            }

            @Override
            public void OnSignUpFailed(ErrorMessage errorMessage) {
                //signUp Failed
                // Toast a failure message and  or simple write your logic here which is what you
                // want to happen when user sign up fails

                Log.e("==SignUpFailed==", errorMessage.toString());
            }
        });
```

### Signing Users up with Username and password
#### All you need is the username and password of the user to sign them up. Lets sign abigail up with the username ***abigailobeng*** and password ***passwordOne***.

```Java
  /*
  Call the signUpWithUserNameAndPassword
  method on the devless instance and pass in the username, password, SharedPreferences and your callback that is it.
  */

       String userName = "abigailobeng";
       String password = "passwordOne";

       devless.signUpWithUsernameAndPassword(userName, password, sp, new SignUpResponse() {
            @Override
            public void OnSignUpSuccess(Payload payload) {
                //SignUp is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully sign up
                //Lets just toast our payload

                Log.e("==SignUpResponse==", payload.toString());
            }

            @Override
            public void OnSignUpFailed(ErrorMessage errorMessage) {
                //signUp Failed
                // Toast a failure message and  or simple write your logic here which is what you
                // want to happen when user sign up fails

                Log.e("==SignUpFailed==", errorMessage.toString());
            }
        });

```

### Signing Users up with PhoneNumber and password
#### All you need is the PhoneNumber and password of the user to sign them up. Lets sign abigail up with her PhoneNumber ***02445556677*** and password ***passwordOne***.

```Java
  /*
  Call the signUpWithPhoneNumberAndPassword
  method on the devless instance and pass in the phoneNumber, password, SharedPreferences and your callback that is it.
  */

       String phonenNumber = "02445556677";
       String password     = "passwordOne";

       devless.signUpWithPhoneNumberAndPassword(phoneNumber, password, sp, new SignUpResponse() {
            @Override
            public void OnSignUpSuccess(Payload payload) {
                //SignUp is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully sign up
                //Lets just toast our payload

                Log.e("==SignUpResponse==", payload.toString());
            }

            @Override
            public void OnSignUpFailed(ErrorMessage errorMessage) {
                //signUp Failed
                // Toast a failure message and  or simple write your logic here which is what you
                // want to happen when user sign up fails

                Log.e("==SignUpFailed==", errorMessage.toString());
            }
        });

```

## Logging Users in with devless

### Login User with email and password

#### All you need is the email and password of the user to Log them in. Lets Log abigail in with the email ***abigailobeng@gmail.com*** and password ***passwordOne*** This is how we will go about it.  

```Java

  /*
  Get your string set up and call the loginUserWithEmailAndPassword method on the devless instance we already created and pass in email, password, SharedPreferences and a new new LoginResponse();
  */

  String email = "abigailobeng@gmail.com";
  String password = "passwordOne";

  devless.loginWithEmailAndPassword(email, password, sp, new LoginResponse() {
            @Override
            public void OnLogInSuccess(ResponsePayload responsePayload) {
                //LogIn is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully Logs in
                //Lets just toast our payload
                
                
                Log.e("LogInSuccessResponse", responsePayload.toString());
            }

            @Override
            public void OnLogInFailed(ErrorMessage errorMessage) {
                // LogIn Failed
                // Toast a failure message and  or simply write your logic here which is what you
                // want to happen when user Log in fails
                
                Log.e("LogInFailedResponse", errorMessage.toString());

            }
        });
```

### Login User with username and password

#### All you need is the username and password of the user to log them in. Lets Log abigail in with the username ***abigailobeng*** and password ***passwordOne*** This is how we will go about it.

```Java
  /*
  Get your string set up and call the loginUserWithUserNameAndPassword method on the devless instance we already created and pass in username, password, SharedPreferences and a new LoginResponse();
  */

  String userName = "abigailobeng";
  String password = "passwordOne";

   devless.loginWithUsernameAndPassword(userName, password, sp, new LoginResponse() {
            @Override
            public void OnLogInSuccess(ResponsePayload responsePayload) { 
                //LogIn is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully Logs in
                //Lets just toast our payload
                
                Log.e("LogInSuccessResponse", responsePayload.toString());
            }

            @Override
            public void OnLogInFailed(ErrorMessage errorMessage) {      
                // LogIn Failed
                // Toast a failure message and  or simply write your logic here which is what you
                // want to happen when user Log in fails
                Log.e("LogInFailedResponse", errorMessage.toString());
            }
        });
```


### Login User with phoneNumber and password

#### All you need is the phoneNumber and password of the user to log them in. Lets Log abigail in with the phoneNumber ***0244556677*** and password ***passwordOne*** This is how we will go about it.

```Java
  /*
  Get your string set up and call the loginWithPhoneNumberAndPassword method on the devless instance we already created and pass in phoneNumber, password, SharedPreferences and a new LoginResponse();
  */

  String phoneNumber = "0244556677";
  String password    = "passwordOne";

   devless.loginWithUsernameAndPassword(phoneNumber, password, sp, new LoginResponse() {
            @Override
            public void OnLogInSuccess(ResponsePayload responsePayload) { 
                //LogIn is Successful
                //Toast a success message and checkout the payload or just do what you want with the payload
                //or simple write your logic here which is what you wan to happen when user successfully Logs in
                //Lets just toast our payload
                
                Log.e("LogInSuccessResponse", responsePayload.toString());
            }

            @Override
            public void OnLogInFailed(ErrorMessage errorMessage) {      
                // LogIn Failed
                // Toast a failure message and  or simply write your logic here which is what you
                // want to happen when user Log in fails
                Log.e("LogInFailedResponse", errorMessage.toString());
            }
        });
```

# Logout / Signout

```Java
  devless.logout(new LogoutResponse() {
            @Override
            public void OnLogOutSuccess(ResponsePayload responsePayload) {
                //Put your logic here on logout success
                Log.e("LogOutResponse", responsePayload.toString());
            }
        });
```

# Update User profile
```Java
	/*params (
	    String email,
            String password,
            String username,
            String phoneNumber,
            String firstname,
            String lastname,
            String others,
            final RequestResponse requestResponse)*/
	    
	 //enter your details and leave blanc what you dont want to update  or fill it with what exists already
	 
	 devless.updateProfile("", "password", "omoomo", "", "", "", "", new RequestResponse() {
	    @Override
	    public void onSuccess(ResponsePayload response) {
		Log.e("passed", response.toString());
	    }

	    @Override
	    public void userNotAuthenticated(ErrorMessage message) {
		Log.e("failed", message.toString());
	    }
	});
```

# Search / Query Data
#### Key things
- ***where*** takes fielfName and value you want to search
- ***size*** takes how many items you want the search to return
Example: lets look into our names table in the new_service service and look for anyone with the name charles and return onl 2 of the results

### Querying / Searching With Params
```Java
    //Create a hashmap of all the params you will need for your query eg, orderBy, id etc
    Map<String, Object> params = new HashMap<>();
    
    //add all your params this way
    params.put( "size", 2);
    params.put( "offset", 2);
    params.put( "orderBy", "id");
    params.put( "where",   "id,2");
    params.put( "search", "name,koobi");
    params.put( "orWhere", "id,3");
    params.put( "between", "id,1,2");
    params.put( "greaterThan", "id,1");
    params.put( "lessThanEqual", "id,2");
    params.put( "where", "name,koobi");
    
    //you can now call on the search method and search and get your response
    
    devless.search(serviceName, tableName, params, new SearchResponse() {
                @Override
                public void OnSuccess(String response) {
                    Log.e("==Success==", response);
                }
    
                @Override
                public void UserNotAuthenticated(String message) {
                    Log.e("==UserNotAuthenticated==", message);
                }
            });
    
```



# Devless_Android_SDK(dv-android-sdk-1.0)
This is a working library to add to your android project in order for your app to talk to your delvess backend
### Thanks for following this guide. Keep using devless and tell your friends about devless. DevLess is easy and superfast.
