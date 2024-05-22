package by.mycompany.restApiGym.repository;

import by.mycompany.restApiGym.model.HallsSections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallsSectionsRepository extends JpaRepository<HallsSections, Long> {
    HallsSections findByHallIdAndSectionId(Long hallId, Long sectionId);
}
