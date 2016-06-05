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

    private final static String DEFAULT_CODE_COUNTRY = "38";

    private String numberRaw;

    private String numberPhone;
    private String numberFormat;
    private String country;
    private String oper;

    public Phone() {
    }

    public Phone(String numberRaw, String country, String oper) {
        this.numberRaw = numberRaw;
        this.country = country;
        this.oper = oper;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String number) {
        this.numberPhone = numberPhone;
    }

    public String getNumberRaw() {
        return numberRaw;
    }

    public void setNumberRaw(String numberRaw) {
        this.numberRaw = numberRaw;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public void convert() {
        numberPhone = (country == null ? DEFAULT_CODE_COUNTRY : country) + numberRaw.replaceAll("\\(|\\)|\\+|-|\\s*", "");

        int len = numberPhone.length();

        numberFormat = "+"
                + numberPhone.substring(0, len - 9)
                + " ("
                + numberPhone.substring(len - 9, len - 7)
                + ") "
                + numberPhone.substring(len - 7, len - 4)
                + " "
                + numberPhone.substring(len - 4, len);

    }
}
