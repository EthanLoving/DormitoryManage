package per.crystal.dormmanage.util;

/**
 * @author Crystal
 * 2018/01/05 13:06
 */
public interface Constants {

    String SESSION_KEY_USER = "session_user"; 

    /**
     * Restful 对外的静态变量
     */
    interface JsonView {

        /**
         * 成功
         */
        String STATUS_SUCCESS = "success";

        /**
         * 失败
         */
        String STATUS_FAIL = "fail";

    }
}
