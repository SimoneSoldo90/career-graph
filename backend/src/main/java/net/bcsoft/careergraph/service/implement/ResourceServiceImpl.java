package net.bcsoft.careergraph.service.implement;

import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.entity.Resource;
import net.bcsoft.careergraph.mapper.ResourceMapper;
import net.bcsoft.careergraph.service.IResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements IResourceService {

    ResourceMapper resourceMapper;

    public ResourceServiceImpl(ResourceMapper resourceMapper){
        this.resourceMapper = resourceMapper;
    }
    public List<ResourceDTO> findByStepId(Long stepId){
        List<Resource> resourceList = resourceMapper.selectByStepId(stepId);
        List<ResourceDTO> result = new ArrayList<>();
        for (Resource resource : resourceList) {
            result.add(new ResourceDTO(resource.getId(), resource.getSkillId(), resource.getResourceTypeId(), resource.getDescription(), resource.getUrl()));
        }
        return result;
    }
}
