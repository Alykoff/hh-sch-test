## Требования
* jdk (>= 7)

## Описание сборки и запуска

#### Сборка/тестирование/запуск при помощи [maven](http://maven.apache.org/)

* Требуется наличие установленого `maven`

* В файле `pom.xml` найти элемент `<mainClass>` и в нем прописать либо
`${hh.class.task2}` (для дальнейшего запуска первой задачи), либо `${hh.class.task1}` (для дальнейшего запуска второй задачи). Сохранить внесенные изменения.

* Открыть командную строку(терминал) в корневой папке проекта.

* Если требуется запустить тесты, то в командной строке следует запустить команду: 

    `mvn clean test`

* Для сборки проекта следует ввести команду:

    `mvn clean test compile assembly:single`

* После этого проект соберется со всеми зависимостями.

* Для запуска (после сборки) проекта в командной строке следует ввести: 

    `java -jar target/hh-sch-test-1.0.0-jar-with-dependencies.jar` + `передаваемые аргументы`

* Например:

     `java -jar target/hh-sch-test-1.0.0-jar-with-dependencies.jar 11 12 23 99`


#### Компилирование при помощи `javac` и запуск при помощи `java`

* Открыть командную строку(терминал) в корневой папке проекта.

* Создать папку `target` в корневой папке:

     `mkdir target`

* Скомпилировать `Task1.java` и `Task2.java` в папку `target`:

     `javac -d target -cp ./src/main/java/ src/main/java/ru/hh/assignments/task1/Task1.java`

     `javac -d target -cp ./src/main/java/ src/main/java/ru/hh/assignments/task2/Task2.java`

* Запустить `Task1.java` и `Task2.java`:

     `java -cp target ru.hh.assignments.task1.Task1 12 11 10 15`

     `java -cp target ru.hh.assignments.task2.Task2 12 11 10 15`

