package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}