import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook{
    static File file;
    static Scanner scanner;
    static int userInput;
    static String searchData;
    static int loop;

    public static void main(String [] args)throws IOException{
        file = new File("data.txt");
        scanner = new Scanner(System.in);
        userInput = 0;
        loop = 5;
        while(true ){
            System.out.print("\n\n\n+-------------------WELCOME YOUR PHONE BOOK --------------------+\n|Plese select option:\t\t\t\t\t\t|\n|\t\t 1)\tAdd a contact\t :(press 1)\t\t|\n|\t\t 2)\tView all contacts:(press 2)\t\t|\n|\t\t 3)\tSearch \t\t :(press 3)\t\t|\n|\t\t 4)\tExit \t\t :(press 4)\t\t|\n+---------------------------------------------------------------+\n\nOption\t:" );
            userInput = Integer.parseInt(scanner.nextLine());
        
            switch(userInput) {
                case 1: while(loop > 0 ){addContact(); exit(); }
                        break;

                case 2: while(loop > 0 ){viweAll(); exit();}
                        break;

                case 3: while(loop > 0 ){
                            System.out.print("* Enter Name \t:");
                            searchData = scanner.nextLine().toLowerCase();
                            viweAll();
                            exit();
                        }
                        break;

                case 4:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Please try agian");
            }
        }   
    }

    public static void viweAll() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        System.out.println(" +---------------+--------------+-------------------------------+");
        System.out.printf("%15s %12s %20s\n", " |      NAME     |", "PHONE NUMBER", "|              Email            |");
        System.out.println(" +---------------+--------------+-------------------------------+");
        while(reader.hasNextLine()){
            String[] listdatas= reader.nextLine().split(",");
            if(searchData!=null){
                if(listdatas[0].toLowerCase().contains(searchData))
                    System.out.printf(" %s %-13s %-14s %-31s %s\n","|",listdatas[0],"|"+listdatas[1],"|"+listdatas[2],"|");  
            }else
                System.out.printf(" %s %-13s %-14s %-31s %s\n","|",listdatas[0],"|"+listdatas[1],"|"+listdatas[2],"|"); 
        }
        searchData=null;
        System.out.println(" +---------------+--------------+-------------------------------+\n\n\n\n\n");
        reader.close();
    }

    public static void addContact() throws IOException {

        String data ="";

        System.out.print("* Plaese Enter the Name \t:");
        while(true ){
            String name = scanner.nextLine();
            if(name.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$")){
                data = name;
                break;
            }else 
                System.out.print("\t\t*************************\n\t\t!-* Input valide Name *-!\n\t\t*************************\n:");
        }

        System.out.print("* Plaese Enter the Phone Number \t:");
        while(true ){
            String mobile = scanner.nextLine();
            if( mobile.matches("^0[0-9]{9}$")){
                data = data+"," + mobile;
                break;
            }else 
                System.out.print("\t\t*********************************\n\t\t!-* Input valide Phone Number *-!\n\t\t*********************************\n:");
        }
        
        System.out.print("* Plaese Enter the Email \t:");
        while(true ){
            String email = scanner.nextLine();
            if( email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
                data = data+"," + email;
                break;
            }else 
                System.out.print("\t\t**************************\n\t\t!-* Input valide Email *-!\n\t\t**************************\n:");
        }

        FileWriter fw = new FileWriter("data.txt",true);
        fw.write(data+"\n");
        fw.close();

        System.out.println("-----------------------Successfully saved-----------------------");
    }

    public static void exit(){
        System.out.print("Are You want to Contiune ? \n\t 1. YES(press 1) \t 1. NO(press 2) ");
        int input = Integer.parseInt(scanner.nextLine());
        switch(input) {

            case 1: break;

            case 2: loop = 0; 
                    break;

            default: System.out.println("\t\t************************\n\t\t!-* Please try agian *-!\n\t\t************************");

        }
    }
}
