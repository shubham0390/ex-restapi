package com.mmt.shubh.service.member;

import com.mmt.shubh.database.entity.MemberEntity;
import com.mmt.shubh.database.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.Account;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.account.IAccountService;
import com.mmt.shubh.service.converter.IEntityModelConverter;
import com.mmt.shubh.service.expensebook.IExpenseBookService;
import com.mmt.shubh.utility.AccountType;
import com.mmt.shubh.utility.Constants;
import com.mmt.shubh.utility.ExpenseBookType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "memberServiceImpl")
@Slf4j
@Transactional
public class MemberServiceImpl implements IMemberService {

    @Qualifier(value = "memberSQLRepository")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier(value = "expenseBookServiceImpl")
    @Autowired
    IExpenseBookService mIExpenseBookService;

    @Qualifier(value = "accountServiceImpl")
    @Autowired
    IAccountService mAccountService;

    @Qualifier(value = "memberEntityModelConverter")
    @Autowired
    IEntityModelConverter<MemberEntity, Member> mIEntityModelConverter;

    public Member updateMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.updateMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }

    public long deleteMember(long id) {
        mMemberRepository.deleteMember(id);
        return 0;
    }

    public Member getMember(String emailId) {
        return mIEntityModelConverter.toModel(mMemberRepository.getMemberByEmail(emailId));
    }

    public Member registerMember(Member member) {
        log.debug("REGISTER MEMBER STARTS");
        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberByEmail(member.getMemberEmail());
        } catch (NoResultException e) {
            log.info("Member is not present");
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
        }

        if (memberEntity == null) {
            MemberEntity entity = mIEntityModelConverter.toEntity(member);
            entity.setRegistered(true);
            try {
                memberEntity = mMemberRepository.createMember(entity);
            } catch (DataIntegrityViolationException e) {
                Response.ResponseBuilder builder = Response.status(Response.Status.CONFLICT)
                        .entity("Member already present with following email Address");
                throw new WebApplicationException(builder.build());
            }
        } else {
            if (memberEntity.isRegistered()) {
                log.debug("Member is already registered.");
                Response.ResponseBuilder builder = Response.status(Response.Status.CONFLICT);
                builder.entity("Member already registered. Try to login again");
                throw new WebApplicationException(builder.build());
            } else {
                log.debug("Member is already present but not registered. Marking as registered");
                memberEntity.setRegistered(true);
                mMemberRepository.updateMember(memberEntity);
            }
        }

        Account account = createCashAccount(member);
        ExpenseBook expenseBook = createPersonalExpenseBook(member);

        Member member1 = mIEntityModelConverter.toModel(memberEntity);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        member1.setAccounts(accounts);
        List<ExpenseBook> expenseBooks = new ArrayList<>();
        expenseBooks.add(expenseBook);
        member1.setExpenseBooks(expenseBooks);

        log.debug("REGISTER MEMBER END");
        return member1;
    }

    private ExpenseBook createPersonalExpenseBook(Member member) {
        ExpenseBook expenseBook = new ExpenseBook();
        expenseBook.setOwnerEmailId(member.getMemberEmail());
        expenseBook.setName("Personal");
        expenseBook.setDescription("This is personal Expense book for " + member.getMemberName());
        expenseBook.setType(ExpenseBookType.PERSONAL.name());
        expenseBook.setCreationDate(new Date());
        expenseBook.setClientId(member.getMemberName() + 1);
        List<Member> members = new ArrayList<>();
        members.add(member);
        expenseBook.setMemberList(members);
        mIExpenseBookService.createExpenseBook(expenseBook);
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

    public String deleteMember(String emailId) {
        return null;
    }

    @Override
    public List<Member> getExpenseBookMember(long expenseBookId) {
        return mIEntityModelConverter.toModel(mMemberRepository.getMembersByExpenseBook(expenseBookId));
    }

    @Override
    public List<Member> createMembers(List<Member> members) {
        members.forEach(this::createMember);
        return null;
    }

    @Override
    public Member getMemberByEmail(String memberEmail) {
        MemberEntity memberByEmail = null;
        try {
            memberByEmail = mMemberRepository.getMemberByEmail(memberEmail);
        } catch (NoResultException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following email Id" + memberEmail).build());
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following email Id" + memberEmail).build());
        }
        return mIEntityModelConverter.toModel(memberByEmail);
    }

    @Override
    public Member createMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.createMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }

    @Override
    public Member getMemberById(long memberServerId) {
        MemberEntity memberByEmail = null;
        try {
            memberByEmail = mMemberRepository.getMemberById(memberServerId);
        } catch (NoResultException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following  Id" + memberServerId).build());
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following  Id" + memberServerId).build());
        }
        return mIEntityModelConverter.toModel(memberByEmail);
    }

    @Override
    public String generateAccessToken(String memberEmail) {
        return null;
    }

    @Override
    public String login(Member member) {
        String memberEmail = member.getMemberEmail();
        boolean isRegistered = false;

        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberByEmail(memberEmail);
        } catch (NoResultException e) {
            log.info("Member is not present");

        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
        }

        if (memberEntity != null) {
            isRegistered = memberEntity.isRegistered();
        }

        if (!isRegistered) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member Registered  with following id. " +
                    " following email Id" + memberEmail).build());
        }
        return generateAccessToken(memberEmail);
    }

    @Override
    public void logout(String emailId) {

    }
}
