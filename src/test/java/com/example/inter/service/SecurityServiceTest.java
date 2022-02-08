package com.example.inter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    @InjectMocks
    SecurityService service;

    String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAltmTQ6CG+N/WKWLeH+ytrMUSTtom5ZvTRKq/Z3bUevH4kc8I40R45l3gE5TibKfceIuRr0XxZHYq42FzAC2I2w1FHoveHPmaVVtxThAyAqDUrLYarwk+ouAv+hcBKs9nhl25hvQw2Ym4PJDl7YYlbhBUicsPLjR+RhwHvDlZ1MsBLOQH7ULq31Q/YR1e4uqBH7/Tf47SM0v8P7z/jIHgPdqweiokWrlie9a+0icoEBKRI4h5KzgiJTQpNxoqCKXbSo/IBm23rToi4aaWq1yq5EOIEyk8ZHc4h0U57YJN6z2eHNXPSKCJYmWDL1TZvoUycZXyLoomdFlw5nJUCM2njQIDAQAB";

    String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCW2ZNDoIb439YpYt4f7K2sxRJO2iblm9NEqr9ndtR68fiRzwjjRHjmXeATlOJsp9x4i5GvRfFkdirjYXMALYjbDUUei94c+ZpVW3FOEDICoNSsthqvCT6i4C/6FwEqz2eGXbmG9DDZibg8kOXthiVuEFSJyw8uNH5GHAe8OVnUywEs5AftQurfVD9hHV7i6oEfv9N/jtIzS/w/vP+MgeA92rB6KiRauWJ71r7SJygQEpEjiHkrOCIlNCk3GioIpdtKj8gGbbetOiLhpparXKrkQ4gTKTxkdziHRTntgk3rPZ4c1c9IoIliZYMvVNm+hTJxlfIuiiZ0WXDmclQIzaeNAgMBAAECggEAObf+dP3TJx7eXu+JdkWlHlo5axplj61T5Q5ANmJtYq17Iq9JA0yLxfldZ6CsEk8YOLeOgrYMaxgbyOzch9DkcIxfShTSY9vNhaN7cxcXS5ImTf9PrnGnGcMWGGb891hskCkEd/KvTk03xVTb5UpSwA5XinG72T4UotbOzuJS0OxT3gl/w4Yq1Ebb8bI8IwlcwV+Yz5PcMze5OTzjTFKRp3snEHK95MEMKGgXYy3Uvq1MkutkehSDG6Eus60Ec+L3Qhr1nKEyieJidg5vDXK21SlDC+DmPEAnqhXL5atD+SFR8JIN9qDz+h7zYV09sslcMJwHc2oizVATVVja2OMvgQKBgQDk9yC7Z2bIR6zFCBHfTG5aNr2y9bqiveu6O9CxgSY+7t0PGzw4+i35Xfb3gpSUK9hy0C007CWppVjBt+t41oe/M2AyB7hpzenE7RsJgPnp30jwOF/WrmeaXFTjbtp0WZNe5KgwF/n2eNAtA0ErjBuHrH9kHC95tWmcPVBH8WmrOQKBgQCoqUYJdcA4X7Ec6OMsuAlnxTuFICOIH2BgOVsL1ksH05OYL9PCtbK56oAFRCq7np8x4j3UgSJs5o5npsFVGgfH/6RMNXC/0FFX8qhUUsEfbuf4TBtVUwJ4NVZI5uCYTyvJrK7Xaf7LJyJJYyh5uPn9z1XufaeWQDwQjWdNiJEa9QKBgCEsKMLeBUcpEz/snmJCuY46dhtyfJrNd1ShC+hbtMXTZ5WjtYLvxC9nzLspYQtimtZvkM0lFYPRn91ZHzV/p2UtNVeqRK6XlbHhRFSJKiwXHIMpN8FrvTjg/a+BxQasX6yU5kUphB6QfGC5Iv0DLI3okQBZEQvKG8X2ICZ8NHoJAoGAEyRTDJPOoxdJNKzBy7t/ZcWewbcqETLubz1TNf62OUcsAyO6dujERhF1QiPAbdbM3o580fdt/soT83ObkZsIswwyi2utRjfQY5jovdk+jeC6dxW8LGzlZNs41cFOUDr8D0GH/m7LOidQ3t9gIqB5zvKPICgmswjLSF2kEcqkHVkCgYAM7PtgWm8+alz5LkjF2laPislSebd2EsyiV6W+kMpzkDxPZN26klde2qzL2ZYJDWOgfKslHpQGZoSCa1HMk/YgBkjGtL2FTjc+IPwpP+oH8wc9qMdf3LtzlEv4eNSR9sYX2IDQmU0YtcWqQkED6yI85KQNRiDqahP7z+YpMEWh6w==";

    @Test
    public void deveriaEncriptarCorretamente() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, InvalidKeySpecException {
        String valorTeste = "teste";
        String encriptado = service.encrypt(valorTeste, publicKey);
        String descriptado = decrypt(encriptado, privateKey);

        assertThat(descriptado, equalTo(valorTeste));
    }


    public PrivateKey decodePrivateKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        return key;
    }

    public String decrypt(String cipherContent, String privKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey(privKey));
        byte[] cipherContentBytes = Base64.getDecoder().decode(cipherContent.getBytes());
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
        String decoded = new String(decryptedContent);
        return decoded;
    }

}