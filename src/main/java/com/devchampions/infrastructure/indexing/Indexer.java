package com.devchampions.infrastructure.indexing;

public interface Indexer {

    <T extends IndexedWithSuppliedId> void append(Index<T> index);


}
