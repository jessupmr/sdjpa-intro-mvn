package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest  // this just loads enough context to test your data layer
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"}) // this configures our DataInitializer to run
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    /**
     * Be default, spring will run each test in a transaction and then roll it back, which is the desired behavior 99%
     * of the time. For this example, in order to get testJpaTestSliceTransaction to pass we had to force the test
     * order and then tell the first test to actually commit. These features are available in JUnit 5.
     */

    @Commit
    @Order(1)
    @Test
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "1334235", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSliceTransaction() {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(3);
    }
}
