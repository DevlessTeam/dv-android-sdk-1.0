package androidsdk.devless.io.devless.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public static Map<String, Object> signUp (String userName, String email, String password, String phoneNumber, String firstName, String lastName) {

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
}
