package registrar;

public class Constants {
    public static final Integer USER_ID = Integer.parseInt(System.getProperty("user"));
    public static final String LOGIN = System.getProperty("login");
    public static final String PASSWORD = System.getProperty("password");
    public static final String APPLICATION_ID = System.getProperty("application");
    public static final String SCOPE = System.getProperty("scope");
    public static final String GROUP_ID = System.getProperty("group");

    private Constants() {}

}
