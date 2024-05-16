package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
}