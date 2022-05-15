package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.StudyGroup;
import view.console.ConsoleClient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ParserCSV { // Подумать, как сделать обобщенный парсер
    public static String toCSV(Collection<StudyGroup> collection) {
        StringBuilder stringBuilder = new StringBuilder("");
        Object[] collArray = collection.toArray();
//        String[] header = makeStudyGroupHeader();
//        stringBuilder.append(Arrays.toString(header).replace("[","").replace("]",""));
//        stringBuilder.append("\n");
        for (Object obj : collArray) {
            stringBuilder.append(Arrays.toString(makeData(obj)).replace("[","").replace("]",""));
            stringBuilder.append("\n");
        }
        return String.valueOf(stringBuilder);
    }

    public static TreeSet<StudyGroup> readFile (String file){
        Comparator<StudyGroup> sgc = new StudyGroupComparator(); //Будут использовать в main, добовление коллекции через addAll
        TreeSet<StudyGroup> collection = new TreeSet<>(sgc);
        try(CSVReader reader = new CSVReader(new FileReader(file, StandardCharsets.UTF_8))){
            List<String[]> input = reader.readAll();
            input.forEach((object)-> {
                collection.add(new StudyGroup(object));
            });
        }catch (FileNotFoundException exception){
            ConsoleClient.printError("Файл не найден!");
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return collection;
    }

    public TreeSet<StudyGroup> csvFromData (String string){
        Comparator<StudyGroup> sgc = new StudyGroupComparator(); //Будут использовать в main, добовление коллекции через addAll
        TreeSet<StudyGroup> collection = new TreeSet<>(sgc);
        String[] bigData = string.split("\n");
        //String[] header = bigData[0].split(",");
        for (int i = 1; i< bigData.length; i++ ){
            String[] data = bigData[i].replace(" ","").split(",");
            collection.add(new StudyGroup(data));
        }
        return collection;
    }

//    public static  <T extends CreateObjectFromString> TreeSetCollection<T>  csvFromData (String string){
//        TreeSetCollection<T> collection = new TreeSetCollection<>();
//        String[] bigData = string.split("\n");
//        Constructor<T> constructor
//                = T.class.getDeclaredConstructor();
//        for (int i = 1; i< bigData.length; i++ ){
//            String[] data = bigData[i].replace(" ","").split(",");
//            collection.addCommand(createObject(T.class.getDeclaredConstructor(), fromStudyGroup(data)));
//        }
//        return collection;
//    }
//
//    private static <T> Object createObject(Constructor<T> constructor,
//                                      Object[] arguments) {
//
//        System.out.println("Constructor: " + constructor.toString());
//        Object object = null;
//
//        try {
//            object = constructor.newInstance(arguments);
//            System.out.println("Object: " + object.toString());
//            return object;
//        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
//            //handle it
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return object;
//    }
//
//    private static StudyGroupDTO fromStudyGroup (String[] string){
//        Object[] obj = new Object[10];
//        obj[0]= string[0];
//        obj[1]= Integer.parseInt(string[1]);
//        obj[2]= string[2];
//        obj[3]= new CoordinatesDTO(Integer.parseInt(string[3]), Integer.parseInt(string[4]));
//        obj[4]= LocalDate.parse(string[5]);
//        obj[5]= Long.parseLong(string[6]);
//        obj[6]= Double.parseDouble(string[7]);
//        obj[7]= FormOfEducationDTO.valueOf(string[8]);
//        obj[8]= SemesterDTO.valueOf(string[9]);
//        obj[9]= new PersonDTO(string[10], LocalDateTime.parse(string[11]), Float.parseFloat(string[12]), string[13]);
//        return new StudyGroupDTO(string[0],Integer.parseInt(string[1]),string[2],Integer.parseInt(string[3]),Integer.parseInt(string[4]),
//                LocalDate.parse(string[5]),Long.parseLong(string[6]),Double.parseDouble(string[7]),FormOfEducationDTO.valueOf(string[8]),
//                SemesterDTO.valueOf(string[9]),string[10],LocalDateTime.parse(string[11]),Float.parseFloat(string[12]),string[13]);
//    }

    private static String[] makeStudyGroupHeader(){
        return new String[]{"id",  "name",  "coordinatesX",  "coordinatesY",  "creationDate",
                "studentsCount",  "averageMark",  "formOfEducation",  "semesterEnum",  "groupAdmin",  "birthday",  "weight",  "passportID"};
    }

    private static String[] makeHeader(Object object) {
        String[] recordHd = object.toString().split(",");
        String[] header = new String[recordHd.length];
        for (int i = 0; i < recordHd.length; i++) {
            String[] sword = recordHd[i].split("=");
            header[i] = sword[0];
        }
        return header;
    }

    private static String[] makeData(Object object) {
        String[] recordDt = object.toString().split(",");
        String[] data = new String[13];
        for (int i = 0; i < recordDt.length; i++) {
            String[] sword = recordDt[i].split("=");
            data[i] = sword[sword.length -1] ;
        }
        for (int i = 0; i < data.length; i++){
            data[i] = "\"" + data[i] + "\"";
        }
        return data;
    }

}
