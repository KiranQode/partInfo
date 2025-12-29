package com.example.onshapeapp.partInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.onshapeapp.partInfo.model.PhysicalProperties;
import com.example.onshapeapp.partInfo.service.OnshapeService;

// @RestController
// @RequestMapping("/api/onshape")
// public class OnshapeController {
//     private final OnshapeService onshapeService;

//     public OnshapeController(OnshapeService onshapeService){
//         this.onshapeService = onshapeService;
//     }

//     @GetMapping("/properties/{docID}/{wID}/{eID}")
//     public PhysicalProperties getProperties(
//         @PathVariable String docID,
//         @PathVariable String wID,
//         @PathVariable String eID){
//             return onshapeService.getPartStudioPhysicalProperties(docID, wID, eID);
//         }
// }

@Controller // This is important for returning HTML
public class OnshapeController {

    private final OnshapeService onshapeService;

    public OnshapeController(OnshapeService onshapeService) {
        this.onshapeService = onshapeService;
    }

    @GetMapping("/api/onshape/properties/{did}/{wid}/{eid}")
    public String getProperties(@PathVariable String did, 
                                @PathVariable String wid, 
                                @PathVariable String eid, 
                                Model model) {
        
        PhysicalProperties props = onshapeService.getPartStudioPhysicalProperties(did, wid, eid);
        
        // This makes the 'props' object available to the HTML file
        model.addAttribute("properties", props);
        
        // This returns the name of the file: templates/part-info.html
        return "part-info"; 
    }
}