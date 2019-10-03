package models;

import org.sql2o.*;

public class DB {

//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news", "ktscates", "ktscates");
    public static String connectionString = "jdbc:postgresql://ec2-174-129-43-40.compute-1.amazonaws.com:5432//d2ig9l20sv4fei"; //!
    public static Sql2o sql2o = new Sql2o(connectionString, "whkxlzdymvmkhf", "34d10f3172ad1e41df97db4247ff789b6d1ab67b50f570d0158d733c272f7b08"); //!
}
