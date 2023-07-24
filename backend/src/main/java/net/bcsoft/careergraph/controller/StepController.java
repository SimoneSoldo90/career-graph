package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StepController {
    IStepService stepService;

    public StepController(IStepService stepService) {
        this.stepService = stepService;
    }

    @PostMapping("/steps")
    public StepDTO createStep(StepDTO stepDTO){
        return stepService.create(stepDTO); // Implementare
    }

    @GetMapping("/steps")
    public List<StepDTO> getStep(){
        return stepService.getAll();
    }

    @GetMapping("/steps/{stepId}")
    public StepDTO getStepById(@PathVariable Integer stepId){
        return stepService.getById(stepId); // Implementare
    }

    @PutMapping("/steps/{stepId}")
    public StepDTO updateStep(@PathVariable Integer stepId, @RequestBody StepDTO stepDTO){
        return stepService.update(stepId, stepDTO);
    }

    @DeleteMapping("/steps/{stepId}")
    public void deleteStep(@PathVariable Integer stepId){
        stepService.delete(stepId);
    }
}
