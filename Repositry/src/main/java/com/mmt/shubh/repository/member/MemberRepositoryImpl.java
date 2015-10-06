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


    public void updateMember(MemberEntity memberEntity) {
        try {
            MemberEntity memberEntity1 = update(memberEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidEntityException(e.getMessage());
        } catch (TransactionRequiredException e) {
            throw new UnrecoverableException(e.getMessage());
        } catch (PersistenceException exception) {
            throw new UnrecoverableException(exception.getMessage());
        }
    }

    public void deleteMember(long id) {
        deleteById(id);
    }

    public void deleteMemberByEmailId(String emailId) {
       // delete(findByColumnNameAndStringValue(Member_.memberEmail, emailId));
    }

    public void registerMember(MemberEntity member) {
        try {
            create(member);
        } catch (EntityExistsException e) {
            throw new DuplicateEntityException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new InvalidEntityException(e.getMessage());
        } catch (TransactionRequiredException e) {
            throw new UnrecoverableException(e.getMessage());
        } catch (PersistenceException exception) {
            throw new UnrecoverableException(exception.getMessage());
        }

    }

    @Override
    public List<MemberEntity> getMembersByExpenseBook(long expenseBookId) {
        return null;
    }
}
