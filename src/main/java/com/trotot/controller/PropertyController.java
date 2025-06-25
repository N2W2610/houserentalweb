package com.trotot.controller;


import com.trotot.dto.PropertyDTO;
import com.trotot.model.Property;
import com.trotot.repository.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping({"", "/"})
    public String showPropertyList(Model model) {
        List<Property> props = propertyRepository.findAll();
        model.addAttribute("props", props);
        return "AllProperty";
    }

    @GetMapping("/create")
    public String CreatePropertyPage(Model model) {
        PropertyDTO propertyDto = new PropertyDTO();
        model.addAttribute("propertyDto", propertyDto);
        return "CreateProperty";
    }

    @PostMapping("/create")
    public String createProperty(
            @Valid @ModelAttribute PropertyDTO propertyDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "CreateProperty";
        }
        Date createdAt = new Date();

        Property property = new Property();
        property.setTitle(propertyDto.getTitle());
        property.setAddress(propertyDto.getAddress());
        property.setPrice(propertyDto.getPrice());
        property.setDescription(propertyDto.getDescription());
        property.setCreated_at(createdAt);
        property.setStatus("Còn trống");

        propertyRepository.save(property);

        return "redirect:/properties";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    ) {
        try {
            Property property = propertyRepository.findById(id).get();
            model.addAttribute("property", property);

            PropertyDTO propertyDto = new PropertyDTO();
            property.setTitle(propertyDto.getTitle());
            property.setAddress(propertyDto.getAddress());
            property.setPrice(propertyDto.getPrice());
            property.setDescription(propertyDto.getDescription());
            model.addAttribute("propertyDto", propertyDto);


        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/properties";
        }

        return "EditProperty";
    }

    @PostMapping("/edit")
    public String updateProperty(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute PropertyDTO propertyDto,
            BindingResult result
    ) {
        try {
            Property property = propertyRepository.findById(id).get();
            model.addAttribute("property", property);

            if (result.hasErrors()) {
                return "EditProperty";
            }

            property.setTitle(propertyDto.getTitle());
            property.setAddress(propertyDto.getAddress());
            property.setPrice(propertyDto.getPrice());
            property.setDescription(propertyDto.getDescription());
            propertyRepository.save(property);

        } catch (Exception e) {

        }
        return "redirect:/properties";
    }

    @GetMapping("/delete")
    public String deleteProperty(
            @RequestParam int id
    ) {
        try {
            Property property = propertyRepository.findById(id).get();
            propertyRepository.delete(property);
        } catch (Exception e) {

        }
        return "redirect:/properties";
    }
}
