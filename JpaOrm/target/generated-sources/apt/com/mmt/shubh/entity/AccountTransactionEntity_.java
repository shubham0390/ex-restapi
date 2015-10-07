package com.mmt.shubh.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountTransactionEntity.class)
public abstract class AccountTransactionEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SingularAttribute<AccountTransactionEntity, Integer> debitAmount;
	public static volatile SingularAttribute<AccountTransactionEntity, String> transactionName;
	public static volatile SingularAttribute<AccountTransactionEntity, Integer> creditAmount;
	public static volatile SingularAttribute<AccountTransactionEntity, Date> transactionDate;
	public static volatile SingularAttribute<AccountTransactionEntity, Integer> balanceAmount;
	public static volatile SingularAttribute<AccountTransactionEntity, AccountEntity> account;

}

