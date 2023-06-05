package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.testbase.TestBaseStore;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCURDTTestWithSteps extends TestBaseStore
{

    static String name = "Roseville" + TestUtils.getRandomName();
    static  String type = "BigBox" + TestUtils.getRandomName();
    static  String address ="1643 County Road B2" + TestUtils.getRandomName();
    static String address2 = "";
    static Double lat = 120.23;
    static Double lng = 120.10;
    static String hours =  "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static String city = "Roseville"+ TestUtils.getRandomName();
    static String state = "MN"+ TestUtils.getRandomName();
    static String zip = "55113" + TestUtils.getRandomName();
    static String service = "{}";
    static int storeId=10;

    @Steps
    StoreSteps storeSteps;
    @WithTag("storefeature:NEGATIVE")
    @Title("This Will create A new Store")
    @Test
    public void test001()
    {
      ValidatableResponse response = storeSteps.createStore(name,type,address,address2,city,state,zip);
        response.log().all().statusCode(201);
    }

    @WithTags({
            @WithTag("storefeature:SMOKE"),
            @WithTag("storefeature:POSITIVE")
    })

    @Title("This will updating store")
    @Test
    public void test002()
    {
        storeSteps.updateStore(storeId,name,type,address,address2,city,state,zip).log().all().statusCode(200);
        HashMap<String, Object> storesMap = storeSteps.getStoneName(name);
        Assert.assertThat(storesMap, hasValue(name));
    }

    @WithTags({
            @WithTag("storefeature:SMOKE"),
            @WithTag("storefeature:NEGATIVE")
    })

    @Title("This will partial updating store ")
    @Test
    public void test003() {
         name = "Rose" + TestUtils.getRandomName();
         type = "Box" + TestUtils.getRandomName();
        storeSteps.updatePartialStore(storeId,name,type).log().all().statusCode(200);
        HashMap<String, Object> storesMap = storeSteps.getStoneName(name);
        Assert.assertThat(storesMap, hasValue(name));
    }

    @WithTags({
            @WithTag("storefeature:SMOKE"),
            @WithTag("storefeature:POSITIVE")
    })
    @Title("This will delete Store")
    @Test
    public void test004() {
        storeSteps.deleteStore().statusCode(200);
          storeSteps.getStoreBuId(storeId).statusCode(404).log().status();

    }

    @WithTag("storefeature:POSITIVE")
    @Title("This will Get all Store")
       @Test
        public void test005()
        {
            SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_STORE)
                    .then().log().all().statusCode(200);
        }





}
