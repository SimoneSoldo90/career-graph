package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.RoadmapController;
import net.bcsoft.careergraph.controller.StepController;
import net.bcsoft.careergraph.dto.ResourceDTO;
import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.RoadmapLinkDTO;
import net.bcsoft.careergraph.dto.StepDTO;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.service.IRoadmapService;
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

    @Test
    void findSteps_ok() throws NoContentException, InternalException {
        //Creo una falsa List di RoadmapDTO
        List<StepDTO> stepDTOList = new ArrayList<>();
        stepDTOList.add(new StepDTO(0L,0L,  4, null, null,null, null, null));
        //Imposto in quali casi il mock deve rispondermi con roadmapDTOList
        Mockito.doReturn(stepDTOList).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        StepController stepController = new StepController(stepService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = stepController.findSteps();
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void findSteps_noContent() throws InternalException, NoContentException {
        //Imposto che il roadmap service mi deve lanciare una eccezione
        Mockito.doThrow(new NoContentException("test")).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        RoadmapController roadmapController = new RoadmapController(null);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = roadmapController.findRoadmaps();
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    //////////////////////////////////////////////////////////
    @Test
    void findResource_ok() throws NoContentException, InternalException {
        //Creo una falsa List di RoadmapDTO
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        resourceDTOList.add(new ResourceDTO(0L,0L,  0L, null,null, null));
        //Imposto in quali casi il mock deve rispondermi con roadmapDTOList
        Mockito.doReturn(resourceDTOList).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        StepController stepController = new StepController(stepService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = stepController.findAllResource(0L);
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void findResource_noContent() throws InternalException, NoContentException {
        //Imposto che il roadmap service mi deve lanciare una eccezione
        Mockito.doThrow(new NoContentException("test")).when(stepService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        StepController stepController = new StepController(null);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = stepController.findAllResource(0L);
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    //////////////////////////////////////////////////////////////////////////////////

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

}


