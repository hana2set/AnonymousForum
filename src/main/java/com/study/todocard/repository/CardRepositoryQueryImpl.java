package com.study.todocard.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import static com.study.todocard.entity.QCard.card;

@RequiredArgsConstructor
public class CardRepositoryQueryImpl implements CardRepositoryQuery {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Card> findAllRelationCardByUser(Pageable pageable, User user) {
        JPAQuery<Card> query = jpaQueryFactory.select(card)
                .from(card)
                .where(card.commentList.any().user.eq(user));

        query.orderBy(card.commentList.any().modifiedAt.desc());

        JPAQuery<Long> countQuery  = jpaQueryFactory.select(Wildcard.count)
                .from(card)
                .where(card.commentList.any().user.eq(user));


        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchOne);
    }
}
