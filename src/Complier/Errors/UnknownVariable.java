package Complier.Errors;

public class UnknownVariable extends Exception{

    public UnknownVariable(String errorMessage){
        super(errorMessage);
    }
    public UnknownVariable(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
