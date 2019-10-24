package com.base.apiserver.api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.apiserver.service.MemberService;

@Slf4j
@RestController
@RequestMapping(value="/api/v1/test")
public class TestController {
	
	@Autowired private MemberService service;
	@Autowired @Lazy private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public boolean test() 
	{
		log.info("여기 올 수 있니??");
		return true;
	}
}