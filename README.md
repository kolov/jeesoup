# jeesoup

Trying Out JEE stuff, nothing fancy here.

### CDI injection of configuration parameters

There are a some examples on internet on how to CDI inject values from a properties file, but the reality is often more complicated than a single proprties file containing everything. Here is an example of parameters coming from a configuration service. Each parameter is queried from the service with both appication key (same for all parameters within the application) and parameter key. See [GreetController](https://github.com/kolov/jeesoup/blob/master/src/main/java/com/akolov/jeesoup/controller/GreetController.java) for an example how to make a custom annotation, so that application key is set only once (DRY).


Configure Datasource in Websphere liberty:

    <library id="h2">
        <fileset dir="/Users/[me]/.m2/repository/com/h2database/h2/1.4.189" />
    </library>
    <dataSource id="DefaultDataSource" jndiName="jdbc/jeesoup" type="javax.sql.DataSource">
      <jdbcDriver libraryRef="h2" javax.sql.ConnectionPoolDataSource="org.h2.jdbcx.JdbcDataSource" javax.sql.DataSource="org.h2.jdbcx.JdbcDataSource" javax.sql.XADataSource="org.h2.jdbcx.JdbcDataSource" />
        <properties URL="jdbc:h2:mem:jkk" user="sa" password="sa" databaseName="jkk" />
    </dataSource>
