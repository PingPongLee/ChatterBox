/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_socketlayout;

/**
 *
 * @author ReneNN
 */
public class MessageError extends Message
{
    private String ErrorMessage;

    public String getErrorMessage()
    {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }
    
    
}
