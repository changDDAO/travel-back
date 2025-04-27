package com.project.travel.domain.auth.repository;


public interface AuthRedisRepository {

    void saveRefreshToken(String userId, String refreshToken);
}
