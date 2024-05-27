package by.mycompany.restApiGym.controller;

import by.mycompany.restApiGym.model.Clients;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restApiGym.repository.ClientsRepository;
import by.mycompany.restApiGym.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsRepository clientsRepository;
    private final UserService userService;


    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clientsList", clientsRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = clientsRepository.count();
        System.out.println("Clients count: " + count);
        model.addAttribute("clientsCount", count);
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

    @GetMapping("/clients/delete")
    public String showDeleteFormByName() {
        return "selectDeleteClients";
    }

    @PostMapping("/clients/delete")
    public String deleteClientsID(@RequestParam("id") Long id) {

        clientsRepository.deleteById(id);
        return "redirect:/clients";
    }


    @GetMapping("/clients/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            List<Clients> clients = clientsRepository.findByFirstNameContainingOrLastNameContaining(name, name);
            model.addAttribute("clientsList", clients);
        }
        return "clients";
    }
    @GetMapping("/clients/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateClients";
    }


    @GetMapping("/clients/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Clients clients = clientsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid clients Id:" + id));
        model.addAttribute("clients", clients);
        return "updateClients";
    }

    @PostMapping("/clients/update/{id}")
    public String updateClient(@PathVariable("id") Long id, @ModelAttribute("clients") Clients clients) {
        clients.setId(id);
        clientsRepository.save(clients);
        return "redirect:/clients";
    }
    @GetMapping("/clients/count")
    public String countClients(Model model) {
        long count = clientsRepository.count();
        model.addAttribute("clientsCount", count);
        return "clients";
    }
}
