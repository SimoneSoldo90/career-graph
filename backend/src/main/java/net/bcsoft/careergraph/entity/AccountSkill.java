package net.bcsoft.careergraph.entity;

public class AccountSkill {
    private Long id;
    private Long idAccount;
    private Long idSkill;
    private Long idSkillStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Long idSkill) {
        this.idSkill = idSkill;
    }

    public Long getIdSkillStatus() {
        return idSkillStatus;
    }

    public void setIdSkillStatus(Long idSkillStatus) {
        this.idSkillStatus = idSkillStatus;
    }
}
