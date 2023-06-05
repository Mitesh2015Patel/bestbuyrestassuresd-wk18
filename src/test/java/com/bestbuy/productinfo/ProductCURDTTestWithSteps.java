package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBaseProduct;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCURDTTestWithSteps extends TestBaseProduct {
    static String name = "Amazone5" + TestUtils.getRandomName();
    static String type = "Battery";
    static Double price = 5.99;
    static Double shipping = 1.99;
    static String description = "Amazone Basic Home Battery1" + TestUtils.getRandomName();
    static String model = "Amazon Basic 1.0" + TestUtils.getRandomName();
    static String manufacturer = "Amazon China";
    static String upc = "Text";
    static String url = "http://www.amazon.co.uk/battery";
    static String image = "http://www.amazon.co.uk/battery/imag";
    static int productId = 9999713;


    @Steps
    ProductSteps productSteps = new ProductSteps();


    @WithTag("Productfeature:NEGATIVE")

    @Title("This Will create A new Store")
    @Test
    public void test001() {
        productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(201).log().all();
    }

    @WithTag("Productfeature:POSITIVE")
    @Title("Update the product information and verify the updated information")
    @Test
    public void test03() {

        productSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);

    }

    @WithTags({
            @WithTag("Productfeature:SMOKE"),
            @WithTag("Productfeature:POSITIVE")
    })
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test04() {

        productSteps.deleteProduct(productId).statusCode(200).log().status();
        productSteps.getProductById(productId).statusCode(404).log().status();
    }

    @WithTags({
            @WithTag("Productfeature:SMOKE"),
            @WithTag("Productfeature:NEGATIVE")
    })
    @Title("Get all data")
    @Test
    public void test005() {
        productSteps.getAllData().log().all().statusCode(200);

    }

    @WithTags({
            @WithTag("Productfeature:SMOKE"),
            @WithTag("Productfeature:POSITIVE")
    })
    @Title("Get Single user")
    @Test
    public void test006() {
        productId = 9999713;
        productSteps.getProductById(productId).statusCode(200).log().all();

    }
}
