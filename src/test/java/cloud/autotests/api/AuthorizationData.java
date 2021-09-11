package cloud.autotests.api;

import cloud.autotests.api.model.Authorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class AuthorizationData {

    private final String auth2;
    private final String accessToken;

    private static class InitAuthorizationData {
        private static final AuthorizationData authorizationData = new AuthorizationData();
    }

    @SneakyThrows
    private AuthorizationData() {
        Authorization authorization = new UserApi().getAuthorizationData();
        accessToken = "Bearer " + authorization.getAccessToken();
        auth2 = new ObjectMapper().writeValueAsString(authorization);
    }

    public static AuthorizationData authorizationData() {
        return InitAuthorizationData.authorizationData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return auth2;
    }

}
