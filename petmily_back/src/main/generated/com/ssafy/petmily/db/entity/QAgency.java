package com.ssafy.petmily.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAgency is a Querydsl query type for Agency
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAgency extends EntityPathBase<Agency> {

    private static final long serialVersionUID = -227367251L;

    public static final QAgency agency = new QAgency("agency");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath agencycode = createString("agencycode");

    public final StringPath agencyname = createString("agencyname");

    //inherited
    public final NumberPath<Long> no = _super.no;

    public QAgency(String variable) {
        super(Agency.class, forVariable(variable));
    }

    public QAgency(Path<? extends Agency> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgency(PathMetadata metadata) {
        super(Agency.class, metadata);
    }

}

