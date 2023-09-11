package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.entity.Skill;
import net.bcsoft.careergraph.mapper.SkillMapper;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements ISkillService {

    /*
    ### JSON skill
{
    id: int, // non presente se POST request
	step_id: int,
    title: string,
    description: string,
	resources: [] // presente solo se GET response
}
     */

    SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    @Override
    public List<SkillDTO> findAll() {
        List<Skill> skillList = skillMapper.selectAll();
        List<SkillDTO> skillDTOList = new ArrayList<>();


        return null;
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
        System.out.println("Funziona!");
        return null;
    }


    @Override
    public SkillDTO update(SkillDTO skillDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public SkillDTO findById(Long skillId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public List<SkillDTO> findByStepId(Long stepId) {
        List<Skill> skillList = skillMapper.selectByStepId(stepId);
        List<SkillDTO> result = new ArrayList<>();
        for (Skill skill : skillList) {
            result.add(new SkillDTO(skill.getId(), stepId, skill.getTitle(), skill.getDescription(), null));
        }

        return result;
    }

    @Override
    public List<Resource> findAllResource(Long skillId) {
        return null;
    }

    @Override
    public Resource createResource(Long skillId, Resource resource) {
        return null;
    }

    @Override
    public Resource findResourceById(Long skillId, Long resourceId) {
        return null;
    }

    @Override
    public Resource updateResource(Long skillId, Long resourceId, Resource resource) {
        return null;
    }
}
