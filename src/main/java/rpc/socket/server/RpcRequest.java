package rpc.socket.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: lce
 * @Date: 2023/4/20 0020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest {

    private String methodName;

    private Object[] arguments;


}
