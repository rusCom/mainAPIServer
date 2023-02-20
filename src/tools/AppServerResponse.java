package tools;

import org.json.JSONArray;
import org.json.JSONObject;

public class AppServerResponse {
    Integer statusCode;
    public String body;
    private JSONObject authorization;
    private JSONObject bodyJSON;
    private JSONArray bodyArrayJSON;
    private String fileName;

    public JSONObject postBody;
    public AppServerResponse(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public AppServerResponse(Integer statusCode, JSONObject bodyJSON) {
        this.statusCode = statusCode;
        this.bodyJSON = bodyJSON;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatus(){
        return switch (statusCode) {
            case 200, 1001 -> "OK";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 451 -> "Unavailable For Legal Reasons";
            case 500 -> "Internal Server Error";
            case 503 -> "Service Unavailable";
            default -> "HZ";
        };
    }


    public void setPostBody(JSONObject postBody) {
        this.postBody = postBody;
    }

    public JSONObject getPostBody() {
        return postBody;
    }

    public String getBodyString(){
        return body;
    }

    public JSONObject getBodyJSON(){return bodyJSON;}

    public JSONArray getBodyArrayJSON() {
        return bodyArrayJSON;
    }

    public String getBodyType(){
        if (bodyJSON != null)return "json";
        if (bodyArrayJSON != null)return "jsonArray";
        if (body != null)return "string";
        return "null";
    }

    public String getBodyLog(){
        return switch (getBodyType()) {
            case "json" -> bodyJSON.toString();
            case "jsonArray" -> bodyArrayJSON.toString();
            default -> body;
        };
    }

    public void setStatus(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatus(Integer statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public void setStatus(Integer statusCode, JSONObject bodyJSON){
        this.statusCode = statusCode;
        this.bodyJSON = bodyJSON;
    }

    public void setStatus(Integer statusCode, JSONArray bodyArrayJSON){
        this.statusCode = statusCode;
        this.bodyArrayJSON = bodyArrayJSON;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.statusCode = 1001;
        this.fileName = fileName;
    }

    public JSONObject getAuthorization() {
        if (this.authorization == null){
            this.authorization = new JSONObject();
        }
        return authorization;
    }

    public void addAuthorization(String fieldName, String fieldValue){
        this.authorization.put(fieldName, fieldValue);
    }

    public void addAuthorization(String fieldName, Integer fieldValue){
        this.authorization.put(fieldName, fieldValue);
    }

    public void setAuthorization(JSONObject authorization) {
        this.authorization = authorization;
    }
}
