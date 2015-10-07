package com.mmt.shubh.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExpenseBookEntity.class)
public abstract class ExpenseBookEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SetAttribute<ExpenseBookEntity, MemberEntity> memberList;
	public static volatile SingularAttribute<ExpenseBookEntity, String> name;
	public static volatile SingularAttribute<ExpenseBookEntity, String> profileImagePath;
	public static volatile SingularAttribute<ExpenseBookEntity, String> description;
	public static volatile SingularAttribute<ExpenseBookEntity, String> type;
	public static volatile SetAttribute<ExpenseBookEntity, ExpenseEntity> expenses;

}

