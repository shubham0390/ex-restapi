package com.mmt.shubh.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MemberEntity.class)
public abstract class MemberEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SingularAttribute<MemberEntity, String> userPassword;
	public static volatile SingularAttribute<MemberEntity, String> coverPhotoUrl;
	public static volatile SingularAttribute<MemberEntity, String> displayName;
	public static volatile SingularAttribute<MemberEntity, String> memberName;
	public static volatile SetAttribute<MemberEntity, ExpenseBookEntity> expenseBook;
	public static volatile SetAttribute<MemberEntity, ExpenseEntity> expense;
	public static volatile ListAttribute<MemberEntity, DeviceEntity> deviceEntities;
	public static volatile SingularAttribute<MemberEntity, String> memberEmail;
	public static volatile SingularAttribute<MemberEntity, String> profilePhotoUrl;

}

