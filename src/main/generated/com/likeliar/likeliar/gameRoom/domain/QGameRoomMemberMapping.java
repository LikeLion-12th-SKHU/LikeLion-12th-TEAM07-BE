package com.likeliar.likeliar.gameRoom.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameRoomMemberMapping is a Querydsl query type for GameRoomMemberMapping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameRoomMemberMapping extends EntityPathBase<GameRoomMemberMapping> {

    private static final long serialVersionUID = 462504689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameRoomMemberMapping gameRoomMemberMapping = new QGameRoomMemberMapping("gameRoomMemberMapping");

    public final QGameRoom gameRoom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.likeliar.likeliar.member.domain.QMember member;

    public QGameRoomMemberMapping(String variable) {
        this(GameRoomMemberMapping.class, forVariable(variable), INITS);
    }

    public QGameRoomMemberMapping(Path<? extends GameRoomMemberMapping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameRoomMemberMapping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameRoomMemberMapping(PathMetadata metadata, PathInits inits) {
        this(GameRoomMemberMapping.class, metadata, inits);
    }

    public QGameRoomMemberMapping(Class<? extends GameRoomMemberMapping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameRoom = inits.isInitialized("gameRoom") ? new QGameRoom(forProperty("gameRoom"), inits.get("gameRoom")) : null;
        this.member = inits.isInitialized("member") ? new com.likeliar.likeliar.member.domain.QMember(forProperty("member")) : null;
    }

}

