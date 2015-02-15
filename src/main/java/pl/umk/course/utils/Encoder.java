package pl.umk.course.utils;

import pl.umk.course.exceptions.EncodingException;

public interface Encoder {

    String encrypt(String input) throws EncodingException;
    String decrypt(String input) throws EncodingException;

}
