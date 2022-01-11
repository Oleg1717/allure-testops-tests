package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.models.dashboards.Widget;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardWidgetsRequests {

    public static Response getWidgetDataResponse(int widgetId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(ApiEndpoint.WIDGET_DATA, widgetId)
                .then()
                .extract().response();
    }

    public static Response getAddWidgetResponse(Widget widget) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .post(ApiEndpoint.WIDGET)
                .then()
                .extract().response();
    }

    public static Response getEditWidgetResponse(Widget widget) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .patch(ApiEndpoint.WIDGET_ID, widget.getId())
                .then()
                .extract().response();
    }

    public static Response getDeleteWidgetResponse(int widgetId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(ApiEndpoint.WIDGET_ID, widgetId)
                .then()
                .extract().response();
    }
}
