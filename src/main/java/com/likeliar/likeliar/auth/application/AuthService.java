package com.likeliar.likeliar.auth.application;

import com.likeliar.likeliar.auth.api.dto.response.UserInfo;

public interface AuthService {
    String getIdToken(String authCode);
    UserInfo getUserInfo(String idToken);

    String getProvider();
}
