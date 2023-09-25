package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.SkillController;
import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.service.ISkillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SkillTest {

    @MockBean
    ISkillService skillService;

    @Test
    void findSkills_ok() throws NoContentException, InternalException {
        List <SkillDTO> skillDTOList = new ArrayList<>();
        skillDTOList.add(new SkillDTO(0L, "Test", "Descr", null));
        Mockito.doReturn(skillDTOList).when(skillService).findAllSkills();
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findSkills();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findSkills_noContent() throws NoContentException, InternalException {
        Mockito.doThrow(new NoContentException("test")).when(skillService).findAllSkills();
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findSkills();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void findSkillById_ok() throws NotFoundException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doReturn(skillDTO).when(skillService).findSkillById(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findSkillById(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findSkillById_NotFound() throws NotFoundException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new NotFoundException("test")).when(skillService).findSkillById(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findSkillById(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode() ,HttpStatus.NOT_FOUND);
    }

    @Test
    void createSkill_Created() throws BadRequestException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doReturn(skillDTO).when(skillService).createSkill(skillDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.createSkill(skillDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void createSkill_BadRequest() throws BadRequestException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new BadRequestException("test")).when(skillService).createSkill(skillDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.createSkill(skillDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateSkill_Ok() throws ConflictException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doReturn(skillDTO).when(skillService).updateSkill(skillDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.updateSkillId(skillDTO.id(), skillDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateSkill_Conflict() throws ConflictException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new ConflictException("test")).when(skillService).updateSkill(skillDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.updateSkillId(skillDTO.id(), skillDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    void findResources_Ok() throws NoContentException, InternalException {
        List <ResourceDTO> resourceDTOList = new ArrayList<>();
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        resourceDTOList.add(new ResourceDTO(0L, 0L, 0L, "test", "test", "test"));
        Mockito.doReturn(resourceDTOList).when(skillService).findAllResource(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findResources(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
