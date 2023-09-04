package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.service.IStepResourceService;

import java.util.List;

public class StepResourceImpl implements IStepResourceService {
    @Override
    public ResourceDTO create(Integer stepId, ResourceDTO resourceDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public List<ResourceDTO> getAll(Integer stepId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public ResourceDTO getById(Integer stepId, Integer resourceId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public ResourceDTO update(Integer stepId, Integer resourceId, ResourceDTO resourceDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public ResourceDTO delete(Integer stepId, Integer resourceId) {
        System.out.println("Funziona!");
        return null;
    }
}
