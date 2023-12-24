package Complier.Errors;

public class TestError extends Exception{
    public TestError(String errorMessage){
        super(errorMessage);
    }
    public TestError(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
