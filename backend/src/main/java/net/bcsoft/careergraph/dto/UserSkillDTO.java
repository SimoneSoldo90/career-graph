package net.bcsoft.careergraph.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.bcsoft.careergraph.entity.AccountSkill;

public record UserSkillDTO(Long id, @JsonProperty("user_id") Long userId, @JsonProperty("skill_id") Long skillId, Long status) {
    public AccountSkill toEntity(){
        AccountSkill accountSkill = new AccountSkill();
        accountSkill.setId(id);
        accountSkill.setIdAccount(userId);
        accountSkill.setIdSkill(skillId);
        accountSkill.setIdSkillStatus(status);
        return accountSkill;
    }
}

/*
public class UserSkillDTO {
    Integer id;
    @JsonProperty("user_id")
    Integer userId;
    @JsonProperty("skill_id")
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
}*/
