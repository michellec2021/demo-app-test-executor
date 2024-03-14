package app.demoappservice.demo.model;

import core.framework.api.json.Property;

/**
 * @author michelle
 */
public class UserInfo {
    @Property(name = "email")
    public String email;
    @Property(name = "first_name")
    public String firstName;
    @Property(name = "last_name")
    public String lastName;
    @Property(name = "phone")
    public String phone;
    @Property(name = "user_id")
    public String userId;
}
