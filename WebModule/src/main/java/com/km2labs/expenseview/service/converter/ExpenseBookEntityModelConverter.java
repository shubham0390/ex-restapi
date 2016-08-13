package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.ExpenseBookEntity;
import com.km2labs.expenseview.rest.model.ExpenseBook;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;

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
        // expenseBookEntity.setClientId(expenseBook.getClientId());
        expenseBookEntity.setDateTime(expenseBook.getCreationDate());
        expenseBookEntity.setDescription(expenseBook.getDescription());
        expenseBookEntity.setName(expenseBook.getName());
        expenseBookEntity.setType(expenseBook.getType());
        //expenseBookEntity.setOwnerEmailId(expenseBook.getOwnerEmailId());
        expenseBookEntity.setProfileImagePath(expenseBook.getProfileImagePath());
        return expenseBookEntity;
    }

    public ExpenseBook toModel(ExpenseBookEntity expenseBook) {
        ExpenseBook expenseBookEntity = new ExpenseBook();
        //expenseBookEntity.setClientId(expenseBook.getClientId());
        expenseBookEntity.setCreationDate(expenseBook.getDateTime());
        expenseBookEntity.setDescription(expenseBook.getDescription());
        expenseBookEntity.setName(expenseBook.getName());
        expenseBookEntity.setType(expenseBook.getType());
        //expenseBookEntity.setOwnerEmailId(expenseBook.getOwnerEmailId());
        expenseBookEntity.setProfileImagePath(expenseBook.getProfileImagePath());
        return expenseBookEntity;
    }

    @Override
    public Collection<ExpenseBookEntity> toEntity(Collection<ExpenseBook> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Collection<ExpenseBook> toModel(Collection<ExpenseBookEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}
