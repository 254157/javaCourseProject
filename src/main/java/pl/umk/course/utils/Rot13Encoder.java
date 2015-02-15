package pl.umk.course.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.umk.course.entities.EncodingEntity;
import pl.umk.course.exceptions.EncodingException;
import pl.umk.course.repositories.EncodingEntityRepository;

import java.util.Date;
import java.util.List;

@Component
public class Rot13Encoder implements Encoder {

    public static final List<Character> ALPHABET = Lists.charactersOf("abcdefghijklmnopqrstuvwxyz");
    public String encrypt(String input) throws EncodingException {
        EncodingEntity entity = repository.findOneBySourceText(input);

        if (entity != null) {
            System.out.println(entity);
            System.out.println("Receiving data from database for input: " + input);
            return entity.getEncodedText();
        }

        String result = "";
        if (!alphabetValidator.isValid(ALPHABET, input)) {
            throw new EncodingException("Not valid alphabet");
        }

        for (Character c : input.toCharArray()) {
            result += encodeCharacter(c);
        }

        System.out.println("Saving encoded data to database for input: " + input);
        repository.save(new EncodingEntity(input, result, new Date()));

        return result;
    }

    public String decrypt(String input) throws EncodingException {
        return encrypt(input);
    }

    private Character encodeCharacter(Character c) {
        if (c == ' ') {
            return ' ';
        }
        Integer charPosition = ALPHABET.indexOf(c);
        Integer encryptedCharPosition = (charPosition + 13) % 26;
        Character encryptedChar = ALPHABET.get(encryptedCharPosition);
        return encryptedChar;
    }

    private final AlphabetValidator alphabetValidator;
    private final EncodingEntityRepository repository;

    @Autowired
    public Rot13Encoder(AlphabetValidator alphabetValidator, EncodingEntityRepository repository) {
        this.alphabetValidator = alphabetValidator;
        this.repository = repository;
    }
}
