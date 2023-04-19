package rpc.socket.server;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import rpc.socket.server.api.CalculatorService;
import rpc.socket.server.api.CalculatorServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
public class MainClass {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int port = 8080;

        // Start server
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Start server success");

        while (true) {
            // Accept client connection
            Socket socket = serverSocket.accept();

            // Receive request
            InputStream inputStream = socket.getInputStream();
            byte[] requestData = new byte[1024];
            int length = inputStream.read(requestData);
            byte[] resultData = Arrays.copyOfRange(requestData, 0, length);

            // Deserialize request
            ObjectMapper mapper = new ObjectMapper();
            RpcRequest request = mapper.readValue(resultData, RpcRequest.class);

            System.out.println("Accept client request " + JSONObject.toJSONString(request));

            // Invoke method
            String methodName = request.getMethodName();
            Object[] arguments = request.getArguments();
            CalculatorService service = new CalculatorServiceImpl();
            Method method = service.getClass().getMethod(methodName, Integer.TYPE, Integer.TYPE);
            int result = (int) method.invoke(service, arguments[0], arguments[1]);

            // Serialize response
            RpcResponse response = new RpcResponse(result);
            byte[] responseData = serialize(response);

            // Send response
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(responseData);


            System.out.println("Send response to client " + JSONObject.toJSONString(request));
        }
    }


    public static byte[] serialize(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(obj);
    }


}
