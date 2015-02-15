package pl.umk.course.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.umk.course.Application;
import pl.umk.course.entities.EncodingEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class EncodingEntityRepositoryIntegrationTest {
    @Autowired
    EncodingEntityRepository repository;

    @Before
    public void setUp() throws Exception {
        List<EncodingEntity> entities = Arrays.asList(
            new EncodingEntity("abc", "nop", new Date(100)),
            new EncodingEntity("bcd", "opq", new Date(100)),
            new EncodingEntity("cde", "pqr", new Date(100))
        );
        for (EncodingEntity entity : entities) {
            repository.save(entity);
        }
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void should_return_proper_object() throws Exception {
        EncodingEntity expectedEntity = new EncodingEntity("bcd", "opq", new Date(100));

        assertThat(repository.findOneBySourceText("bcd")).isEqualTo(expectedEntity);
    }

    @Test
    public void should_return_null_when_not_found() throws Exception {
        assertThat(repository.findOneBySourceText("notfound")).isNull();
    }
}
