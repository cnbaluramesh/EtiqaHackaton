package my.etiqa.demo.constant;

public class ApiConstants {

    private ApiConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String BASE_API = "/api";
    public static final String PRODUCT = "/product";
    public static final String PRODUCT_BY_ID = "/product/{id}";

    public static final String PRODUCT_CONTROLLER_TAG = "Product Controller";
    public static final String PRODUCT_CONTROLLER_DESCRIPTION = "Operations related to products";
}
