package cloud.autotests.api.requests;

import cloud.autotests.api.helpers.EndPoints;
import cloud.autotests.api.models.dashboards.Widget;
import cloud.autotests.api.models.dashboards.WidgetData;
import io.restassured.http.ContentType;

import static cloud.autotests.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardWidgetsRequests {

    public static WidgetData getWidgetDataRequest(int widgetId) {
        return given()
                .spec(spec().request())
                .when()
                .get(EndPoints.WIDGET_DATA, widgetId)
                .then()
                .statusCode(200)
                .extract().as(WidgetData.class);
    }

    public static Widget addWidgetRequest(Widget widget) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .post(EndPoints.WIDGET)
                .then()
                .statusCode(200)
                .extract().as(Widget.class);
    }

    public static Widget editWidgetRequest(Widget widget) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(widget)
                .when()
                .patch(EndPoints.WIDGET_ID, widget.getId())
                .then()
                .statusCode(200)
                .extract().as(Widget.class);
    }

    public static void deleteWidget(int widgetId) {
        given()
                .spec(spec().request())
                .pathParam("id", widgetId)
                .when()
                .delete(EndPoints.WIDGET_ID)
                .then()
                .statusCode(202);
    }
}