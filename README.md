

# dv-android-sdk-1.0

## SETUP
#### Step One
Download the the 'dv-android-sdk-1.0.aar' file [here](https://raw.github.com/charlesagyemang/Devless_Android_SDK/blob/master/dv-android-sdk-1.0.aar) and save it on your pc or mac


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
    String serviceName = ""; //put the particular service name you want to query data from here
    String devlessToken = ""; //put the devless token right here

    //example below
  */

  //Do it this way
  String appUrl = "http://afterpush.herokuapp.com";  // remember no slash after the com just the absolute URL
  String serviceName = "new_service"; //this is the name of my service;
  String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6"; //this is my token

  Devless devless = new Devless(this, appUrl, serviceName, devlessToken);

```

#### We will be using our instance we created to **query** **post**, **edit**, **delete** and **sign users up** and even **log in**. lets start with querying data from a particular table.

## Querying Data From your database in any of your services

```Java

/**
//call the getData Method on the devless instance. it takes two parameters(tableName, new //Devless.RequestResponse) ie table name and callback. your response will be in the void OnSuccess method  //Do what ever you want with the response.

String tableName = ""// Your table name here
devless.getData(tableName, new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String response) {
              Log.v("-----Success-----", response);
          }
      });
 //watch this example below      
*/  

String tableName = "names";   

devless.getData(tableName, new Devless.RequestResponse(){
            @Override
            public void OnSuccess(String response){
                //Do what you want with the data here
                //lets toast it
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                //lest log the response
                Log.v("-----Get Response-----", response);

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

    //call the postData method on the devless instance you created earlier and specify the table name
    devless.postData(tableName, dataToPost, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
              //Do what you want with the data here
              //lets toast it
              Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

              //lest log the response
              Log.v("-----Get Response-----", response);

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

      //Call the edit method on the devless instance you already created and pass in the table name, dataToChange, id of the recored and a new Devless.RequestResponse() to process your response to see whether it has been updated or not

      devless.edit(tableName, dataToChange, "1", new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String response) {
              //Do what you want with the data here
              //lets toast it
              Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

              //lest log the response
              Log.v("-----Get Response-----", response);

              //Do whatever you want with the data
          }
      });
```

## Deleting data from your table
#### All you need is the id of the record you want to delete and just call the delete ,method on the devless instance we already created. Lets say we want to delete an item with id "2" from the table. this is exactly how we will go about it.

```Java
devless.delete(tableName, "2", new Devless.RequestResponse() {
          @Override
          public void OnSuccess(String response) {
            //Do what you want with the data here
            //lets toast it
            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

            //lest log the response
            Log.v("-----Get Response-----", response);

            //Do whatever you want with the data
          }
      });
```

## Deleting all the data in the table at once
#### This is quite easy to do because the only parameter required is the tableName. lets delete all the records in our names table under the service ***new_service***. 

```Java
  //Call the deleteAll method on the instance and pass in the table name and a new instance oo
  //Devless.RequestResponse()
  devless.deleteAll(tableName, new Devless.RequestResponse() {
           @Override
           public void OnSuccess(String response) {
             //Do what you want with the data here
             //lets toast it
             Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

             //lest log the response
             Log.v("-----Get Response-----", response);

             //Do whatever you want with the data
           }
       });
```

# Authenticating with DevLess

### Signing up with Email and Password
#### All you need is the email and password of the user to sign them up. lets sign abgail up with the email ***abigailobeng@gmail.com*** and password ***passwordOne***.

```Java
  //Call the signUpWithEmailAndPassword method on the devless instance and pass in the email and //password that is it.

        String email = "abigailobeng@gmail.com";
        String password = "passwordOne";

        devless.signUpWithEmailAndPassword(email, password, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
              //Do what you want with the data here
              //lets toast it
              Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

              //lest log the response
              Log.v("-----Get Response-----", response);

              //Do whatever you want with the data
            }
        });
```

### Signing Users up with Username and password
#### All you need is the username and password of the user to sign them up. Lets sign abigail up with the username ***abigailobeng*** and password ***passwordOne***.

```Java
  //Call the signUpWithUserNameAndPassword method on the devless instance and pass in the username //and password that is it.

       String username = "abigailoben";
       String password = "passwordOne";

       devless.signUpWithUserNameAndPassword(username, password, new Devless.RequestResponse() {
           @Override
           public void OnSuccess(String response) {
             //Do what you want with the data here
             //lets toast it
             Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

             //lest log the response
             Log.v("-----Get Response-----", response);

             //Do whatever you want with the data
           }
       });
```

## Logging Users in with devless

### Login User with email and password

#### All you need is the email and password of the user to Log them in. Lets Log abigail in with the email ***abigailobeng@gmail.com*** and password ***passwordOne*** This is how we will go about it.  

```Java
  //Get your string set up and call the loginUserWithEmailAndPassword method on the devless //instance we already created and pass in email, password and a new Devless.LoginResponse();
  String username = "abigailobeng@gmail.com";
  String password = "passwordOne";
  devless.loginUserWithEmailAndPassword(email, password, new Devless.LoginResponse() {
          @Override
          public void OnLogInSuccess(String payload) {
              //When user name and password matches  do something
              //Lets toast something

              Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

              //use the payload for something or lets just log it for now
              Log.v("-----Get Payload-----", payload);
          }

          @Override
          public void OnLogInFailed(String error) {
            //When user name and password does not match or record does not exist
            //Lets toast something

            Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
          }
      });
```

### Login User with username and password

#### All you need is the username and password of the user to log them in. Lets Log abigail in with the username ***abigailobeng*** and password ***passwordOne*** This is how we will go about it.

```Java
  //Get your string set up and call the loginUserWithUserNameAndPassword method on the devless //instance we already created and pass in username, password and a new Devless.LoginResponse();
  String username = "abigailobeng";
  String password = "passwordOne";
  devless.loginUserWithUserNameAndPassword(username, password, new Devless.LoginResponse() {
            @Override
            public void OnLogInSuccess(String payload) {
              //When user name and password matches  do something
              //Lets toast something

              Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

              //use the payload for something or lets just log it for now
              Log.v("-----Get Payload-----", payload);
            }

            @Override
            public void OnLogInFailed(String error) {
              //When user name and password does not match or record does not exist
              //Lets toast something

              Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
```
        List<String> signUpDetails = new ArrayList<>(Arrays.asList(
                email, password, username, phoneNumber, firstname, lastname
        ));

        devless.call("devless", "signUp", signUpDetails, new Devless.RequestResponse() {
            @Override
            public void OnSuccess(String response) {
              //Do what you please with the response you get

              Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

              //use the payload for something or lets just log it for now
              Log.v("-----Get Response-----", response);
            }
        });

```
### This ***Call*** thingy is a very powerful tool in devles read more about it and exploit take full advantage of it. Thanks for following this guide. Keep using devless and tell your friends about devless. Devless is easy and superfast.
# Devless_Android_SDK
This is a working library to add to your android project in order for your app to talk to your delvess backend
### Thanks for following this guide. Keep using devless and tell your friends about devless. DevLess is easy and superfast.

