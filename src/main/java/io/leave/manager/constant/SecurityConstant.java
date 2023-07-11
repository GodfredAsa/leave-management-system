package io.leave.manager.constant;

public class SecurityConstant {

    public static final long TOKEN_EXPIRATION_Ms = 60 * 60 * 1000 * 24; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final int JWT_TOKEN_EXTRACTION = 7;
    public static final String AUTHORIZATION_HEADER = "Authorization";

}

