package library_managment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library_managment.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
