package by.mycompany.restApiGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.mycompany.restApiGym.model.Section;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findBySectionNameContaining(String sectionName);
    long count();
}
