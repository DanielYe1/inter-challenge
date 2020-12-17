package service;

import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repository.UserRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Test
    public void deveriaInserirUsuarioOk(){
        User user = mock(User.class);
        service.add(user);

        verify(repository, times(1)).insert(user);
    }

    @Test
    public void deveriaDeletarUsuarioOk(){
        User user = mock(User.class);
        String id = "1234";

        when(user.getId()).thenReturn(id);
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).delete(user);


        assertThat(service.delete(id), equalTo(true));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deveriaDeletarUsuarioFalha(){
        User user = mock(User.class);
        String id = "1234";

        when(user.getId()).thenReturn(id);
        when(repository.existsById(id)).thenReturn(false);
        doNothing().when(repository).delete(user);


        assertThat(service.delete(id), equalTo(false));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).deleteById(id);
    }
}