package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.StepController;
import net.bcsoft.careergraph.dto.*;
import net.bcsoft.careergraph.exception.*;
import net.bcsoft.careergraph.service.IStepService;
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
public class StepTest {
    @MockBean
    IStepService stepService;


    /////////////STEP///////////////

    @Test
    void findSteps_ok() throws NoContentException, InternalException {
        List<StepDTO> stepDTOList = new ArrayList<>();
        stepDTOList.add(new StepDTO(0L,0L,  4, null, null,null, null, null));
        Mockito.doReturn(stepDTOList).when(stepService).findAll();
        StepController stepController = new StepController(stepService);
        ResponseEntity re = stepController.findSteps();
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void findSteps_noContent() throws NoContentException, InternalException {
        Mockito.doThrow(new NoContentException("test")).when(stepService).findAll();
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findSteps();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }
    @Test
    void createStep_Created() throws BadRequestException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        Mockito.doReturn(stepDTO).when(stepService).create(stepDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createStep(stepDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void createStep_BadRequest() throws BadRequestException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null,null);
        Mockito.doThrow(new BadRequestException("test")).when(stepService).create(stepDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createStep(stepDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    void updateStep_Ok() throws ConflictException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        Mockito.doReturn(stepDTO).when(stepService).update(stepDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateStep(stepDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateStep_Conflict() throws ConflictException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        Mockito.doThrow(new ConflictException("test")).when(stepService).update(new StepDTO(0L,0L,null,null,null,null, null, null));
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateStep(stepDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    void findStepById_ok() throws NotFoundException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        Mockito.doReturn(stepDTO).when(stepService).findById(stepDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findStepById(stepDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findStepById_NotFound() throws NotFoundException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null,null, null, null, null);
        Mockito.doThrow(new NotFoundException("test")).when(stepService).findById(stepDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findStepById(stepDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode() ,HttpStatus.NOT_FOUND);
    }
    @Test
    void deleteStep_NotFound() throws NotFoundException, ConflictException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null,null,null,null,null);
        Mockito.doThrow(new NotFoundException("test")).when(stepService).deleteStep(stepDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteStep(stepDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @Test
    void deleteStep_Conflict() throws NotFoundException, ConflictException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        Mockito.doThrow(new ConflictException("test")).when(stepService).deleteStep(stepDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteStep(stepDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    /////////////////////////RESOURCE/////////////////////////////////
    @Test
    void findResource_ok() throws NoContentException, InternalException {
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        resourceDTOList.add(new ResourceDTO(0L,0L,  0L, null,null, null));
        Mockito.doReturn(resourceDTOList).when(stepService).findAll();
        StepController stepController = new StepController(stepService);
        ResponseEntity re = stepController.findAllResource(0L);
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
    }

    @Test

    void findResources_noContent() throws NoContentException, InternalException {
        Mockito.doThrow(new NoContentException("test")).when(stepService).findAll();
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findSteps();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void createResource_Created() throws BadRequestException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, null, null, null, null);
        Mockito.doReturn(resourceDTO).when(stepService).createResource(resourceDTO.stepId(), resourceDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createResource(resourceDTO.stepId(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void createResource_BadRequest() throws BadRequestException, InternalException {
        StepDTO stepDTO = new StepDTO(0L, 0L, null, null, null, null, null, null);
        ResourceDTO resourceDTO= new ResourceDTO(0L, 0L, 0L, null,null, null);
        Mockito.doThrow(new BadRequestException("test")).when(stepService).createResource(stepDTO.id(), resourceDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createResource(stepDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateResource_Ok() throws ConflictException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, null, null, null);
        Mockito.doReturn(resourceDTO).when(stepService).updateResource(resourceDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateResource(resourceDTO.stepId(), resourceDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateResource_Conflict() throws ConflictException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, null, null, null);
        Mockito.doThrow(new ConflictException("test")).when(stepService).updateResource(new ResourceDTO(0L,0L,0L,null,null,null));
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateResource(resourceDTO.stepId(), resourceDTO.id(), resourceDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }
    @Test
    void findResourceById_ok() throws NotFoundException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, null, null, null, null);
        Mockito.doReturn(resourceDTO).when(stepService).findByResourceId(resourceDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findByIdResource(resourceDTO.stepId(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findResourceById_NotFound() throws NotFoundException, InternalException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, null, null,null, null);
        Mockito.doThrow(new NotFoundException("test")).when(stepService).findByResourceId(resourceDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findByIdResource(resourceDTO.stepId(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode() ,HttpStatus.NOT_FOUND);
    }
    @Test
    void deleteResource_NotFound() throws NotFoundException, ConflictException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        Mockito.doThrow(new NotFoundException("test")).when(stepService).deleteResource(resourceDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteResource(resourceDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteResource_Conflict() throws NotFoundException, ConflictException {
        ResourceDTO resourceDTO = new ResourceDTO(0L, 0L, 0L, "test", "desc", "test");
        Mockito.doThrow(new ConflictException("test")).when(stepService).deleteResource(resourceDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteResource(resourceDTO.id(), resourceDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }



    /////////////////////////////////ROADMAPLINK/////////////////////////////////////////////////

    @Test
    void findAllRoadmapLink_ok() throws NoContentException, InternalException {
        //Creo una falsa List di RoadmapDTO
        List<RoadmapLinkDTO> roadmapLinkDTOList = new ArrayList<>();
        roadmapLinkDTOList.add(new RoadmapLinkDTO(0L,0L,0L, null, null));
        //Imposto in quali casi il mock deve rispondermi con roadmapDTOList
        Mockito.doReturn(roadmapLinkDTOList).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        StepController stepController = new StepController(stepService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = stepController.findStepRoadmapLinkList(0L);
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void findRoadmapLink_noContent() throws InternalException, NoContentException {
        //Imposto che il roadmap service mi deve lanciare una eccezione
        Mockito.doThrow(new NoContentException("test")).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        StepController stepController = new StepController(stepService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = stepController.findStepRoadmapLinkList(0L);
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void createRoadmapLink_Created() throws BadRequestException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, null, null, null);
        Mockito.doReturn(roadmapLinkDTO).when(stepService).createRoadmapLink(roadmapLinkDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createStepRoadmapLink(roadmapLinkDTO.stepId(), roadmapLinkDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }
    @Test
    void createRoadmapLink_BadRequest() throws BadRequestException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, null, null, null);
        Mockito.doThrow(new BadRequestException("test")).when(stepService).createRoadmapLink(roadmapLinkDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.createStepRoadmapLink(roadmapLinkDTO.stepId(), roadmapLinkDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    void updateRoadmapLink_Ok() throws ConflictException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO (0L, 0L, 0L, null, null);
        Mockito.doReturn(roadmapLinkDTO).when(stepService).updateRoadmapLink(roadmapLinkDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateRoadmapLink(roadmapLinkDTO.stepId(), roadmapLinkDTO.id(), roadmapLinkDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    void updateRoadmapLink_Conflict() throws ConflictException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, 0L, null, null);
        Mockito.doThrow(new ConflictException("test")).when(stepService).updateRoadmapLink(roadmapLinkDTO);
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.updateRoadmapLink(roadmapLinkDTO.stepId(), roadmapLinkDTO.id(), roadmapLinkDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }
    @Test
    void findByRoadmapLinkId_ok() throws NotFoundException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, null, null, null);
        Mockito.doReturn(roadmapLinkDTO).when(stepService).findByRoadmapLinkId(roadmapLinkDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findByRoadmapLinkId(roadmapLinkDTO.stepId(), roadmapLinkDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    void findByRoadmapLinkId_NotFound() throws NotFoundException, InternalException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, null, null,null);
        Mockito.doThrow(new NotFoundException("test")).when(stepService).findByRoadmapLinkId(roadmapLinkDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.findByRoadmapLinkId(roadmapLinkDTO.stepId(), roadmapLinkDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode() ,HttpStatus.NOT_FOUND);
    }
    @Test
    void deleteRoadmapLink_NotFound() throws NotFoundException, ConflictException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, 0L, "test", "desc");
        Mockito.doThrow(new NotFoundException("test")).when(stepService).deleteRoadmapLink(roadmapLinkDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteStepRoadmapLink(roadmapLinkDTO.id(), roadmapLinkDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteRoadmapLink_Conflict() throws NotFoundException, ConflictException {
        RoadmapLinkDTO roadmapLinkDTO = new RoadmapLinkDTO(0L, 0L, 0L, "test", "desc");
        Mockito.doThrow(new ConflictException("test")).when(stepService).deleteRoadmapLink(roadmapLinkDTO.id());
        StepController stepController = new StepController(stepService);
        ResponseEntity responseEntity = stepController.deleteStepRoadmapLink(roadmapLinkDTO.id(), roadmapLinkDTO.id());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

}