package server_communication;

/**
 * Created by product on 10/24/2016.
 */
public class ServerUrl {

    public static final String BASE_URL="http://166.62.85.195/weburlyServices/webapi/Product/";
    public static String IMAGE_URL="http://weburly.com/image/";

    public static class DrowerCategory{
        public static String SERVICE_URL=BASE_URL+"category";
     }
    public static class GetSubCategoryProduct{
        public static String SERVICE_URL=BASE_URL;
    }


}
