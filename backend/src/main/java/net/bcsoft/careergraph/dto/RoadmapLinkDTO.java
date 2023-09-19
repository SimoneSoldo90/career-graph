package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.bcsoft.careergraph.entity.RoadmapLink;

public record RoadmapLinkDTO(Long id, @JsonProperty("step_id") Long stepId, @JsonProperty("roadmap_id") Long roadmapId, @JsonProperty("roadmap_title") String roadmapTitle, @JsonProperty("roadmap_description") String roadmapDescription) {

    public RoadmapLink toEntity() {
        RoadmapLink roadmapLink = new RoadmapLink();
        roadmapLink.setId(this.id);
        roadmapLink.setStepId(this.stepId);
        roadmapLink.setRoadmapId(this.roadmapId);
        return roadmapLink;
    }
}

/*
public class RoadmapLinkDTO {
    @JsonProperty("step_id")
    private Integer stepId;
    @JsonProperty("roadmap_id")
    private Integer roadmapId;
    @JsonProperty("roadmap_title")
    private String roadmapTitle;
    @JsonProperty("roadmap_description")
    private String roadmapDescription;
    public RoadmapLinkDTO(){}

    public RoadmapLinkDTO(int stepId, int roadmapId, String roadmapTitle, String roadmapDescription) {
        this.stepId = stepId;
        this.roadmapId = roadmapId;
        this.roadmapTitle = roadmapTitle;
        this.roadmapDescription = roadmapDescription;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(int roadmapId) {
        this.roadmapId = roadmapId;
    }

    public String getRoadmapTitle() {
        return roadmapTitle;
    }

    public void setRoadmapTitle(String roadmapTitle) {
        this.roadmapTitle = roadmapTitle;
    }

    public String getRoadmapDescription() {
        return roadmapDescription;
    }

    public void setRoadmapDescription(String roadmapDescription) {
        this.roadmapDescription = roadmapDescription;
    }
}*/


