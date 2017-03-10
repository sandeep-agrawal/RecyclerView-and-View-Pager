package com.askme.askmeassignment.utils;

import com.askme.askmeassignment.entity.ItemEntity;
import com.askme.askmeassignment.entity.ProductEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeeplabs108 on 28/05/16.
 */
public class JsonParser {
    private static JsonParser jsonParser;
    private JsonParser(){

    }

    public static JsonParser getInstance(){
        if(jsonParser == null){
            jsonParser = new JsonParser();
        }
        return jsonParser;
    }

    public  List<ProductEntity> parseJson(String json) {
        List<ProductEntity> productEntities = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for ( int i =0;i<jsonArray.length();i++) {
                JSONObject productObject = jsonArray.getJSONObject(i);
                ProductEntity productEntity = new ProductEntity();
                productEntity.setLabel(productObject.optString("label"));
                productEntity.setTemplate(productObject.optString("template"));
                productEntity.setImage(productObject.optString("image"));
                JSONArray itemArray = productObject.getJSONArray("items");
                List<ItemEntity> itemEntities = new ArrayList<>();
                for ( int j = 0 ;j< itemArray.length();j++) {
                    ItemEntity itemEntity = new ItemEntity();
                    JSONObject itemObject = itemArray.getJSONObject(j);
                    itemEntity.setLabel(itemObject.optString("label"));
                    itemEntity.setImage(itemObject.optString("image"));
                    itemEntity.setWeb_url(itemObject.optString("web-url"));
                    itemEntities.add(itemEntity);
                }
                productEntity.setItemEntities(itemEntities);
                productEntities.add(productEntity);
            }

        } catch (JSONException e) {

        }
        return productEntities;
    }
}
