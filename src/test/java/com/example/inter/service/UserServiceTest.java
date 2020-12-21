package com.example.inter.service;

import com.example.inter.controller.DTO.UserDTO;
import com.example.inter.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.inter.repository.UserRepository;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Mock
    SecurityService securityService;

    @Test
    public void deveriaInserirUsuarioOk() throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);
        String pubKey = "1234";

        service.add(userDTO, pubKey);

        verify(repository, times(1)).insert(any(User.class));
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
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);
        String id = "1234";
        String publicKey = "chave";

        when(repository.findById(id)).thenReturn(Optional.of(applicationUser));
        when(repository.save(applicationUser)).thenReturn(applicationUser);

        assertThat(service.update(id, userDTO, publicKey), equalTo(true));
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(applicationUser);
    }

    @Test
    public void deveriaAtualizarUsuarioFalha() {
        UserDTO userDTO = mock(UserDTO.class);
        User applicationUser = mock(User.class);
        String id = "1234";
        String publicKey = "chave";

        assertThat(service.update(id, userDTO, publicKey), equalTo(false));
        verify(repository, times(0)).save(applicationUser);
    }
}