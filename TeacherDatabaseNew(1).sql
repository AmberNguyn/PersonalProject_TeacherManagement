INSERT INTO nationality(country_code, country, nationality)
VALUES
('US', 'United States', 'American'),
('VN', 'Vietnam', 'Vietnamese'),
('CA', 'Canada', 'Canadian'),
('UK-SC', 'United Kingdom - Scotland', 'Scottish'),
('UK-BR', 'United Kingdom - Britain', 'British'),
('UK-IR', 'United Kingdom - Ireland', 'Irish'),
('UK-W', 'United Kingdom - Wales', 'Welsh');


INSERT INTO teacher(	employee_code, first_name, middle_name, last_name, 
						date_of_birth, phone_number, address, private_email,
						school_email, teacher_type, gender, degree, nationality_id)
VALUES 
('ANH.N6', 'Anh', 'Ngoc Truc', 'Nguyen', '1994-0s6-06', '0903553257', '1A Avenue Binh Tan district', 'anh.nguyen@gmail.com', 'anh.n6@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - English Teaching', 2),
('KHANH.T2', 'Khanh', 'Ngoc', 'Tran', '1997/04/30', '0815488295', '132/2 Nguyen Trai Street, D1', 'ngockhanh23@gmail.com', 'khanh.t2@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - Language Teaching', 2),
('GARLEN.S1', 'Garlen', 'John', 'Smith', '1990/06/14', '0905443867', 'Moonlight Apartment', 'garlen234@gmail.com', 'garlen.s1@gmail.com', 'EXPATRIATE', 'MALE', 'Bachelor of Art', 1),
('JOANNA.P3', 'Joanna', 'Lina', 'Potts', '1990/07/18', '0903451123', 'Moonlight Apartment', 'joanna.teaching@gmail.com', 'joanna.p3@gmail.com', 'EXPATRIATE', 'FEMALE', 'Bachelor of Art', 4),
('TU.N2', 'Tu', 'Thanh', 'Nguyen', '1982-05-12', '0905436440', '124/2 Ngo Gia Tu Street, D5', 'thanhtu.teaching@gmail.com', 'tu.n2@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - Language teaching', 2),
('ANH.P1', 'Anh', null, 'Phan', '1990-10-24', '0902468312', '432 Kinh Duong Vuong Street, D6', 'anhphan24@gmail.com', 'anh.p1@gmail.com', 'VIETNAMESE', 'MALE', 'Master of Art in TESOL', 2),
('SEAN.P1', 'Sean', null, 'Pratt', '1983-05-14', '0904346923', 'Summerset Apartment, D6', 'SeanPratt245@gmail.com', 'sean.p1@gmail.com', 'EXPATRIATE', 'MALE', 'Bachelor of Art', 6),
('TONI.S2', 'Toni', null, 'Strictland', '1985-09-18', '0906472056', 'Starlight Aparment, D6', 'toniStrictland.teaching@gmail.com', 'tony.s2', 'EXPATRIATE', 'MALE', 'Bachelor of Art', 3),
('HUNG.P3', 'Hung', 'Phuc', 'Phan', '1993-03-16', '0903458234', '12 Tan Hoa Dong Street, D6', 'PhucHung223@gmail.com', 'hung.p3@gmail.com', 'VIETNAMESE', 'MALE', 'Bachelor of Art - Language Teaching', 2),
('LINH.T13', 'Linh', 'Khanh', 'Tran', '1998-06-23', '0901245964', '23/3 Hau Giang Street, D6', 'linh.working@gmail.com', 'linh.t13@gmail.com', 'VIETNAMESE', 'FEMALE', 'Bachelor of Art - Foreign Language', 2);
 
INSERT INTO certification(name)
VALUES
('TESOL'),
('TEFL'),
('TESL'),
('CELTA'),
('DELTA'),
('IELTS'),
('TOEFL');


