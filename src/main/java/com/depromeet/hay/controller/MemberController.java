package com.depromeet.hay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
    
    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> signUp(@RequestBody Member member) {
    	try {
		memberService.signUp(member);
		return new ResponseEntity<>(HttpStatus.CREATED);
    	} catch (DuplicateKeyException e) {
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	}
    }
    
//    @ExceptionHandler(value = DuplicateKeyException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public void duplicateEmailHandler() {
//    	
//    }
}
