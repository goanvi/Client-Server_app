package controller;

import controller.exceptions.EmptyCollectionException;
import model.Semester;
import model.StudyGroup;
import view.console.ConsoleClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class CollectionManager { //Надо будет дописать loadCollection
    private final StudyGroupComparator comparator = new StudyGroupComparator();
    private TreeSet<StudyGroup> studyGroupCollection = new TreeSet<>(comparator);
//    FileWorker fileWorker;
    LocalDateTime lastSaveTime;
    LocalDateTime lastLoadTime;

    public CollectionManager(String readFile) {
//        this.fileWorker = new FileWorker(readFile);
        loadCollection(readFile);
        for (StudyGroup group: studyGroupCollection){
            IdManager.saveStudyGroupID(group.getID());
        }
    }

    public void clearCollection() {
        if (studyGroupCollection.size() == 0) ConsoleClient.println("Коллекция и так пустая");
        studyGroupCollection.clear();
    }

    public void addToCollection(StudyGroup studyGroup) {
        studyGroupCollection.add(studyGroup);
    }

    public void addCollection(TreeSet<StudyGroup> collection) {
        this.studyGroupCollection = collection;
    }

    public void removeGreater(StudyGroup studyGroup) throws EmptyCollectionException {
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
//        for (StudyGroupDTO group : studyGroupCollection) {
//            if (group.compareTo(studyGroup) > 0) {
//                IdManager.removeStudyGroupID(group.getID());
//                studyGroupCollection.remove(group);
//            }
//        }
        Iterator<StudyGroup> iterator = studyGroupCollection.iterator();
        while(iterator.hasNext()){
            StudyGroup group = iterator.next();
            if (group.compareTo(studyGroup)>0){
                IdManager.removeStudyGroupID(group.getID());
                iterator.remove();
            }
        }
    }

    public void removeLower(StudyGroup studyGroup) throws EmptyCollectionException{
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
//        for (StudyGroupDTO group : studyGroupCollection) {
//            if (group.compareTo(studyGroup) < 0) {
//                IdManager.removeStudyGroupID(group.getID());
//                studyGroupCollection.remove(group);
//            }
//        }
        Iterator<StudyGroup> iterator = studyGroupCollection.iterator();
        while(iterator.hasNext()){
            StudyGroup group = iterator.next();
            if (group.compareTo(studyGroup)<0){
                IdManager.removeStudyGroupID(group.getID());
                iterator.remove();
            }
        }
    }
//    public void saveCollection() throws IOException,SecurityException {
//        if (studyGroupCollection.size() == 0) ConsoleClient.println("Коллекция пуста");
//        fileWorker.writer(ParserCSV.toCSV(studyGroupCollection));
//        lastSaveTime = LocalDateTime.now();
//    }
//
//    public void saveCollection(String file) throws IOException,SecurityException {
//        if (studyGroupCollection.size() == 0) ConsoleClient.println("Коллекция пуста");
//        fileWorker.writer(ParserCSV.toCSV(studyGroupCollection),file);
//        lastSaveTime = LocalDateTime.now();
//    }

    public void loadCollection(String file) {
        studyGroupCollection = ParserCSV.readFile(file);
        lastLoadTime = LocalDateTime.now();
    }

    public StudyGroup getAnyBySemesterEnum(Semester semester) throws EmptyCollectionException{
        if (studyGroupCollection.isEmpty()) throw new EmptyCollectionException();
        for (StudyGroup studyGroup : studyGroupCollection) {
            if (studyGroup.getSemesterEnum().equals(semester)) {
                return studyGroup;
            }
        }
        return null; // вывести ошибку если приходит null
    }

    public StudyGroup getByValue(StudyGroup studyGroup) throws EmptyCollectionException{
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
        for (StudyGroup group : studyGroupCollection) {
            if (group.equals(studyGroup)) return group;
        }
        return null;
    }

    public StudyGroup getByID(Integer id) throws EmptyCollectionException{
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
        for (StudyGroup studyGroup : studyGroupCollection) {
            if (studyGroup.getID().equals(id)) return studyGroup;
        }
        return null;
    }

    public long getSumOfStudentsCount() throws EmptyCollectionException{
        long sum = 0;
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
        for (StudyGroup group : studyGroupCollection) {
            sum += group.getStudentsCount();
        }
        return sum;
    }

    public List<StudyGroup> getLessThanStudentsCount(long studCount) throws EmptyCollectionException{
        List<StudyGroup> groups = new ArrayList<>();
        if (studyGroupCollection.size() == 0) throw new EmptyCollectionException();
        for (StudyGroup group : studyGroupCollection) {
            if (group.getStudentsCount() < studCount) {
                groups.add(group);
            }
        }
        return groups; // обработать если приходит пустая колллекция
    }

    public void remove(StudyGroup studyGroup){
        studyGroupCollection.remove(studyGroup);
    }

    public TreeSet<StudyGroup> getCollection() {
        return studyGroupCollection;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(){
        lastSaveTime = LocalDateTime.now();
    }

    public String getCollectionType() {
        return studyGroupCollection.getClass().getName();
    }

    public int getCollectionSize() {
        return studyGroupCollection.size();
    }

    public LocalDateTime getLastLoadTime() {
        return lastLoadTime;
    }
}
