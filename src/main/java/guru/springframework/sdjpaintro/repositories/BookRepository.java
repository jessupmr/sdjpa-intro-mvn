package guru.springframework.sdjpaintro.repositories;

import guru.springframework.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MJ on 06 Jul 23
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
