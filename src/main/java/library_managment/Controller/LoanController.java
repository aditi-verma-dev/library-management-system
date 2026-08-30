package library_managment.Controller;

import library_managment.Model.Loan;
import library_managment.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
