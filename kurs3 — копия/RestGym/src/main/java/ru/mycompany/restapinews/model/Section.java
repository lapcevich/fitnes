package ru.mycompany.restapinews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sections")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long id;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "description", columnDefinition = "text")
    private String description;


}
