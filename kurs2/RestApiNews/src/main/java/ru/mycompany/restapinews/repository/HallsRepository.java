package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Halls;

public interface HallsRepository extends JpaRepository<Halls, Long> {
}
