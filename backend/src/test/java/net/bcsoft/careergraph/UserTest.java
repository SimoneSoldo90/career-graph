package net.bcsoft.careergraph;

import net.bcsoft.careergraph.controller.RoadmapController;
import net.bcsoft.careergraph.controller.UserController;
import net.bcsoft.careergraph.dto.RoadmapDTO;
import net.bcsoft.careergraph.dto.UserDTO;
import net.bcsoft.careergraph.dto.UserSkillDTO;
import net.bcsoft.careergraph.exception.BadRequestException;
import net.bcsoft.careergraph.exception.ConflictException;
import net.bcsoft.careergraph.exception.InternalException;
import net.bcsoft.careergraph.exception.NoContentException;
import net.bcsoft.careergraph.exception.NotFoundException;
import net.bcsoft.careergraph.service.IUserService;
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
public class UserTest {

    @MockBean
    IUserService userService;

    // FIND BY ID

    @Test
    void findById_ok() throws NotFoundException, InternalException {
        UserDTO fakeUserDTO = new UserDTO(0L, "ssoUid", "first name", "last name", "email");
        Mockito.doReturn(fakeUserDTO).when(userService).findById(fakeUserDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUser(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findById_notFound() throws NotFoundException, InternalException {
        Mockito.doThrow(new NotFoundException("test")).when(userService).findById(0L);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUser(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // FIND BY ID USER SKILL

    @Test
    void findUserSkillByUserId_ok() throws InternalException, NoContentException {
        List<UserSkillDTO> fakeUserSkillDTOList = new ArrayList<>();
        fakeUserSkillDTOList.add(new UserSkillDTO(0L, 0L, 0L, "test"));
        UserDTO fakeUserDTO = new UserDTO(0L, "ssoUid", "first name", "last name", "email");
        Mockito.doReturn(fakeUserSkillDTOList).when(userService).findUserSkillByUserId(fakeUserDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUserSkillByUserId(fakeUserDTO.id());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findUserSkillByUserId_noContent() throws InternalException, NoContentException {
        UserDTO fakeUserDTO = new UserDTO(0L, "ssoUid", "first name", "last name", "email");
        Mockito.doThrow(new NoContentException("test")).when(userService).findUserSkillByUserId(fakeUserDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUserSkillByUserId(fakeUserDTO.id());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // CREATE

    @Test
    void createUserSkill_created() throws BadRequestException, InternalException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doReturn(fakeUserSkillDTO).when(userService).createUserSkill(fakeUserSkillDTO);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.createUserSkill(0L, fakeUserSkillDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void createUserSkill_badRequest() throws BadRequestException, InternalException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doThrow(new BadRequestException("test")).when(userService).createUserSkill(fakeUserSkillDTO);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.createUserSkill(0L, fakeUserSkillDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    // UPDATE

    @Test
    void updateUserSkill_ok() throws ConflictException, InternalException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doReturn(fakeUserSkillDTO).when(userService).updateUserSkill(fakeUserSkillDTO);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.updateUserSkill(0L, 0L, fakeUserSkillDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateUserSkill_conflict() throws ConflictException, InternalException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doThrow(new ConflictException("test"), new InternalException("test")).when(userService).updateUserSkill(fakeUserSkillDTO);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.updateUserSkill(0L, 0L, fakeUserSkillDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CONFLICT);
    }

    // SELECT BY ID SKILL

    @Test
    void findUserSkillById_ok() throws NotFoundException, InternalException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doReturn(fakeUserSkillDTO).when(userService).findUserSkillById(fakeUserSkillDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUserSkillById(0L, 0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findUserSkillById_notFound() throws NotFoundException, InternalException {
        Mockito.doThrow(new NotFoundException("test")).when(userService).findUserSkillById(0L);
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.findUserSkillById(0L, 0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }


    // DELETE

    @Test
    void deleteUserSkill_ok() throws ConflictException, NotFoundException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doAnswer(invocation -> null).when(userService).deleteUserSkill(fakeUserSkillDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.deleteUserSkill(fakeUserSkillDTO.userId());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteUserSkill_notFound() throws ConflictException, NotFoundException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doThrow(new NotFoundException("test")).when(userService).deleteUserSkill(fakeUserSkillDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.deleteUserSkill(fakeUserSkillDTO.userId());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteUserSkill_conflict() throws ConflictException, NotFoundException {
        UserSkillDTO fakeUserSkillDTO = new UserSkillDTO(0L, 0L, 0L, "test");
        Mockito.doThrow(new ConflictException("test")).when(userService).deleteUserSkill(fakeUserSkillDTO.id());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.deleteUserSkill(fakeUserSkillDTO.userId());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CONFLICT);
    }
}
