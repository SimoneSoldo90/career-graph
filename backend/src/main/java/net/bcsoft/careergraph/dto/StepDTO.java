package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.bcsoft.careergraph.entity.Step;

import java.util.List;

public record StepDTO(Long id, @JsonProperty("roadmap_id") Long roadmapId, Integer ord, String title, String description,

                               @JsonProperty("resources") List <ResourceDTO> rescourceDTOList,
                               @JsonProperty("roadmap_links") List <RoadmapLinkDTO> roadmapLinkDTOList,
                               @JsonProperty("skills") List <SkillDTO> skillDTOList) {

    public Step toEntity(){
        Step step = new Step();
        step.setId(this.id);
        step.setRoadmapId(this.roadmapId);
        step.setOrd(this.ord);
        step.setTitle(this.title);
        step.setDescription(this.description);
        return step;
    }
}

/*
public class StepDTO {
    Integer id;
    @JsonProperty("roadmap_id")
    Integer roadmapId;
    Integer order;
    String title;
    String description;
    @JsonProperty("resources")
    List <ResourceDTO> rescourceList = new ArrayList<>();
    @JsonProperty("roadmap_links")
    List <RoadmapLinkDTO> roadmapLinkList = new ArrayList<>();
    @JsonProperty("skills")
    List <SkillDTO> skillList = new ArrayList<>();


    public StepDTO(){}

    public StepDTO(Integer id, Integer roadmapId, Integer order, String title, String description, List <ResourceDTO> rescourceList, List <RoadmapLinkDTO> roadmapLinkList, List <SkillDTO> skillList) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.order = order;
        this.title = title;
        this.description = description;
        this.rescourceList = rescourceList;
        this.roadmapLinkList = roadmapLinkList;
        this.skillList = skillList;
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
}*/
