package pl.edu.agh.kis.pz1.exceptions;

import java.io.IOException;

public class WrongPathException extends IOException {
    public WrongPathException(){}

    public WrongPathException(String message){
        super(message);
    }
}
