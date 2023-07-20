package net.bcsoft.careergraph.dto;

public class UserSkillDTO {
    Integer id;
    Integer userId;
    Integer skillId;
    String status;

    public UserSkillDTO() {
    }

    public UserSkillDTO(Integer id, Integer userId, Integer skillId, String status) {
        this.id = id;
        this.userId = userId;
        this.skillId = skillId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}