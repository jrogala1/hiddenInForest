package com.company.app.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;

public class userInput {

    public String name;
    public String surname;
    public String email;


    private static final String domain = "@mex.com";
    private static String pattern = "[A-Za-ząęóśńżźł]+";

    public userInput()
    {
        this.name = "";
        this.surname = "";

    }

    public String generateEmail() throws IOException {

        standarizeInput();

        int i = 2;
        this.email = surname + "." + name + domain;

        fileChecker file = new fileChecker();

        while (file.read(this.email) != 0)
        {
            this.email = surname + "." + name + i + domain;
            i++;
        }

        file.write(this.email);

        return this.email;

    }

    public int checkInput(String input)
    {
            if (input.matches(pattern))
            {
                return 0;
            }
            else
            {
                return -1;
            }
    }


    private void standarizeInput()
    {
        this.name = Normalizer.normalize((this.name),Normalizer.Form.NFD);
        this.name = this.name.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","");
        this.surname = Normalizer.normalize((this.surname),Normalizer.Form.NFD);
        this.surname = this.surname.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","");

        this.name = this.name.toLowerCase();
        this.surname = this.surname.toLowerCase();

    }



}
