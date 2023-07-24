package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SkillDTO {
    Integer id;
    @JsonProperty("step_id")
    Integer stepId;
    String title;
    String description;
    @JsonProperty("resources")
    List<ResourceDTO> resourceList = new ArrayList<>();

    public SkillDTO() {}

    public SkillDTO(int id, int stepId, String title, String description, List<ResourceDTO> resourceList) {
        this.id = id;
        this.stepId = stepId;
        this.title = title;
        this.description = description;
        this.resourceList = resourceList;
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

    public List<ResourceDTO> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceDTO> resourceList) {
        this.resourceList = resourceList;
    }
}