INSERT INTO certification_detail(score, issued_date, expired_date, description, teacher_id, certification_id)
VALUES
(9.0, '2020-05-06', '2023-05-06', 'Rank: average', 												2,	1),
(8.0, '2021-04-05', '2023-04-05', 'Listening: 8.0, Reading: 8.0, Speaking 8.0, Writing 8.0', 	2,	6),
(8.5, '2021-12-04', '2023-12-04', 'Rank: good', 												1,	1),
(7.5, '2021-12-21', '2023-12-21', 'Rank: average', 												3,	1),
(7.5, '2021-05-23', '2023-05-23', 'Rank: good', 												4,	2),
(7.5, '2020-07-07', '2022-07-07', 'Rank: good',													5,	1),
(6.5, '2020-06-02', '2022-06-02', 'Listening: 7.0, Reading: 7.0, Speaking 6.5, Writing 6.5',	5,	6),
(8.0, '2019-05-07', '2021-05-07', 'Rank: good',													6,	2),
(8.5, '2021-06-07', '2023-06-07', 'Listening: 9.0, Reading: 9.0, Speaking 8.5, Writing 8.5',	6,	6),
(7.0, '2020-05-23', '2022-05-23', 'Rank: average', 												7,	4),
(8.0, '2020-03-12', '2022-03-12', 'Rank: good', 												8,	3),
(7.0, '2020-12-06', '2022-12-06', 'Rank: average', 												9,	2),
(7.0, '2020-11-21', '2022-11-21', 'Rank: average', 												10,	1),
(6.5, '2021-12-12', '2022-12-12', 'Listening: 9.0, Reading: 9.0, Speaking 8.5, Writing 8.5',	10,	7);


INSERT INTO contract(	contract_id, start_date, end_date, pay_rate, account_number,
						bank, branch, account_name, description, is_signed, teacher_id)
VALUES
('VN1102A', '2022-12-04', '2023-12-04', 180000, '10002406931', 'Vietcombaank', 'HCM', 'Nguyen Ngoc Truc Anh', '1 year contract for kid and young learner classes', true, 1),

('VN1103A', '2022-02-04', '2023-02-04', 180000, '10005394572', 'Vietcombank', 'HCM', 'Tran Ngoc Khanh', '1 year contract for kid and young learner classes', true, 2),
('VN1103B', '2022-02-04', '2023-02-04', 300000, '10005394572', 'Vietcombank', 'HCM', 'Tran Ngoc Khanh', '1 year contract for IELTS classes', true, 2),

('FR1201', '2022-03-12', '2023-03-12', 500000, '10005739503', 'Vietcombank', 'HCM', 'Garlen John Smith', '1 year contract for kid and young learn, adult, ielts classes', true, 3),

('FR1202', '2022-04-17', '2023-04-17', 480000, '10005403856', 'Vietcombank', 'HCM', 'Joanna Lina Potts', '1 year contract for kid and young learn, adult, ielts classes', true, 4),

('VN1104A', '2021-07-17', '2022-07-17', 175000, '10006930222', 'Vietcombank', 'HCM', 'Nguyen Thanh Tu', '1 year contract for kid and young learner classes', true, 5),
('VN1104B', '2021-07-17', '2022-07-17', 300000, '10006930222', 'Vietcombank', 'HCM', 'Nguyen Thanh Tu', '1 year contract for IELTS classes', true, 5),

('VN1105A', '2021-06-15', '2022-06-15', 190000, '10005493056', 'Vietcombank', 'HCM', 'Phan Anh', '1 year contract for kid and young learner classes', true, 6),
('VN1105B', '2021-06-15', '2022-06-15', 310000, '10005493056', 'Vietcombank', 'HCM', 'Phan Anh', '1 year contract for IELTS classes', true, 6),

('VN1106A', '2022-06-17', '2023-06-17', 195000, '10005493056', 'Vietcombank', 'HCM', 'Phan Anh', '1 year contract for kid and young learner classes', false, 6),
('VN1106B', '2022-06-17', '2023-06-17', 310000, '10005493056', 'Vietcombank', 'HCM', 'Phan Anh', '1 year contract for IELTS classes', false, 6),

('FR1203', '2022-03-17', '2023-03-17', 420000, '100054355535', 'Vietcombank', 'HCM', 'Sean Pratt', '1 year contract for kid and young learn, adult, ielts classes', true, 7),

('FR1204', '2021-06-19', '2021-06-19', 480000, '100045357886', 'Vietcombank', 'HCM', 'Toni Strictland', '1 year contract for kid and young learn, adult, ielts classes', true, 8),
('FR1205', '2022-06-21', '2022-06-21', 480000, '100045357886', 'Vietcombank', 'HCM', 'Toni Strictland', '1 year contract for kid and young learn, adult, ielts classes', false, 8),


