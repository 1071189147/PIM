import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/* @author 王啸林 16130110069 */
class PIMTodo extends PIMEntity {
    public String date,todoText;
    public PIMTodo() {
        T = "todo";
    }
    public void setDate(String d) {
        date=d;
    }
    void setTodoText(String todo) {
        todoText=todo;
    }
    public void fromString(String s) {
    }
    public String toString() {
        return ("Item "+PIMManager.items+": TODO "+Priority+" "+date+" "+todoText+".");
    }
    public PIMTodo(String data,String todoText,String  priority){
        this.todoText= todoText;
        this.date=data;
        this.Priority=priority;
        this.T = "todo";

    }
    public static PIMCollection fromResultSet(ResultSet rs) throws SQLException {//将结果集合，转换为employee类，并加到linkedlist中
        PIMCollection pimt = new PIMCollection();
        while(rs.next()) {

            String todoText = rs.getString("todoText");
            String date = rs.getString("date");
            String priority= rs.getString("priority");

            PIMTodo t = new PIMTodo (date,todoText ,priority);
            pimt.add(t);
        }
        return pimt;
    }

    public String insertSQL() {//增加
        return String.format("insert into PIMTodo values(\"%s\", \"%s\", \"%s\");",
                todoText, date, Priority);
    }

    public String deleteSQL() {//删除
        return String.format("delete from pimtodo ");
    }
}
