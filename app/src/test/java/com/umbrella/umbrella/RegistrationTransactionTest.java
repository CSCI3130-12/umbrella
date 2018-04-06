package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationTransactionTest {
    @Test
    public void itCanBeCreated() {
        Student student = emptyStudent();
        RegistrationTransaction transaction = new RegistrationTransaction(student);
    }

    @Test
    public void emptyRegistrationIsValid() {
        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        assertTrue(transaction.isValid());
    }

    @Test
    public void youCantRegisterForTooManyCourses() {
        TestFactory factory = new TestFactory();
        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());

        for (int i  = 0; i < Student.MAX_COURSES + 1; i++) {
            transaction.addOperation(new RegisterForLectureOrLabOperation(factory.fakeLectureLab()));
        }

        assertFalse(transaction.isValid());
    }

    @Test
    public void youCantRegisterForACourseWithoutPrerequisites() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        course.addPrerequisite(factory.fakeCourse());
        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(
                factory.fakeLectureLabForCourse(course)
        ));

        assertFalse(transaction.isValid());
    }

    @Test
    public void youCanRegisterForACourseWithPrerequisites() {
        TestFactory factory = new TestFactory();

        Course preReq = factory.fakeCourse();
        Course newCourse = factory.fakeCourse();
        newCourse.addPrerequisite(preReq);

        Student student = new Student();
        student.addCredit(preReq);

        RegistrationTransaction transaction = new RegistrationTransaction(student);
        transaction.addOperation(new RegisterForLectureOrLabOperation(
                factory.fakeLectureLabForCourse(newCourse)
        ));

        assertTrue(transaction.isValid());
    }

    @Test
    public void youCantRegisterForACourseWithoutCoRequisites() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        course.addCorequisite(factory.fakeCourse());

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(
                    factory.fakeLectureLabForCourse(course)
        ));

        assertFalse(transaction.isValid());
    }

    @Test
    public void youCanRegisterForACourseWithCoRequisites() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        Course coReq = factory.fakeCourse();
        course.addCorequisite(coReq);

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(
                factory.fakeLectureLabForCourse(coReq)
        ));
        transaction.addOperation(new RegisterForLectureOrLabOperation(
                factory.fakeLectureLabForCourse(course)
        ));

        assertTrue(transaction.isValid());
    }

    @Test
    public void cantRegisterForCourseWithoutRequiredLab() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        RequiredLectureLabs requirements = new RequiredLectureLabs();
        requirements.addRequiredLabOption(factory.fakeLectureLabForCourse(course));
        course.setRequirements(requirements);

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(
                new RegisterForLectureOrLabOperation(factory.fakeLectureLabForCourse(course))
        );

        assertFalse(transaction.isValid());
    }

    @Test
    public void canRegisterForCourseWithRequiredLab() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        LectureLab requiredLab = factory.fakeLectureLabForCourse(course);
        RequiredLectureLabs requirements = new RequiredLectureLabs();
        requirements.addRequiredLabOption(requiredLab);
        course.setRequirements(requirements);

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(requiredLab));

        assertTrue(transaction.isValid());
    }

    @Test
    public void canRegisterForCourseWithAnyRequiredLab() {
        TestFactory factory = new TestFactory();

        Course course = factory.fakeCourse();
        LectureLab optionOne = factory.fakeLectureLabForCourse(course);
        LectureLab optionTwo = factory.fakeLectureLabForCourse(course);

        RequiredLectureLabs requirements = new RequiredLectureLabs();
        requirements.addRequiredLabOption(optionOne);
        requirements.addRequiredLabOption(optionTwo);
        course.setRequirements(requirements);

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(optionTwo));

        assertTrue(transaction.isValid());
    }

    @Test
    public void cantRegisterForLectureOrLabThatIsFull() {
        TestFactory factory = new TestFactory();
        LectureLab lab = factory.fakeLectureLab();
        lab.setStudentCount(lab.getMaxStudents());

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(
                new RegisterForLectureOrLabOperation(lab)
        );

        assertFalse(transaction.isValid());
    }

    @Test
    public void canRegisterForLectureOrLabThatIsntFull() {
        TestFactory factory = new TestFactory();
        LectureLab lab = factory.fakeLectureLab();
        lab.setStudentCount(lab.getMaxStudents() - 1);

        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        transaction.addOperation(new RegisterForLectureOrLabOperation(lab));

        assertTrue(transaction.isValid());
    }

    private Student emptyStudent() {
        return new Student(
                "John Doe",
                "hunter2",
                new CourseSet(),
                new LectureLabSet()
        );
    }
}