package net.bcsoft.careergraph.dto;

public class SkillDTO {
    int id;
    private int stepId;
    private String title;
    private String description;

    public void SkillDTO(){}

    public SkillDTO(int id, int stepId, String title, String description) {
        this.id = id;
        this.stepId = stepId;
        this.title = title;
        this.description = description;
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
}


