package com.eduerp.auth_service.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
public class CustomUserDetails implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Setter
    private Role role;

    @Builder
    public CustomUserDetails(
        String email, 
        String password, 
        String name, 
        Role role,
        Boolean enabled,
        Boolean accountNonLocked, 
        Boolean credentialsNonExpired, 
        Boolean accountNonExpired) {
       
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("유효한 이메일이 필요합니다");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다");
        }

        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.enabled = enabled != null ? enabled : true;
        this.accountNonLocked = accountNonLocked != null ? accountNonLocked : true;
        this.credentialsNonExpired = credentialsNonExpired != null ? credentialsNonExpired : true;
        this.accountNonExpired = accountNonExpired != null ? accountNonExpired : true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Role 객체를 SimpleGrantedAuthority로 변환
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    // ===== 계정 활성화 상태 설정 =====

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    
}
