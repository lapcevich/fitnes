package by.mycompany.restApiGym.controller;

import by.mycompany.restApiGym.model.HallsSections;
import by.mycompany.restApiGym.model.Section;
import by.mycompany.restApiGym.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import by.mycompany.restApiGym.model.Halls;
import by.mycompany.restApiGym.repository.HallsRepository;
import by.mycompany.restApiGym.repository.HallsSectionsRepository;
import by.mycompany.restApiGym.service.UserService;

import java.util.List;

@Controller
public class HallsSectionsController {

    private final HallsSectionsRepository hallsSectionsRepository;
    private final SectionRepository sectionRepository;
    private final UserService userService;
    private final HallsRepository hallsRepository;


    @Autowired
    public HallsSectionsController(HallsSectionsRepository hallsSectionsRepository, SectionRepository sectionRepository, UserService userService, HallsRepository hallsRepository) {
        this.hallsSectionsRepository = hallsSectionsRepository;
        this.sectionRepository = sectionRepository;
        this.userService = userService;
        this.hallsRepository = hallsRepository;
    }

    @GetMapping("/allHallsSections")
    public String allHallsSections(Model model) {
        // Retrieve all halls sections from the repository
        List<HallsSections> allHallsSections = hallsSectionsRepository.findAll();
        model.addAttribute("allHallsSections", allHallsSections);

        return "allHallsSections"; // Return the name of the Thymeleaf template
    }

    @GetMapping("/manageHalls")
    public String manageHalls(Model model) {
        // Add any necessary data to the model (e.g., sections, halls)
        // For example:
        model.addAttribute("allSections", sectionRepository.findAll());
        model.addAttribute("allHalls", hallsRepository.findAll());

        return "manageHalls"; // Return the name of the Thymeleaf template
    }

    @PostMapping("/addHallToSection")
    public String addHallToSection(@RequestParam Long hallId, @RequestParam Long sectionId) {
        // Retrieve the hall and section objects based on their IDs
        Halls hall = hallsRepository.findById(hallId).orElse(null);
        Section section = sectionRepository.findById(sectionId).orElse(null);

        if (hall != null && section != null) {
            // Check if the relationship already exists
            HallsSections existingHallsSections = hallsSectionsRepository.findByHallIdAndSectionId(hallId, sectionId);
            if (existingHallsSections != null) {
                // If the relationship already exists, redirect to a relevant page (e.g., manageHalls)
                return "redirect:/manageHalls";
            }

            // Create a new HallsSections entry
            HallsSections hallsSections = new HallsSections();
            hallsSections.setHallId(hallId);
            hallsSections.setHallName(hall.getHallName());
            hallsSections.setSectionId(sectionId);
            hallsSections.setSectionName(section.getSectionName());

            // Save the entry to the database
            hallsSectionsRepository.save(hallsSections);
        }

        // Redirect to a relevant page (e.g., manageHalls)
        return "redirect:/manageHalls";
    }

    @PostMapping("/removeHallFromSection")
    public String removeHallFromSection(@RequestParam Long hallId, @RequestParam Long sectionId) {
        // Retrieve the hall and section objects based on their IDs
        Halls hall = hallsRepository.findById(hallId).orElse(null);
        Section section = sectionRepository.findById(sectionId).orElse(null);

        if (hall != null && section != null) {
            // Find the HallsSections entry
            HallsSections hallsSections = hallsSectionsRepository.findByHallIdAndSectionId(hallId, sectionId);
            if (hallsSections != null) {
                // Delete the entry from the database
                hallsSectionsRepository.delete(hallsSections);
            }
        }

        // Redirect to a relevant page (e.g., manageHalls)
        return "redirect:/manageHalls";
    }

}
