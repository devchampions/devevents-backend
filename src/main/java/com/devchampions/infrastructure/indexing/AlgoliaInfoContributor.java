package com.devchampions.infrastructure.indexing;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
class AlgoliaInfoContributor implements InfoContributor {

    private final String appId;
    private final String webApiKey;

    public AlgoliaInfoContributor(@Value("${algolia.appId}") String appId, @Value("${algolia.webApiKey}") String webApiKey) {
        this.appId = appId;
        this.webApiKey = webApiKey;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("algolia", ImmutableMap.builder()
                .put("appId", appId)
                .put("webApiKey", webApiKey)
                .build());
    }
}
