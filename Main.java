package uk.co.jaxfire;

import java.sql.Date;

public class Main {

    public void go(){

        String ip = "localhost";
        String port = "3306";
        String databaseName = "menagerie";
        String username = "javaTest";
        String password = "admin";

        MySqlPreparedStatement ps = MySqlPreparedStatement.getInstance();
        if (ps != null) {

            ps.initialise(ip, port, databaseName, username, password);

            String name = "Roger";
            String owner = "Jim";
            String species = "Rabbit";
            String sex = "m";
            Date birth = java.sql.Date.valueOf("1989-04-10");
            Date death = java.sql.Date.valueOf("1995-06-20");

            ps.insertToPetTable(name, owner, species, sex, birth, death);

            ps.close();
        }

    }

    public static void main(String[] args){

        new Main().go();

    }
}
