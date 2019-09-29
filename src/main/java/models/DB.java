package models;

import org.sql2o.*;

public class DB {

//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news", "ktscates", "ktscates");
    public static String connectionString = "jdbc:postgresql://ec2-174-129-227-80.compute-1.amazonaws.com:5432/dbpp7ee2scfmrg"; //!
    public static Sql2o sql2o = new Sql2o(connectionString, "gwxlldtvqdqxek", "e500d9c332d9a0119ee3f0119f13671bfa9117822ec9cc3fb38e06f1e3e270ca"); //!
}
