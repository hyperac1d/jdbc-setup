//Main method (Optional! This is intended for testing...)
public static void main(String [] args){
	//Test code snippets here...
}

//Test code snippet for openDB and closeDB methods
if(SQLite.openDB()){
    int id = 4; //If you receive an error, probably sqlite detect duplicate ID value.
    String task = "Task 4";
    String isdone = "NO";
    if(create("task","'"+id+"'"+","+"'"+task+"'"+","+"'"+isdone+"'")){
        System.out.println("Inserted successfully!");
    }
    SQLite.closeDB();
}   

//Create Record Method
public static boolean create(String table, String values){
    boolean result = false;
    try{
        SQLite.stmt = conn.createStatement();
        String query = "INSERT INTO "+ table +" VALUES(" + values + ")";
        stmt.executeUpdate(query);
        //You can include exception handling here. (e.g. Duplicate Data, etc.)
        result = true;
    }
    catch(Exception e){
        System.out.println("Create Error: " + e.getMessage());
    }
    return result;
}

//Test code snippet for update method
if(SQLite.openDB()){
    int id = 4;
    String isdone = "YES";
    if(update("task", "ISDONE='"+isdone+"'", id)){
        System.out.println("Updated successfully!");
    }
    SQLite.closeDB();
}

//Test code snippet for delete method
if(SQLite.openDB()){
    int id = 4;
    if(delete("task", id)){
        System.out.println("Deleted successfully!");
    }
    SQLite.closeDB();
} 

//Test code snippet for read method
if(SQLite.openDB()){
    String[][] r = read("task");
    System.out.println("Task ID:" + r[0][0]);
    System.out.println("Task NAME:" + r[0][1]);
    System.out.println("Task ISDONE?:" + r[0][2]);
    SQLite.closeDB();
}