package com.mmt.shubh.repository.member;

import com.mmt.shubh.BaseRepository;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.exception.DuplicateEntityException;
import com.mmt.shubh.exception.InvalidEntityException;
import com.mmt.shubh.exception.UnrecoverableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository(value = "memberRepositoryImpl")
@Slf4j
public class MemberRepositoryImpl extends BaseRepository<MemberEntity> implements IMemberRepository {


    public MemberEntity updateMember(MemberEntity memberEntity) {
        log.debug("UPDATE MEMBER STARTS");
        try {
            MemberEntity memberEntity1 = update(memberEntity);
            log.debug("UPDATE MEMBER END");
            return memberEntity1;
        } catch (IllegalArgumentException e) {
            log.debug(e.getMessage());
            throw new InvalidEntityException(e.getMessage());
        } catch (TransactionRequiredException e) {
            log.debug(e.getMessage());
            throw new UnrecoverableException(e.getMessage());
        } catch (PersistenceException exception) {
            log.debug(exception.getMessage());
            throw new UnrecoverableException(exception.getMessage());
        }
    }

    public void deleteMember(long id) {
        deleteById(id);
    }

    public void deleteMemberByEmailId(String emailId) {
        // delete(findByColumnNameAndStringValue(Member_.memberEmail, emailId));
    }

    public MemberEntity createMember(MemberEntity member) {
        log.debug("CREATE MEMBER STARTS");
        try {
            create(member);
            log.debug("CREATE MEMBER STARTS");
            return member;
        } catch (EntityExistsException e) {
            log.debug(e.getMessage());
            throw new DuplicateEntityException("Member already exits with following email " + member.getMemberEmail());
        } catch (IllegalArgumentException e) {
            log.debug(e.getMessage());
            throw new InvalidEntityException("Invalid Member data");
        } catch (TransactionRequiredException e) {
            log.debug(e.getMessage());
            throw new UnrecoverableException(e.getMessage());
        } catch (PersistenceException exception) {
            log.debug(exception.getMessage());
            throw new UnrecoverableException(exception.getMessage());
        }

    }

    @Override
    public List<MemberEntity> getMembersByExpenseBook(long expenseBookId) {
        return null;
    }
}
