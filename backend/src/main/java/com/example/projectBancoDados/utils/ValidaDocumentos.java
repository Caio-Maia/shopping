package com.example.projectBancoDados.utils;

import java.util.InputMismatchException;

public class ValidaDocumentos {

    public static boolean isCPF(String documento) {
        // considera-se erro documento's formados por uma sequencia de numeros iguais
        if (documento.equals("00000000000") ||
                documento.equals("11111111111") ||
                documento.equals("22222222222") || documento.equals("33333333333") ||
                documento.equals("44444444444") || documento.equals("55555555555") ||
                documento.equals("66666666666") || documento.equals("77777777777") ||
                documento.equals("88888888888") || documento.equals("99999999999") ||
                (documento.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do documento em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(documento.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(documento.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == documento.charAt(9)) && (dig11 == documento.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static boolean isCNPJ(String documento) {
        // considera-se erro documento's formados por uma sequencia de numeros iguais
        if (documento.equals("00000000000000") || documento.equals("11111111111111") ||
                documento.equals("22222222222222") || documento.equals("33333333333333") ||
                documento.equals("44444444444444") || documento.equals("55555555555555") ||
                documento.equals("66666666666666") || documento.equals("77777777777777") ||
                documento.equals("88888888888888") || documento.equals("99999999999999") ||
                (documento.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                // converte o i-ésimo caractere do documento em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int)(documento.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(documento.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == documento.charAt(12)) && (dig14 == documento.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}