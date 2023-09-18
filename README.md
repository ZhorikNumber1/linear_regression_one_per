# Линейная Регрессия

## Описание проекта
Данный проект реализует алгоритм линейной регрессии и визуализирует результаты с использованием библиотеки JFreeChart. Программа обучается на наборе данных, содержащем пары (x, y), где x - входное значение, а y - целевая переменная. Затем она вычисляет оптимальные веса с помощью метода градиентного спуска и строит график рассеяния, а также прямую регрессии, соответствующую найденным весам.

## Требования
1. JDK (Java Development Kit) версии 8 или выше.
2. Библиотеки Apache Commons Math и JFreeChart.

## Установка и запуск
1. Установите JDK, если его еще нет на вашем компьютере.
2. Скачайте исходные файлы проекта.
3. Откройте командную строку или терминал и перейдите в каталог проекта.
4. Скомпилируйте файлы исходного кода с помощью следующей команды:
   
   javac -d bin -cp "lib/commons-math3-3.6.1.jar:lib/jfreechart-1.5.3.jar" src/main/java/org/example/Main.java
   
   Здесь `lib/commons-math3-3.6.1.jar` и `lib/jfreechart-1.5.3.jar` - это пути к файлам библиотек Apache Commons Math и JFreeChart соответственно. Убедитесь, что пути к файлам библиотек указаны правильно, иначе скрипт компиляции не будет работать.
5. Запустите программу с помощью следующей команды:
   
   java -cp "bin:lib/commons-math3-3.6.1.jar:lib/jfreechart-1.5.3.jar" org.example.Main
   
   Чтобы успешно запустить программу, убедитесь, что все пути к файлам и библиотекам указаны правильно.

После выполнения этих шагов программа должна успешно запуститься и отобразить график рассеяния, а также прямую регрессии на основе найденных весов.

## Результаты
После успешного запуска и выполнения программы, вы увидите график рассеяния и прямую регрессии, соответствующую найденным весам линейной регрессии. Также в консоли будут выведены найденные веса и прогнозы прибыли для двух случаев.