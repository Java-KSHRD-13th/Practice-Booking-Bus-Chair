import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String seat;

        String rec = "\u001B[0m";
        String red= "\u001B[31m";
        String gre = "\u001B[32m";
        String yel = "\u001B[33m";

        int available=0;
        int unavailable=0;

        boolean[] booked = null;
        String bookChair;

        while (true){
            System.out.print("-> Enter the Seat of bus between [25-85]: ");
            seat = sc.next();
            boolean vSeat = Pattern.matches("\\d+", seat);

            if (vSeat)
                if (Integer.parseInt(seat) >= 25 && Integer.parseInt(seat) <= 85)
                    break;
                else
                    System.out.println("Wrong input. You can input numbers of seat between [25-85].");
            else
                System.out.println("Wrong input. Please input only number between [25-85].");
        }

        booked = new boolean[Integer.parseInt(seat)];
        do {
            System.out.println("\n---------------- Display Bus Information ----------------\n");
            System.out.println("______________________________________________________________");
            for (int i = 0; i < booked.length; i++){
                if (!booked[i])
                    available++;
                else
                    unavailable++;
                System.out.print( (!booked[i] ? "|\t"+gre+"(+) "+rec : "| "+red+ (i+1==1 ? "\t" : "")+ "(-) ") + (i+1 < 10 ? "0"+(i+1) : i+1) + rec+ "\t |");

                if((i+1) % 5 == 0){
                    System.out.println();
                    System.out.println("|____________________________________________________________|");
                }
            }
            System.out.print(red+"\t( - ) "+rec+" : Unavailable( "+ red+unavailable + rec+ " )\t\t");
            unavailable=0;
            System.out.println(gre+"( + ) "+rec+" : Available( "+ gre+ available +rec+" )");
            available=0;


            String isBooking;
            while (true){
                System.out.print("\n=> Do you want to booking the chair (Y/N)? : ");
                isBooking = sc.next();
                boolean vIsBooking = Pattern.matches("^[yYnN]$", isBooking);
                System.out.println(vIsBooking? "" : "Wrong input. Please input only yes(y/Y) or no(n/N).");
                if (vIsBooking)
                    break;
            }

            if (isBooking.equalsIgnoreCase("y")){
                while (true){
                    System.out.print("\n-> Enter Chair number to booking: ");
                    bookChair = sc.next();
                    boolean vBookChair = Pattern.matches("\\d+", bookChair);
                    System.out.println(vBookChair? "" : "Wrong input. Please input only number.");
                    if (vBookChair)
                        break;
                }

                if (Integer.parseInt(bookChair) <= booked.length){
                    String YesNo;
                    if(booked[Integer.parseInt(bookChair) -1])
                        System.out.println("The chair you are looking has been booked.");
                    else{
                        while (true){
                            System.out.print("-> Do you want to book chair number "+ bookChair+" (y/n): ");
                            YesNo = sc.next();
                            boolean vYesNo = Pattern.matches("^[yYnN]$", YesNo);
                            System.out.println(vYesNo? "" : "Wrong input. Please input only yes(y/Y) or no(n/N).");
                            if (vYesNo)
                                break;
                        }

                        if (YesNo.equalsIgnoreCase("y")) {
                            if (!booked[Integer.parseInt(bookChair) - 1])
                                booked[Integer.parseInt(bookChair) - 1] = true;
                            else
                                System.out.println("The chair has been already booked. Please booking again.");

                            System.out.println("\n======================================================");
                            System.out.println("||"+gre+"\tChair number " + bookChair + " has been booked successfully.\t"+rec+"||");
                            System.out.println("======================================================\n");
                        }
                    }

                }
                else
                    System.out.println("Please check the number of seats and rewrite the booking seat.");

                System.out.println(bookChair);
            }else {
                System.out.println("Thank you! Good Luck!😁");
                break;
            }
        }while (true);
    }
}