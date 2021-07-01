package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var bigL = new Author("Big", "Lebowski");
    var book = new Book("Monty Python", "134567");

    bigL.getBooks().add(book);
    book.getAuthors().add(bigL);

    authorRepository.save(bigL);
    bookRepository.save(book);

    var rod = new Author("Rod", "Johnson");
    var noEJB = new Book("J2EE", "1345672");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    authorRepository.save(rod);
    bookRepository.save(noEJB);

    System.out.println("Here we go");
    System.out.println("Book count:" + bookRepository.count());
  }
}
