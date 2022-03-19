package com.epam.librarysalessystem.service;

import com.epam.librarysalessystem.dao.MemberDao;
import com.epam.librarysalessystem.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * MemberService
 *
 * @Since 2022/3/18
 */
@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 查询所有的成员
     *
     * @return member of list
     */
    public List<Member> findAllMemberList() {
        return memberDao.findAll();
    }

    /**
     * 创建或者更新成员信息
     *
     * @return member of list
     * @throws ValidationException 抛出已存在用户异常
     */
    public Member saveMember(Member member) throws ValidationException {
        boolean isExistMember = findByIdentityCardId(member.getIdentityCardId()).isPresent();
        if (isExistMember) {
            throw new ValidationException("The ID card has been used, please check it", member.getIdentityCardId());
        }
        return memberDao.save(member);
    }

    /**
     * 更具cardId查询对象的成员信息
     *
     * @return member of list
     */
    public Optional<Member> findByIdentityCardId(String cardId) {
        Member member = new Member();
        member.setIdentityCardId(cardId);
        Example<Member> example = Example.of(member);
        return memberDao.findOne(example);
    }
}
