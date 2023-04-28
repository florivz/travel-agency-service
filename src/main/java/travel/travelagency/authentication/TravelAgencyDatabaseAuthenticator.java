package travel.travelagency.authentication;

import travel.travelagency.database.TravelAgencyEntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

public class TravelAgencyDatabaseAuthenticator {

    public static TravelAgencyEntityManagerFactory loginToDataBase(String username, String password) {
        Map<String, String> loginProperties = new HashMap<>();
        loginProperties.put("javax.persistence.jdbc.user", username);
        loginProperties.put("javax.persistence.jdbc.password", password);
        try{
            return new TravelAgencyEntityManagerFactory(loginProperties);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
