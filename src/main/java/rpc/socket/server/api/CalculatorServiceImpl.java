package rpc.socket.server.api;

public class CalculatorServiceImpl implements CalculatorService {
    public int add(int a, int b) {
        return a + b;
    }
}