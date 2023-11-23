package api.endpoints;


public class Routes {
public static String base_Url = "https://petstore.swagger.io/v2";
	
	//User module
	public static String post_Url = base_Url+"/user";	
	public static String get_Url = base_Url+"/user/{username}";	
	public static String update_Url = base_Url+"/user/{username}";
	public static String delete_Url = base_Url+"/user/{username}";


}
