package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {

    @Step()
    public ValidatableResponse createProduct(String name, String type, Double price, Double shipping, String upc,
                                             String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCT_ID)
                .then();
    }

    @Step("Updating Product information with ProductsId: {0}, name : {1}, type: {2}, price: {3}, shipping: {4}, upc: {5},description: {6}, manufacturer: {7},model: {8},url: {9},image: {10}")
    public ValidatableResponse updateProduct(int productsId, String name, String type, Double price, Double shipping, String upc,
                                             String description, String manufacturer, String model, String url, String image)
    {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()

                .header("Content-Type", "application/json")

                .pathParam("productsID", productsId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_BY_PRODUCT_ID)
                .then();

    }

    @Step("Deleting Product information with ProductsId: {0}")
    public ValidatableResponse deleteProduct(int productsId) {
        return SerenityRest
                .given()
                .pathParam("productsID", productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_ID)
                .then();
    }

    @Step("Getting Product information with ProductsId: {0}")
    public ValidatableResponse getProductById(int productsId) {
        return SerenityRest
                .given()
                .pathParam("productsID", productsId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step()
    public ValidatableResponse getAllData() {


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

}
