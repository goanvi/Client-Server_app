package dto;

//import controller.IdManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudyGroupDTO implements Serializable{ //Потребуется переделать конструкторы для корректной работы по ТЗ
//    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesDTO coordinatesDTO; //Поле не может быть null
//    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private double averageMark; //Значение поля должно быть больше 0
    private FormOfEducationDTO formOfEducationDTO; //Поле может быть null
    private SemesterDTO semesterDTOEnum; //Поле не может быть null
    private PersonDTO groupAdmin; //Поле может быть null

    public StudyGroupDTO(String name, CoordinatesDTO coordinatesDTO, long studentsCount,
                         double averageMark, FormOfEducationDTO formOfEducationDTO, SemesterDTO semesterDTOEnum, String adminName,
                         String adminBirthday, float adminWeight) {
//        this.id = IdManager.setStudyGroupID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinatesDTO = coordinatesDTO;
//        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducationDTO = formOfEducationDTO;
        this.semesterDTOEnum = semesterDTOEnum;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm"); //Доделать День рождения админа, придумать, как сделать запись проще
        this.groupAdmin = new PersonDTO(adminName, LocalDateTime.parse(adminBirthday, dtf), adminWeight);
//        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroupDTO(String name, CoordinatesDTO coordinatesDTO, long studentsCount,
                         double averageMark, FormOfEducationDTO formOfEducationDTO, SemesterDTO semesterDTOEnum, PersonDTO personDTO) {
//        this.id = IdManager.setStudyGroupID(Math.abs(UUID.randomUUID().hashCode()));
        this.name = name;
        this.coordinatesDTO = coordinatesDTO;
//        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducationDTO = formOfEducationDTO;
        this.semesterDTOEnum = semesterDTOEnum;
        this.groupAdmin = personDTO;
//        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroupDTO(int id, String name, CoordinatesDTO coordinatesDTO, long studentsCount,
                         double averageMark, FormOfEducationDTO formOfEducationDTO, SemesterDTO semesterDTOEnum, PersonDTO personDTO) {
//        this.id = id;
        this.name = name;
        this.coordinatesDTO = coordinatesDTO;
//        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.averageMark = averageMark;
        this.formOfEducationDTO = formOfEducationDTO;
        this.semesterDTOEnum = semesterDTOEnum;
        this.groupAdmin = personDTO;
//        IdManager.saveStudyGroupID(this.id);
    }

    public StudyGroupDTO(String[] string) {
//        this.id = Integer.parseInt(string[0]);
        this.name = string[1];
        this.coordinatesDTO = new CoordinatesDTO(Integer.parseInt(string[2]), Integer.parseInt(string[3]));
//        this.creationDate = LocalDate.parse(string[4]);
        this.studentsCount = Long.parseLong(string[5]);
        this.averageMark = Double.parseDouble(string[6]);
        this.formOfEducationDTO = FormOfEducationDTO.valueOf(string[7]);
        this.semesterDTOEnum = SemesterDTO.valueOf(string[8]);
        if (!string[9].equals("null")) {
            if (!string[10].equalsIgnoreCase("null")) {
                this.groupAdmin = new PersonDTO(string[9],
                        LocalDateTime.parse(string[10]), Float.parseFloat(string[11]), string[12]);
            } else {
                this.groupAdmin = new PersonDTO(string[9],
                        null, Float.parseFloat(string[11]), string[12]);
            }

        } else groupAdmin = null;
    }

    public StudyGroupDTO (){
        this.name = null;
        this.coordinatesDTO = null;
        this.studentsCount = 0;
        this.averageMark = 0;
        this.formOfEducationDTO = null;
        this.semesterDTOEnum = null;
        this.groupAdmin = null;
    }

//    public Integer getID(){
//        return id;
//    }

    public String getName() {
        return name;
    }

    public CoordinatesDTO getCoordinates() {
        return coordinatesDTO;
    }

//    public LocalDate getCreationDate() {
//        return creationDate;
//    }

    public FormOfEducationDTO getFormOfEducation() {
        return formOfEducationDTO;
    }

    public PersonDTO getGroupAdmin() {
        return groupAdmin;
    }

    public SemesterDTO getSemesterEnum(){
        return semesterDTOEnum;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public double getAverageMark() {
        return averageMark;
    }

//    public void setId(Integer id){
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(CoordinatesDTO coordinatesDTO) {
        this.coordinatesDTO = coordinatesDTO;
    }

//    public void setCreationDate(LocalDate creationDate) {
//        this.creationDate = creationDate;
//    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public void setFormOfEducation(FormOfEducationDTO formOfEducationDTO) {
        this.formOfEducationDTO = formOfEducationDTO;
    }

    public void setSemesterEnum(SemesterDTO semesterDTOEnum) {
        this.semesterDTOEnum = semesterDTOEnum;
    }

    public void setGroupAdmin(PersonDTO groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

//    @Override
//    public String toString() {
//        if (groupAdmin == null) {
//            return
//                    "id=" + id +
//                    ", name=" + name +
//                    ", coordinatesX=" + coordinatesDTO.getX() +
//                    ", coordinatesY=" + coordinatesDTO.getY() +
//                    ", creationDate=" + creationDate +
//                    ", studentsCount=" + studentsCount +
//                    ", averageMark=" + averageMark +
//                    ", formOfEducationDTO=" + formOfEducationDTO +
//                    ", semesterDTOEnum=" + semesterDTOEnum +
//                    ", groupAdmin=null";
//        } else {
//            return
//                    "id=" + id +
//                    ", name=" + name +
//                    ", coordinatesX=" + coordinatesDTO.getX() +
//                    ", coordinatesY=" + coordinatesDTO.getY() +
//                    ", creationDate=" + creationDate +
//                    ", studentsCount=" + studentsCount +
//                    ", averageMark=" + averageMark +
//                    ", formOfEducationDTO=" + formOfEducationDTO +
//                    ", semesterDTOEnum=" + semesterDTOEnum +
//                    ", groupAdmin=" + groupAdmin.toString();
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StudyGroupDTO group = (StudyGroupDTO) o;
//        return
////                id == group.id &&
//                studentsCount == group.studentsCount &&
//                Double.compare(group.averageMark, averageMark) == 0 && name.equals(group.name) &&
//                coordinatesDTO.equals(group.coordinatesDTO) && creationDate.equals(group.creationDate) &&
//                formOfEducationDTO == group.formOfEducationDTO && semesterDTOEnum == group.semesterDTOEnum &&
//                Objects.equals(groupAdmin, group.groupAdmin);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(
////                id,
//                name, coordinatesDTO, creationDate,
//                studentsCount, averageMark, formOfEducationDTO, semesterDTOEnum, groupAdmin);
//    }
}

