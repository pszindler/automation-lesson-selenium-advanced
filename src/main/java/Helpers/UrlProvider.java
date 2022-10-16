package Helpers;

public class UrlProvider {
    public static final String BASE_URL = System.getProperty("appUrl");
    public static final String ORDER_HISTORY_URL = BASE_URL + "index.php?controller=history";
    private static final String SIGN_IN_URL = BASE_URL + "index.php?controller=authentication&back=my-account";

}
