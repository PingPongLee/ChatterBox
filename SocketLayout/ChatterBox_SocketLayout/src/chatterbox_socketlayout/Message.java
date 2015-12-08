/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_socketlayout;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Daniel Jørgensen & René Nielsen
 */
public abstract class Message
{
    private messageType type;
    private int subType;
    private Date time;
    public static SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public messageType getType()
    {
        return type;
    }

    public void setType(messageType type)
    {
        this.type = type;
    }

    public int getSubType()
    {
        return subType;
    }

    public void setSubType(int subType)
    {
        this.subType = subType;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }
}
