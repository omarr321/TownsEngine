package Complier.Errors;

public class InvaildCommand extends Exception{

    public InvaildCommand(String errorMessage){
        super(errorMessage);
    }
    public InvaildCommand(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
