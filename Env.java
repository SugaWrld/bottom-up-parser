import java.util.ArrayList;
import java.util.HashMap;

public class Env
{
    HashMap<String, Object> table;
    ArrayList<?> list;
    public Env prev;

    public Env(Env prev)
    {
        this.table = new HashMap<>();
        this.list = new ArrayList<>();
        this.prev = prev;
    }

    public void Put(String name, Object value)
    {
        this.table.put(name, value);
    }

    public Object Get(String name)
    {
        for(Env env = this; env != null; env = env.prev)
        {
            if (env.table.containsKey(name)) {
                return env.table.get(name);
            }
        }
        return null;
    }

}
