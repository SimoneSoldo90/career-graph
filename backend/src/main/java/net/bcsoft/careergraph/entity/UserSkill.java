package net.bcsoft.careergraph.entity;

public class UserSkill {
    private Long id;
    private Long userId; // accountId
    private Long skillId;
    private String skillStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillStatusId() {
        return skillStatusId;
    }

    public void setSkillStatusId(String skillStatusId) {
        this.skillStatusId = skillStatusId;
    }
}
