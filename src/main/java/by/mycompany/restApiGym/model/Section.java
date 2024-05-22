package by.mycompany.restApiGym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "Section_Hall",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "hall_id")
    )

    private Set<Halls> halls = new HashSet<>();


}
