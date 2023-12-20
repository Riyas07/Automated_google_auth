package Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class service {
  private static InputStream inputStream=null;
  private static Properties properties=null;
    static
    {
      try
      {
          inputStream =new FileInputStream("properties/config.properties");
           properties=new Properties();
          properties
                  .load(inputStream);
      }
      catch (IOException e)
      {
          System.out.println(e.getMessage());
      }
    }
    public static String get_client_id() throws IOException {

      return  properties.getProperty("client_id");
    }
    public static String get_client_secret()
    {
       return properties.getProperty("client_secret");
    }
    public static String get_refresh_token_url()
    {
        return properties.getProperty("refresh_token_url");
    }
    public static String get_refresh_token()
    {
        return properties.getProperty("refresh_token");
    }
    public static String get_grant_type()
    {
        return properties.getProperty("grant_type");
    }
    public static String get_user_profile_url()
    {
        return properties.getProperty("user_profile_url");
    }
    public static String get_send_email_url()
    {
        return properties.getProperty("send_email_url");
    }
}
