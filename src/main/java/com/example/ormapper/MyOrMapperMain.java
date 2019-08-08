package com.example.ormapper;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MyOrMapperMain {

    private static final String URL = "jdbc:h2:mem:~/test";

    private static final String USER = "sa";

    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        // H2 Databaseを起動
        Server server = Server.createTcpServer("-tcpPort", "8043");
        server.start();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // 検索実行＋表示
            MyOrMapper myOrMapper = new MyOrMapper(con);
            List<Employee> employees = myOrMapper.findAll(Employee.class);
            employees.forEach(System.out::println);
        }

        // H2 Databaseを停止
        server.shutdown();
        System.exit(-1); // FIXME コレを書かないとmain()メソッドが終了しない
    }
}
