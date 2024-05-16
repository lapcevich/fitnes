package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mycompany.restapinews.model.Halls;
import ru.mycompany.restapinews.repository.HallsRepository;

@Controller
@RequiredArgsConstructor
public class HallsController {
    private final HallsRepository hallsRepository;
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


}