package pl.umk.course.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.umk.course.entities.EncodingEntity;
import pl.umk.course.exceptions.EncodingException;
import pl.umk.course.repositories.EncodingEntityRepository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class Rot13EncoderTest {

    @Test
    public void should_encrypt_text() throws Throwable {
        String encryptedText = encoder.encrypt("abc");

        assertThat(encryptedText).isEqualTo("nop");
    }

    @Test
    public void should_encrypt_other_text() throws Throwable {
        String encryptedText = encoder.encrypt("bcd");

        assertThat(encryptedText).isEqualTo("opq");
    }

    @Test
    public void should_encryt_text_with_alphabet_ending_letters() throws Throwable {
        String encryptedText = encoder.encrypt("xyz");

        assertThat(encryptedText).isEqualTo("klm");
    }

    @Test
    public void should_encryt_text_with_space() throws Throwable {
        String encryptedText = encoder.encrypt("abc xyz");

        assertThat(encryptedText).isEqualTo("nop klm");
    }


    @Test
    public void should_decrypt_text() throws Throwable {
        String decryptedText = encoder.decrypt("nop");

        assertThat(decryptedText).isEqualTo("abc");
    }

    @Test
    public void should_decrypt_other_text() throws Throwable {
        String decryptedText = encoder.decrypt("opq");

        assertThat(decryptedText).isEqualTo("bcd");
    }

    @Test
    public void should_decryt_text_with_alphabet_ending_letters() throws Throwable {
        String decryptedText = encoder.decrypt("klm");

        assertThat(decryptedText).isEqualTo("xyz");
    }

    @Test
    public void should_decryt_text_with_space() throws Throwable {
        String decryptedText = encoder.decrypt("nop klm");

        assertThat(decryptedText).isEqualTo("abc xyz");
    }

    @Test(expected = EncodingException.class)
    public void should_throw_error_when_unexpected_char() throws Throwable {
        when(validator.isValid(anyList(), eq("a$"))).thenReturn(false);

        encoder.decrypt("a$");
    }

    @Test
    public void should_encode_text_when_not_in_database() throws Throwable {
        when(repository.findOneBySourceText("abc")).thenReturn(null);
        encoder.encrypt("abc");

        verify(repository, times(1)).findOneBySourceText("abc");
        verify(repository, times(1)).save(any(EncodingEntity.class));
    }

    @Test
    public void should_return_from_database_if_saved() throws Throwable {
        when(repository.findOneBySourceText("abc")).thenReturn(mock(EncodingEntity.class));
        encoder.encrypt("abc");

        verify(repository, times(1)).findOneBySourceText("abc");
        verify(repository, never()).save(any(EncodingEntity.class));
    }

    @Mock
    AlphabetValidator validator;

    @Mock
    EncodingEntityRepository repository;

    Encoder encoder;

    @Before
    public void setUp() {
        initMocks(this);
        encoder = new Rot13Encoder(validator, repository);
        when(validator.isValid(anyList(), anyString())).thenReturn(true);
    }

}