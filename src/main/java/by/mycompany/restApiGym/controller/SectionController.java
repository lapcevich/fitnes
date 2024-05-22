package by.mycompany.restApiGym.controller;

import by.mycompany.restApiGym.model.Section;
import by.mycompany.restApiGym.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restApiGym.repository.HallsRepository;
import by.mycompany.restApiGym.service.UserService;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class SectionController {
    private final SectionRepository sectionRepository;
    private final UserService userService;
    private final HallsRepository hallsRepository;

    /*    @GetMapping("/sections")
        public String sections(Model model) {
            model.addAttribute("sectionList", sectionRepository.findAll());
            return "section";
        }*/
    @GetMapping("/sections")
    public String sections(Model model) {
        model.addAttribute("sectionList",  sectionRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = sectionRepository.count();
        System.out.println("Sections count: " + count);
        model.addAttribute("sectionsCount", count);// Добавьте эту строку// Добавьте эту строку
        return "section";
    }

    @GetMapping("/sections/create")
    public String createSection(Model model) {
        model.addAttribute("section", new Section());
        return "createSection";
    }

    @PostMapping("/sections/create")
    public String createSection(@ModelAttribute Section section) {
        sectionRepository.save(section);
        return "redirect:/sections";
    }

    @GetMapping("/sections/delete")
    public String showDeleteForm() {
        return "selectDeleteSection";
    }

    @PostMapping("/sections/delete")
    public String deleteSection(@RequestParam("id") Long id) {
        sectionRepository.deleteById(id);
        return "redirect:/sections";
    }

    @GetMapping("/sections/search")
    public String search(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            List<Section> sections = sectionRepository.findBySectionNameContaining(name);
            model.addAttribute("sectionList", sections);
        } else {
            model.addAttribute("sectionList", sectionRepository.findAll());
        }
        return "section";
    }

    @GetMapping("/sections/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateSection";
    }

    @GetMapping("/sections/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid section Id:" + id));
        model.addAttribute("section", section);
        return "updateSection";
    }

    @PostMapping("/sections/update/{id}")
    public String updateSection(@PathVariable("id") Long id, @ModelAttribute("section") Section section) {
        section.setId(id);
        sectionRepository.save(section);
        return "redirect:/sections";
    }
    @GetMapping("/sections/count")
    public String countSection(Model model) {
        long count = sectionRepository.count();
        model.addAttribute("sectionsCount", count);
        return "section";
    }
}


