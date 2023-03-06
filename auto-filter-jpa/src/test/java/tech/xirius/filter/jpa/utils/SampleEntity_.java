package tech.xirius.filter.jpa.utils;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SampleEntity.class)
public abstract class SampleEntity_ {

	public static volatile SingularAttribute<SampleEntity, String> name;
	public static volatile SingularAttribute<SampleEntity, Integer> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}

