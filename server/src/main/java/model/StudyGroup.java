package model;

import controller.IdManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class StudyGroup implements Serializable{ //Потребуется переделать конструкторы для корректной работы по ТЗ
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private double averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name, Coordinates coordinates, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, String adminName,
                      String adminBirthday, float adminWeight) {
        this.id = IdManager.setStudyGroupID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm"); //Доделать День рождения админа, придумать, как сделать запись проще
        this.groupAdmin = new Person(adminName, LocalDateTime.parse(adminBirthday, dtf), adminWeight);
        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroup(String name, Coordinates coordinates, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, Person person) {
        this.id = IdManager.setStudyGroupID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = person;
        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroup(int id,String name, Coordinates coordinates, long studentsCount,
                      double averageMark, FormOfEducation formOfEducation, Semester semesterEnum, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = person;
        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroup(String[] string) {
        this.id = Integer.parseInt(string[0]);
        this.name = string[1];
        this.coordinates = new Coordinates(Integer.parseInt(string[2]), Integer.parseInt(string[3]));
        this.creationDate = LocalDate.parse(string[4]);
        this.studentsCount = Long.parseLong(string[5]);
        this.averageMark = Double.parseDouble(string[6]);
        this.formOfEducation = FormOfEducation.valueOf(string[7]);
        this.semesterEnum = Semester.valueOf(string[8]);
        if (!string[9].equals("null")) {
            if (!string[10].equalsIgnoreCase("null")) {
                this.groupAdmin = new Person(string[9],
                        LocalDateTime.parse(string[10]), Float.parseFloat(string[11]), string[12]);
            } else {
                this.groupAdmin = new Person(string[9],
                        null, Float.parseFloat(string[11]), string[12]);
            }

        } else groupAdmin = null;
    }

    public double compareTo (StudyGroup studyGroup){
        return averageMark-studyGroup.getAverageMark();
    }

    public Integer getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public Semester getSemesterEnum(){
        return semesterEnum;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String toString() {
        if (groupAdmin == null) {
            return  "id=" + id +
                    ", name=" + name +
                    ", coordinatesX=" + coordinates.getX() +
                    ", coordinatesY=" + coordinates.getY() +
                    ", creationDate=" + creationDate +
                    ", studentsCount=" + studentsCount +
                    ", averageMark=" + averageMark +
                    ", formOfEducation=" + formOfEducation +
                    ", semesterEnum=" + semesterEnum +
                    ", groupAdmin=null";
        } else {
            return  "id=" + id +
                    ", name=" + name +
                    ", coordinatesX=" + coordinates.getX() +
                    ", coordinatesY=" + coordinates.getY() +
                    ", creationDate=" + creationDate +
                    ", studentsCount=" + studentsCount +
                    ", averageMark=" + averageMark +
                    ", formOfEducation=" + formOfEducation +
                    ", semesterEnum=" + semesterEnum +
                    ", groupAdmin=" + groupAdmin.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup group = (StudyGroup) o;
        return id == group.id && studentsCount == group.studentsCount &&
                Double.compare(group.averageMark, averageMark) == 0 && name.equals(group.name) &&
                coordinates.equals(group.coordinates) && creationDate.equals(group.creationDate) &&
                formOfEducation == group.formOfEducation && semesterEnum == group.semesterEnum &&
                Objects.equals(groupAdmin, group.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate,
                studentsCount, averageMark, formOfEducation, semesterEnum, groupAdmin);
    }
}

