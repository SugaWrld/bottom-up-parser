import java.util.ArrayList;
import java.util.HashMap;

public class Env {

    ArrayList<String> returnTypeList;
    ArrayList<String> functionNameList;
    HashMap<String, ArrayList<ParseTree.Param>> funcHashMap;
    HashMap<String, Object> table;
    public Env prev;
    public int address = 1;

    public Env(Env prev) {
        this.table = new HashMap<>();
        this.returnTypeList = new ArrayList<>();
        this.functionNameList = new ArrayList<>();
        this.funcHashMap = new HashMap<>();
        this.prev = prev;
    }

    public Env(){
        this.returnTypeList = new ArrayList<>();
        this.functionNameList = new ArrayList<>();
        this.funcHashMap = new HashMap<>();
    }

    public void Put(String name, Object value) {
        this.table.put(name, value);
        this.address++;
    }

    public void putReturnType(String type){
        this.returnTypeList.add(type);
    }
    public String getReturnType(){
        return this.returnTypeList.get(this.returnTypeList.size()-1);
    }
    public String getReturnTypeOfIndex(int index){
        return this.returnTypeList.get(index);
    }

    public void putFunctionName(String name){
        this.functionNameList.add(name);
    }
    public String getFunctionName(){
        return this.functionNameList.get(this.functionNameList.size()-1);
    }
    public int getIndexOfFunctionName(String name){
        return this.functionNameList.indexOf(name);
    }
    public Boolean isFunctionNameExist(String name){
        return this.functionNameList.contains(name);
    }

    public void putFuncHashMap(String name, ArrayList<ParseTree.Param> value){
        this.funcHashMap.put(name, value);
    }
    public ArrayList<ParseTree.Param> getFuncHashMap(String name){
        return this.funcHashMap.get(name);
    }
    public Boolean isFuncHashMapExist(String name){
        return this.funcHashMap.containsKey(name);
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