package Models.User;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;

    public static final class UserBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthday;

        UserBuilder() {

        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            User user = new User();
            user.birthday = this.birthday;
            user.email = this.email;
            user.firstName = this.firstName;
            user.password = this.password;
            user.lastName = this.lastName;
            return user;
        }
    }
}
