package com.example.inter.service;

import com.example.inter.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.inter.repository.UserRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
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

        when(repository.existsById(id)).thenReturn(true);

        assertThat(service.delete(id), equalTo(true));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deveriaDeletarUsuarioFalha(){
        User user = mock(User.class);
        String id = "1234";

        when(repository.existsById(id)).thenReturn(false);


        assertThat(service.delete(id), equalTo(false));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).deleteById(id);
    }

    @Test
    public void deveriaAtualizarUsuarioOk(){
        User user = mock(User.class);
        String id = "1234";

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(user)).thenReturn(user);

        assertThat(service.update(id, user), equalTo(true));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void deveriaAtualizarUsuarioFalha(){
        User user = mock(User.class);
        String id = "1234";

        assertThat(service.update(id, user), equalTo(false));
        verify(repository, times(0)).save(user);
    }
}