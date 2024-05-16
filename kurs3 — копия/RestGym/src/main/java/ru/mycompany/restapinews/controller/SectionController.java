package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mycompany.restapinews.model.Section;
import ru.mycompany.restapinews.repository.SectionRepository;

@Controller
@RequiredArgsConstructor
public class SectionController {
    private final SectionRepository sectionRepository;

    @GetMapping("/sections")
    public String sections(Model model) {
        model.addAttribute("sectionList", sectionRepository.findAll());
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
}
