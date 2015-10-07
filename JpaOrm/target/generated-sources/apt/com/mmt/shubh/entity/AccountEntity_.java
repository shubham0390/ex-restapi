package com.mmt.shubh.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountEntity.class)
public abstract class AccountEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SingularAttribute<AccountEntity, String> accountName;
	public static volatile SingularAttribute<AccountEntity, Integer> accountBalance;
	public static volatile SetAttribute<AccountEntity, AccountTransactionEntity> expense;

}

