package rpc.socket.client;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import rpc.socket.server.RpcRequest;
import rpc.socket.server.RpcResponse;
import rpc.socket.server.api.CalculatorService;
import rpc.socket.server.api.CalculatorServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8080;

        // Connect to server
        Socket socket = new Socket(host, port);

        // Data to be sent
        CalculatorService service = new CalculatorServiceImpl();
        String methodName = "add";
        Object[] arguments = new Object[]{1, 2};
        RpcRequest request = new RpcRequest(methodName, arguments);

        // Serialize data
        byte[] requestData = serialize(request);

        // Send request
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(requestData);

        System.out.println("Send request success");


        // Receive response
        InputStream inputStream = socket.getInputStream();
        byte[] responseData = new byte[1024];
        int length = inputStream.read(responseData);
        byte[] resultData = Arrays.copyOfRange(responseData, 0, length);

        // Deserialize response
        ObjectMapper mapper = new ObjectMapper();
        RpcResponse response = mapper.readValue(resultData, RpcResponse.class);

        System.out.println("Get response : " + JSONObject.toJSONString(response));
    }

    public static byte[] serialize(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(obj);
    }


}
