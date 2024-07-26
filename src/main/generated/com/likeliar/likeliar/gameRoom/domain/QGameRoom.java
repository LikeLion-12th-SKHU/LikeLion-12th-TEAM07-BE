package com.likeliar.likeliar.gameRoom.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameRoom is a Querydsl query type for GameRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameRoom extends EntityPathBase<GameRoom> {

    private static final long serialVersionUID = -493456733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameRoom gameRoom = new QGameRoom("gameRoom");

    public final StringPath content = createString("content");

    public final com.likeliar.likeliar.member.domain.QMember leader;

    public final NumberPath<Long> playerNumber = createNumber("playerNumber", Long.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final StringPath roomName = createString("roomName");

    public final StringPath subject = createString("subject");

    public QGameRoom(String variable) {
        this(GameRoom.class, forVariable(variable), INITS);
    }

    public QGameRoom(Path<? extends GameRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameRoom(PathMetadata metadata, PathInits inits) {
        this(GameRoom.class, metadata, inits);
    }

    public QGameRoom(Class<? extends GameRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.leader = inits.isInitialized("leader") ? new com.likeliar.likeliar.member.domain.QMember(forProperty("leader")) : null;
    }

}

