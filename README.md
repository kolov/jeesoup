# jeesoup

Trying Out JEE stuff, nothing fancy here.

### Configuration values injection 

If configuration values come from configuration service, annotate them andlet CDI inject them instead of calling the configuraton service explicitely. See [GreetController](https://github.com/kolov/jeesoup/blob/master/src/main/java/com/akolov/jeesoup/controller/GreetController.java) for two possible approaches.


Configure Datasource in Websphere liberty:

  <library id="h2">
    <fileset dir="/Users/[me]/.m2/repository/com/h2database/h2/1.4.189" />
  </library>
  <dataSource id="DefaultDataSource" jndiName="jdbc/jeesoup" type="javax.sql.DataSource">
    <jdbcDriver libraryRef="h2" javax.sql.ConnectionPoolDataSource="org.h2.jdbcx.JdbcDataSource" javax.sql.DataSource="org.h2.jdbcx.JdbcDataSource" javax.sql.XADataSource="org.h2.jdbcx.JdbcDataSource" />
    <properties URL="jdbc:h2:mem:jkk" user="sa" password="sa" databaseName="jkk" />
  </dataSource>
