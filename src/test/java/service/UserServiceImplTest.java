package service;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.repository.UserRepository;
import com.example.carsharingservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    private static UserRepository userRepository;
    @InjectMocks
    private static UserServiceImpl userService;

    private static User testUser;

    @BeforeAll
    static void beforeAll() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("johndoe@example.com");
        testUser.setRole(User.Role.CUSTOMER);
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser = userService.save(testUser);

        assertNotNull(savedUser);
        assertEquals(testUser.getId(), savedUser.getId());
        assertEquals(testUser.getFirstName(), savedUser.getFirstName());
        assertEquals(testUser.getLastName(), savedUser.getLastName());
        assertEquals(testUser.getEmail(), savedUser.getEmail());
        assertEquals(testUser.getRole(), savedUser.getRole());

        verify(userRepository, times(1)).save(any(User.class));
    }

/*    @Test
    void testUpdateUser() {
        Long userId = testUser.getId();
        User.Role newRole = User.Role.MANAGER;

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User updatedUser = userService.updateUserRole(userId, newRole);

        assertNotNull(updatedUser);
        assertEquals(newRole, updatedUser.getRole());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(testUser);
    }*/
}
