package by.mycompany.restApiGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.mycompany.restApiGym.model.Employees;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    long count();
}