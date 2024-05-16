package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mycompany.restapinews.model.Employees;
import ru.mycompany.restapinews.repository.EmployeesRepository;

@Controller
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesRepository employeesRepository;
    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employeesList", employeesRepository.findAll());
        return "employees";
    }

    @GetMapping("/employees/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employees());
        return "createEmployee";
    }

    @PostMapping("/employees/create")
    public String createEmployee(@ModelAttribute Employees employee) {
        employeesRepository.save(employee);
        return "redirect:/employees";
    }
}