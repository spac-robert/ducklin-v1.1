package ro.robert.ducklin.model.role;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ro.robert.ducklin.model.role.ApplicationUserPermission.*;

public enum Role {
    ADMIN(Sets.newHashSet(ADMIN_REGISTER, ADMIN_LOGIN, ADMIN_POST)),
    USER(Sets.newHashSet(USER_LOGIN, USER_REGISTER, USER_POST));

    private final Set<ApplicationUserPermission> permission;

    Role(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> collect = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return collect;
    }
}
