package travel.travelagency.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;

import java.io.InputStream;
import java.util.Properties;

public class LanguagePropertiesLoader {

    static final Logger logger = LogManager.getLogger(LanguagePropertiesLoader.class);

    private static final String MSG_FAILED_TO_LOAD_PROPERTIES = "Loading %s failed";

    public static Properties loadProperties(String propertiesPath) {
        Properties dbAccessProperties;
        try(InputStream is = LanguagePropertiesLoader.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            dbAccessProperties = new Properties();
            dbAccessProperties.load( is );
        } catch (Exception e) {
            final String MSG = String.format(MSG_FAILED_TO_LOAD_PROPERTIES, propertiesPath);
            logger.error(MSG);
            throw new RuntimeException(MSG);
        }
        return dbAccessProperties;
    }

}
