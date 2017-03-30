## MPJ and Maven Demo
A Java MPI/MPJ-Express parallel programming project setup in conjunction with Maven

## Short Howto

Clone, read the code and scripts!

### Install MPJ

```batch
cd installmpj
install.bat
```

### Clean and Package

```batch
mvn clean package
```

### Run

```batch
run.bat
```


## Long Howto

If you want to setup MPJ project with maven, try these steps.

1. You first manually install the `mpj.jar` to your local `.m2` maven repository. This can be done by:

    1.1 Download latest mpj-express. Extract folder. Go to `mpj-v0_44\lib` and grab `mpj.jar`, put in some `tmp` folder.

    1.2 Inside `tmp` folder, launch `cmd.exe` and [type these][1]:
    
```batch
mvn install:install-file -Dfile=mpj.jar -DgroupId=mpj -DartifactId=mpj -Dversion=0.44 -Dpackaging=jar
```

Assuming you have already installed maven and `mvn` command is in your path.

2. Now go to your project `pom.xml` and add this to one of your `dependencies` node.

```xml
<dependency>
    <groupId>mpj</groupId>
    <artifactId>mpj</artifactId>
    <version>0.44</version>
    <scope>provided</scope>
</dependency>
```

3. Use `maven-assembly-plugin` to [create executable jar file][2] aka `jar-with-dependencies`.

4. Then run `mpjrun.bat` using `-jar` flag.
```batch
mpjrun.bat -np 4 -jar target/mpj-maven-1.0-SNAPSHOT-jar-with-dependencies.jar
```


[1]: https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html

[2]: http://maven.apache.org/plugins/maven-assembly-plugin/