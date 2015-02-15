package pl.umk.course.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.umk.course.entities.EncodingEntity;

public interface EncodingEntityRepository extends CrudRepository<EncodingEntity, Long> {
    EncodingEntity findOneBySourceText(String sourceText);

    @Query("select e from EncodingEntity e where e.sourceText = :text")
    EncodingEntity anotherFindOneBySourceText(@Param("text") String sourceText);

}