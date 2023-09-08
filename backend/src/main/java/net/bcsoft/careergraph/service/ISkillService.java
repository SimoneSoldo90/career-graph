package net.bcsoft.careergraph.service;

import net.bcsoft.careergraph.dto.SkillDTO;

import java.util.List;

public interface ISkillService {

    List<SkillDTO> getAll();
    SkillDTO create(SkillDTO skillDTO);
    SkillDTO update(Integer skillId, SkillDTO skillDTO);
    SkillDTO getById(Integer skillId);
    List<SkillDTO> findByStepId(Long stepId);

}
