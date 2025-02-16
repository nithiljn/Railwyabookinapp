package booking;

import java.util.*;

public class Main
{
    //function for bokoing ticket
    public static void bookTicket(Passanger p)
    {
        Ticketbooker booker = new Ticketbooker();
       
        if(Ticketbooker.availableWaitingList == 0)
        {
            System.out.println("No Tickets Available");
            return;
        }
        //check if preferred berth is available
        if((p.berthPreference.equals("L") && Ticketbooker.availableLowerBerths > 0 )||
           (p.berthPreference.equals("M") && Ticketbooker.availableMiddleBerths > 0) ||
           (p.berthPreference.equals("U") && Ticketbooker.availableUpperBerths > 0))
        {
            System.out.println("Preferred Berth Available");
            if(p.berthPreference.equals("L"))
            {
                System.out.println("Lower Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticketbooker.lowerBerthsPositions.get(0)),"L");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticketbooker.lowerBerthsPositions.remove(0);
                Ticketbooker.availableLowerBerths--;
                
                

            }
            else if(p.berthPreference.equals("M"))
            {
                System.out.println("Middle Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticketbooker.middleBerthsPositions.get(0)),"M");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticketbooker.middleBerthsPositions.remove(0);
                Ticketbooker.availableMiddleBerths--;

            }
            else if(p.berthPreference.equals("U"))
            {
                System.out.println("Upper Berth Given");
                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticketbooker.upperBerthsPositions.get(0)),"U");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                Ticketbooker.upperBerthsPositions.remove(0);
                Ticketbooker.availableUpperBerths--;
            }

        }
        //preference not available -> book the available berth
       
        else if(Ticketbooker.availableLowerBerths > 0)
        {
            System.out.println("Lower Berth Given");
            //call booking function in the TicketBooker class
            booker.bookTicket(p,(Ticketbooker.lowerBerthsPositions.get(0)),"L");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            Ticketbooker.lowerBerthsPositions.remove(0);
            Ticketbooker.availableLowerBerths--;
            

        }
        else if(Ticketbooker.availableMiddleBerths > 0)
        {
            System.out.println("Middle Berth Given");
            //call booking function in the TicketBooker class
            booker.bookTicket(p,(Ticketbooker.middleBerthsPositions.get(0)),"M");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            Ticketbooker.middleBerthsPositions.remove(0);
            Ticketbooker.availableMiddleBerths--;

        }
        else if(Ticketbooker.availableUpperBerths > 0)
        {
            System.out.println("Upper Berth Given");
            //call booking function in the TicketBooker class
            booker.bookTicket(p,(Ticketbooker.upperBerthsPositions.get(0)),"U");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            Ticketbooker.upperBerthsPositions.remove(0);
            Ticketbooker.availableUpperBerths--;
            
        }
        // if no berth available go to RAC
        else if(Ticketbooker.availableRacTickets > 0)
        {
            System.out.println("RAC available");
            booker.addToRAC(p,(Ticketbooker.racPositions.get(0)),"RAC" );
        }
        // if no RAC available go to WL
        else if(Ticketbooker.availableWaitingList > 0)
        {
            System.out.println("Added to Waiting List");
            booker.addToWaitingList(p,(Ticketbooker.waitingListPositions.get(0)),"WL");
            
        }
        
    }
    //cancel ticket function
    public static void cancelTicket(int id)
    {
        Ticketbooker booker = new Ticketbooker();
        //check if passenger id is valid
        if(!Ticketbooker.passengers.containsKey(id))
        {
            System.out.println("Passenger detail Unknown");
        }
        else
            booker.cancelTicket(id);
    }
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        //loop to get choices from user until he stops
        while(loop)
        {
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
            int choice = s.nextInt();
            switch(choice)
            {
                // book ticket
                case 1:
                {
                    //get details from Passenger
                    System.out.println("Enter Passenger name,age and berth preference (L,M or U)");
                    String name = s.next();
                    int age = s.nextInt();
                    //get berth preference (L,U,M)
                    String berthPreference = s.next();
                    //create a passenger object
                    Passanger p = new Passanger(name,age,berthPreference);
                    //booking
                    bookTicket(p);
                }
                break;
                //cancel ticket
                case 2:
                {
                    //get passenger id to cancel
                    System.out.println("Enter passenger Id to cancel");
                    int id = s.nextInt();
                    cancelTicket(id);
                }
                break;
                //available tickets print
                case 3:
                {
                    Ticketbooker booker = new Ticketbooker();
                    booker.printAvailable();
                }
                break;
                //occupied tickets print
                case 4:
                {
                    Ticketbooker booker = new Ticketbooker();
                    booker.printPassengers();
                }
                break;
                //exit
                case 5:
                {
                    loop = false;
                }
                break;
                default:
                break;
            }
        }
    }
}