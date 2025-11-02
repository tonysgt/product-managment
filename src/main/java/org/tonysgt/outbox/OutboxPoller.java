package org.tonysgt.outbox;

import jakarta.enterprise.context.ApplicationScoped;
import org.tonysgt.entities.OutboxEvent;

import java.util.List;

@ApplicationScoped
public class OutboxPoller {

    public List<OutboxEvent> pollUnprocessed() {
        //TODO pagination
        return OutboxEvent.find("processed=false").list();
    }
}
