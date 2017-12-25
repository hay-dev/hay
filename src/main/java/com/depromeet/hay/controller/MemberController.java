package com.depromeet.hay.controller;

import java.util.List;

import com.depromeet.hay.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody Member member) {
		memberService.signUp(member);
    }

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Member> search(@RequestParam("search") String text) {
		return memberService.search(text);
	}

	@PostMapping("{id}/follows/{targetId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void follow(@PathVariable int id, @PathVariable int targetId) {
    	memberService.follow(id, targetId);
	}

    @GetMapping(value = "/{id}/followers")
	@ResponseStatus(HttpStatus.OK)
	public List<Member> followers(@PathVariable int id) {
    	return memberService.getFollowers(id);
	}

    @GetMapping(value = "/{id}/followings")
	@ResponseStatus(HttpStatus.OK)
	public List<Member> followings(@PathVariable int id) {
    	return memberService.getFollowings(id);
	}

    @PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
    public void modifyMember(@PathVariable int id, @RequestBody Member member) {
    	member.setId(id);
    	memberService.modifyMember(member);
    }

    @PostMapping("/{id}/likes/{articleId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void likeArticle(@PathVariable int id, @PathVariable int articleId) {
    	memberService.like(id, articleId);
	}

	@GetMapping("/{id}/likings")
	@ResponseStatus(HttpStatus.OK)
	public List<Article> getLikings(@PathVariable int id) {
    	return memberService.getLikings(id);
	}
    
    /* exception handlers */

    // 이미 해당 이메일로 가입한 계정이 있을 때
	// 이미 팔로우한 회원일 때
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void duplicateKeyExceptionHandler() {}

    // 존재하지 않는 회원을 팔로우했을 때
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void dataIntegrityViolationExceptionHandler() {}
}
