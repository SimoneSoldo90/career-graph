package net.bcsoft.careergraph.dto;

public class ResourceDTO {
    Integer stepId;
    Integer skillId;
    String type;
    String description;
    String url;

    public ResourceDTO() {
    }

    public ResourceDTO(Integer steoId, Integer skillId, String type, String description, String url) {
        this.stepId = steoId;
        this.skillId = skillId;
        this.type = type;
        this.description = description;
        this.url = url;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
