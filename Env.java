import java.util.HashMap;

public class Env {

    HashMap<String, Object> table;
    public Env prev;
    public int address = 1;

    public Env(Env prev) {
        this.table = new HashMap<>();
        this.prev = prev;
    }

    public void Put(String name, Object value) {
        this.table.put(name, value);
        this.address++;
    }

    public Object Get(String name) {
        for(Env env = this; env != null; env = env.prev) {
            if(env.table.containsKey(name)) {
                return env.table.get(name);
            }
        }
        return null;
    }

}
