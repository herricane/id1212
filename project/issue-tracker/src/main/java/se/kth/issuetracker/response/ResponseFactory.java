package se.kth.issuetracker.response;

public class ResponseFactory {

    public static Response buildSuccessResponse(Object data) {
        return buildResponse(ResponseCode.SUCCESS, data);
    }

    public static Response buildFailResponse() {
        return buildResponse(ResponseCode.FAIL, null);
    }

    private static Response buildResponse(ResponseCode responseCode, Object data) {
        return new Response(responseCode.code, responseCode.name(), data);
    }
}
