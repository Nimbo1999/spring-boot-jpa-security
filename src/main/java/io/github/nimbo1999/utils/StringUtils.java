package io.github.nimbo1999.utils;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

import io.github.nimbo1999.domain.enums.MaskType;

public class StringUtils {
    private static String CPF_MASK = "###.###.###-##";
    private static String POSTAL_CODE_MASK = "#####-###";
    private static String CELL_PHONE_MASK = "(##) #####-####";
    private static String RESIDENTIAL_PHONE_MASK = "(##) ####-####";

    public static String removeNonDigits(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    public static String getStringMask(String value, MaskType maskType) {
        MaskFormatter format;

        try {
            switch(maskType) {
                case CPF:
                    format = StringUtils.getCpfMask(value);
                    break;
                case POSTALCODE:
                    format = StringUtils.getPostalCodeMask(value);
                    break;
                case CELL_PHONE:
                    format = StringUtils.getCellPhoneMask(value);
                    break;
                default:
                    format = StringUtils.getResidentialPhoneMask(value);
                    break;
            }
        } catch(ParseException ex) {
            ex.printStackTrace();
            return value;
        }

        JFormattedTextField formatted = new JFormattedTextField(format);
        formatted.setText(value);
        return formatted.getText();
    }

    private static MaskFormatter getCpfMask(String value) throws ParseException {
        return new MaskFormatter(CPF_MASK);
    }

    private static MaskFormatter getPostalCodeMask(String value) throws ParseException {
        return new MaskFormatter(POSTAL_CODE_MASK);
    }

    private static MaskFormatter getCellPhoneMask(String value) throws ParseException {
        return new MaskFormatter(CELL_PHONE_MASK);
    }

    private static MaskFormatter getResidentialPhoneMask(String value) throws ParseException {
        return new MaskFormatter(RESIDENTIAL_PHONE_MASK);
    }
}
