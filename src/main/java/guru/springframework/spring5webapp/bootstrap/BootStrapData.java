package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var publisher = new Publisher();
    publisher.setName("SFG Publishing");
    publisher.setCity("Oulu");
    publisher.setState("Finland");
    publisher.setAddressLine1("Sunset Avenue 123");
    publisher.setZip("90100");
    publisherRepository.save(publisher);

    var author = new Author("Big", "Lebowski");
    var bookMonty = new Book("Monty Python", "134567");

    bookMonty.setPublisher(publisher);
    publisher.getBooks().add(bookMonty);

    author.getBooks().add(bookMonty);
    bookMonty.getAuthors().add(author);

    authorRepository.save(author);
    bookRepository.save(bookMonty);
    publisherRepository.save(publisher);

    var rod = new Author("Rod", "Johnson");
    var noEJB = new Book("J2EE", "1345672");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    noEJB.setPublisher(publisher);
    publisher.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(publisher);


    System.out.println("Here we go");
    System.out.println("Book count:" + bookRepository.count());
    System.out.println("publisher number of books:" + (long) publisher.getBooks().size());
  }
}
