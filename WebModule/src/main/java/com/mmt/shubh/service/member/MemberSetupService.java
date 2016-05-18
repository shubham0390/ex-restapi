package com.mmt.shubh.service.member;

import com.mmt.shubh.rest.model.Account;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.account.IAccountService;
import com.mmt.shubh.service.expensebook.IExpenseBookService;
import com.mmt.shubh.utility.AccountType;
import com.mmt.shubh.utility.Constants;
import com.mmt.shubh.utility.ExpenseBookType;
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
