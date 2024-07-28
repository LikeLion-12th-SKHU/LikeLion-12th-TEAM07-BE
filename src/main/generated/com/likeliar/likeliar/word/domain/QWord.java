package com.likeliar.likeliar.word.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWord is a Querydsl query type for Word
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWord extends EntityPathBase<Word> {

    private static final long serialVersionUID = 2116925149L;

    public static final QWord word = new QWord("word");

    public final StringPath explanation = createString("explanation");

    public final StringPath subject = createString("subject");

    public final StringPath vocabulary = createString("vocabulary");

    public final NumberPath<Long> wordId = createNumber("wordId", Long.class);

    public QWord(String variable) {
        super(Word.class, forVariable(variable));
    }

    public QWord(Path<? extends Word> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWord(PathMetadata metadata) {
        super(Word.class, metadata);
    }

}

