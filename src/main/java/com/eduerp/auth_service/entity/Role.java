package com.eduerp.auth_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Builder
    public Role(String name, String description) {
        // 필수 필드 검증
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("역할 이름은 필수입니다");
        }

        // Spring Security 호환성을 위한 접두사 확인 및 추가
        if (!name.startsWith("ROLE_")) {
            name = "ROLE_" + name;
        }

        this.name = name;
        this.description = description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}