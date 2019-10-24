package com.base.apiserver.api;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.apiserver.model.Member;
import com.base.apiserver.service.MemberService;


@Slf4j
@RestController
@RequestMapping(value="/api/v1/member")
public class MemberController {
	
	@Autowired private MemberService service;
	@Autowired @Lazy private AuthenticationManager authenticationManager;
	
	//@AuthenticationPrincipal
	@PostMapping("/signup")
	public boolean signup(@RequestBody Member member) 
	{
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		member.setId(UUID.randomUUID().toString());
		member.setPassword(enc.encode(member.getPassword()));
		member.grantAuthority("ROLE_USER");
		
		return service.save(member);
	}
	
	@PostMapping(value="/signin")
    public boolean signin(@RequestParam("name") String name, @RequestParam("pw") String pw) 
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, pw);
		
		log.info("token:", token);
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		return true;
    }
}