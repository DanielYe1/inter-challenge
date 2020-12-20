package com.example.inter.service;

import com.example.inter.controller.DTO.UserDTO;
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
    public void deveriaInserirUsuarioOk() {
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);

        when(userDTO.toApplicationUser()).thenReturn(applicationUser);
        service.add(userDTO);

        verify(repository, times(1)).insert(applicationUser);
    }

    @Test
    public void deveriaDeletarUsuarioOk() {
        User user = mock(User.class);
        String id = "1234";

        when(repository.existsById(id)).thenReturn(true);

        assertThat(service.delete(id), equalTo(true));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deveriaDeletarUsuarioFalha() {
        User user = mock(User.class);
        String id = "1234";

        when(repository.existsById(id)).thenReturn(false);


        assertThat(service.delete(id), equalTo(false));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).deleteById(id);
    }

    @Test
    public void deveriaAtualizarUsuarioOk() {
        String id = "1234";
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);

        when(userDTO.toApplicationUser()).thenReturn(applicationUser);

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(applicationUser)).thenReturn(applicationUser);

        assertThat(service.update(id, userDTO), equalTo(true));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(applicationUser);
    }

    @Test
    public void deveriaAtualizarUsuarioFalha() {
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);
        String id = "1234";
        when(userDTO.toApplicationUser()).thenReturn(applicationUser);

        assertThat(service.update(id, userDTO), equalTo(false));
        verify(repository, times(0)).save(applicationUser);
    }
}