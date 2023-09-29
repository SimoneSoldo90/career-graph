package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.SkillController;
import net.bcsoft.careergraph.controller.UserController;
import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.SkillDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.service.ISkillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Test
    void findResources_noContent() throws NoContentException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new NoContentException("test")).when(skillService).findAllResource(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findResources(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void findResourceById_ok() throws NotFoundException, InternalException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        Mockito.doReturn(resourceDTO).when(skillService).findResourceById(skillDTO.id(), resourceDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findResourceById(skillDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findResourceById_NotFound() throws NotFoundException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new NotFoundException("test")).when(skillService).findResourceById(skillDTO.id(), resourceDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.findResourceById(skillDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode() ,HttpStatus.NOT_FOUND);
    }

    @Test
    void createResource_Created() throws BadRequestException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doReturn(resourceDTO).when(skillService).createResource(skillDTO.id(), resourceDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.createResource(skillDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void createResource_BadRequest() throws BadRequestException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new BadRequestException("test")).when(skillService).createResource(skillDTO.id(), resourceDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.createResource(skillDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateResource_Ok() throws ConflictException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doReturn(resourceDTO).when(skillService).updateResource(resourceDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.updateResource(skillDTO.id(), resourceDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateResource_Conflict() throws ConflictException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new ConflictException("test")).when(skillService).updateResource(resourceDTO);
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.updateResource(skillDTO.id(), resourceDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    void deleteSkill_Ok() throws ConflictException, NotFoundException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doAnswer(invocation -> null).when(skillService).deleteSkill(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity re = skillController.deleteSkill(skillDTO.id());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteSkill_NotFound() throws NotFoundException, ConflictException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new NotFoundException("test")).when(skillService).deleteSkill(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.deleteSkill(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteSkill_Conflict() throws NotFoundException, ConflictException {
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new ConflictException("test")).when(skillService).deleteSkill(skillDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.deleteSkill(skillDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    void deleteResource_Ok() throws ConflictException, NotFoundException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doAnswer(invocation -> null).when(skillService).deleteResource(resourceDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity re = skillController.deleteResource(skillDTO.id(), resourceDTO.id());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }


    @Test
    void deleteResource_NotFound() throws NotFoundException, ConflictException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new NotFoundException("test")).when(skillService).deleteResource(resourceDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.deleteResource(skillDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteResource_Conflict() throws NotFoundException, ConflictException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        SkillDTO skillDTO = new SkillDTO(0L, "test", "descr", null);
        Mockito.doThrow(new ConflictException("test")).when(skillService).deleteResource(resourceDTO.id());
        SkillController skillController = new SkillController(skillService);
        ResponseEntity responseEntity = skillController.deleteResource(skillDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }
}
