
1)  Для компиляции программы, находясь в директории ex02/ImagesToChar неоходимо выполнить:
    javac -cp "lib/jcommander-1.72.jar:lib/JColor-5.0.0.jar" src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/Converter.java -d ./target

----------------------------------------------------------------------------------

2) Импортируем сторонние библиотеки
    cd target
    jar xf ../lib/jcommander-1.72.jar
    jar xf ../lib/JColor-5.0.0.jar
    rm -rf META-INF
    cd ..

----------------------------------------------------------------------------------

3) Для создания архива, находясь в директории ex02/ImagesToChar неоходимо выполнить:
    jar cmfv manifest.txt ./target/app.jar -C target .


----------------------------------------------------------------------------------

4) Для запуска архива, находясь в директории ex02 неоходимо выполнить:
    java -jar target/app.jar --background=YELLOW --main=RED
    или
    java -jar target/app.jar --background=RED --main=YELLOW
    или
    java -jar target/app.jar --background=RED --main=BLACK
    или
    java -jar target/app.jar --background=BLACK --main=PINK
    или
    java -jar target/app.jar --background=BLACK --main=CYAN



итд

----------------------------------------------------------------------------------
5) Удаление
    rm -rf target

Доступные цвета (после аргументов --background и --main можно выбрать любой из доступных ниэе цветов
так же флаги --background и --main можно вызывать в сокращенном виде (-bgc и -mc)):
YELLOW
RED
PINK
BLUE
GREEN
MAGENTA
CYAN
WHITE
BLACK


    javac -cp "lib/jcommander-1.82.jar:lib/JColor-5.5.1.jar" src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/Converter.java -d ./target
