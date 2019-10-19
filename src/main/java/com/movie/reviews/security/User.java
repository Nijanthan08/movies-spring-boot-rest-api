package com.movie.reviews.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.movie.reviews.domain.Role;

public class User implements UserDetails {

	/**
	 *  serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	public User(Integer id, String firstName, String lastName, String emailId, String admin, String active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.admin = admin;
		this.active = active;
		this.role = "Y".equalsIgnoreCase(admin) ? Role.ADMIN.toString() : Role.USER.toString();
	}

	private Integer id;

	private String firstName;

	private String lastName;

	private String emailId;

	private String admin;

	private String active;

	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role));
		return list;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return "Y".equalsIgnoreCase(active) ? true : false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return "Y".equalsIgnoreCase(active) ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return "Y".equalsIgnoreCase(active) ? true : false;
	}

	@Override
	public boolean isEnabled() {
		return "Y".equalsIgnoreCase(active) ? true : false;
	}

}
