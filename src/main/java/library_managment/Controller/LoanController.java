package library_managment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library_managment.Model.Loan;
import library_managment.Service.LoanService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/issue")
    public Loan issueBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return loanService.issueBook(bookId, memberId);
    }

    @PostMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }
}
