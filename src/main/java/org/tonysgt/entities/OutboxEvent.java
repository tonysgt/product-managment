package org.tonysgt.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "outboxevent")
public class OutboxEvent extends PanacheEntity {

    @Column(name = "aggregateid")
    private String aggregateId;

    @Column(name = "eventtype")
    private String eventType;

    @Column(name = "payload")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "processed")
    private Boolean processed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateid) {
        this.aggregateId = aggregateid;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventtype) {
        this.eventType = eventtype;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdat) {
        this.createdAt = createdat;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

}
