package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mycompany.restapinews.model.Clients;
import ru.mycompany.restapinews.repository.ClientsRepository;

@Controller
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsRepository clientsRepository;

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
}
