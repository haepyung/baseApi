package com.base.apiserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@ToString
@Entity
@Table(name = "Members")
public class Member implements UserDetails {

	@Id
	@Column(length = 50)
	private String id;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;
	
	@CreationTimestamp
	private Date created;

	@UpdateTimestamp
	private Date modified;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private List<MemberRole> roles;
	
	@Transient
	private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

    public Member() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    public void grantAuthority(String authority) {
        if ( roles == null ) roles = new ArrayList<MemberRole>();
        MemberRole r = new MemberRole();
        r.setName(authority);
        roles.add(r);
    }
    
    @Override
    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }
}