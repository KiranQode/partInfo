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

    @GetMapping("/")
    public String getRightPanel(
            @RequestParam(value = "did", required = false) String did,
            @RequestParam(value = "wid", required = false) String wid,
            @RequestParam(value = "eid", required = false) String eid,
            Model model) {
        String preFilledUrl = "";
        if (did != null && !did.isEmpty() && !did.contains("{")) {
            preFilledUrl = String.format("https://cad.onshape.com/documents/%s/w/%s/e/%s", did, wid, eid);
            
            // Auto-load properties if IDs are real
            try {
                PhysicalProperties props = onshapeService.getPartStudioPhysicalProperties(did, wid, eid);
                model.addAttribute("properties", props);
            } catch (Exception e) {
                model.addAttribute("error", "Error fetching data: " + e.getMessage());
            }
        }

        model.addAttribute("preFilledUrl", preFilledUrl);
        return "part-info";
    }

    // Result page (modified to make properties optional)
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