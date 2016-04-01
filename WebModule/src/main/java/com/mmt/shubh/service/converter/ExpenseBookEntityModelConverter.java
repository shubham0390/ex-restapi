package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.ExpenseBookEntity;
import com.mmt.shubh.rest.model.ExpenseBook;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 8/12/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExpenseBookEntityModelConverter implements IEntityModelConverter<ExpenseBookEntity, ExpenseBook> {

    @Inject
    private MemberEntityModelConverter memberEntityModelConverter;

    public ExpenseBookEntity toEntity(ExpenseBook expenseBook) {
        ExpenseBookEntity expenseBookEntity = new ExpenseBookEntity();
        expenseBookEntity.setClientId(expenseBook.getClientId());
        expenseBookEntity.setDateTime(expenseBook.getCreationDate());
        expenseBookEntity.setDescription(expenseBook.getDescription());
        expenseBookEntity.setName(expenseBook.getName());
        expenseBookEntity.setType(expenseBook.getType());
        expenseBookEntity.setOwnerEmailId(expenseBook.getOwnerEmailId());
        expenseBookEntity.setProfileImagePath(expenseBook.getProfileImagePath());
        return expenseBookEntity;
    }

    public ExpenseBook toModel(ExpenseBookEntity expenseBookEntity) {
        return null;
    }

    @Override
    public List<ExpenseBookEntity> toEntity(List<ExpenseBook> m) {
        return null;
    }

    @Override
    public List<ExpenseBook> toModel(List<ExpenseBookEntity> e) {
        return null;
    }
}
