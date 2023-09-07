package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.SkillDTO;

import java.util.List;

public interface ISkillService {

    public List<SkillDTO> getAll();
    public SkillDTO create(SkillDTO skillDTO);
    public SkillDTO update(SkillDTO skillDTO);
    public SkillDTO getById(Long skillId);
    public void delete(Long skillId);

}
