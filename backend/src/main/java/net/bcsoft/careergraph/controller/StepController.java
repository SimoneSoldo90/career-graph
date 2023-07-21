package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.StepDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StepController {
    @PostMapping("/steps")
    public StepDTO createStep(StepDTO stepDTO){
        return new StepDTO(); // Implementare
    }

    @GetMapping("/steps")
    public List<StepDTO> getStep(List<StepDTO> stepDTOList){
        return new ArrayList<StepDTO>(); // Implementare
    }

    @GetMapping("/steps/{stepId}")
    public StepDTO getStep(@PathVariable Integer stepId){
        return new StepDTO(); // Implementare
    }

    @PutMapping("/steps/{stepId}")
    public StepDTO updateStep(@PathVariable Integer stepId, @RequestBody StepDTO stepDTO){
        return new StepDTO(); // Implementare
    }

    @DeleteMapping("/steps/{stepId}")
    public void deleteStep(@PathVariable Integer stepId){
        // Implementare
    }
}
