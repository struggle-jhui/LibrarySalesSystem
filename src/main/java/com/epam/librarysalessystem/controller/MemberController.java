package com.epam.librarysalessystem.controller;

import com.epam.librarysalessystem.entity.Member;
import com.epam.librarysalessystem.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * MemberController
 *
 * @Since 2022/3/18
 */
@RestController
@Slf4j
@RequestMapping("/library/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping()
    public List<Member> findMemberInfoList() {
        log.info("findMemberInfoList start");
        List<Member> allMembers = memberService.findAllMemberList();
        log.debug("queryMemberInfos end, allMembers is {}", allMembers);
        return allMembers;
    }

    @PostMapping()
    public void createMemberInfo(@Valid @RequestBody Member member) throws ValidationException {
        log.info("createMemberInfo start, input param is {}", member);
        memberService.saveMember(member);
    }
}
