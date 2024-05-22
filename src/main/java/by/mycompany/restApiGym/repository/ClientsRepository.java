package by.mycompany.restApiGym.repository;

import by.mycompany.restApiGym.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    List<Clients> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    long count();
}
