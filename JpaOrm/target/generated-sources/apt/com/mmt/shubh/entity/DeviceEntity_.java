package com.mmt.shubh.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeviceEntity.class)
public abstract class DeviceEntity_ extends com.mmt.shubh.entity.AbstractEntity_ {

	public static volatile SingularAttribute<DeviceEntity, String> registrationToken;
	public static volatile SingularAttribute<DeviceEntity, MemberEntity> member;
	public static volatile SingularAttribute<DeviceEntity, Long> id;
	public static volatile SingularAttribute<DeviceEntity, String> deviceUUID;

}

