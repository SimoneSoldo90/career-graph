package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {
    SkillMapper skillMapper;

    public SkillServiceImpl(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    @Override
    public List<SkillDTO> getAll() {
        List<Skill> skillList = skillMapper.selectAll();
        List<SkillDTO>skillDTOList = new ArrayList<>();
        for(Skill skill : skillList) {
        SkillDTO skillDTO = new SkillDTO(skill.getId(), null, skill.getTitle(), skill.getDescription(), null);
        skillDTOList.add(skillDTO);
        }
        return skillDTOList;
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
        Skill skill = skillDTO.toEntity();
        skillMapper.insert(skill);
        Skill result = skillMapper.selectById(skill.getId());
        return new SkillDTO(result.getId(), null, result.getTitle(), result.getDescription(), null);
    }

    @Override
    public SkillDTO update(SkillDTO skillDTO) {
        Skill skill = skillDTO.toEntity();
        skillMapper.update(skill);
        return new SkillDTO(skill.getId(), null, skill.getTitle(), skill.getDescription(), null);
    }

    @Override
    public SkillDTO getById(Long skillId) {
        Skill result = skillMapper.selectById(skillId);
        return new SkillDTO(result.getId(), null, result.getTitle(), result.getDescription(), null);
    }
    @Override
    public void delete(Long skillId) {
        System.out.println("Funziona");
    }
}

