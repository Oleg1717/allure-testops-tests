package cloud.autotests.api;

import cloud.autotests.api.model.Authorization;

public class AuthorizationData {

    private final String auth2;
    private final String accessToken;

    private static class InitAuthorizationData {
        private static final AuthorizationData authorizationData = new AuthorizationData();
    }

    private AuthorizationData() {
        Authorization authorization = new UserApi().getAuthorizationData();
        accessToken = "Bearer " + authorization.getAccessToken();
        auth2 = authorization.toString();
    }

    public static AuthorizationData authorizationData() {
        return InitAuthorizationData.authorizationData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAuth2() {
        return auth2;
    }

}