('VN1107A', '2022-01-03', '2023-01-03', 180000, '10004324534', 'Vietcombank', 'HCM', 'Phan Phuc Hung', '1 year contract for kid and young learner classes', true, 9),


('VN1108A', '2022-02-17', '2023-02-17', 175000, '10004324554', 'Vietcombank', 'HCM', 'Tran Khanh Linh', '1 year contract for kid and young learner classes', true, 10),
('VN1108B', '2022-02-17', '2023-02-17', 290000, '10004324554', 'Vietcombank', 'HCM', 'Tran Khanh Linh', '1 year contract for IELTS classes', true, 10);




INSERT INTO clazz(class_id, number_of_student, start_date, end_date, total_course_hours, course_book)
VALUES
--in progress
('SKA1-Aa1401', 	20, '2022-03-30', '2022-06-30', 48, 'LOOK 1'), 
('SKA2-Aa1402', 	18, '2022-03-30', '2022-06-30', 48, 'LOOK 1'),
('SKB1-Ab1408', 	19, '2022-04-17', '2022-07-17', 48, 'LOOK 3'),
('IELTS5.0-D2034', 	10, '2022-04-16', '2022-07-16', 72, 'IELTS Trainer 1'),

('YI1-D1610',		15, '2022-05-12', '2022-08-12', 72, 'Solution Pre-intermediate'), 
('YI3-D1621',		16, '2022-06-26', '2022-09-26', 72, 'Solution Intermediate'),
('SKB3-Ab1409', 	21, '2022-04-12', '2022-07-12', 48, 'LOOK 4'),
('SKC4-Aa1432', 	16, '2022-05-01', '2022-08-01', 48, 'LOOK 6'),
('IELTS5.0-D2032', 	11, '2022-06-01', '2022-09-01', 72, 'IELTS Trainer 1'),
('IELTS6.5-D2043', 	12, '2022-06-08', '2022-09-08', 72, 'IELTS Trainer 2'),
('YI3-D1623', 		14, '2022-06-09', '2022-09-08', 72, 'Solution Intermidiate'),

-- new classes
('SKC1-D1407', 17, '2022-07-01', '2022-10-01', 48, 'LOOK 5'),
('IELTS6.5A', 11, '2022-07-16', '2022-10-16', 72, 'IELTS Trainer 2'),
('YE1-D1612', 15, '2022-07-03', '2022-10-03', 72, 'Motivate 1'),
('SKC2-D1423', 15, '2022-07-03', '2022-10-03', 48, 'LOOK 5'),
--finished
('YI1-D1609', 16, '2022-03-14', '2022-06-14', 72, 'Solution Pre-Intermediate'),
('SKB3-Aa1407', 15, '2022-03-12', '2022-06-12', 48, 'LOOK 4'),
('IELTS6.5-D2023', 	10, '2022-03-03', '2022-06-03', 72, 'IELTS Trainer 2');

INSERT INTO assignment_detail(course_start_date, course_end_date, expected_hours, active_hours, leave_note, pay_rate, contract_id, clazz_id)
VALUES
('2022-03-30', '2022-06-30', 28, 28, null, 180000, 1, 1),
('2022-03-30', '2022-06-30', 20, 20, null, 500000, 4, 1),

('2022-03-30', '2022-06-30', 28, 28, null, 180000, 2, 2),
('2022-03-30', '2022-06-30', 20, 20, null, 500000, 4, 2),

('2022-04-17', '2022-07-17', 28, 28, null, 180000, 2, 3),
('2022-04-17', '2022-07-17', 20, 20, null, 480000, 5, 3),

('2022-04-16', '2022-07-16', 42, 42, null, 300000, 3, 4),
('2022-04-16', '2022-07-16', 30, 30, null, 500000, 4, 4),

('2022-05-12', '2022-08-12', 42, 39, 'sick', 175000, 6, 5),
('2022-05-12', '2022-08-12', 30, 30, null, 420000, 12,5),

('2022-06-26', '2022-09-26', 42, 42, null, 180000, 15, 6),
('2022-06-26', '2022-09-26', 30, 30, null, 480000, 12,6),

('2022-04-12', '2022-07-12', 28, 24, 'sick', 175000, 16, 7),
('2022-04-12', '2022-07-12', 20, 20, null, 480000, 5,7),

