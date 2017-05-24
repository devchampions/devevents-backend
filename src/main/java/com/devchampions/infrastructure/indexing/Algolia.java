package com.devchampions.infrastructure.indexing;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.IndexSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.util.Arrays.asList;

@Component
class Algolia implements Indexer {

    private final APIClient client;

    public Algolia(@Value("${algolia.appId}") String appId, @Value("${algolia.apiKey}") String apiKey) {
        this.client = new ApacheAPIClientBuilder(appId, apiKey).build();
    }

    @Override
    public <T> void append(Index<T> idx) {
        com.algolia.search.Index<T> index = client.initIndex(idx.name(), idx.type());
        try {
            index.setSettings(new IndexSettings().setCustomRanking(Collections.singletonList("desc(rank)")));
            if (idx.relevanceBy().length > 0) {
                IndexSettings settings = new IndexSettings().setSearchableAttributes(asList(idx.relevanceBy()));
                index.setSettings(settings);
            }
            index.addObject(idx.indexed());
        } catch (AlgoliaException e) {
            throw new IndexingFailed(e);
        }
    }
}
