package reading;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ami on 2019/2/14.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
