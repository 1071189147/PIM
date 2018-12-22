import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/* @author 王啸林 16130110069 */
class PIMAppointment extends PIMEntity {
    public String date,description;
    public void setDate(String d) {
        date=d;
    }

    void setDescription(String des) {
        description=des;
    }
    @Override
    public void fromString(String s) {
        // TODO Auto-generated method stub
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("Item "+PIMManager.items+": APPOINTMENT "+Priority+" "+date+" "+description+".");
    }
    public  PIMAppointment(){
        T = "appointment";
    }


    public PIMAppointment(String date,String description,String  priority){
        this.date= date;
        this.description=description;
        this.Priority=priority;
        this.T = "appointment";
    }
    public static PIMCollection fromResultSet(ResultSet rs) throws SQLException {//将结果集合，转换为employee类，并加到linkedlist中
        PIMCollection pima = new PIMCollection();
        while(rs.next()) {
            String date = rs.getString("date");
            String description = rs.getString("description");

            String priority= rs.getString("priority");


            PIMAppointment a = new PIMAppointment (date, description ,priority);
            pima.add(a);
        }
        return pima;
    }
    public String insertSQL() {
        return String.format("insert into PIMappointment values(\"%s\", \"%s\", \"%s\");",
                date,description,  Priority);
    }

    public String deleteSQL() {//删除
        return String.format("delete from PIMappointment ");
    }
}