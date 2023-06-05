package com.bestbuy.testbase;

import com.bestbuy.constants.StorePath;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseStore
{
    public static PropertyReader propertyReader;

   @BeforeClass
    public static void inIt() {

        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = StorePath.STORES;
    }

}
