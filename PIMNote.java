import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/* @author 王啸林 16130110069 */
class PIMNote extends PIMEntity {
    String noteText;
    void setNoteText(String n) {
        noteText=n;
    }
    @Override
    public void fromString(String s) {
        // TODO Auto-generated method stub
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("Item "+PIMManager.items+": NOTE "+Priority+" "+noteText+".");
    }
    public PIMNote(){
        T ="note";
    }



    public PIMNote(String noteText,String  priority){
        this.noteText= noteText;
        this.T = "note";
        this.Priority=priority;
    }
    public static PIMCollection fromResultSet(ResultSet rs) throws SQLException {//将结果集合，转换为employee类，并加到linkedlist中
        PIMCollection pimn = new PIMCollection();
        while(rs.next()) {

            String noteText = rs.getString("noteText");

            String priority= rs.getString("priority");


            PIMNote n = new PIMNote (noteText ,priority);
            pimn.add(n);
        }
        return pimn;
    }

    public String insertSQL() {
        return String.format("insert into PIMNote values( \"%s\", \"%s\");",
                noteText, Priority);
    }
    public String deleteSQL() {//删除
        return String.format("delete from PIMnote ");
    }
}
