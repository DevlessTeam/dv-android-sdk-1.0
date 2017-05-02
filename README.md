![alt text](https://img.shields.io/badge/dv--android--sdk--1.0-official-blue.svg)

# Devless_Android_SDK(dv-android-sdk-1.0)

## SETUP
#### Step One
Download the the 'dv-android-sdk-1.0.aar' file [here](https://github.com/DevlessTeam/dv-android-sdk-1.0/raw/master/dv-android-sdk-1.0/src/dv-android-sdk-1.0.aar) and save it on your pc or mac


#### Step two
Open your project that already exists or create a new project on ***Android Studio** and click on ***file*** a drop down will appear. Hover on ***new*** move to the the drop down on the right and select ***New Module...***

You should see a wizard, select the option that says ***Import .JAR/.AAR Package***  and click on next

 You should now have two input fields select the icon on the far right of the first input filed and navigate to where you saved the 'dv-android-sdk-1.0.aar' you downloaded earlier, select it and click next. Wait for it to sync and build and complete.


#### Step Three
- Go to your app build gradle and add the code below there.
- Click on sync.
- Wait till its done.
```Java
  //dv-android-sdk-1.0 Dependencies
  compile 'com.squareup.okhttp3:okhttp:3.4.1'
  compile 'com.squareup.retrofit2:retrofit:2.0.0'
  compile 'com.squareup.retrofit2:converter-gson:2.0.0'
  compile project(':dv-android-sdk-1.0')
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
  name of your shratd prefernce variable. Im my case i called my shared preference sp
  */
  devless.addUserToken(sp);

```

#### We will be using our instance we created to **query** **post**, **edit**, **delete** and **sign users up** and even **log in**. lets start with querying data from a particular table.

## Querying Data From your database in any of your services

```Java

/**
//call the getData Method on the devless instance. it takes three parameters(serviceName, tableName, //new Devless.RequestResponse) ie service name, table name and callback. Your response will be in the //void OnSuccess method  //Do what ever you want with the response.

String serviceName = "";// Your service name here
String tableName = "";// Your table name here
devless.getData(serviceName, tableName, new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String s) {
              Log.v("----Get Response----", s);
          }
      });
 //watch this example below      
*/  

String serviceName = "new_service";//replace with your serviceName
String tableName = "names";//replace with your tableName

devless.getData(serviceName, tableName, new Devless.RequestResponse(){
            @Override
            public void OnSuccess(String s){
                //Do what you want with the data here
                //lets toast it
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

                //lest log the response
                Log.v("-----Get Response-----", s);

                //Do whatever you want with the data
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

    devless.postData(serviceName, tableName, dataToPost, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String s) {
              //Do what you want with the data here
              //lets toast it
              Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

              //lest log the response
              Log.v("-----Post Response-----", s);

              //Do whatever you want with the data
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
      Call the edit method on the devless instance you already created and pass in the service name, table name, dataToChange, id of the recored and a new Devless.RequestResponse() to process your response to see whether it has been updated or not
      */

      String serviceName = "new_service";//your service name
      String tableName = "names";//your table name
      String id = "16"// replace with the id you want to delete

      devless.edit(serviceName, tableName, dataToChange, id, new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String s) {
              //Do what you want with the data here
              //lets toast it
              Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

              //lest log the response
              Log.v("-----Edit Response-----", s);

              //Do whatever you want with the data
          }
      });
```

## Deleting data from your table
#### All you need is the id of the record you want to delete and just call the delete ,method on the devless instance we already created. Lets say we want to delete an item with id "2" from the table. this is exactly how we will go about it.

```Java
String serviceName = "new_service";//your service name
String tableName = "names";//your table name
String id = "1";// replace with the id you want to delete

devless.delete(serviceName, tableName, id, new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String s) {
            //Do what you want with the data here
            //lets toast it
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

            //lest log the response
            Log.v("-----Delete Response-----", s);

            //Do whatever you want with the data
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

        devless.signUpWithEmailAndPassword(email, password, sp, new Devless.SignUpResponse() {
            @Override
            public void OnSignUpSuccess(String s) {
              /*
              Toast a success message and checkout the payload or just do what you want with the payload or simple write your logic here which is what you wan to happen when user successfully sign up
              */
              Log.e("EmailPassSignUpSuccess", s);
            }

            @Override
            public void OnSignUpFailed(String s) {
              /*
              Toast a failure message and  or simple write your logic here which is what you want to happen when user sign up fails
              */
                Log.e("EmailPassSignUpFailure", s);
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

       devless.signUpWithUsernameAndPassword(userName, password, sp, new Devless.SignUpResponse() {
             @Override
             public void OnSignUpSuccess(String s) {
               /*
               Toast a success message and chevkout the paload or just do what you want with the payload or simple write your logic here which is waht you wan to happen when user successfully sign up
               */
                 Log.e("UnamePassignUpSucc", s);
             }

             @Override
             public void OnSignUpFailed(String s) {
              /*
               Toast a failure message and  or simple write your
               logic here which is what you want to happen when user sign up fails
               */
                Log.e("UnamePassSignUpFail", s);
             }
         });
```

## Logging Users in with devless

### Login User with email and password

#### All you need is the email and password of the user to Log them in. Lets Log abigail in with the email ***abigailobeng@gmail.com*** and password ***passwordOne*** This is how we will go about it.  

```Java

  /*
  Get your string set up and call the loginUserWithEmailAndPassword method on the devless instance we already created and pass in email, password, SharedPreferences and a new Devless.LoginResponse();
  */

  String userName = "abigailobeng@gmail.com";
  String password = "passwordOne";

  devless.loginWithEmailAndPassword(userName, password, sp, new Devless.LoginResponse() {
           @Override
           public void OnLogInSuccess(String s) {
             /*
              Toast a success message and checkout the paload or just do what you want with the payload or simple write your logic here which is what you want to happen when user successfully logs in
              */
               Log.e("EmailPassLoginSucc", s);
           }

           @Override
           public void OnLogInFailed(String s) {
             /*
             Toast a failure message and  or simple write your
             logic here which is what you want to happen when user log in fails
             */
               Log.e("EmailPassLoginfail", s);
           }
       });
```

### Login User with username and password

#### All you need is the username and password of the user to log them in. Lets Log abigail in with the username ***abigailobeng*** and password ***passwordOne*** This is how we will go about it.

```Java
  /*
  Get your string set up and call the loginUserWithUserNameAndPassword method on the devless instance we already created and pass in username, password, SharedPreferences and a new Devless.LoginResponse();
  */

  String userName = "abigailobeng";
  String password = "passwordOne";

  devless.loginWithUsernameAndPassword(userName, password, sp, new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String s) {
              /*
              Toast a success message and checkout the paload or just do what you want with the payload or simple write your logic here which is what you want to happen when user successfully logs in
              */
                Log.e("UnamePassLogInSucc", s);
            }

            @Override
            public void OnLogInFailed(String s) {
              /*
              Toast a failure message and  or simple write your logic here
              which is what you want to happen when user log in fails
              */
                Log.e("UnamePassLoginFail", s);
            }
        });
```


# Devless_Android_SDK(dv-android-sdk-1.0)
This is a working library to add to your android project in order for your app to talk to your delvess backend
### Thanks for following this guide. Keep using devless and tell your friends about devless. DevLess is easy and superfast.
