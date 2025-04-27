package com.project.travel.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class AuthRedisRepositoryImpl implements AuthRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private final String REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN:";
    private final Duration REFRESH_TOKEN_EXPIRE = Duration.ofMinutes(30);

    @Override
    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForSet()
                .add(REFRESH_TOKEN_PREFIX + userId, refreshToken);

        redisTemplate.expire(REFRESH_TOKEN_PREFIX + userId, REFRESH_TOKEN_EXPIRE);
    }
}
