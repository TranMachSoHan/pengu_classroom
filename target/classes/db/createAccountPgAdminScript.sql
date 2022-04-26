	INSERT INTO public.account(
	id, password, profile_picture,role, username)
	VALUES (1, '123', '123','TEACHER','teacher1');

INSERT INTO public.account(
	id, password, profile_picture, role, username)
	VALUES (2, '123', '123', 'TEACHER', 'teacher2');

INSERT INTO public.account(
	id, password, profile_picture, role, username)
	VALUES (3, '123', '123', 'STUDENT', 'student1');

INSERT INTO public.account(
	id, password, profile_picture,role, username)
	VALUES (4, '123', '123', 'STUDENT', 'student2');

INSERT INTO public.students(
	average_mark, nickname, id)
	VALUES (8, 'student1', 3);

INSERT INTO public.students(
	average_mark, nickname, id)
	VALUES (8, 'student2', 4);

INSERT INTO public.teachers(
	id)
	VALUES (1);

INSERT INTO public.teachers(
	id)
	VALUES (2);

INSERT INTO public.courses(
	id, course_code, description, end_time, start_time, title, teacher_id)
	VALUES (5, 'C1', 'des', '2019-04-28T14:45:15', '2019-04-28T14:45:15', 'course1', '1');

INSERT INTO public.courses(
	id, course_code, description, end_time, start_time, title, teacher_id)
	VALUES (6, 'C2', 'des', '2019-04-28T14:45:15', '2019-04-28T14:45:15', 'course2', '2');

INSERT INTO public.enrollments(
	id, course_code, course_id, student_id)
	VALUES (10, 'C1', 5, 3);

INSERT INTO public.enrollments(
	id, course_code, course_id, student_id)
	VALUES (11, 'C1', 5, 4);
--
-- INSERT INTO public.submission(
-- 	id, data, name, type)
-- 	VALUES (50, 1, 'Sub1', 'word');
--
-- INSERT INTO public.submission(
-- 	id, data, name, type)
-- 	VALUES (51, 2, 'Sub2', 'word');

-- INSERT INTO public.homeworks(
-- 	id, description, due_date, feedbacks, is_submitted, marks, titles, enrollment_id, submission_id)
-- 	VALUES (31, 'a', '2019-04-28T14:45:15', 'a', TRUE, 4, 'h1', 11, 51);
--
-- INSERT INTO public.homeworks(
-- 	id, description, due_date, feedbacks, is_submitted, marks, titles, enrollment_id, submission_id)
-- 	VALUES (30, 'a', '2019-04-28T14:45:15', 'a', TRUE, 4, 'h1', 10, 50);