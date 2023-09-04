package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepServiceImpl implements IStepService {
    @Override
    public StepDTO create(StepDTO stepDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public List<StepDTO> getAll() {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public StepDTO getById(Integer stepId) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public StepDTO update(Integer stepId, StepDTO stepDTO) {
        System.out.println("Funziona!");
        return null;
    }

    @Override
    public StepDTO delete(Integer stepId) {
        System.out.println("Funziona!");
        return null;
    }
}
