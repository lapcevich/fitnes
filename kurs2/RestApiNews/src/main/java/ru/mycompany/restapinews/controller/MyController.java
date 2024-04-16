package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mycompany.restapinews.model.Clients;
import ru.mycompany.restapinews.model.Halls;
import ru.mycompany.restapinews.model.Employees;
import ru.mycompany.restapinews.repository.ClientsRepository;
import ru.mycompany.restapinews.repository.HallsRepository;
import ru.mycompany.restapinews.repository.EmployeesRepository;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final HallsRepository hallsRepository;
    private final ClientsRepository clientsRepository;
    private final EmployeesRepository employeesRepository;

    public MyController() {
        hallsRepository = null;
        clientsRepository = null;
        employeesRepository = null;
    }

    // Halls controllers
    @GetMapping("/halls")
    public String halls(Model model) {
        model.addAttribute("hallsList", hallsRepository.findAll());
        return "halls";
    }

    @GetMapping("/halls/create")
    public String createHall(Model model) {
        model.addAttribute("hall", new Halls());
        return "createHall";
    }

    @PostMapping("/halls/create")
    public String createHall(@ModelAttribute Halls hall) {
        hallsRepository.save(hall);
        return "redirect:/halls";
    }

    @GetMapping("/halls/search")
    public String searchHallID(Model model) {
        return "searchHallID";
    }

    @GetMapping("/halls/search/{id}")
    public String searchHallID(@PathVariable("id") Long id, Model model) {
        Halls hall = hallsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id:" + id));
        model.addAttribute("hallsList", hall);
        return "halls";
    }

    @GetMapping("/halls/update")
    public String updateHallID(Model model) {
        return "updateHallID";
    }

    @GetMapping("/halls/update/{id}")
    public String updateHallID(@PathVariable("id") Long id, Model model) {
        Halls hall = hallsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id:" + id));
        model.addAttribute("hallsList", hall);
        return "halls";
    }

    // Clients controllers
    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clientsList", clientsRepository.findAll());
        return "clients";
    }

    @GetMapping("/clients/create")
    public String createClient(Model model) {
        model.addAttribute("client", new Clients());
        return "createClient";
    }

    @PostMapping("/clients/create")
    public String createClient(@ModelAttribute Clients client) {
        clientsRepository.save(client);
        return "redirect:/clients";
    }

    // Employees controllers
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
