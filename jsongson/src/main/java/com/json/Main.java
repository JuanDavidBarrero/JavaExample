package com.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.json.myClass.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", "John");
        jsonObject.addProperty("age", 30);
        jsonObject.addProperty("isStudent", true);

        JsonObject addressObject = new JsonObject();
        addressObject.addProperty("city", "New York");
        addressObject.addProperty("country", "USA");

        CustomData myClassInstance = new CustomData(10, 25);
        String nestedJson = gson.toJson(myClassInstance);

        jsonObject.add("address", addressObject);

        addressObject.addProperty("class", nestedJson);

        String json = gson.toJson(jsonObject);
        System.out.println("JSON: " + json);

        System.out.println("\n\n --------------------------- \n\n");

        JsonObject decodedJsonObject = JsonParser.parseString(json).getAsJsonObject();

        String name = decodedJsonObject.get("name").getAsString();
        int age = decodedJsonObject.get("age").getAsInt();
        boolean isStudent = decodedJsonObject.get("isStudent").getAsBoolean();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Is Student: " + isStudent);

        JsonObject decodedAddressObject = decodedJsonObject.getAsJsonObject("address");
        String city = decodedAddressObject.get("city").getAsString();
        String country = decodedAddressObject.get("country").getAsString();

        System.out.println("City: " + city);
        System.out.println("Country: " + country);

        String classJson = decodedAddressObject.get("class").getAsString();
        CustomData decodedCustomData = gson.fromJson(classJson, CustomData.class);

        System.out.println("CustomData: " + decodedCustomData.getdata());
        System.out.println("CustomData: " + decodedCustomData.getdata2());
    }
}