package net.bcsoft.careergraph.dto;

import java.util.ArrayList;
import java.util.List;

public class SkillDTO {
    int id;
    private int stepId;
    private String title;
    private String description;
    List<ResourceDTO> resources = new ArrayList<>();


    public SkillDTO(){}

    public SkillDTO(int id, int stepId, String title, String description,List<ResourceDTO> resources) {
        this.id = id;
        this.stepId = stepId;
        this.title = title;
        this.description = description;
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
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

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
}


