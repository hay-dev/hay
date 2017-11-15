package com.depromeet.hay.controller;

import com.depromeet.hay.domain.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.MemberService;

import java.util.List;

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

	@RequestMapping(value = "/{id}/follows/{followingId}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void follow(@PathVariable int id, @PathVariable int followingId) {
		memberService.follow(new Follow(id, followingId));
	}

    @RequestMapping(value = "/{id}/followers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Member> followers(@PathVariable int id) {
    	return memberService.getFollowers(id);
	}


    /* exception handlers */

    // 이미 해당 이메일로 가입한 계정이 있을 때
	// 이미 팔로우한 회원일 때
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void duplicateKeyExceptionHandler() {}

    // 존재하지 않는 회원을 팔로우했을 때
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void dataIntegrityViolationExceptionHandler() {}
}
