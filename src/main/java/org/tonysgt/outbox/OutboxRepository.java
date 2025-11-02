package org.tonysgt.outbox;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.tonysgt.entities.OutboxEvent;

@ApplicationScoped
public class OutboxRepository
{
    @Transactional
    public boolean updateEventProcessingState(OutboxEvent event){
        int update = OutboxEvent.update("processed = true WHERE id=?1", event.id);
        return update == 1;
    }
}
