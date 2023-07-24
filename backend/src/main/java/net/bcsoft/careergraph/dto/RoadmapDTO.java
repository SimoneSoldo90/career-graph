package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RoadmapDTO {
    Integer id;
    String title;
    String description;
    @JsonProperty("steps")
    List <StepDTO> stepList = new ArrayList<>();

    public RoadmapDTO(){

    }

    public RoadmapDTO(Integer id, String title, String description, List <StepDTO> stepList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stepList = stepList;
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
