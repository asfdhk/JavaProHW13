package academy.prog.chatserversprint.repo;

import academy.prog.chatserversprint.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.id > :id")
    List<Message> findNew(long id);

    @Query("SELECT c FROM Message c WHERE c.to =:to")
    List<Message> findPrivate(String to);

}
