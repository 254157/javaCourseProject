package pl.umk.course.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.umk.course.entities.UserEntity;


public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    @Query(value = "select e from UserEntity e where e.email = :email")
    UserEntity findUserByEmail(@Param("email") String email);
}
