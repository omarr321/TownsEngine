package Complier.Errors;

public class NoFileSet extends Exception{
    public NoFileSet(String errorMessage){
        super(errorMessage);
    }
    public NoFileSet(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
