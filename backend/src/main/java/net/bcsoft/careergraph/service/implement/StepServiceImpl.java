package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.entity.Step;
import net.bcsoft.careergraph.mapper.StepMapper;
import net.bcsoft.careergraph.service.IResourceService;
import net.bcsoft.careergraph.service.IRoadmapLinkService;
import net.bcsoft.careergraph.service.ISkillService;
import net.bcsoft.careergraph.service.IStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StepServiceImpl implements IStepService {

    StepMapper stepMapper;
    IResourceService resourceService;
    IRoadmapLinkService roadmapLinkService;
    ISkillService skillService;

    @Autowired
    public StepServiceImpl(StepMapper stepMapper, IResourceService resourceService, IRoadmapLinkService roadmapLinkService, ISkillService skillService) {
        this.stepMapper = stepMapper;
        this.resourceService = resourceService;
        this.roadmapLinkService = roadmapLinkService;
        this.skillService = skillService;
    }

    /** JSON step
    {
        id: int, // non presente se create
        roadmap_id: int,
        order: int,
        title: string,
        description: string,
        resources: [], // presente solo se find
        roadmap_links: [], // presente solo se find
        skills: [] // presente solo se find
    }
     */


    @Override
    @Transactional
    public StepDTO create(StepDTO stepDTO) {
        Step step = stepDTO.toEntity();
        stepMapper.insert(step);
        Step result = stepMapper.selectById(step.getId());
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    public List<StepDTO> findAll() {
        List<Step> stepList = stepMapper.selectAll();
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList) {
            List<ResourceDTO> resourceDTOList = resourceService.findByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = roadmapLinkService.findByStepId(step.getId());
            List<SkillDTO> skillDTOList = skillService.findByStepId(step.getId());
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public List<StepDTO> findByRoadmapId(Long roadmapId) {
        List<Step> stepList = stepMapper.findByRoadmapId(roadmapId);
        List<StepDTO> stepDTOList = new ArrayList<>();
        for(Step step : stepList){
            List<ResourceDTO> resourceDTOList = resourceService.findByStepId(step.getId());
            List<RoadmapLinkDTO> roadmapLinkDTOList = roadmapLinkService.findByStepId(step.getId());
            List<SkillDTO> skillDTOList = skillService.findByStepId(step.getId());
            stepDTOList.add(new StepDTO(step.getId(), step.getRoadmapId(), step.getOrd(), step.getTitle(), step.getDescription(),
                    resourceDTOList, roadmapLinkDTOList, skillDTOList));
        }
        return stepDTOList;
    }

    @Override
    public StepDTO findById(Long stepId) {
        Step result = stepMapper.selectById(stepId);
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    @Override
    @Transactional
    public StepDTO update(StepDTO stepDTO) {
        Step step = stepDTO.toEntity();
        stepMapper.update(step);
        Step result = stepMapper.selectById(step.getId());
        return new StepDTO(result.getId(), result.getRoadmapId(), result.getOrd(), result.getTitle(), result.getDescription(), null, null, null);
    }

    /*public StepDTO delete(Integer stepId) {
        System.out.println("Funziona!");
        return null;
    }*/
}
