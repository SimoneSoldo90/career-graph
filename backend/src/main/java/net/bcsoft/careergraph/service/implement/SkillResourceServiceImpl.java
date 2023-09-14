package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.service.ISkillResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillResourceServiceImpl implements ISkillResourceService {
    @Override
    public ResourceDTO create(Integer skillId, ResourceDTO resourceDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public List<ResourceDTO> getAll(Integer skillId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public ResourceDTO getById(Integer skillId, Integer resourceId) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public ResourceDTO update(Integer skillId, Integer resourceId, ResourceDTO resourceDTO) {
        System.out.println("Funziona");
        return null;
    }

    @Override
    public ResourceDTO delete(Integer skillId, Integer resourceId) {
        System.out.println("Funziona");
        return null;
    }
}
