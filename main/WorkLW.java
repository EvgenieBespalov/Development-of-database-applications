package Main;

import hibernate.logic.NotebookLogic;
import hibernate.logic.ListTagsLogic;

import models.Listtags;
import models.Notebook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkLW {

    public static void WorkLW()
    {
        int choice, number = 1;

        System.out.println("Лабораторная работа №2. Hibernate");

        mainExit:
        while(true)
        {
            Scanner scanner = new Scanner(System.in);

            NotebookLogic notebookLogic = new NotebookLogic();
            ListTagsLogic listtagsLogic = new ListTagsLogic();
            List<Notebook> listAllNotebooks = notebookLogic.findAll();
            List<Listtags> listAlltags = listtagsLogic.findAll();

            for (Notebook allNotebooks : listAllNotebooks) {
                System.out.println("Запись №" + number + "\n" + allNotebooks);
                number++;
            }
            number = 1;
            for (Listtags allTags : listAlltags) {
                System.out.println("Тег №" + number + ": " + allTags);
                number++;
            }
            number = 1;

            consoleView();
            System.out.print("Выполнить функцию: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1-> {
                    System.out.println("Ввод новой записи");
                    List<Listtags> tags = new ArrayList<Listtags>();
                    System.out.print("Введите название записи: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите текст записи: ");
                    String text = scanner.nextLine();
                    System.out.print("Введите дату записи в формате дд.мм.гггг: ");
                    String date = scanner.nextLine();
                    System.out.print("Введите теги записи, выход - 0: ");
                    String tag = scanner.nextLine();
                    while(tag.equals("0") == false)
                    {
                        tags.add(new Listtags(tag));
                        tag = scanner.nextLine();
                    }
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    notebookLogic.save(new Notebook(name, LocalDate.parse(date, dtf), text, tags));
                }
                case 2-> {
                    System.out.println("Удаление записи");
                    System.out.println("Введите номер удаляемой записи");
                    int delNumber;
                    delNumber = scanner.nextInt();
                    scanner.nextLine();
                    notebookLogic.delete(listAllNotebooks.get(delNumber-1));
                }
                case 3-> {
                    System.out.println("Редактирование записи");
                    System.out.println("Введите номер редактируемой записи");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    Notebook updateNote = listAllNotebooks.get(choice-1);

                    System.out.println("1. Редактирование имени записи");
                    System.out.println("2. Редактирование текста записи");
                    System.out.println("3. Редактирование даты записи");
                    System.out.println("4. Редактирование тегов записи");
                    System.out.println("5. Редактирование записи по всем полям");
                    System.out.println("0. Закончить редактирование");
                    innerExit:
                    while(true)
                    {

                        System.out.print("Выполнить функцию: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice){
                            case 1->{
                                System.out.print("Введите название записи: ");
                                String name = scanner.nextLine();
                                updateNote.setName(name);
                            }
                            case 2->{
                                System.out.print("Введите текст записи: ");
                                String text = scanner.nextLine();
                                updateNote.setText(text);
                            }
                            case 3->{
                                System.out.print("Введите дату записи в формате дд.мм.гггг: ");
                                String date = scanner.nextLine();
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                updateNote.setDate(LocalDate.parse(date, dtf));
                            }
                            case 4->{
                                System.out.print("Введите теги записи, выход - 0: ");
                                String tag = scanner.nextLine();
                                List<Listtags> tags = new ArrayList<Listtags>();
                                while(tag.equals("0") == false)
                                {
                                    tags.add(new Listtags(tag));
                                    tag = scanner.nextLine();
                                }
                                updateNote.setTags(tags);
                            }
                            case 5->{
                                System.out.print("Введите название записи: ");
                                String name = scanner.nextLine();
                                System.out.print("Введите текст записи: ");
                                String text = scanner.nextLine();
                                System.out.print("Введите дату записи в формате дд.мм.гггг: ");
                                String date = scanner.nextLine();
                                System.out.print("Введите теги записи, выход - 0: ");
                                String tag = scanner.nextLine();
                                List<Listtags> tags = new ArrayList<Listtags>();
                                while(tag.equals("0") == false)
                                {
                                    tags.add(new Listtags(tag));
                                    tag = scanner.nextLine();
                                }
                                updateNote.setName(name);
                                updateNote.setText(text);
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                updateNote.setDate(LocalDate.parse(date, dtf));
                                updateNote.setTags(tags);
                            }
                            case 0->{
                                break innerExit;
                            }
                            default -> System.out.println("Такого варианта нет!");
                        }
                    }
                    notebookLogic.update(updateNote);
                }
                case 4-> {
                    System.out.println("Поиск по заданию");
                    System.out.println("1. Поиск по имени");
                    System.out.println("2. Поиск по дате");
                    System.out.println("3. Поиск по тегам");
                    System.out.println("4. Поиск по слову");
                    System.out.println("0. Закончить редактирование");
                    innerExit:
                    while(true)
                    {
                        System.out.print("Выполнить функцию: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice){
                            case 1->{
                                System.out.println("Поиск по названию\nВведите название записи");
                                String name = scanner.nextLine();
                                List<Notebook> listSearchNotebooks = notebookLogic.findByName(name);
                                if(listSearchNotebooks.size() == 0){
                                    System.out.println("Записей не найдено!");
                                }
                                else{
                                    for (Notebook searchNotebooks : listSearchNotebooks) {
                                        System.out.println(searchNotebooks);
                                    }
                                }
                            }
                            case 2->{
                                System.out.print("Поиск по дате создания в указанном интервале\nВведите первую дату в формате дд.мм.гггг: ");
                                String firstDate = scanner.nextLine();
                                System.out.print("Введите вторую дату в формате дд.мм.гггг: ");
                                String secondDate = scanner.nextLine();
                                List<Notebook> listSearchNotebooks = notebookLogic.findByDate(firstDate, secondDate);
                                if(listSearchNotebooks.size() == 0){
                                    System.out.println("Записей не найдено!");
                                }
                                else{
                                    for (Notebook searchNotebooks : listSearchNotebooks) {
                                        System.out.println(searchNotebooks);
                                    }
                                }
                            }
                            case 3->{
                                System.out.print("Поиск по тегу\nВведите тег: ");
                                String tag = scanner.nextLine();
                                List<Notebook> listSearchNotebooks = notebookLogic.findByTags(tag);
                                if(listSearchNotebooks.size() == 0){
                                    System.out.println("Записей не найдено!");
                                }
                                else{
                                    for (Notebook searchNotebooks : listSearchNotebooks) {
                                        System.out.println(searchNotebooks);
                                    }
                                }
                            }
                            case 4->{
                                System.out.print("Поиск по указанному слову в тексте записи\nВведите слово: ");
                                String text = scanner.nextLine();
                                List<Notebook> listSearchNotebooks = notebookLogic.findByText(text);
                                if(listSearchNotebooks.size() == 0){
                                    System.out.println("Записей не найдено!");
                                }
                                else{
                                    for (Notebook searchNotebooks : listSearchNotebooks) {
                                        System.out.println(searchNotebooks);
                                    }
                                }
                            }
                            case 0->{
                                break innerExit;
                            }
                            default -> System.out.println("Такого варианта нет!");
                        }
                    }
                }
                case 5-> {
                    System.out.println("Ввод нового тега");
                    System.out.println("Введите новый тег");
                    String tag = scanner.nextLine();
                    listtagsLogic.save(new Listtags(tag));
                }
                case 0-> {
                    break mainExit;
                }

                default -> System.out.println("Такого варианта нет!");
            }

        }
    }

    public static void consoleView()
    {
        System.out.println();
        System.out.println("Список функций:");
        System.out.println("1. Добавление записей");
        System.out.println("2. Удаление записей из таблицы");
        System.out.println("3. Редактирование записей");
        System.out.println("4. Поиск по заданию");
        System.out.println("5. Добавление тегов");
        System.out.println("0. Завершение\n");
    }
}
