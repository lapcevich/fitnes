package by.mycompany.restApiGym.repository;

import by.mycompany.restApiGym.model.Halls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HallsRepository extends JpaRepository<Halls, Long> {
    List<Halls> findByHallNameContaining(String hallName);
    long count();
}