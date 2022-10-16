package am.itspace.bookstore.service;

import am.itspace.bookstore.entity.Book;
import am.itspace.bookstore.entity.User;
import am.itspace.bookstore.exception.DuplicateResourceException;
import am.itspace.bookstore.repository.BookRepository;
import am.itspace.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findLast20Books() {
        return bookRepository.findTop20ByOrderByIdDesc();
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public void removeById(int id) {
        bookRepository.deleteById(id);
    }
}
