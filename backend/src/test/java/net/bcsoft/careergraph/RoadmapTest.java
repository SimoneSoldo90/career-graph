package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.RoadmapController;
import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
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
        Assertions.assertTrue(re.getStatusCode().equals(HttpStatus.OK));
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
}
