package com.mmt.shubh.service.converter;

import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.rest.model.ExpenseBook;
import org.springframework.stereotype.Component;

/**
 * Created by Subham Tyagi
 * On 8/12/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExpenseBookEntityModelConverter implements EntityModelConverter<ExpenseBookEntity,ExpenseBook> {
    public ExpenseBookEntity toEntity(ExpenseBook expenseBook) {
        return null;
    }

    public ExpenseBook toModel(ExpenseBookEntity expenseBookEntity) {
        return null;
    }
}
