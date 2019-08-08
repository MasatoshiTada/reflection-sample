package com.example.ormapper;

import com.google.common.base.CaseFormat;
import org.h2.tools.RunScript;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * リフレクションとアノテーションを利用して作成したORマッパーです。
 * リフレクションとアノテーションの説明のために作ったものですので、実装はとても簡易です。
 * 本番用のコードでは真似しないでください。
 */
public class MyOrMapper {

    private final Connection con;

    public MyOrMapper(Connection con) throws Exception {
        this.con = con;
        // H2 Databaseに対して、create.sql内のCREATE文やINSERT文を実行
        RunScript.execute(con, new FileReader("create.sql"));
    }

    /**
     * エンティティクラスに対応したテーブルから、全レコードを取得します。
     * @param entityClass エンティティクラス
     * @param <E> エンティティ
     * @return 全レコードを保持したリスト
     * @throws Exception
     */
    public <E> List<E> findAll(Class<E> entityClass) throws Exception {
        try (PreparedStatement ps = con.prepareStatement(createSqlFromEntity(entityClass));
             ResultSet rs = ps.executeQuery()) {
            List<E> list = new ArrayList<>();
            while (rs.next()) {
                // ResultSetの各列の値を保持したエンティティインスタンスを作り、リストに追加
                E entityInstance = mapResultSetToEntity(rs, entityClass);
                list.add(entityInstance);
            }
            return list;
        }
    }

    /**
     * エンティティクラスに付加されたアノテーションから、SELECT文を作ります。
     * @param entityClass エンティティクラス
     * @return SELECT文（SELECT ... FROM Xxx）
     */
    private String createSqlFromEntity(Class entityClass) {
        StringBuilder sql = new StringBuilder("SELECT ");
        List<String> columnNames = getColumnNames(entityClass);
        sql.append(String.join(",", columnNames));
        sql.append(" FROM ");
        // MyEntityアノテーションに指定されたテーブル名を取得
        MyEntity myEntity = (MyEntity) entityClass.getAnnotation(MyEntity.class);
        String tableName = myEntity.tableName();
        sql.append(tableName);
        return sql.toString();
    }

    /**
     * ResultSetの各列から値を取り出して、その値を詰めたエンティティインスタンスを返します。
     * @param rs ResultSet
     * @param entityClass エンティティクラス
     * @param <E> エンティティ
     * @return エンティティインスタンス
     * @throws Exception
     */
    private <E> E mapResultSetToEntity(ResultSet rs, Class entityClass) throws Exception {
        // エンティティインスタンスを生成
        Constructor<E> constructor = entityClass.getConstructor();
        E entityInstance = constructor.newInstance();
        // 列名のリストを取得
        List<String> columnNames = getColumnNames(entityClass);
        // 全列の値を、対応する全フィールドに代入
        for (String columnName : columnNames) {
            String columnValue = rs.getString(columnName);
            // スネークケースの列名（first_name）を、setterメソッド名（setFirstName）に変換
            String setterMethodName = "set" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnName);
            // setterメソッドを実行して、列の値をフィールドに代入
            Method setterMethod = entityClass.getMethod(setterMethodName, String.class);
            setterMethod.invoke(entityInstance, columnValue);
        }
        // 各列の値が代入されたエンティティインスタンスを返す
        return entityInstance;
    }

    /**
     * エンティティクラスから、全列名を持ったリストを返します。
     * @param entityClass エンティティクラス
     * @return 全列名を持ったリスト
     */
    private List<String> getColumnNames(Class entityClass) {
        // 戻り値となるリスト
        List<String> columnNames = new ArrayList<>();
        // 全フィールドを取得
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            // フィールドに付加されたMyColumnアノテーションを取得
            MyColumn myColumn = field.getAnnotation(MyColumn.class);
            // アノテーションに設定された列名を取得
            String columnName = myColumn.name();
            // リストに列名を追加
            columnNames.add(columnName);
        }

        // 以上の処理をラムダ式で書くとこんな感じ
//        List<String> columnNames = Arrays.stream(entityClass.getDeclaredFields())
//                .map(field -> field.getAnnotation(MyColumn.class))
//                .map(myColumn -> myColumn.name())
//                .collect(Collectors.toList());
        return columnNames;
    }
}
