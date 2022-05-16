INSERT INTO public.account(
    password, profile_picture,roles, username)
VALUES ('123', '123456','TEACHER','teacher1');

INSERT INTO public.account(
    password, profile_picture, roles, username)
VALUES ('123', '123456', 'TEACHER', 'teacher2');

INSERT INTO public.account(
    password, profile_picture, roles, username)
VALUES ('123', '123', 'STUDENT', 'student1');

INSERT INTO public.account(
    password, profile_picture,roles, username)
VALUES ('123', '123', 'STUDENT', 'student2');

INSERT INTO public.account(
    password, profile_picture, roles, username)
VALUES ('123', '123', 'STUDENT', 'student3');

INSERT INTO public.account(
    password, profile_picture, roles, username)
VALUES ('123', '123', 'STUDENT', 'student4');

INSERT INTO public.account(
    password, profile_picture, roles, username)
VALUES ('123', '123', 'STUDENT', 'student5');

INSERT INTO public.students(
    average_mark, nickname, student_id, id)
VALUES (8, 'student1', '12345678910112', 3);

INSERT INTO public.students(
    average_mark, nickname, student_id, id)
VALUES (6, 'student2','23412345682345', 4);

INSERT INTO public.students(
    average_mark, nickname, student_id, id)
VALUES (6, 'student3', '12345622910112', 5);

INSERT INTO public.students(
    average_mark, nickname, student_id, id)
VALUES (11, 'student4', '12345678910122', 6);

INSERT INTO public.students(
    average_mark, nickname, student_id, id)
VALUES (12, 'student5', '12345678910132', 7);

INSERT INTO public.teachers(
    id)
VALUES (1);

INSERT INTO public.teachers(
    id)
VALUES (2);

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (50, 'C1', 'des', '2019-04-28', '2019-04-28', 'course1', '1');

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (51, 'C2', 'des', '2020-04-28', '2020-04-28', 'course2', '2');

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (52, 'C3', 'des', '2019-04-30', '2019-06-18', 'course3', '1');

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (53, 'C4', 'des', '2019-04-28', '2019-04-28', 'course4', '2');

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (54, 'C5', 'des', '2019-04-28', '2019-04-28', 'course5', '1');

INSERT INTO public.courses(
    id, course_code, description, end_time, start_time, title, teacher_id)
VALUES (55, 'C6', 'des', '2019-04-28', '2019-04-28', 'course6', '2');

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (20, 'C1', 50, 3);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (21, 'C2', 51, 3);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (22, 'C1', 50, 4);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (23, 'C2', 51, 4);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (24, 'C1', 50, 5);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (25, 'C1', 50, 6);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (26, 'C2', 51, 6);

INSERT INTO public.enrollments(
    id, course_code, course_id, student_id)
VALUES (27, 'C2', 51, 5);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (30, 'a', '2019-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, TRUE, 6.5, 'https://s3.console.aws.amazon.com/s3/object/pengu-classroom?region=ap-southeast-1&prefix=submission/1651337964017-Dynamic_Programming.pdf', '2019-04-28T14:45:15', 'a', 20);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (31, 'a', '2019-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, FALSE, null, null, null, 'a', 22);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (32, 'a', '2019-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, FALSE, null, null, null, 'a', 24);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (33, 'a', '2019-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, FALSE, null, null, null, 'a', 25);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (34, 'b', '2019-04-28T14:45:15', 'a', 'ASSIGNMENT', FALSE, TRUE, TRUE, null, null,'2019-04-28T14:45:15', 'ab', 20);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (35, 'b', '2019-04-28T14:45:15', 'a', 'ASSIGNMENT', FALSE, TRUE, TRUE, null, null, '2019-04-28T14:45:15', 'ab', 22);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (36, 'b', '2019-04-28T14:45:15', 'a', 'ASSIGNMENT', FALSE, TRUE, TRUE, null, null,'2019-04-28T14:45:15', 'ab', 24);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (37, 'b', '2019-04-28T14:45:15', 'a', 'ASSIGNMENT', FALSE, TRUE, TRUE, null, null,'2019-04-28T14:45:15', 'ab', 25);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (38, 'c', '2023-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, TRUE, null, null,null, 'abc', 20);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (39, 'c', '2023-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, TRUE, null, null, null, 'abc', 22);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (40, 'c', '2023-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, TRUE, null, null,null, 'abc', 24);

INSERT INTO public.homeworks(
    id, description, due_date, feedbacks, type, is_graded, is_published, is_submitted, marks, submissions, submission_Time, titles, enrollment_id)
VALUES (41, 'c', '2023-04-28T14:45:15', 'a', 'EXAM', FALSE, TRUE, TRUE, null, null,null, 'abc', 25);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (80, 'MON', 'II', 50);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (81, 'MON', 'III', 50);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (82, 'MON', 'IV', 50);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (83, 'WED', 'III', 50);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (84, 'MON', 'III', 51);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (85, 'MON', 'III', 51);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (86, 'MON', 'III', 52);

INSERT INTO public.events(
    id, day, timezone, course_id)
VALUES (87, 'MON', 'III', 54);