/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformThingSpeak;

/**
 *
 * @author Trang
 */
public class Feed {
    private String created_at;
    private String entry_id;
    private String field1;
    private String field2;
    private String field3;

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
    
}
