package spring.dataBase.repository.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, QA, USER, DEVELOPER, MANAGER, ANALYST, DESIGNER, ENGINEER, CONSULTANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
