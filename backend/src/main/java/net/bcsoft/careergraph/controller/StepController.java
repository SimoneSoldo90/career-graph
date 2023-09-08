package net.bcsoft.careergraph.controller;

import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StepController {
    IStepService iStepService;

    @Autowired
    public StepController(IStepService iStepService) {
        this.iStepService = iStepService;
    }

    @PostMapping("/steps")
    public StepDTO createStep(@RequestBody StepDTO stepDTO){
        return iStepService.create(stepDTO); // Implementare
    }

    @GetMapping("/steps")
    public List<StepDTO> findSteps(){
        return iStepService.findAll();
    }

    @GetMapping("/steps/{stepId}")
    public StepDTO findStepById(@PathVariable Long stepId){
        return iStepService.findById(stepId); // Implementare
    }

    @PutMapping("/steps")
    public StepDTO updateStep(@RequestBody StepDTO stepDTO){
        return iStepService.update(stepDTO);
    }

    /*@DeleteMapping("/steps/{stepId}")
    public void deleteStep(@PathVariable Long stepId){
        iStepService.delete(stepId);
    }*/
}
