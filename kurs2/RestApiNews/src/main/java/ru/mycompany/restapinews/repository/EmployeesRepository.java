package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
}
