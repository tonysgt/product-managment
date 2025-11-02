package org.tonysgt.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "outboxevent")
public class OutboxEvent extends PanacheEntity {

    @Column(name = "aggregateid")
    private String aggregateid;

    @Column(name = "eventtype")
    private String eventtype;

    @Column(name = "payload")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "createdat")
    private Instant createdat;

    @ColumnDefault("false")
    @Column(name = "processed")
    private Boolean processed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregateid() {
        return aggregateid;
    }

    public void setAggregateid(String aggregateid) {
        this.aggregateid = aggregateid;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

}
