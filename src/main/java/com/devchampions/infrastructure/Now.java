package com.devchampions.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


@Component
public class Now {

    private final Reactions reactions;

    private final TransactionTemplate tx;

    public Now(Reactions reactions, PlatformTransactionManager txManager) {
        this.reactions = reactions;
        this.tx = new TransactionTemplate(txManager);
    }

    public <R extends Command.R, C extends Command<R>> R execute(C command) {
        Reaction<C, R> reaction = reactions.byCommand(command);
        return tx.execute(status -> reaction.react(command));
    }

}
