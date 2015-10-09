package com.mmt.shubh.service.member;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.converter.EntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "memberServiceImpl")
@Slf4j
@Transactional
public class MemberServiceImpl implements IMemberService {

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("memberEntityModelConverter")
    @Autowired
    EntityModelConverter<MemberEntity, Member> mEntityModelConverter;

    public Member updateMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.updateMember(mEntityModelConverter.toEntity(member));
        return mEntityModelConverter.toModel(memberEntity);
    }

    public long deleteMember(long id) {
        return 0;
    }

    public Member getMember(String emailId) {
        return null;
    }

    public Member registerMember(Member member) {
        log.debug("REGISTER MEMBER STARTS");
        MemberEntity memberEntity = mMemberRepository.createMember(mEntityModelConverter.toEntity(member));
        log.debug("REGISTER MEMBER END");
        return mEntityModelConverter.toModel(memberEntity);
    }

    public String deleteMember(String emailId) {
        return null;
    }

    @Override
    public List<Member> getExpenseBookMember(long expenseBookId) {
        return null;
    }
}
