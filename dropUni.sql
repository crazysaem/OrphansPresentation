ALTER TABLE dependency DROP CONSTRAINT FK_dependency_successor_id
ALTER TABLE dependency DROP CONSTRAINT FK_dependency_predecessor_id
ALTER TABLE lecture DROP CONSTRAINT FK_lecture_PROFESSOR_ID
ALTER TABLE assistant DROP CONSTRAINT FK_assistant_ID
ALTER TABLE assistant DROP CONSTRAINT FK_assistant_PROFESSOR_ID
ALTER TABLE participation DROP CONSTRAINT FK_participation_student_id
ALTER TABLE participation DROP CONSTRAINT FK_participation_lecture_id
ALTER TABLE professor DROP CONSTRAINT FK_professor_ID
ALTER TABLE exam DROP CONSTRAINT FK_exam_lecture_id
ALTER TABLE exam DROP CONSTRAINT FK_exam_PROFESSOR_ID
ALTER TABLE exam DROP CONSTRAINT FK_exam_student_id
DROP TABLE dependency
DROP TABLE lecture
DROP TABLE assistant
DROP TABLE student
DROP TABLE participation
DROP TABLE professor
DROP TABLE employee
DROP TABLE exam
DROP SEQUENCE UNI.GlobalSequence
