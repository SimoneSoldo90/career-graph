package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.bcsoft.careergraph.entity.Resource;

public record ResourceDTO(Long id, @JsonProperty("step_id") Long stepId, @JsonProperty("skill_id") Long skillId, @JsonProperty("resource_type_id") String type, String description, String url){
public Resource toEntity() {
    Resource resource = new Resource();
    resource.setId(this.id);
    resource.setStepId(this.stepId);
    resource.setSkillId(this.skillId);
    resource.setResourceTypeId(this.type);
    resource.setDescription(this.description);
    resource.setUrl(this.url);
    return  resource;
    }
}

/*ublic class ResourceDTO {
    @JsonProperty("step_id")
    Integer id;
    @JsonProperty("skill_id")
    Integer skillId;
    String type;
    String description;
    String url;

    public ResourceDTO() {
    }

    public ResourceDTO(Integer id, Integer skillId, String type, String description, String url) {
        this.id = id;
        this.skillId = skillId;
        this.type = type;
        this.description = description;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}*/

