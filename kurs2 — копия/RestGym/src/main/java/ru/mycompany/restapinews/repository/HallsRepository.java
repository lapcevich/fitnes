package ru.mycompany.restapinews.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mycompany.restapinews.model.Halls;

public interface HallsRepository extends CrudRepository<Halls, Long>{}
