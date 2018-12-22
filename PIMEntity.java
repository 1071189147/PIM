import java.io.Serializable;

/* @author 王啸林 16130110069 */
public abstract class PIMEntity implements Serializable {
    String Priority;
    public String T;
    PIMEntity() {
        Priority = "normal";
    }

    PIMEntity(String priority) {
        Priority = priority;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String p) {
        Priority = p;
    }

    abstract public void fromString(String s);

    abstract public String toString();
}
