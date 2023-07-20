package net.bcsoft.careergraph.dto;

import java.util.ArrayList;
import java.util.List;

public class RoadmapDTO {
    Integer id;
    String title;
    String description;
    List <StepDTO> steps = new ArrayList<>();

    public RoadmapDTO(){

    }

    public RoadmapDTO(Integer id, String title, String description, List <StepDTO> steps) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.steps = steps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
