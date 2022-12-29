package Backend.Data;

import Backend.Utils.Helpers;

import static java.lang.System.currentTimeMillis;

public class Variables {
    Helpers helpers = new Helpers();

    public String baseURIUser = "https://bookstore.toolsqa.com/Account/v1/User";
    public String baseURIAuth = "https://bookstore.toolsqa.com/Account/v1/Authorized";
    public String baseURIToken = "https://bookstore.toolsqa.com/Account/v1/GenerateToken";


    public String result = "User authorized successfully.";
    public int code = 1207;
    public String message = "User not found!";
    public int existCode = 1204;
    public String existMessage = "User exists!";


    public String name = "natia";
    public static String validName = "natia1"+currentTimeMillis();
    public String invalidName = "nat";
    public static String invalidPassword = "Nat"+currentTimeMillis()+"&";
    public static String validpassword = "Natia"+currentTimeMillis()+"#";


}
