package by.mycompany.restApiGym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HallsSections")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallsSections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hall_id", nullable = false)
    private Long hallId;

    @Column(name = "hall_name", nullable = false)
    private String hallName;

    @Column(name = "section_id", nullable = false)
    private Long sectionId;

    @Column(name = "section_name", nullable = false)
    private String sectionName;
}
