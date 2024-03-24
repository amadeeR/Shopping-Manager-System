package console;

import java.util.Scanner;

import static console.WestminsterShoppingManager.productArrayList;

public class Validation {

    static Scanner scanner = new Scanner(System.in);
    public double doubleValidator(String message){       //Validation of double inputs
        boolean notValidated = true;
        double input =0;

        while(notValidated){
            try{
                System.out.println(message);
                input = Double.parseDouble(scanner.nextLine());
                if(input>0){
                    notValidated=false;
                }
                else{
                    System.out.print("\n--Enter a valid input--\n");
                }
            }
            catch(NumberFormatException e){
                System.out.print("\n--Please enter an integer--\n");

            }
        }
        return input;
    }
    public int numberValidation(String message,int min,int max) { //Validation of int inputs
        int input = 0;
        boolean notValidated=true;
        while(notValidated) {
            try {
                System.out.println(message);
                input = Integer.parseInt(scanner.nextLine());


                if (input>=min && input<=max) {
                    notValidated=false;

                }else {
                    System.out.print("\n--Error. The option range is " + min + " to " + max +"--\n");
                    //scanner.nextLine();
                }


            } catch (NumberFormatException e) {
                System.out.print("\n--Please enter an integer.--\n");
                //scanner.nextLine();//to consume the invalid input and move on to the next line of input.
            }

        }
        return input;

    }

    public static String productIDValidator(String message) {  //Validation of the ID of a product

        String pID="";
        boolean validation=true;

        while (validation) {
            try {
                System.out.println(message);
                pID = scanner.nextLine();

                // Check if the product ID already exists in the ArrayList
                boolean idExists = false;
                for (int i = 0; i < productArrayList.size(); i++) {
                    Product product = productArrayList.get(i);
                    if (product.getProductID().equalsIgnoreCase(pID)) {
                        System.out.println("\n--ID already Exists!Please enter a unique ID--\n");
                        idExists = true;
                        break;
                    }
                }

                if (!idExists && !pID.matches("[AB]{1}\\d{3}")) {// regex
                    System.out.println("\n--Invalid product ID format--\n");
                } else if (!idExists) {
                    validation = false;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return pID;
    }


    public static String stringValidator(String message) {   //String input values

          String name="";
        boolean validation=true;

        while(validation){

            try {
                System.out.println(message);
                name = scanner.nextLine();
                if (!name.matches("[a-zA-Z_]+")) {//regex
                    System.out.println("\n--Invalid input. Please enter an alphabetical value--\n");

                } else {
                    validation = false;
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }return name;}



}
