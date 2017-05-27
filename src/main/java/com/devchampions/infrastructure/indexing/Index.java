package com.devchampions.infrastructure.indexing;

public interface Index<T extends IndexedWithSuppliedId> {

    String name();

    Class<T> type();

    T indexed();

    default String indexedId() {
        return indexed().id();
    }

    default String[] relevanceBy() {
        return new String[]{};
    }

    default String[] rankBy() {
        return new String[]{};
    }

    class Simple<T extends IndexedWithSuppliedId> implements Index<T> {

        private final String name;
        private final T indexed;
        private String[] relevanceBy = new String[]{};
        private String[] rankBy = new String[]{};

        public Simple(String name, T indexed) {
            this.name = name;
            this.indexed = indexed;
        }

        public void relevanceBy(String... order) {
            this.relevanceBy = order;
        }

        public void rankBy(String... rankBy) {
            this.rankBy = rankBy;
        }

        @Override
        public String[] rankBy() {
            return rankBy;
        }

        @Override
        public String[] relevanceBy() {
            return relevanceBy;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Class<T> type() {
            return (Class<T>) indexed.getClass();
        }

        @Override
        public T indexed() {
            return indexed;
        }
    }
}
