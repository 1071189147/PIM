/* @author 王啸林 16130110069 */
import java.net.SocketOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class PIMManager {
    static int items=0;
//    static String[] List=new String[100];
   static PIMCollection list = new PIMCollection();
    public static Connection connection;

    static {
        try {
            connection = DB.getConnection();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            System.exit(1);
        }
    }

    public static void main(String[] args)throws IOException {
        // TODO Auto-generated method stub
        System.out.println("Welcome to PIM.");
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
            String input = in.next();
            switch(input) {
                case "List":
                    System.out.println("There are "+items+" items.");
                    int i= items-1;
//
                    while(i>-1){
                        System.out.println(list.get(i));
                        i--;
                    }
                    break;
                case "Create":
                    System.out.println("Enter an item type(todo,note,contact,appointment)");
                    input=in.next();
                    switch(input) {
                        case "todo":
                            PIMTodo todo=new PIMTodo();
                            System.out.println("Enter date for todo item:");
                            BufferedReader buff=new BufferedReader(new InputStreamReader(System.in));
                            input=buff.readLine();
                            todo.setDate(input);
                            System.out.println("Enter todo text:");
                            BufferedReader buff1=new BufferedReader(new InputStreamReader(System.in));
                            input=buff1.readLine();
                            todo.setTodoText(input);
                            System.out.println("Enter todo priority:");
                            BufferedReader buff2=new BufferedReader(new InputStreamReader(System.in));
                            input=buff2.readLine();
                            todo.setPriority(input);
                            items++;


//                            String r=todo.toString();
                            list.add(todo);
                            break;
                        case "note":
                            PIMNote note=new PIMNote();
                            System.out.println("Enter note text:");
                            BufferedReader buff3=new BufferedReader(new InputStreamReader(System.in));
                            input=buff3.readLine();
                            note.setNoteText(input);
                            System.out.println("Enter note priority:");
                            input=in.next();
                            note.setPriority(input);
                            items++;
//                            String r1=note.toString();
                            list.add(note);
                            break;
                        case "contact":
                            PIMContact contact=new PIMContact();
                            System.out.println("Enter firstname for contact item:");
                            BufferedReader buff4=new BufferedReader(new InputStreamReader(System.in));
                            input=buff4.readLine();
                            contact.setFirstname(input);
                            System.out.println("Enter lastname for contact item:");
                            BufferedReader buff5=new BufferedReader(new InputStreamReader(System.in));
                            input=buff5.readLine();
                            contact.setLastname(input);
                            System.out.println("Enter email for contact item:");
                            BufferedReader buff6=new BufferedReader(new InputStreamReader(System.in));
                            input=buff6.readLine();
                            contact.setEmail(input);
                            System.out.println("Enter contact priority:");
                            input=in.next();
                            contact.setPriority(input);
                            items++;
//                            String  r2=contact.toString();
                            list.add(contact);
                            break;

                        case "appointment":
                            PIMAppointment appointment=new PIMAppointment();
                            System.out.println("Enter date for appointment item:");
                            BufferedReader buff7=new BufferedReader(new InputStreamReader(System.in));
                            input=buff7.readLine();
                            appointment.setDate(input);
                            System.out.println("Enter appointment description:");
                            BufferedReader buff8=new BufferedReader(new InputStreamReader(System.in));
                            input=buff8.readLine();
                            appointment.setDescription(input);
                            System.out.println("Enter appointment priority:");
                            input=in.next();
                            appointment.setPriority(input);
                            items++;
//                            String r3=appointment.toString();
//                            List[items]=r3;
                            list.add(appointment);
                            break;

                        default:
                            System.out.println("the item type is not exist");
                            break;
                    }
                    break;

                case "Save":
//
                     try {
                         for (PIMEntity pe:list.getTodos()) {
                             PIMTodo todo = (PIMTodo) pe;
                             DB.exec(todo.insertSQL(),connection);
                         }
                         for (PIMEntity pe:list.getAppointments()) {
                             PIMAppointment pa = (PIMAppointment) pe;
                             DB.exec(pa.insertSQL(),connection);
                         }
                         for (PIMEntity pe:list.getContact()) {
                             PIMContact pc = (PIMContact) pe;
                             DB.exec(pc.insertSQL(),connection);
                         }
                         for (PIMEntity pe:list.getNotes()) {
                             PIMContact pc = (PIMContact) pe;
                             DB.exec(pc.insertSQL(),connection);
                         }
                     }catch (SQLException se){
                         se.printStackTrace();
                     }

                     System.out.println("Item have been saved.");
                    break;
                case "Load":
//
//                    PIMCollection pc =new PIMCollection();
                    try {
//                        int a[]=new int [1];
                        ResultSet rstodo=DB.execSQL("SELECT * FROM  PIMTodo",connection);
                        PIMCollection t=PIMTodo.fromResultSet(rstodo);
                        items += t.size();
                        list.addAll(t);

                        ResultSet rscon=DB.execSQL("SELECT * FROM  PIMContact",connection);
                        PIMCollection t1=PIMContact.fromResultSet(rscon);
                        items += t1.size();
                        list.addAll(t1);

                        ResultSet rsa=DB.execSQL("SELECT * FROM  PIMAppointment",connection);
                        PIMCollection t2=PIMAppointment.fromResultSet(rsa);
                        items += t2.size();
                        list.addAll(t2);

                        ResultSet rsn=DB.execSQL("SELECT * FROM  PIMNote",connection);
                        PIMCollection t3=PIMNote.fromResultSet(rsn);
                        items += t3.size();
                        list.addAll(t3);

                    }catch (SQLException se){
                        se.printStackTrace();
                    }


                    System.out.println("Item have been loaded.");
                    break;

                case "Quit":
                    in.close();
                    return;

                default:
                    System.out.println("the command is not exist");
                    break;

            }

        }

    }

}

