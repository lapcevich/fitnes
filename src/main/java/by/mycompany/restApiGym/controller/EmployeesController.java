package by.mycompany.restApiGym.controller;

import by.mycompany.restApiGym.model.Employees;
import by.mycompany.restApiGym.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restApiGym.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesRepository employeesRepository;
    private final UserService userService;

  /*  @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employeesList", employeesRepository.findAll());
        return "employees";
    }*/
    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employeesList", employeesRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = employeesRepository.count();
        System.out.println("Employees count: " + count);
        model.addAttribute("employeesCount", count);
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
    @GetMapping("/employees/delete")
    public String showDeleteForm() {
        return "selectDeleteEmployees";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployees(@RequestParam("id") Long id) {
        employeesRepository.deleteById(id);
        return "redirect:/employees";
    }
    @GetMapping("/employees/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            List<Employees> employees = employeesRepository.findByFirstNameContainingOrLastNameContaining(name, name);
            model.addAttribute("employeesList", employees);
        }
        return "employees";
    }
    @GetMapping("/employees/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateEmployees";
    }


    @GetMapping("/employees/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employees employees = employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id:" + id));
        model.addAttribute("employees", employees);
        return "updateEmployees";
    }

    @PostMapping("/employees/update/{id}")
    public String updateEmployees(@PathVariable("id") Long id, @ModelAttribute("employees") Employees employees) {
        employees.setId(id);
        employeesRepository.save(employees);
        return "redirect:/employees";
    }
    @GetMapping("/employees/count")
    public String countEmployees(Model model) {
        long count = employeesRepository.count();
        model.addAttribute("employeesCount", count);
        return "employees";
    }

}