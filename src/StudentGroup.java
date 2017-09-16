import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * <p>
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 */
public class StudentGroup implements StudentArrayOperation {

    private Student[] students;
    public StudentGroup(){}
    /**
     * DO NOT remove or change this constructor, it will be used during task check
     *
     * @param length
     */
    public StudentGroup(int length) {
        this.students = new Student[length];
    }


    @Override
    public Student[] getStudents() {
        // Add your implementation here
        return students;
    }

    @Override
    public void setStudents(Student[] students) {
        // Add your implementation here
        if (students == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < students.length; i++)
            this.students[i] = students[i];

    }

    @Override
    public Student getStudent(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        else
            return students[index];
    }

    @Override
    public void setStudent(Student student, int index) {
        // Add your implementation here
        if (student == null || index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        else
            students[index] = student;

    }

    @Override
    public void addFirst(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        Student newList[] = new Student[students.length+1];
        newList[0] = student;
        for (int i = 0; i < students.length; i++) {
            newList[i+1] = students[i];
        }
        students = newList;
    }

    @Override
    public void addLast(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        Student newList[] = new Student[students.length+1];
        for (int i = 0; i < students.length; i++) {
            newList[i] = students[i];
        }
        newList[students.length] = student;
        students = newList;
    }

    @Override
    public void add(Student student, int index) {
        // Add your implementation here
        if (student == null || index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        Student newList[] = new Student[students.length+1];
        for (int i = 0; i < index; i++) {
            newList[i] = students[i];
        }
        newList[index] = student;
        for (int i = index ; i < students.length; i++) {
            newList[i+1] = students[i];
        }
        students = newList;
    }

    @Override
    public void remove(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        Student newList[] = new Student[students.length-1];
        int j=0;
        for (int i = 0; i < students.length; i++) {
            if (i != index) {
                newList[j] = students[i];
                j++;
            }
        }
        students = newList;
    }

    @Override
    public void remove(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(student)) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public void removeFromIndex(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        Student newList[] = new Student[index];
        for (int i = 0; i < index; i++) {
            newList[i] = students[i];
        }
        students = newList;
    }

    @Override
    public void removeFromElement(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(student)) {
                removeFromIndex(i);
                break;
            }
        }
    }

    @Override
    public void removeToIndex(int index) {
        // Add your implementation here
        if (index < 0 || index >= students.length)
            throw new IllegalArgumentException();
        int newLen = students.length - index -1;
        Student newList[] = new Student[newLen];
        int j=0;
        for (int i = index +1 ; i < students.length ; i++) {
            newList[j] = students[i];
            j++;
        }
        students = newList;
    }

    @Override
    public void removeToElement(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(student)) {
                removeToIndex(i);
                break;
            }
        }
    }

    @Override
    public void bubbleSort() {
        // Add your implementation here
        int n = students.length;
        Student temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (students[j].compareTo(students[i]) == -1) {
                    temp = students[j];
                    students[j] = students[i];
                    students[i] = temp;
                }
            }
        }
    }

    @Override
    public Student[] getByBirthDate(Date date) {
        // Add your implementation here
        Student[] st3 = new Student[students.length];
        if (date == null)
            throw new IllegalArgumentException();
        int j = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            if (students[i].getBirthDate().equals(date)) {
                st3[j] = students[i];
                j++;
            }
        }
        return st3;
    }


    @Override
    public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
        // Add your implementation here
        Student[] st1 = new Student[students.length];
        if (firstDate == null || lastDate == null)
            throw new IllegalArgumentException();
        int j = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            Date dt = students[i].getBirthDate();
            if (dt.after(firstDate)) {
                if (dt.equals(lastDate) && dt.before(lastDate)) {
                    st1[j] = students[i];
                    j++;
                }
            }
        }
        return st1;
    }

    @Override
    public Student[] getNearBirthDate(Date date, int days) {
        // Add your implementation here
        Student[] st2 = new Student[students.length];
        if (date == null)
            throw new IllegalArgumentException();
        int j = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            Date dt = students[i].getBirthDate();
            if (dt.equals(date)) {
                st2[j] = students[i];
                j++;
            }
            long diff = Math.abs(dt.getTime() - date.getTime());
            long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (days <= diffDays) {
                st2[j] = students[i];
                j++;
            }
        }
        return st2;
    }


    @Override
    public int getCurrentAgeByDate(int indexOfStudent) {
        // Add your implementation here
        int age=0;
        if (indexOfStudent == 0)
            throw new IllegalArgumentException();
        else {
            Calendar t = Calendar.getInstance();
            int cy = t.get(Calendar.YEAR);
            int dy = students[indexOfStudent].getBirthDate().getYear();
            age = cy - dy;
            int cm = t.get(Calendar.MONTH);
            int dm = students[indexOfStudent].getBirthDate().getMonth();
            if (dm > cm)
                age--;
            else if (dm == cm) {
                int cd = t.get(Calendar.DAY_OF_MONTH);
                int dd = students[indexOfStudent].getBirthDate().getDay();
                if (dd > cd)
                    age--;
            }
        }
        return age;
    }

    @Override
    public Student[] getStudentsByAge(int age) {
        // Add your implementation here
        Student[] st = new Student[students.length];
        int j = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            int reqage = getCurrentAgeByDate(i);
            if (reqage == age) {
                st[j] = students[i];
                j++;
            }
        }
        return st;
    }

    @Override
    public Student[] getStudentsWithMaxAvgMark() {
        // Add your implementation here
        List<Student> temp = new ArrayList<>();

        double max = -1.0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getAvgMark() > max) {
                max = students[i].getAvgMark();
            }
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i].getAvgMark() == max) {
                temp.add(students[i]);
            }
        }
        Student[] studentsList = new Student[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            studentsList[i] = temp.get(i);
        }
        return studentsList;
    }

    @Override
    public Student getNextStudent(Student student) {
        // Add your implementation here
        if (student == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(student)) {
                return students[i+1];
            }
        }
        return null;
    }
}