('2022-05-01', '2022-08-01', 28, 28, null, 190000, 8, 8),
('2022-05-01', '2022-08-01', 20, 18, 'sick', 480000, 13, 8),

('2022-06-01', '2022-09-01', 42, 42, null, 310000, 9, 9),
('2022-06-01', '2022-09-01', 30, 30, null, 480000, 13, 9),

('2022-06-08', '2022-09-08', 42, 42, null, 310000, 9, 10),
('2022-06-08', '2022-09-08', 30, 30, null, 480000, 13, 10),

('2022-06-09', '2022-09-08', 42, 42, null, 175000, 16, 11),
('2022-06-09', '2022-09-08', 30, 30, null, 480000, 4, 11),

--new clases


-- finished
('2022-03-14', '2022-06-14', 42, 42, null, 190000, 8, 16),
('2022-03-14', '2022-06-14', 30, 30, null, 500000, 4, 16),

('2022-03-12', '2022-06-12', 28, 28, null, 180000, 2, 17),
('2022-03-12', '2022-06-12', 20, 18, 'sick', 480000, 5, 17),

('2022-03-03', '2022-06-03', 42, 42, null, 290000, 17, 18),
('2022-03-03', '2022-06-03', 30, 30, null, 480000, 13, 18);





INSERT INTO payment(transferred_date, transferred_amount,income_tax, income_before_tax, payment_type, assignment_detail_id, is_paid)
VALUES
--on going
('2022-07-15', 4536000, 504000, 5040000, 'CASH', 1, FALSE),
('2022-07-15', 9000000, 1000000, 10000000, 'CREDITCARD', 2, FALSE),

('2022-07-15', 4536000, 504000, 5040000, 'CASH', 3, FALSE),
('2022-07-15', 9000000, 1000000, 10000000, 'CREDITCARD', 4, FALSE),

('2022-07-31', 4536000, 504000, 5040000, 'CASH', 5, FALSE),
('2022-07-31', 8640000, 960000, 9600000, 'CREDITCARD', 6, FALSE),

('2022-07-31', 11340000, 1260000, 12600000, 'CREDITCARD', 7, FALSE),
('2022-07-31', 13500000, 1500000, 15000000, 'CREDITCARD', 8, FALSE),

('2022-08-31', 6142500, 682500, 6825000, 'CREDITCARD', 9, FALSE),
('2022-08-31', 11340000, 1260000, 12600000, 'CREDITCARD', 10, FALSE),

('2022-10-15', 6804000, 756000, 7560000, 'CREDITCARD', 11, FALSE),
('2022-10-15', 12960000, 1440000, 14400000, 'CREDITCARD', 12, FALSE),

('2022-07-31', 3780000, 420000, 4200000, 'CREDITCARD', 13, FALSE),
('2022-07-31', 8640000, 960000, 9600000, 'CREDITCARD', 14, FALSE),

('2022-08-15', 4788000, 532000, 5320000, 'CREDITCARD', 15, FALSE),
('2022-08-15', 7776000, 864000, 8640000, 'CREDITCARD', 16, FALSE),

('2022-09-15', 11718000, 1302000, 13020000, 'CREDITCARD', 17, FALSE),
('2022-09-15', 12960000, 1440000, 14400000, 'CREDITCARD', 18, FALSE),

('2022-09-15', 11718000, 1302000, 13020000, 'CREDITCARD', 19, FALSE),
('2022-09-15', 12960000, 1440000, 14400000, 'CREDITCARD', 20, FALSE),

('2022-09-15', 6615000, 735000, 7350000, 'CREDITCARD', 21, FALSE),
('2022-09-15', 12960000, 1440000, 14400000, 'CREDITCARD', 22, FALSE),

-- NEW CLASS

-- finished
('2022-07-31', 7182000, 798000, 7980000, 'CREDITCARD', 23, TRUE),
('2022-07-31', 13500000, 1500000, 15000000, 'CREDITCARD', 24, TRUE),

('2022-07-31', 4536000, 504000, 5040000, 'CASH', 25, TRUE),
('2022-07-31', 7776000, 864000, 8640000, 'CREDITCARD', 26, TRUE),

('2022-07-31', 10962000, 1218000, 12180000, 'CREDITCARD', 27, TRUE),
('2022-07-31', 12960000, 1440000, 14400000, 'CREDITCARD', 28, TRUE);