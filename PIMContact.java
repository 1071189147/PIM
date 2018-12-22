import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/* @author 王啸林 16130110069 */
class PIMContact extends PIMEntity {
    String firstname,lastname,email;
    void setFirstname(String f) {
        firstname = f;
    }
    void setLastname(String l) {
        lastname = l;
    }
    void setEmail(String e) {
        email = e;
    }

    @Override
    public void fromString(String s) {
        // TODO Auto-generated method stub
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("Item "+PIMManager.items+": CONTACT "+Priority+" "+firstname+" "+lastname+" "+email+".");
    }
    public PIMContact(){
        T ="contact";
    }
    public PIMContact(String firstname,String lastname,String email,String  priority){
       this.firstname= firstname;
       this.lastname=lastname;
       this.email=email;
       this.Priority=priority;
       this.T ="contact";
    }
    public  static PIMCollection fromResultSet(ResultSet rs) throws SQLException {//将结果集合，转换为employee类，并加到linkedlist中
        PIMCollection pimcon = new PIMCollection();
        while(rs.next()) {
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String email= rs.getString("email");
            String priority= rs.getString("priority");


            PIMContact e = new PIMContact (firstname, lastname , email,priority);
            pimcon.add(e);
        }
       return pimcon;
    }
    public String insertSQL() {
        return String.format("insert into PIMContact values(\"%s\", \"%s\", \"%s\", \"%s\");",
                firstname,lastname, email, Priority);
    }

    public String deleteSQL() {//删除
        return String.format("delete from pimcontact ");
    }

}
