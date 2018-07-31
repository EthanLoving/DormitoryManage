package per.crystal.dormmanage.controller.view;


import per.crystal.dormmanage.util.Constants;

/**
 * 对外的JSON 对象封装
 * @author Crystal
 * 2018/20/10
 */
public class JsonViewObject {

    /**
     * 状态值
     */
    private String status;

    /**
     * 返回的消息描述
     */
    private String message;

    /**
     * 返回的内容
     */
    private Object content;

    public JsonViewObject successPack(Object content) {
        this.setMessage("");
        this.setContent(content);
        this.setStatus(Constants.JsonView.STATUS_SUCCESS);
        return this;
    }

    public JsonViewObject successPack(Object content, String msg) {
        this.setContent(content);
        this.setMessage(msg);
        this.setStatus(Constants.JsonView.STATUS_SUCCESS);
        return this;
    }

    public JsonViewObject failPack(Exception e) {
        String message = e.getMessage();
        int index = message.indexOf(":");
        setMessage(index == -1 ? message : message.substring(index + 1));
        setContent("");
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }

    public JsonViewObject failPack(String errMsg) {
        setMessage(errMsg);
        setContent("");
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }
   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
