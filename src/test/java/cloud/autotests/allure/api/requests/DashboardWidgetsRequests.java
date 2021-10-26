package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.models.dashboards.Widget;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardWidgetsRequests {

    public static Response getWidgetData(int widgetId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(EndPoints.WIDGET_DATA, widgetId)
                .then()
                .extract().response();
    }

    public static Response addWidget(Widget widget) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .post(EndPoints.WIDGET)
                .then()
                .extract().response();
    }

    public static Response editWidget(Widget widget) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .patch(EndPoints.WIDGET_ID, widget.getId())
                .then()
                .extract().response();
    }

    public static Response deleteWidget(int widgetId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(EndPoints.WIDGET_ID, widgetId)
                .then()
                .extract().response();
    }
}
