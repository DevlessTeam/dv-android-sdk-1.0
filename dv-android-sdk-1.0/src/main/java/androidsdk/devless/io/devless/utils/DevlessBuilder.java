package androidsdk.devless.io.devless.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class DevlessBuilder {

    public static String formUrl (String rootUrl, String serviceName) {
        return rootUrl +  "/api/v1/service/" + serviceName + "/";
    }

    public static Map<String, Object> createPostBody (String table_name, Map<String, Object> fieldMap ) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> mainList  = new ArrayList<Object>();
        Map<String, Object> miniMap = new HashMap<String, Object>();
        List<Object> fieldList = new ArrayList<Object>();
        fieldList.add(fieldMap);
        miniMap.put("name", table_name);
        miniMap.put("field", fieldList);
        mainList.add(miniMap);
        map.put("resource", mainList);
        return map;
    }


    public static Map<String, Object> createPatchBody (String table_name,  Map<String, Object> dataChange, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> mainList  = new ArrayList<Object>();
        Map<String, Object> miniMap = new HashMap<String, Object>();
        List<Object> fieldList = new ArrayList<Object>();
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("where", "id," + id);
        List<Object> dataFields = new ArrayList<>();
        dataFields.add(dataChange);
        fieldMap.put("data",dataFields);
        fieldList.add(fieldMap);
        miniMap.put("name", table_name);
        miniMap.put("params", fieldList);
        mainList.add(miniMap);
        map.put("resource", mainList);
        return map;
    }



    public static Map<String, Object> createDeleteBody (String table_name, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> mainList  = new ArrayList<Object>();
        Map<String, Object> miniMap = new HashMap<String, Object>();
        List<Object> fieldList = new ArrayList<Object>();
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("delete", "true");
        fieldMap.put("where", "id,=," + id);
        fieldList.add(fieldMap);
        miniMap.put("name", table_name);
        miniMap.put("params", fieldList);
        mainList.add(miniMap);
        map.put("resource", mainList);
        return map;
    }

    public static Map<String, Object> createDeleteAllBody (String table_name) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> mainList  = new ArrayList<Object>();
        Map<String, Object> miniMap = new HashMap<String, Object>();
        List<Object> fieldList = new ArrayList<Object>();
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("delete", "true");
        fieldList.add(fieldMap);
        miniMap.put("name", table_name);
        miniMap.put("params", fieldList);
        mainList.add(miniMap);
        map.put("resource", mainList);
        return map;
    }

    public static Map<String, Object> signUpBuilder (String userName,
                                              String email,
                                              String password,
                                              String phoneNumber,
                                              String firstName,
                                              String lastName)
    {

        Map<String, Object> signUp = new HashMap<>();
        List<String> params = new ArrayList<>(Arrays.asList(
                email,
                password,
                userName,
                phoneNumber,
                firstName,
                lastName,
                "null"
        ));
        signUp.put("params", params);
        signUp.put("jsonrpc", "2.0");
        signUp.put("method", "devless");
        signUp.put("id", "1000");

        return  signUp;

    }

    public static Map<String, Object> callBodyBuilder (String serviceName, List<String> params) {

        Map<String, Object> signUp = new HashMap<>();
        signUp.put("params", params);
        signUp.put("jsonrpc", "2.0");
        signUp.put("method", serviceName);
        signUp.put("id", "1000");
        return  signUp;

    }

    public static boolean checkAuth ( String response){

        boolean bool =  false;
        try {
            JSONObject JO = new JSONObject(response);
            int statusCode = JO.getInt("status_code");
            if (statusCode == 625 ||  statusCode == 609 || statusCode == 619 ||  statusCode == 636 || statusCode == 614 || statusCode == 1000){
                bool = true;
            }  else if (statusCode == 628){
                bool = false;
            } else {
                bool = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


       return bool;
    }

    public static boolean checkGetSuccess(String response){
       boolean bool = false;
        try {
            JSONObject JO = new JSONObject(response);
            int statusCode = JO.getInt("status_code");
            if (statusCode == 634 ||  statusCode == 604 ) {
                bool = false;
            }else{
                bool = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bool;

    }

    public static int checkPostSuccess(String response){
        int bool = 0;
        try {
            JSONObject JO = new JSONObject(response);
            int statusCode = JO.getInt("status_code");
            if (statusCode == 634 ||  statusCode == 604 ) {
                bool = -1;
            }else if (statusCode == 700){
                bool = 0;
            }else if (statusCode == 629 || statusCode == 614){
                bool = 2;
            } else {
                bool = 1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bool;

    }
}
