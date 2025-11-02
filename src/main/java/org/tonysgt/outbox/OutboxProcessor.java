package org.tonysgt.outbox;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.tonysgt.entities.OutboxEvent;

import java.util.List;

@ApplicationScoped
public class OutboxProcessor {

    @Inject
    OutboxPoller outboxPoller;

    @Inject
    @Channel("outbox-event-out")
    Emitter<OutboxEvent> outboxEventEmitter;

    @Inject
    OutboxRepository outboxRepository;


    @Scheduled(every = "1s")
    public void processOutbox(){
        List<OutboxEvent> outboxEvents = outboxPoller.pollUnprocessed();
        for (OutboxEvent event : outboxEvents) {
            try {
                Log.info("Processing outbox event " + event.getId());
                outboxEventEmitter.send(event);
                boolean b = outboxRepository.updateEventProcessingState(event);
                if(!b){
                    Log.error("Error updating event!");
                }
                Log.info("Processed outbox event " + event.getId());
            } catch (Exception e) {
                Log.error("Error publishing event", e);
            }
        }
    }
}
