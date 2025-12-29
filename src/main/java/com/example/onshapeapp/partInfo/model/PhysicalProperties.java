package com.example.onshapeapp.partInfo.model;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhysicalProperties {

    // Onshape puts everything inside a "bodies" map
    private Map<String, BodyProperties> bodies;

    public Map<String, BodyProperties> getBodies() { return bodies; }
    public void setBodies(Map<String, BodyProperties> bodies) { this.bodies = bodies; }

    // Helper method to get the TOTAL mass of the Part Studio
    public Double getActualMass() {
        if (bodies != null && bodies.containsKey("-all-")) {
            List<Double> massList = bodies.get("-all-").getMass();
            return (massList != null && !massList.isEmpty()) ? massList.get(0) : 0.0;
        }
        return 0.0;
    }

    // This internal class matches the data for each part/body
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BodyProperties {
        private List<Double> mass;
        private List<Double> volume;

        public List<Double> getMass() { return mass; }
        public void setMass(List<Double> mass) { this.mass = mass; }

        public List<Double> getVolume() { return volume; }
        public void setVolume(List<Double> volume) { this.volume = volume; }
    }
}