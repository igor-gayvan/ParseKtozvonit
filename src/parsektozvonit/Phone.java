/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsektozvonit;

/**
 *
 * @author Igor Gayvan
 */
public class Phone {

    private String numberRaw;
    private String number;

    public Phone() {
    }

    public Phone(String numberRaw) {
        this.numberRaw = numberRaw;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberRaw() {
        return numberRaw;
    }

    public void setNumberRaw(String numberRaw) {
        this.numberRaw = numberRaw;
    }

}
