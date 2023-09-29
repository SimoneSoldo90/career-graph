package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.RoadmapController;
import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IRoadmapService;
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
public class RoadmapTest {

    @MockBean
    IRoadmapService roadmapService;

    // SELECT ALL

    @Test
    void findRoadmaps_ok() throws NoContentException, InternalException {
        //Creo una falsa List di RoadmapDTO
        List<RoadmapDTO> roadmapDTOList = new ArrayList<>();
        roadmapDTOList.add(new RoadmapDTO(0L, "Test", "Descr", null));
        //Imposto in quali casi il mock deve rispondermi con roadmapDTOList
        Mockito.doReturn(roadmapDTOList).when(roadmapService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        RoadmapController roadmapController = new RoadmapController(roadmapService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = roadmapController.findRoadmaps();
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findRoadmaps_noContent() throws InternalException, NoContentException {
        //Imposto che il roadmap service mi deve lanciare una eccezione
        Mockito.doThrow(new NoContentException("test")).when(roadmapService).findAll();
        //Creo il controller con "new" invece che con "@Autowired"
        RoadmapController roadmapController = new RoadmapController(roadmapService);

        //Eseguo la chiamata che voglio testare
        ResponseEntity re = roadmapController.findRoadmaps();
        //Verifico con una asserzione che il risultato sia quello che mi aspetto
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // CREATE

    @Test
    void create_created() throws BadRequestException, InternalException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doReturn(fakeRoadmapDTO).when(roadmapService).create(fakeRoadmapDTO);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.createRoadmap(fakeRoadmapDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void create_badRequest() throws BadRequestException, InternalException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doThrow(new BadRequestException("test")).when(roadmapService).create(fakeRoadmapDTO);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.createRoadmap(fakeRoadmapDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
        // Assertions.assertEquals(re.getBody(), "Error creating roadmap: test");
    }

    // FIND BY ID

    @Test
    void findRoadmapById_ok() throws NotFoundException, InternalException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doReturn(fakeRoadmapDTO).when(roadmapService).findById(fakeRoadmapDTO.id());
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.findRoadmapById(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findRoadmapById_notFound() throws NotFoundException, InternalException {
        Mockito.doThrow(new NotFoundException("test")).when(roadmapService).findById(0L);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.findRoadmapById(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // UPDATE

    @Test
    void updateRoadmap_ok() throws ConflictException, InternalException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doReturn(fakeRoadmapDTO).when(roadmapService).update(fakeRoadmapDTO);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.updateRoadmap(0L, fakeRoadmapDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateRoadmap_conflict() throws ConflictException, InternalException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doThrow(new ConflictException("test"), new InternalException("test")).when(roadmapService).update(fakeRoadmapDTO);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.updateRoadmap(0L, fakeRoadmapDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CONFLICT);
    }

    // DELETE

    @Test
    void deleteRoadmap_ok() throws NotFoundException, ConflictException {
        RoadmapDTO fakeRoadmapDTO = new RoadmapDTO(0L, "title", "description", null);
        Mockito.doAnswer(invocation -> null).when(roadmapService).deleteRoadmap(fakeRoadmapDTO.id());
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.deleteRoadmap(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteRoadmap_notFound() throws NotFoundException, ConflictException {
        Mockito.doThrow(new NotFoundException("test")).when(roadmapService).deleteRoadmap(0L);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.deleteRoadmap(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteRoadmap_conflict() throws NotFoundException, ConflictException {
        Mockito.doThrow(new ConflictException("test")).when(roadmapService).deleteRoadmap(0L);
        RoadmapController roadmapController = new RoadmapController(roadmapService);
        ResponseEntity re = roadmapController.deleteRoadmap(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CONFLICT);
    }
}
