package library_managment.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library_managment.Exception.BookNotFoundException;
import library_managment.Exception.BookUnavailableException;
import library_managment.Exception.LoanNotFoundException;
import library_managment.Exception.MemberNotFoundException;
import library_managment.Model.Book;
import library_managment.Model.Loan;
import library_managment.Model.LoanStatus;
import library_managment.Repository.BookRepository;
import library_managment.Repository.LoanRepository;
import library_managment.Repository.MemberRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Loan issueBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));

        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("No copies available for book id: " + bookId);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBookId(bookId);
        loan.setMemberId(memberId);
        loan.setIssueDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setStatus(LoanStatus.ISSUED);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + loanId));

        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + loan.getBookId()));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        return loanRepository.save(loan);
    }
}