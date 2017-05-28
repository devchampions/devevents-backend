package com.devchampions.infrastructure.indexing;

import com.algolia.search.APIClient;
import com.algolia.search.ApacheAPIClientBuilder;
import com.algolia.search.exceptions.AlgoliaException;
import com.algolia.search.objects.IndexSettings;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
class Algolia implements Indexer {

    private final APIClient client;
    private final ConcurrentHashSet<String> configured = new ConcurrentHashSet<>();

    public Algolia(@Value("${algolia.appId}") String appId, @Value("${algolia.apiKey}") String apiKey) {
        this.client = new ApacheAPIClientBuilder(appId, apiKey).build();
    }

    @Override
    public <T extends IndexedWithSuppliedId> void append(Index<T> index) {
        com.algolia.search.Index<T> algoliaIndex = client.initIndex(index.name(), index.type());
        try {
            boolean newIndex = configured.add(index.name());
            if (newIndex) {
                configureRanking(index, algoliaIndex);
                configureRelevance(index, algoliaIndex);
            }
            algoliaIndex.addObject(index.indexedId(), index.indexed());
        } catch (AlgoliaException e) {
            throw new IndexingFailed(e);
        }
    }

    private <T extends IndexedWithSuppliedId> void configureRanking(Index<T> idx, com.algolia.search.Index<T> index) throws AlgoliaException {
        index.setSettings(new IndexSettings().setCustomRanking(asList(idx.rankBy())));
    }

    private <T extends IndexedWithSuppliedId> void configureRelevance(Index<T> idx, com.algolia.search.Index<T> index) throws AlgoliaException {
        if (idx.relevanceBy().length > 0) {
            IndexSettings settings = new IndexSettings().setSearchableAttributes(asList(idx.relevanceBy()));
            index.setSettings(settings);
        }
    }
}
