package com.example.onshapeapp.partInfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onshapeapp.partInfo.model.PhysicalProperties;
import com.example.onshapeapp.partInfo.service.OnshapeService;

@RestController
@RequestMapping("/api/onshape")
public class OnshapeController {
    private final OnshapeService onshapeService;

    public OnshapeController(OnshapeService onshapeService){
        this.onshapeService = onshapeService;
    }

    @GetMapping("/properties/{docID}/{wID}/{eID}")
    public PhysicalProperties getProperties(
        @PathVariable String docID,
        @PathVariable String wID,
        @PathVariable String eID){
            return onshapeService.getPartStudioPhysicalProperties(docID, wID, eID);
        }
}
