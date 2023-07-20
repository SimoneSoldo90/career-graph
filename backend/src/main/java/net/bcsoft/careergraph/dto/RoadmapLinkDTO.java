package net.bcsoft.careergraph.dto;

public class RoadmapLinkDTO {
    private int stepId;
    private int roadmapId;
    private String roadmapTitle;
    private String roadmapDescription;
    public void RoadMapLinkDTO(){}

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
}

