package com.devchampions.infrastructure.auth;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
class AuthInfoContributor implements InfoContributor {

    private final ImmutableMap<String, String> info;

    public AuthInfoContributor(
            @Value("${auth0.issuer}") String issuer,
            @Value("${auth0.scope}") String scope,
            @Value("${auth0.domain}") String domain,
            @Value("${auth0.clientId}") String clientId) {

        info = ImmutableMap.<String, String>builder()
                .put("issuer", issuer)
                .put("scope", scope)
                .put("domain", domain)
                .put("clientId", clientId)
                .build();
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("auth", info);
    }
}
