package com.sm.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sm.enums.ApplicationUserPermission.*;

public enum ApplicationUserRoles {
    SUPER_ADMIN(Sets.newHashSet(USER_READ, USER_WRITE)),
    SYSTEM_ADMIN(Sets.newHashSet()),
    MEMBER(Sets.newHashSet(USER_READ));

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getUserPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
