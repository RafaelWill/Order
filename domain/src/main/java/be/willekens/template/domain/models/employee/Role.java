package be.willekens.template.domain.models.employee;

public enum Role {
    ADMIN("Admin");

    private final String prettifiedName;

    Role(String prettifiedName) {
        this.prettifiedName = prettifiedName;
    }

    public String getPrettifiedName() {
        return prettifiedName;
    }
}
