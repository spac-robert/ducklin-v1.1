package ro.robert.ducklin.model.role;

public enum ApplicationUserPermission {

    ADMIN_REGISTER("admin:register"),
    ADMIN_LOGIN("admin:register"),
    ADMIN_POST("admin:post"),

    USER_REGISTER("tester:add-bug"),
    USER_LOGIN("tester:add-bug"),
    USER_POST("user:post");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
    }
