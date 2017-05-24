package com.devchampions.infrastructure;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collection;

@Component
class Reactions {
    
    private final LoadingCache<Type, Reaction> cachedReactions;

    @Autowired
    public Reactions(ListableBeanFactory beanFactory) {
        this.cachedReactions = Caffeine.newBuilder()
                .build(commandType -> reactions(beanFactory)
                        .stream()
                        .filter(reaction -> reaction.commandType().isSupertypeOf(commandType))
                        .findFirst()
                        .orElseThrow(() -> new NoReactionFound(commandType)));
    }

    private Collection<Reaction> reactions(ListableBeanFactory beanFactory) {
        return beanFactory.getBeansOfType(Reaction.class).values();
    }

    public <C extends Command<R>, R extends Command.R> Reaction<C, R> byCommand(C command) {
        return cachedReactions.get(command.type());
    }
}