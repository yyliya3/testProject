package com.bsuir.health_analyzer.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Health")
@Table(name = "health")
public class HealthModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private int cpu;
    @Getter @Setter private int gpu;
    @Getter @Setter private int ram;

    @Temporal(TemporalType.TIMESTAMP)
    Date time;
}
