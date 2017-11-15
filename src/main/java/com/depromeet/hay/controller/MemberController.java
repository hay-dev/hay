package com.depromeet.hay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody Member member) {
		memberService.signUp(member);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void duplicateEmailHandler() {

    }
}
