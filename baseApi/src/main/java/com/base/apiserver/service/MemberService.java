package com.base.apiserver.service;


import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.apiserver.config.AppConfig;
import com.base.apiserver.model.Member;
import com.base.apiserver.model.MemberRole;
import com.base.apiserver.repo.MemberRepo;

import static com.base.apiserver.model.QMember.member;


@Service()
@Transactional(readOnly=true)
public class MemberService extends QuerydslRepositorySupport implements UserDetailsService
{	
	@Autowired private MemberRepo repo;
	@Autowired private AppConfig config;
	
	public MemberService()
	{
		super(Member.class);
	}
	
	@Modifying
	@Transactional
	public boolean save(Member entity)
	{
		try {
			
			for (MemberRole role : entity.getRoles()) 
				if (StringUtils.isBlank(role.getId()))
					role.setId(entity.getId());
			
			repo.save(entity);
		} 
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public Member findOne(String id)
    {
        return from(member).where(member.id.eq(id)).fetchFirst();
    }
	
	public List<Member> findAll()
	{
		return repo.findAll();
	}
	
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException 
    {
        Optional<Member> account = repo.findByUsername(s);
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Member findAccountByUsername(String username) throws UsernameNotFoundException 
    {
        Optional<Member> account = repo.findByUsername(username);
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }
}
