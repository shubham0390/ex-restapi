package com.km2labs.expenseview.service.member;

import com.km2labs.expenseview.rest.model.Account;
import com.km2labs.expenseview.rest.model.ExpenseBook;
import com.km2labs.expenseview.rest.model.Member;
import com.km2labs.expenseview.service.account.IAccountService;
import com.km2labs.expenseview.service.expensebook.IExpenseBookService;
import com.km2labs.expenseview.utility.AccountType;
import com.km2labs.expenseview.utility.Constants;
import com.km2labs.expenseview.utility.ExpenseBookType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Subham on 18/05/16.
 */
@Component
@Slf4j
public class MemberSetupService implements IMemberSetupService {

    @Autowired
    @Qualifier(value = "expenseBookServiceImpl")
    private IExpenseBookService mExpenseBookService;

    @Autowired
    @Qualifier(value = "accountServiceImpl")
    private IAccountService mAccountService;


    private ExpenseBook createPersonalExpenseBook(Member member) {
        ExpenseBook expenseBook = new ExpenseBook();
        expenseBook.setOwnerKey(12);
        expenseBook.setName("Personal");
        expenseBook.setDescription("This is personal Expense book ");
        expenseBook.setType(ExpenseBookType.PERSONAL.name());
        expenseBook.setCreationDate(new Date());
        List<Member> members = new ArrayList<>();
        members.add(member);
        expenseBook.setMemberList(members);
        mExpenseBookService.createExpenseBook(expenseBook);
        return expenseBook;
    }

    private Account createCashAccount(Member member) {
        Account account = new Account();
        account.setAccountName(Constants.CASH_ACCOUNT_NAME);
        account.setAccountType(AccountType.CASH.name());
        account.setAccountBalance(0);
        account.setMember(member.getMemberEmail());
        mAccountService.addAccount(account);
        return account;
    }

    @Override
    public void setup(Member member) {

    }
}
