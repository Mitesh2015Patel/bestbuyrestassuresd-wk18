package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating store with name :{0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}")
    public ValidatableResponse createStore(String name,String type,String address,String address2,String city,String state, String zip){
        StorePojo storesPojo = new StorePojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORE_BY_ID)
                .then();
    }

    @Step("Updating Store With name : {0}, type : {1}, address : {2}, address2 : {3},city : {4}, state : {5}, zip :{6}")
    public ValidatableResponse updateStore(int storeId,String name,String type,String address,String address2,String city,String state, String zip)
    {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .put("/7")
                .then();
    }


    @Step("Updating Store With name : {0}, type : {1}")
    public ValidatableResponse updatePartialStore(int storeId,String name,String type)
    {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
       return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch()
                .then();
    }


    @Step("Getting Store information by storesID : {0}")
    public ValidatableResponse getStoreBuId(int id) {
        return SerenityRest.given()
                .pathParam("storesID",id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();

    }


    @Step("Getting Store information with name: {0}")
    public HashMap<String, Object> getStoneName(String name) {
        String s1 = "data.findAll{it.name='";
        String  s2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORE)
                .then()
                .statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }

    @Step
    public ValidatableResponse deleteStore()
    {
        return SerenityRest.given()
                .pathParam("storeID",7)
                .when()
                .delete("/{id}")
                .then();
    }


}
