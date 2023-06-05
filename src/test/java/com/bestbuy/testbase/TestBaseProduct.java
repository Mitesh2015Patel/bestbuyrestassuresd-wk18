package com.bestbuy.testbase;

import com.bestbuy.constants.ProductPath;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBaseProduct {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void inIt() {

        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = ProductPath.PRODUCTS;
    }
}
