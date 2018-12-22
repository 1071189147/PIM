import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PIMCollection extends ArrayList<PIMEntity> {


    public PIMCollection getNotes(){
        PIMCollection c = new PIMCollection();

        for (PIMEntity pimEntity : this) {
            if(pimEntity.T.equals("note")){
                c.add(pimEntity);
            }
        }
        return c;
    }
    public PIMCollection getTodos(){
        PIMCollection c = new PIMCollection();

        for (PIMEntity pimEntity : this) {
            if(pimEntity.T.equals("todo")){
                c.add(pimEntity);
            }
        }
        return c;
    }


    public PIMCollection getAppointments(){
        PIMCollection c = new PIMCollection();

        for (PIMEntity pimEntity : this) {
            if(pimEntity.T.equals("appointment")){
                c.add(pimEntity);
            }
        }
        return c;
    }
    public PIMCollection getContact(){
        PIMCollection c = new PIMCollection();

        for (PIMEntity pimEntity : this) {
            if(pimEntity.T.equals("contact")){
                c.add(pimEntity);
            }
        }
        return c;
    }
    public PIMCollection getItemsForDate(Date d){
        String newdate = d.getMonth() + "/" + d.getDate() + "/" + d.getYear();
        PIMCollection c = new PIMCollection();

        for (PIMEntity pimEntity : this.getTodos()) {
            PIMTodo pimTodo = (PIMTodo) pimEntity;
            if(pimTodo.date.equals(newdate) ){
                c.add(pimEntity);
            }
        }

        for (PIMEntity pimEntity : this.getAppointments()) {
            PIMAppointment pimAppointment = (PIMAppointment) pimEntity;
            if(pimAppointment.date.equals(newdate) ){
                c.add(pimEntity);
            }
        }
        return c;
    }



}
