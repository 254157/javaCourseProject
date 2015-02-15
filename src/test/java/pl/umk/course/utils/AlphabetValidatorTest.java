package pl.umk.course.utils;

import com.google.common.collect.Lists;
import org.junit.Test;
import pl.umk.course.utils.AlphabetValidator;

import static org.fest.assertions.Assertions.assertThat;

public class AlphabetValidatorTest {

    @Test
    public void shoule_check_valid_text() {
        AlphabetValidator validator = new AlphabetValidator();

        Boolean actual = validator.isValid(Lists.charactersOf("abc"), "abc cba");

        assertThat(actual).isTrue();
    }

    @Test
    public void shoule_check_other_valid_text() {
        AlphabetValidator validator = new AlphabetValidator();

        Boolean actual = validator.isValid(Lists.charactersOf("abcdef"), "abc");

        assertThat(actual).isTrue();
    }

    @Test
    public void shoule_check_invalid_text() {
        AlphabetValidator validator = new AlphabetValidator();

        Boolean actual = validator.isValid(Lists.charactersOf("abc"), "a$cb");

        assertThat(actual).isFalse();
    }

}