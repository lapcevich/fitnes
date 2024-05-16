package ru.mycompany.restapinews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Halls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Halls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long id;

    @Column(name = "hall_name", nullable = false)
    private String hallName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "class_schedule")
    private String classSchedule;
}