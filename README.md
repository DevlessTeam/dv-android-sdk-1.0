# dv-android-sdk-1.0

## SETUP
#### Step One
Download the the 'dv-android-sdk-1.0.aar' file [here](https://raw.github.com/charlesagyemang/Devless_Android_SDK/blob/master/dv-android-sdk-1.0.aar) and save it on your pc or mac


#### Step two
Open your project that already exists or create a new project in android studio and click on ***file*** a drop down will aapear and just hover on ***new*** move to the the drop down on the right and select ***New Module...***

You will be taken to a wizard page select the option that says ***Import .JAR/.AAR Package***  and click on next

It will bring you to  a place where you have two input fields select the icon on the far right of the first input filed and navigate to where you saved the 'dv-android-sdk-1.0.aar' you downloaded earlier and select it and click next. Wait for it to sync and build and complete


#### Step Three
Go to your app build gradle and paste this code there and click on sync. Wait till its done.
```Java
  //dv-android-sdk-1.0 Dependencies
  compile 'com.squareup.okhttp3:okhttp:3.4.1'
  compile 'com.squareup.retrofit2:retrofit:2.0.0'
  compile 'com.squareup.retrofit2:converter-gson:2.0.0'
  compile project(':dv-android-sdk-1.0')
```


You are done with the SETUP!!!! Yey! given you have done the above so lets go straight into the usage

### Usage

#### Get these four things before starting.
Get your **app url**, **the service name**, **devless token** and **table name**

#### Create a devless instance and like this
```Java

  /**
    String appUrl = ""; //put your app url here without ant slash at the back
    String serviceName = ""; //put the particular service name you want to query data from here
    String devlessToken = ""; //put the devless token right here

    //Watch the example below
  */

  //Do it this way
  String appUrl = "http://afterpush.herokuapp.com";  // remember no backslach after the com
  String serviceName = "new_service"; //this is the name of my service;
  String devlessToken = "f9372bad91503a3d4da8824ef6e9ebe6"; //this is my token

  Devless devless = new Devless(this, appUrl, serviceName, devlessToken);

```

#### We will be using our instance we created to **query** **post**, **edit**, **delete** and **sign users up** and even **log in**. lets start with querying data from a particular table.

## Querying Data From your database in any of your services

```Java

/**
//call the get dataMethod on the devless instance. it takes two parameters(tableName, new //Devless.RequestResponse) ie table name and call back. your response will be in the void OnSuccess do //what ever you want with the response.

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

## Adding Data To Your Backend

#### Create a Map<String, Object> with the key being your field and the value being what you want to put in that field. So If we want to post this data  ***{"name" : "Abigail", "email" : "obengabigail@gmail.com"}*** to our names table because our table has two fields ie ***name*** and ***email***.

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
