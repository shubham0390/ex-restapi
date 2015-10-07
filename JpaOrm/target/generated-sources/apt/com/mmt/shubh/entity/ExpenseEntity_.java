package com.mmt.shubh.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExpenseEntity.class)
public abstract class ExpenseEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SingularAttribute<ExpenseEntity, Long> date;
	public static volatile SingularAttribute<ExpenseEntity, String> amount;
	public static volatile SingularAttribute<ExpenseEntity, Long> syncKey;
	public static volatile SingularAttribute<ExpenseEntity, AccountTransactionEntity> accountTransaction;
	public static volatile SingularAttribute<ExpenseEntity, String> name;
	public static volatile SingularAttribute<ExpenseEntity, MemberEntity> member;
	public static volatile SingularAttribute<ExpenseEntity, String> description;
	public static volatile SingularAttribute<ExpenseEntity, ExpenseBookEntity> expenseBook;
	public static volatile SingularAttribute<ExpenseEntity, CategoryEntity> category;

}

