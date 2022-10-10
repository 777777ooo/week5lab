
package models;
/**
 *
 * @author Dont1
 */
public class AccountService {
   public User login(String username, String password) {
       
        if ((username.equals("abe") || username.equals("barb")) && password.equals("password")) {
            return new User (username, null);
            
        } else {
            return null;
        }
    }
}
   
