package Shared.Domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by emanuele on 03/04/16.
 */
public class Posizione implements Serializable{


    private String id = UUID.randomUUID().toString();
    private Integer x;
    private Integer y;

    public Posizione(){};

    public Posizione(Integer x, Integer y) {
        if (x>=0 && y>=0) {
            this.x = x;
            this.y = y;
        }
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getMap(){

        Map posizione = new HashMap();

        posizione.put("X",this.x);
        posizione.put("Y",this.y);

        return posizione;

    }

}
