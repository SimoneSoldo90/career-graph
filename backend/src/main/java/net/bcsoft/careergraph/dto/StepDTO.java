package net.bcsoft.careergraph.dto;

import java.util.ArrayList;
import java.util.List;

public class StepDTO {
    Integer id;
    Integer roadmapId;
    Integer order;
    String title;
    String description;
    List <ResourceDTO> resources = new ArrayList<>();
    List <RoadmapLinkDTO> roadmapLinks = new ArrayList<>();
    List <SkillDTO> skills = new ArrayList<>();


    public StepDTO(){}

    public StepDTO(Integer id, Integer roadmapId, Integer order, String title, String description, List <ResourceDTO> resources, List <RoadmapLinkDTO> roadmapLinks, List <SkillDTO> skills) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.order = order;
        this.title = title;
        this.description = description;
        this.resources = resources;
        this.roadmapLinks = roadmapLinks;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(Integer roadmapId) {
        this.roadmapId = roadmapId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
