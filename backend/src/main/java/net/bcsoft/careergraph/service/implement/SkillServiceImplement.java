package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.service.ISkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImplement implements ISkillService {
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
}
