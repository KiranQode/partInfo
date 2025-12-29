package com.example.onshapeapp.partInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onshapeapp.partInfo.model.PhysicalProperties;
import com.example.onshapeapp.partInfo.service.OnshapeService;

@Controller // This is important for returning HTML
public class OnshapeController {

    private final OnshapeService onshapeService;

    public OnshapeController(OnshapeService onshapeService) {
        this.onshapeService = onshapeService;
    }

// 1. Initial empty page
    @GetMapping("/")
    public String home() {
        return "part-info";
    }

    // 2. Result page (modified to make properties optional)
    @GetMapping("/view")
    public String viewProperties(@RequestParam String did, 
                                 @RequestParam String wid, 
                                 @RequestParam String eid, 
                                 Model model) {
        
        PhysicalProperties props = onshapeService.getPartStudioPhysicalProperties(did, wid, eid);
        model.addAttribute("properties", props);
        return "part-info";
    }
}