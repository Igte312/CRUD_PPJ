package Controlador;
import java.util.Arrays;
import java.util.List;
public class ValidationClass {


    public static boolean validateRut(String rut) {
        List<String> excep = Arrays.asList(
                "11111111-1", "1111111-4", "2222222-8", "22222222-2", "3333333-1",
                "33333333-3", "4444444-5", "44444444-4", "55555555-5", "66666666-6",
                "77777777-7", "8888888-k", "88888888-8", "99999999-9"
        );
        String regex1 = "\\d{8}-\\d";
        String regex2 = "\\d{7,8}-[0-9Kk]\n";
        String regex = "\\d{8}-[\\dkK]";

        if (rut.length() < 9 || rut.length() > 10 || excep.contains(rut)) {
            System.out.println("largo o por lista excep");
            return false;
        }
        /*
        if (!rut.matches(regex2)) {
            System.out.println("regex");
            return false;
        }*/

        String[] tmp = rut.split("-");

        String digv = tmp[1].toUpperCase();
        System.out.println("abajo solo el digito ingresado");
        System.out.println(digv);
        String rutSinDigv = tmp[0];
        System.out.println("abajo solo el rut ingresado");
        System.out.println(rutSinDigv);
        int multiplicador = 2;
        int res = 0;
        int cont = 0;
        for (int i = rutSinDigv.length() - 1; i >= 0; i--) {
            try {
                int digito = Integer.parseInt(Character.toString(rutSinDigv.charAt(i)));
                res += digito * multiplicador;
                multiplicador += 1;
                if (multiplicador == 8) {
                    multiplicador = 2;
                }
            } catch (NumberFormatException e) {
                // El carácter es una letra
                cont = cont + 1;
            }
        }
        if (cont > 0){
            System.out.println("cae por letra");
            return false;
        }
        System.out.println("primer resultado del for");
        System.out.println(res);
        int mod11_1 = (res / 11);
        System.out.println(mod11_1);
        int mod11_2 = mod11_1 * 11;
        System.out.println(mod11_2);
        int mod11_3 = (res - mod11_2);
        System.out.println(mod11_3);
        int digvReal = (11 - mod11_3);

        //double digvReal = Math.floor((res - (res / 11.0 * 11)) - 11);
        System.out.println("abajo el dgv modulo 11");
        System.out.println(digvReal);
        if (digvReal == 10) {
            digvReal = 10;// valor K 75

        }
        if (digvReal == 11) {
            digvReal = 0;//valor cero 48
        }
        System.out.println("abajo el dgv calculado");
        String strDigvReal = String.valueOf(digvReal);
        System.out.println(strDigvReal);
        if (strDigvReal.equals("10")){
            strDigvReal = "K";
        }
        if (strDigvReal.equals("11")){
            strDigvReal = "0";
        }
        System.out.println("abajo la v-str");
        System.out.println(strDigvReal);
        System.out.println(strDigvReal.getClass());

        System.out.println("abajo el dgv ingresado");
        System.out.println(digv);

        if (strDigvReal.equals(digv)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean validateNulls(String texto1,String texto2,String texto3,String texto4) {
        boolean valida = false;


        texto1 = texto1.replaceAll(" ", "");
        texto2 = texto2.replaceAll(" ", "");
        texto3 = texto3.replaceAll(" ", "");
        texto4 = texto4.replaceAll(" ", "");
        if (texto1.length() == 0 || texto2.length() == 0 || texto3.length() == 0 || texto4.length() == 0) {
            valida = true;
        }
        return valida;
    }

}
