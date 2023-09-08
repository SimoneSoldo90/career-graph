package net.bcsoft.careergraph.service;


import net.bcsoft.careergraph.dto.ResourceDTO;

import java.util.List;

public interface IResourceService {
    List<ResourceDTO> findByStepId(Long stepId);
}
