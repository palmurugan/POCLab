package com.pal.poc.spring.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "audit_events")
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "event_time")
    private Instant eventTime;

    @Column(name = "event_description")
    private String eventDescription;
}
