package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {

    SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    @Override
    public List<SkillDTO> getAll() {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public SkillDTO update(Integer skillId, SkillDTO skillDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public SkillDTO getById(Integer skillId) {
        System.out.println("Funziona!");
        return null;
    }

    public List<SkillDTO> findByStepId(Long stepId) {
        List<Skill> skillList = skillMapper.selectByStepId(stepId);
        List<SkillDTO> result = new ArrayList<>();
        for (Skill skill : skillList) {
            result.add(new SkillDTO(skill.getId(), skill.getTitle(), skill.getDescription()));
        }

        return result;
    }
}
