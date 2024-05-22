package by.mycompany.restApiGym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restApiGym.model.Halls;
import by.mycompany.restApiGym.repository.HallsRepository;
import by.mycompany.restApiGym.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HallsController {
    private final HallsRepository hallsRepository;
    private final UserService userService;

    /* @GetMapping("/halls")
     public String halls(Model model) {
         model.addAttribute("hallsList", hallsRepository.findAll());
         return "halls";
     }*/
    @GetMapping("/halls")
    public String halls(Model model) {
        model.addAttribute("hallsList", hallsRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = hallsRepository.count();
        System.out.println("Halls count: " + count);
        model.addAttribute("hallsCount", count);// Добавьте эту строку
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

    @GetMapping("/halls/search/id")
    public String searchHallID(Model model) {
        return "searchHallID";
    }

    @GetMapping("/halls/search")
    public String search(@RequestParam(value = "hallName", required = false) String hallName, Model model) {
        if (hallName != null) {
            List<Halls> halls = hallsRepository.findByHallNameContaining(hallName);
            model.addAttribute("hallsList", halls);
        }
        return "halls";
    }

    @GetMapping("/halls/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateHall";
    }


    @GetMapping("/halls/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Halls hall = hallsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id:" + id));
        model.addAttribute("hall", hall);
        return "updateHall";
    }

    @PostMapping("/halls/update/{id}")
    public String updateHall(@PathVariable("id") Long id, @ModelAttribute("hall") Halls hall) {
        hall.setId(id);
        hallsRepository.save(hall);
        return "redirect:/halls";
    }
    @GetMapping("/halls/delete")
    public String showDeleteForm() {
        return "selectDeleteHall";
    }

    @PostMapping("/halls/delete")
    public String deleteHall(@RequestParam("id") Long id) {
        hallsRepository.deleteById(id);
        return "redirect:/halls";
    }

    @GetMapping("/halls/count")
    public String countHalls(Model model) {
        long count = hallsRepository.count();
        model.addAttribute("hallsCount", count);
        return "halls";
    }
}